
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ApplicationService;
import services.FixupTaskService;
import domain.Application;
import domain.FixupTask;
import domain.HandyWorker;

@Controller
@RequestMapping("/application")
public class ApplicationController extends AbstractController {

	//-----------------Services-------------------------
	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private FixupTaskService	fixupTaskService;

	@Autowired
	private ActorService		actorService;


	//-----------------List----------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Application> applications;

		final HandyWorker h = (HandyWorker) this.actorService.findPrincipal();
		applications = this.applicationService.findApplicationsByHandyWorker(h);

		result = new ModelAndView("application/list");
		result.addObject("requestURI", "application/list.do");
		result.addObject("applications", applications);

		return result;
	}

	//	//-----------------Display-------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int applicationId) {
		ModelAndView result;
		Application c;
		c = this.applicationService.findOne(applicationId);
		result = new ModelAndView("application/display");
		result.addObject("application", c);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Application c;
		c = this.applicationService.create();

		result = this.createEditModelAndView(c);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int applicationId) {
		ModelAndView result;
		Application c;

		c = this.applicationService.findOne(applicationId);
		Assert.notNull(c);
		result = this.createEditModelAndView(c);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Application c) {
		ModelAndView result;

		result = this.createEditModelAndView(c, null);

		return result;
	}
	//
	protected ModelAndView createEditModelAndView(final Application app, final String messageCode) {
		final ModelAndView result;
		final HandyWorker hw = (HandyWorker) this.actorService.findPrincipal();
		final Collection<FixupTask> fixs = this.fixupTaskService.findFixupTasksNotAppliedByHandyWorker(hw);

		result = new ModelAndView("application/edit");
		result.addObject("application", app);
		result.addObject("fixupTasks", fixs);
		result.addObject("message", messageCode);

		return result;
	}
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Application a, final BindingResult binding) {
		ModelAndView result;
		System.out.println("entra en el save");

		if (binding.hasErrors())
			result = this.createEditModelAndView(a);
		else
			//try {
			this.applicationService.save(a);
		result = new ModelAndView("redirect:list.do");
		//} catch (final Throwable oops) {
		result = this.createEditModelAndView(a, "cannot.commit.error");
		//}
		return result;
	}

}
