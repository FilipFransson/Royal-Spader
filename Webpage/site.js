$( document ).ready(function() {
	function getMenu(){
		$(".headermenu").html("Länk 1&nbsp;&nbsp;|&nbsp;&nbsp;Länk 2&nbsp;&nbsp;|&nbsp;&nbsp;Länk 3");
	}
	
	function openPage(){
		$(".maincontent").html("");
		$(".maincontent").append("Välkommen!");
	}
	
	openPage();
	getMenu();
});