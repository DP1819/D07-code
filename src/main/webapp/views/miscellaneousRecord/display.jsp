<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="hasRole('HANDYWORKER')">

<p><spring:message code="miscellaneousRecord.display" /></p>

<spring:message code="miscellaneousRecord.title"></spring:message>
:
<jstl:out value="${miscellaneousRecord.title}"></jstl:out>
<br/>

<spring:message code="miscellaneousRecord.attachment"></spring:message>
:
<jstl:out value="${miscellaneousRecord.attachment}"></jstl:out>
<br/>

<spring:message code="miscellaneousRecord.comments"></spring:message>
:
<jstl:out value="${miscellaneousRecord.comments}"></jstl:out>
<br/>

</security:authorize>