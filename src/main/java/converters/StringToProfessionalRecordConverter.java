
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ProfessionalRecordRepository;
import domain.ProfessionalRecord;

@Component
@Transactional
public class StringToProfessionalRecordConverter implements Converter<String, ProfessionalRecord> {

	@Autowired
	private ProfessionalRecordRepository	repository;


	@Override
	public ProfessionalRecord convert(final String s) {
		ProfessionalRecord res;
		int id;

		try {
			if (!StringUtils.isEmpty(s))
				res = null;
			else {
				id = Integer.valueOf(s);
				res = this.repository.findOne(id);
			}
		} catch (final Throwable t) {
			throw new IllegalArgumentException(t);
		}
		return res;
	}

}
