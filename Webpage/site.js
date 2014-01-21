$( document ).ready(function() {
	function getMenu(){
		$(".headermenu").html('<ul><li style="position: relative; float: left;"><a href="">Länk 1</a></li><li style="position: relative; float: left;"><a href="">Länk </a></li><li style="position: relative; float: left;"><a href="">Länk 3</a></li></ul>');
	}
	
	function openPage(){
		$(".maincontent").html("");
		$(".maincontent").append("Välkommen!");
	}
	
	openPage();
	getMenu();
});