<%@ include file="../header.jsp"%>

<div id="title">Results</div>
<div id="form">

	<input id="chord" name="chord" />
	<button type="submit" id="submit">
		<fmt:message key="send" />
	</button>
	${string} - ${hello }
</div>



<div id="result"></div>



<div id="form2Div">

	<form action="<c:url value="/test/save"/>" name="MusicForm" id="form2">

		<input name="musics[0].name"/> <br /> 
		<input  name="musics[1].name"/> <br />


		<button type="submit" id="submit2" >Enviar</button>
	</form>
</div>

<script type="text/javascript">
	$(document).ready(function() {

	});
	
	$("#form2").submit(function() {
		$.ajax({
			type : "POST",
			url : this.action,
			data : $(this).serialize(),
			success : function(json) {
				alert('ok');
			}
		});
		return false;
		
	});
/*
	$("#form2").submit(function() {
		
		
// 		var chords = $("#divChords input");
		
// 		var dados = {};
// 		$.each(chords, function(indice, chord) {
// 			dados["musics.tags["+indice+"].name"] = chord.value;
// 		});
		
// 		dados["musics.name"] = $("#nameMusic").val();
// 		dados["musics.artist"] = $("#artistMusic").val();
		
// 		var dados = {
// 			"musics.name" : $("#nameMusic").val(),
// 			"musics.artist" : $("#artistMusic").val(),
// 		};

// 		var dados = {
// 			"musics[0].name" : $("#nameMusic").val(),
// 			"musics[0].artist" : $("#artistMusic").val(),
// 			"musics[0].tags[0].name" : "tag1",
// 			"musics[0].tags[1].name" : "tag2",
// 			"musics[1].name" : "testeName",
// 			"musics[1].artist" : "testeartist",
// 			"musics[1].tags[0].name" : "tag3",
// 			"musics[1].tags[1].name" : "tag4"
// 		};	
		
		$.ajax({
			type : "POST",
			url : this.action,
// 			data : $(this).serialize(),
			data : dados,
// 			dataType : "json",
			success : function(json) {
				alert('ok');
			}
		});
		return false;
	});

*/
	$("button#submit").click(
			function() {
				$("div#title").animate({
					'color' : '#C00',
					'margin-left' : '+=500'
				}, 2000);
				$("div#result").animate({
					'color' : '#C00',
					'margin-left' : '+=500'
				}, 2000);
				$.ajax({
					url : "<c:url value='/test/chords/json'/>",
					data : {
						"chord" : $('input#chord').val()
					},
					success : function(response) {
						var text = "";
						$.each(response, function(indice, musica) {
							text = text + indice + " - " + musica.name + " - "
									+ musica.urlChord;
							text = text + "<br/>";
						});

						$("div#result").html(text);
					},
					error : function(xhr) {
						alert('Error!  Status = ' + xhr.status);
					}
				});
			});
</script>



<%@ include file="../footer.jsp"%>