$( document ).ready(function() {
	var superadmin = false;
	var producer = false;
	var shopOwner = false;
	var newData;
	var fadeOutTime = 200;
	var fadeInTime = 200;
	
	function getMenu(){
		var menuUrl = "";
		if(superadmin){
			menuUrl = "menu/adminMenu.html";
		} else if(producer) {
			menuUrl = "menu/producerMenu.html";
		} else if(shopOwner) {
			menuUrl = "menu/shopOwnerMenu.html";
		} else {
			menuUrl = "menu/defaultMenu.html";
		}
		
		$.ajax({
			url: menuUrl,
			context: document.body
			}).done(function(data) {
			
				$(".headermenu").html(data);
				
				$(".menulink").click(function (event){
					event.preventDefault();
					$(".menulink").removeClass("active");
					$(this).addClass("active");
					openPageUrlAndUpdateHash(this.href, this.id.replace("topMenuLink",""));
				});
		});
	}
	
	function openMainPage(){
		var pageUrl = "";
		if(superadmin){
			pageUrl = "admin/main.html";
		} else if(producer) {
			pageUrl = "producer/main.html";
		} else if(shopOwner) {
			pageUrl = "shopowner/main.html";
		} else {
			pageUrl = "home/main.html";
		}
		
		openPageUrl(pageUrl);
	}
	
	function openPageUrl(pageUrl){
		$.ajax({
			url: pageUrl,
			context: document.body
			}).done(function(data) {
				$(".maincontent").fadeOut(fadeOutTime);
				newData = data;
				setTimeout(switchData, fadeOutTime);
				$(".maincontent").fadeIn(fadeInTime);
		});
	}
	
	function openPageUrlAndUpdateHash(pageUrl, page){
		$.ajax({
			url: pageUrl,
			context: document.body
			}).done(function(data) {
				$(".maincontent").fadeOut(fadeOutTime);
				newData = data;
				setTimeout(switchData, fadeOutTime);
				$(".maincontent").fadeIn(fadeInTime);
				window.location.hash = "p=" + page;
		});
	}
	
	function switchData(){
		$(".maincontent").html(newData);
		
		$(".link").click(function (event){
			event.preventDefault();
			openPageUrl(this.href);
		});
	}
	
	function getUrl(p){
		if(!superadmin && !producer && !shopOwner){
			if(p == '1'){
				return "pages/default/main.html";
			}
			if(p == '2'){
				return "pages/default/settings.html";
			}
			if(p == '3'){
				return "pages/default/help.html";
			}
			if(p == '4'){
				return "pages/default/newGroceryBag.html";
			}
		}
	}
	
	var hashArray = window.location.hash.replace("#", "").split('&');
	var page = "";
	for(var i = 0; i < hashArray.length; i++){
		if(hashArray[i][0] == 'p'){
			page = hashArray[i].replace("p=", "");
			openPageUrlAndUpdateHash(getUrl(page), page);
		}
	}
	
	if(page == ""){
		openMainPage();
	}
	getMenu();
});