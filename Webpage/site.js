$( document ).ready(function() {	
	var superadmin = false;
	var producer = false;
	var shopOwner = false;
	
	function getMenu(){
		var menuUrl = "";
		if(superadmin){
			menuUrl = "menus/adminMenu.html";
		} else if(producer) {
			menuUrl = "menus/producerMenu.html";
		} else if(shopOwner) {
			menuUrl = "menus/shopOwnerMenu.html";
		} else {
			menuUrl = "menus/defaultMenu.html";
		}
		
		$.ajax({
			url: menuUrl,
			context: document.body
			}).done(function(data) {
			
				$(".headermenu").html(data);
				
				$(".menulink").click(function (event){
					event.preventDefault();
					openPageUrl(this.href)
				});
		});
	}
	
	function openPage(){
		var pageUrl = "";
		if(superadmin){
			pageUrl = "pages/admin/main.html";
		} else if(producer) {
			pageUrl = "pages/producer/main.html";
		} else if(shopOwner) {
			pageUrl = "pages/shopOwner/main.html";
		} else {
			pageUrl = "pages/default/main.html";
		}
		
		openPageUrl(pageUrl);
	}
	
	function openPageUrl(pageUrl){
		$.ajax({
			url: pageUrl,
			context: document.body
			}).done(function(data) {
				
				$(".maincontent").html(data);
		});
	}
	
	openPage();
	getMenu();
});