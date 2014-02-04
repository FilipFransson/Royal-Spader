<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
		
	    <!--<script type="text/javascript">
	     
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
		</script>-->


		<div class="mainwrapper">
			<div class="headerwrapper">
				<h2>
					<a class="homeclick" href="index.html">
						Royal Spader
			  		</a>
		  		</h2>
				<div class="headermenu">
						
				</div>
			</div>
			<div class="bodywrapper">       
		
				<div class="maincontent">
	            	<p>
						Aktivera Javascript för att kunna använda denna sida!
					</p>
				</div>
			</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
