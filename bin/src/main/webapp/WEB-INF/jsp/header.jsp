<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${locale}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="author" content="PluggedIN" />
<meta name="author" content="Raphael Lacerda" />
<meta name="description" content="<fmt:message key="meta.description"/>" />
<meta name="keywords"
	content="sites, web, desenvolvimento, development, java, opensource" />
<title>PluggedIN</title>
</head>
<body>
	<script src="<c:url value='/js/jquery-1.7.1.js'/>"></script>
	<script src="<c:url value='/js/jquery-ui-1.8.16.js'/>"></script>
	<ul>
		<c:forEach items="${errors }" var="error">
			<li>${error.category} - ${error.message}</li>
		</c:forEach>
	</ul>
	
	<c:if test="${not empty userLogged.user }">

		<a href="<c:url value="/logout"/>">Logout ${ userLogged.user.login }</a>
	</c:if>
	</br>	
	<c:if test="${empty userLogged.user }">
			<a href="<c:url value="/login"/>">Login</a>
	</c:if>
	<br/>