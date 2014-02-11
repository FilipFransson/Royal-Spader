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
			menuUrl = "../menu/admin";
		} else if(producer) {
			menuUrl = "../menu/producer";
		} else if(shopOwner) {
			menuUrl = "../menu/shopowner";
		} else {
			menuUrl = "../menu/default";
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
		openPageUrl("main/");
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
				return "../home/main";
			}
			if(p == '2'){
				return "../home/settings";
			}
			if(p == '3'){
				return "../home/help";
			}
			if(p == '4'){
				return "../home/newgrocerybag";
			}
		}
		if(p == '9e60de60-8d77-11e3-baa8-0800200c9a66'){
			return "../admin/main";
		}
		if(p == 'a82b9520-8d77-11e3-baa8-0800200c9a66'){
			return "../admin/shops";
		}
		if(p == 'ae8fef60-8d77-11e3-baa8-0800200c9a66'){
			return "../admin/suppliers";
		}
		if(p == '39af771a-f069-4e2a-bce9-bd2e2f8b383a'){
			//redirect to shops since no id
			return "../admin/shops";
		}
		if(p == 'cf3daa14-80ef-4da3-9d4e-e00ad67174cf'){
			return "../admin/newShop";
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