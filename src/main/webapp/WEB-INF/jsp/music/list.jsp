<%@ include file="../../../template/header.jsp"%>

<div id="userLastMusics" class="well">
	Your last Five Musics <br /> <br /> ${musics}
</div>


<fieldset>
	<form action="<c:url value="/music/save"/>" name="MusicForm"
		method="post" class="form-horizontal">

		<div class="controls controls-row">
			<input type="text" name="music.name" id="name" value="${music.name}"
				placeholder="Name" class="span3" /> <input type="text"
				name="music.artist" id="artist" value="${music.artist}"
				class="span3" placeholder="Artist1" />
		</div>

		<div class="controls">

			<input type="text" name="music.urlYoutubeVideo" id="urlYoutubeVideo"
				value="${music.urlYoutubeVideo}" placeholder="UrlYoutube"
				class="span5" />
		</div>
		<div class="controls">
			<input type="text" name="tags" id="tags" value="${tags}" size="100"
				placeholder="Tags Separated by comma" class="span8" />
			<textarea rows="3" cols="1" class="pull-left"
				name="music.description" id="description"
				value="${music.description}" placeholder="Description"></textarea>

		</div>
		<div class="controls">
			<div id="divChords"></div>
			<div>
				<img alt="Add here" src="<c:url value='/images/add.png'/>"
					id="imgAdd" width="15" /> <img alt="Remove here"
					src="<c:url value='/images/remove.png'/>" id="imgRem" width="15" />
			</div>
		</div>

		<div class="form-actions">
			<button type="submit" id="submit" class="btn btn-primary">
				Save</button>
			<button type="submit" id="submit" class="btn">Cancel</button>
		</div>


	</form>
</fieldset>

<script type="text/javascript">
	var count = 0;
	$("#imgAdd")
			.click(
					function() {

						$("#divChords").append("<div>");
						var name = "music.chords[" + count + "].urlChord";
						var search = "searchChord('#chord" + count + "')";
						$("#divChords div")
								.last()
								.append(
										$('<input type="text" name="'+name+'" id="chord'+count+'"/>'))
								.append(
										$('<img src="<c:url value="/images/lupa.ico"/>" onclick="'
												+ search
												+ '" width="15" href="#"/>'))
								.append("</div>");
						count++;
					});

	$("#imgRem").click(function() {
		$("#divChords div").last().remove();
		count--;
	});

	function searchChord(chordId) {
		alert("Must Go To CifraClub using AJax" + $(chordId).val());
	}
</script>
<%@ include file="../../../template/footer.jsp"%>