
<%@ include file="../header.jsp"%>
<form action="<c:url value="/login/login"/>" name="loginForm"
	method="post">
	<fieldset>
		<legend>Login:</legend>
		<label for="login">Login</label> 
			<input type="text" name="user.login" id="login" value="${user.login}"/> 
		<label for="password">Password</label> 
		<input type="password" name="user.password" id="password" value="${user.password}"/>
		
		<button type="submit" id="submit">
			<fmt:message key="send" />
		</button>
	</fieldset>
</form>

<%@ include file="../footer.jsp"%>