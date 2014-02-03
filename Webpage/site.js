$( document ).ready(function() {
	var superadmin = false;
	var producer = false;
	var shopOwner = true;
	var newData;
	var fadeOutTime = 200;
	var fadeInTime = 200;
	
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
					$(".menulink").removeClass("active");
					$(this).addClass("active");
					openPageUrl(this.href)
				});
			});
	}
	
	function openMainPage(){
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
				$(".maincontent").fadeOut(fadeOutTime);
				newData = data;
				setTimeout(switchData, fadeOutTime);
				$(".maincontent").fadeIn(fadeInTime);
		});
	}
	
	function switchData(){
		$(".maincontent").html(newData);
		
		$(".link").click(function (event){
			event.preventDefault();
			openPageUrl(this.href)
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
			openPageUrl(getUrl(page));
		}
	}
	
	if(page == ""){
		openMainPage();
	} else {
		//if(window.location.hash
	}
	getMenu();
});