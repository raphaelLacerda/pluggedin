
<%@ include file="../header.jsp"%>
${msg}
<form action="<c:url value="/login"/>" name="loginForm" method="post">
	<fieldset>
		<legend>Login</legend>
		<label for="login">Login</label> <input type="text" name="user.login" id="login" />
		<label for="password">Senha</label> 
		<input type="password" name="user.password" id="password" /> 
		<button type="submit" id="submit">
			<fmt:message key="send" />
		</button>
	</fieldset>
</form>

<%@ include file="../footer.jsp"%>