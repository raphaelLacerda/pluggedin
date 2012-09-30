<%@ include file="../../../template/header.jsp"%>


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

<ul class="pager">
  <li><a href="#">Previous</a></li>
  <li><a href="#">Next</a></li>
</ul>



<%@ include file="../../../template/footer.jsp"%>
