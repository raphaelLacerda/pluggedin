<%@ include file="../header.jsp"%>


User searched ${user.name}
</br>

<c:if test="${users.isEmpty()}">
	No Users with ${user.name }
</c:if>
<c:if test="${not empty users}">
	Select one user
	<br/>
	<c:forEach var="_user" items="${users}">
		<a href="<c:url value="/playlist/${_user.login}"/>">${ _user.login} - ${ _user.name}</a>
		<br/>
	</c:forEach>
</c:if>
</br>
${musics}




<%@ include file="../footer.jsp"%>
