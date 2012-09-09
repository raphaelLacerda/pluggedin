<%@ include file="../header.jsp"%>


${userList}
${userList.size()}

<c:if test="${userList.size() == 1}">
	No Users
</c:if>
<%@ include file="../footer.jsp"%>
