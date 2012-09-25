<%@ include file="../../../template/header.jsp"%>

<!-- Main hero unit for a primary marketing message or call to action -->
<div class="hero-unit">
	<h1>Welcome to the PluggedIN!</h1>
	<p class="lead text-info">Aims to facilitate the search of the
		musics that you like to play</p>
	${msg} <br />
	<c:if test="${not empty userLogged.user }">
		<br />
				User Logged ${ userLogged.user.login}
			</c:if>
	<p>
		<a class="btn btn-primary btn-large">Learn more &raquo;</a>
	</p>
</div>

<!-- Example row of columns -->
<div class="row-fluid">
	<div class="span4" id="searchMusic">
		<h2>Musics</h2>
		<form action="<c:url value="/music/search"/>" name="MusicForm"
			method="get">
			<input class="ui-autocomplete-input" name="music" id="musicInput"
				value="Search Here" size="100" autocomplete="list" />
			<button type="submit" id="search" class="btn">Search</button>
		</form>
	</div>
	<div class="span4">
		<h2>PlayLists</h2>
		<p>Then you can make your playlist.</p>
		<p>
			<a class="btn" href="#">View details &raquo;</a>
		</p>
	</div>
	<div class="span4">
		<h2>Find Friends</h2>
		<p>You can look for friends that have the similar tast for it.</p>
		<p>
			<a class="btn" href="#">View details &raquo;</a>
		</p>
	</div>
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

#music {
	width: 25em;
}

.ui-autocomplete {
	height: 100px;
}
</style>


<script src="<c:url value='/js/jquery-1.7.1.js'/>"></script>
<script src="<c:url value='/js/jquery-ui-1.8.16.js'/>"></script>

<script type="text/javascript">     
     
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



<%@ include file="../../../template/footer.jsp"%>