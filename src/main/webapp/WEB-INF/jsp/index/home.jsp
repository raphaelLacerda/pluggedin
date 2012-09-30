<%@ include file="../../../template/header.jsp"%>


<!-- Main hero unit for a primary marketing message or call to action -->
<div class="hero-unit">
	<div class="page-header">
		<h1>
			<small>Welcome to </small>PluggedIN!
		</h1>
		<p class="text-info">Aims to facilitate the search of the musics
			that you like to play</p>
	</div>
	<p>
		<a class="btn btn-primary btn-large" data-toggle="modal" href="#myModal">Learn more &raquo;</a>
	</p>
	<blockquote class="pull-right">
		<p>Easy to use... try it now!</p>
		<small>Jackson, Michael</small>
	</blockquote>
</div>

<!-- Example row of columns -->
<div class="row-fluid">
	<div class="span4" id="searchMusic">
		<h2>Musics</h2>
		<form action="<c:url value="/music/search"/>" name="MusicForm"
			method="get" class="form-search">
			<div class="input-append">
				<input class="ui-autocomplete-input span3 search-query" name="music"
					id="musicInput" value="Search Here" size="100" autocomplete="list" data-provide="typeahead"/>
				<button type="submit" id="search" class="btn">Search</button>
			</div>
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
		<p>You can look for friends that have similar taste for it.</p>
		<p>
			<a class="btn" href="#">View details &raquo;</a>
		</p>
	</div>
</div>


<%@ include file="learnMore.jsp"%>

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


<script type="text/javascript">

	//$('input#musicInput').typeahead();
	
	$("input#musicInput").focus(function() {
		$(this).val('');
	});

	$("input#musicInput").autocomplete(
			{
				source : function(request, response) {
					$.ajax({
						url : "<c:url value='/musics/json'/>",
						data : {
							"music" : request.term
						},
						dataType : "json",
						success : function(musics) {
							response($.map(musics,
									function(item) {
										return {
											data : item,
											label : item.name + "("
													+ item.artist + ")",
											value : item.name,
											result : item.name
										}
									}));
						}
					});
				},
				minLength : 3,
				delay : 1000,
				select : function(event, ui) {
					log(ui.item ? "Selected: " + ui.item.label
							: "Nothing selected, input was " + this.value);
				},
				open : function() {
					$(this).removeClass("ui-corner-all").addClass(
							"ui-corner-top");
				},
				close : function() {
					$(this).removeClass("ui-corner-top").addClass(
							"ui-corner-all");
				}
			});
</script>



<%@ include file="../../../template/footer.jsp"%>