<%@ include file="../header.jsp"%>

	<div id="presentation">
		${msg}
		<br/>
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
			<input class="ui-autocomplete-input" name="music" id="musicInput" value="Search Here" size="100" 
			autocomplete="list"/>
			<button type="submit" id="submit">
				<fmt:message key="send" />
			</button>
		</form>
	</div>
	
	<div id="playlistSearch">
		Search for a user playlist
		<br/><br/>
		<form action="<c:url value="/playlist"/>" name="MusicForm" method="get">
			<input name="user.name" id="userInput" value="Type a user name" size="100" />
			<button type="submit" id="submit">
				<fmt:message key="send" />
			</button>
		</form>
	</div>
	



	<style type="text/css">
	   .ui-autocomplete {
			max-height: 100px;
			overflow-y: auto;
			/* prevent horizontal scrollbar */
			overflow-x: hidden;
			/* add padding to account for vertical scrollbar */
			padding-right: 20px;
		}
		.ui-autocomplete-loading { 
			background: white url('/images/loading.gif') right center no-repeat; 
		}
		#music { width: 25em; }
		.ui-autocomplete {
			height: 100px;
		}
   </style>
   		
	<script type="text/javascript">
     $(function(){
    	 $("div#hide").hide();	 
    	 $("<p>Hi there!</p>").insertAfter("#hide");
     });
     
     //$("input#music").autocomplete({
		//    source: ["c++", "java", "php", "coldfusion", "javascript", "asp", "ruby"]
	//	});
     
     $("div#test").click(function(){
		 $("div#hide").fadeIn('slow');
		 $("div#hide").html('I was hidden');
     });
     $("div#hide").click(function(){
		 $("div#hide").fadeOut('fast');
     });
     $('input#musicInput').click(function (){
    	 $(this).val('');
     });
     
     $('#userInput').click(function (){
    	 $(this).val('');
     });
     
     
	$( "input#musicInput" ).autocomplete({
		source: function( request, response ) {
			$.ajax({
				url: "<c:url value='/musics/json'/>",
				data:{
					"music":request.term
				},
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
		},
		open: function() {
			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
		},
		close: function() {
			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
		}
	});
   </script>

   
   
<%@ include file="../footer.jsp"%>