<%@ include file="../header.jsp"%>

	<div id="presentation">
		Welcome to the PluggedIN!
		<br /> Aims to facilitate the search of musics that you like to play
		<c:if test="${not empty userLogged.user }">
			<br />
			User Logged ${ userLogged.user.login}
		</c:if>
	</div>
	<div id="test">
		Click Here
	</div>
	<div id="hide">
	</div>
	
	<div id="lastMusics">
	<br/><br/><br/><br/><br/><br/>
		The Last Musics added
		<br/><br/>
		<table>
			<c:forEach var="_music" items="${musicList}">
				<tr>
					<td>${_music.user.login}</td>
					<td>${_music.name}</td>
					<td>${_music.artist}</td>
					<td>${_music.dateRecorded}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div id="searchMusic">
		Search for a music
		<br/><br/>
		<form action="<c:url value="/music/search"/>" name="MusicForm" method="get">
			<input
				type="text" name="music" id="music" value="Search Here" size="100"/>
			<button type="submit" id="submit">
				<fmt:message key="send" />
			</button>
		</form>
	</div>
		
	<script type="text/javascript">
     $(document).ready(function(){
    	 //do something
     });
     $("div#hide").hide();
     
     //$("input#music").autocomplete({
		//    source: ["c++", "java", "php", "coldfusion", "javascript", "asp", "ruby"]
	//	});
     
     $("div#test").click(function(){
		 $("div#hide").show('slow');
		 $("div#hide").html('I was hidden');
     });
     $("div#hide").click(function(){
		 $("div#hide").hide('fast');
     });
     $('input#music').click(function (){
    	 $(this).val('');
     });
     
	$( "input#music" ).autocomplete({
		source: function( request, response ) {
			$.ajax({
				url: "<c:url value='/musics/json/"+ request.term +"'/>",
				dataType: "json",
				success: function( musics ) {
					response( $.map( musics, function( item ) {
						return {
							data: item,
							label: item.name + "(" + item.artist+")",
							value: item.name,
							result: item.name
						}
					}));
				}
			});
		},
		minLength: 3,
		delay: 2000,
		select: function( event, ui ) {
			log( ui.item ?
				"Selected: " + ui.item.label :
				"Nothing selected, input was " + this.value);
		}
	});
	
   </script>
   <style type="text/css">
   .ui-autocomplete {
		max-height: 100px;
		overflow-y: auto;
		/* prevent horizontal scrollbar */
		overflow-x: hidden;
		/* add padding to account for vertical scrollbar */
		padding-right: 20px;
	}
	.ui-autocomplete {
		height: 100px;
	}
   </style>
   
<%@ include file="../footer.jsp"%>