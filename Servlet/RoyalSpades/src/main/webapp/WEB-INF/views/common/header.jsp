<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
	<head>
		<link href="<c:url value="/resources/theme/standard.css" />" rel="stylesheet">
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script src="http://datatables.net/download/build/jquery.dataTables.min.js"></script>
		<script src="<c:url value="/resources/theme/site.js" />"></script>
		<title>&spades; Royal Spades &spades;</title>
	</head>
     <script language="JavaScript" type="text/javascript">
     
    $(document).ready(function(){
		$.ajax({
			url: "pages/default/login.html",
			context: document.body
			}).done(function(data) {
				//document.getElementById('popupbox').style.visibility="hidden";
				$("body").append(data);
				$(".loginLink").click(function() {
					temp = document.getElementById('popupbox').getAttribute("style");
					console.log(temp);
					if(temp == "visibility: visible;"){
						console.log("if");
						document.getElementById('popupbox').style.visibility="hidden";
					}else{
						console.log("else");
						document.getElementById('popupbox').style.visibility="visible";
					}
				});
			});
	  	$("div.modal-bg").fadeTo("slow", .5);
		
		$(".loginLink").click(function() {
			if($("#popupbox").is(':visible')){
				console.log("if");
				$('#popupbox').show();
			}else{
				console.log("else");
				$('#popupbox').hide();
			}
		});
	});
  </script>
	</head>
	<body>