<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${locale}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>PluggedIN - A Music Project</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="Music Category">
<meta name="author" content="Raphael Lacerda">
<meta name="keywords"
	content="sites, web, desenvolvimento, development, java, opensource" />
<title>PluggedIN</title>



</head>
<!-- Le styles -->
<link href="<c:url value='/assets/css/bootstrap.css'/>" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}
</style>
<link href="<c:url value='/assets/css/bootstrap-responsive.css'/>"
	rel="stylesheet">

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Le fav and touch icons -->
<link rel="shortcut icon"
	href="<c:url value='/assets/ico/favicon.ico'/>">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="<c:url value='/assets/ico/apple-touch-icon-144-precomposed.png'/>">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="<c:url value='/assets/ico/apple-touch-icon-114-precomposed.png'/>">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="<c:url value='/assets/ico/apple-touch-icon-72-precomposed.png'/>">
<link rel="apple-touch-icon-precomposed"
	href="<c:url value='/assets/ico/apple-touch-icon-57-precomposed.png'/>">
<script src="<c:url value='/js/jquery-1.7.1.js'/>"></script>
<script src="<c:url value='/js/jquery-ui-1.8.16.js'/>"></script>

</head>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="http://www.pluggedin.com.br">PluggedIN</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="active"><a href="<c:url value="/"/>">Home</a></li>
						<li><a href="<c:url value="/musics"/>"><i
								class="icon-music icon-white"> </i>&nbsp;Musics</a></li>
						<li><a href="<c:url value="/register"/>">Register</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">About <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">WhoAreWe</a></li>
								<li class="divider"></li>
								<li class="nav-header">Others</li>
								<li><a href="#">Version</a></li>
							</ul></li>
					</ul>

					<c:if test="${empty userLogged.user }">
						<form class="navbar-form pull-right form-inline"
							action="<c:url value='/login/login'/>" name="loginForm"
							method="post">

							<input class="input-small" type="text" name="user.login"
								id="login" value="${user.login}" placeholder="Login" /> <input
								class="input-small" type="password" name="user.password"
								id="password" value="${user.password}" placeholder="Password" />

							<button type="submit" class="btn">Sign In</button>
						</form>
					</c:if>

					<c:if test="${not empty userLogged.user }">
						<div class="pull-right">
							<a href="<c:url value="/logout"/>">Logout ${
								userLogged.user.login }</a>
						</div>
					</c:if>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>


	<div class="container-fluid">

		<c:if test="${not empty msg or not empty errors}">
			<div class="alert alert-block">
				<button type="button" class="close" data-dismiss="alert">×</button>
				${msg}
				<c:forEach items="${errors }" var="error">
					<li>${error.category} - ${error.message}</li>
				</c:forEach>
				
			</div>
		</c:if>