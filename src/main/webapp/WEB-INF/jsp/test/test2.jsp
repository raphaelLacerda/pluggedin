<%@ include file="../header.jsp"%>

<div id="title">Test2</div>

<div id="teste">
	Remova Aqui <img alt="add here" src="<c:url value='/images/add.png'/>"
		id="remover" width="15" />
</div>
<img alt="add here" src="<c:url value='/images/add.png'/>"
	class="imgAdd" width="15" />
<div id="divChords"></div>


<script type="text/javascript">
	$(".imgAdd")
			.click(
					function() {

						var div = $("#divChords").append("<div>");

						$("#divChords div")
								.last()
								.append($('<br/>'))
								.append($('<input type="text"/>'))
								.append(
										$('<img alt="add here" src="<c:url value="/images/add.png"/>" class="imgAdd" width="15" />'))
								.append("</div>");

					});

	$("#remover").click(function() {

		$("#divChords input").last().remove();
		$("#divChords img").last().remove();
		$("#divChords br").last().remove();

	});
</script>



<%@ include file="../footer.jsp"%>