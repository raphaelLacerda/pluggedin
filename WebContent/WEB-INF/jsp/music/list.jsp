<%@ include file="../header.jsp"%>

<div id="userMusics">
	Musics of ${userLogged.user.login }
	<br />
	${musicList } 
</div>

<div id="userLastMusics">
	Your last Five Musics
	<br/><br/>
	${lastFiveMusics }
</div>


<div id="saveMusic">
	<form action="<c:url value="/music/save"/>" name="MusicForm"
		method="post">
		
		<label for="name">Name</label> 
		<input type="text" name="music.name" id="name" value="${music.name}"/>  
		<label for="description">Description</label> 
		<input type="text" name="music.description" id="description" value="${music.description}"/>  
		<label for="Artist">Artist</label> 
		<input type="text" name="music.artist" id="artist" value="${music.artist}"/>  
		<label for="urlChord">UrlChord</label> 
		<input type="text" name="music.urlChord" id="urlChord" value="${music.urlChord}"/>  
		<label for="urlYoutubeVideo">UrlYoutubeVideo</label> 
		<input type="text" name="music.urlYoutubeVideo" id="urlYoutubeVideo" value="${music.urlYoutubeVideo}"/>  
		<br/>			
		<input type="text" name="tags" id="tags" value="${tags}" size="100"/>
		<button type="submit" id="submit">
			<fmt:message key="send" />
		</button>
		
	</form>
</div>
<%@ include file="../footer.jsp"%>