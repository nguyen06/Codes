
			
		$('#apple').mouseleave(function(){$(this).html('Apple')})
		$('#apple').mouseenter(function(){$(this).prepend($('<img>',{src:'../img/apple.png'}))})
		function play(){
			var audio = document.getElementById("audioA");
   			audio.play();
		}




	$('#orange').mouseleave(function(){$(this).html('Orange')})
	
	$('#orange').mouseenter(function(){$(this).prepend($('<img>',{src:'../img/orange.jpg'}))})
	function orange(){
		var audio = document.getElementById("audioO");
			audio.play();
	}


		


	$('#banana').mouseleave(function(){$(this).html('Banana')})
	
	$('#banana').mouseenter(function(){$(this).prepend($('<img>',{src:'../img/Banana.jpg'}))})
	function banana(){
		var audio = document.getElementById("audioB");
			audio.play();
	}
	



	$('#grape').mouseleave(function(){$(this).html('Grape')})
	
	$('#grape').mouseenter(function(){$(this).prepend($('<img>',{src:'../img/grape.jpg'}))})
	function grape(){
		var audio = document.getElementById("audioG");
			audio.play();
	}
	



	$('#lemon').mouseleave(function(){$(this).html('Lemon')})
	
	$('#lemon').mouseenter(function(){$(this).prepend($('<img>',{src:'../img/lemon.jpg'}))})
	function lemon(){
		var audio = document.getElementById("audioL");
			audio.play();
	}