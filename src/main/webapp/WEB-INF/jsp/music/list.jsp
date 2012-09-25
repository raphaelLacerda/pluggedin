<%@ include file="../../../template/header.jsp"%>

<div id="userMusics">
	Musics of ${userLogged.user.login } <br /> ${musicList }
</div>

<div id="userLastMusics">
	Your last Five Musics <br /> <br /> ${lastFiveMusics }
</div>


<div id="saveMusic">
	<form action="<c:url value="/music/save"/>" name="MusicForm"
		method="post">

		<label for="name">Name</label> 
		<input type="text" name="music.name" id="name" value="${music.name}" /> 
		<br/>
		
		<label for="description">Description</label>
		<input type="text" name="music.description" id="description" value="${music.description}" /> 
		<br/>
		
		<label for="Artist">Artist</label> 
		<input type="text" name="music.artist" id="artist" value="${music.artist}" />
		<br/>
		
		<label for="urlYoutubeVideo">UrlYoutubeVideo</label> 
		<input type="text" name="music.urlYoutubeVideo" id="urlYoutubeVideo" value="${music.urlYoutubeVideo}" /> 
		
		<div>
			Separated by comma <input type="text" name="tags" id="tags"
				value="${tags}" size="100" />
		</div>
		<div>
			Chords
		</div>
		<div id="divChords">
		</div>
		<div>
			<img alt="Add here" src="<c:url value='/images/add.png'/>" id="imgAdd" width="15" />
			<img alt="Remove here" src="<c:url value='/images/remove.png'/>" id="imgRem" width="15" />
		</div>
		
		<button type="submit" id="submit">
			<fmt:message key="send" />
		</button>

	</form>
</div>


<script type="text/javascript">

	var count = 0;
	$("#imgAdd").click(function() {
	
		$("#divChords").append("<div>");
		var name = "music.chords["+count+"].urlChord";
		var search = "searchChord('#chord"+count+"')";
		$("#divChords div")
				.last()
				.append($('<input type="text" name="'+name+'" id="chord'+count+'"/>'))
				.append($('<img src="<c:url value="/images/lupa.ico"/>" onclick="'+search+'" width="15" href="#"/>'))
				.append("</div>");
		count++;
	});

	$("#imgRem").click(function() {
		$("#divChords div").last().remove();
		count--;
	});
	
	function searchChord(chordId){
		alert("Must Go To CifraClub using AJax"+ $(chordId).val());
	}
	
</script>
<%@ include file="../../../template/footer.jsp"%>