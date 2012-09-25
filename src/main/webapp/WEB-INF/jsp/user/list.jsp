<%@ include file="../../../template/header.jsp"%>


${userList}
${userList.size()}

<c:if test="${userList.size() == 1}">
	No Users
</c:if>
<%@ include file="../../../template/footer.jsp"%>
