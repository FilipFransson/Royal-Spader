<script>
	window.location.hash = "p=" + "${pageUid}";
</script>
<h2> 
	Varor
</h2>
<div id="categoryTableDiv"></div>
<form id="newCatForm" style="display: none">
	<input type="text" type="text" name="name" placeholder = "Skriv in din nya varukategori." >
	<button submit="" id="toggleCatBtn">Spara</button>
</form>
	<button id="addCatBtn">L‰gg till ny kategori</button><br>
	<form id="editCatForm" style="display: none">
	<input type="text" type="text" name="name" placeholder = "Skriv in din nya varukategori." >
	<button submit="" id="toggleeditCatBtn">Spara</button>
</form>
	<button id= editCatBtn >Redigera</button><br>
	<button id= deleteCatBtn >Ta bort</button>

<div class="response"></div>
<div class="error"></div>
	<script>
$( document ).ready(function() {	
	
	$.fn.serializeObject = function()
	{
	   var o = {};
	   var a = this.serializeArray();
	   a.re
	   $.each(a, function() {
	       if (o[this.name]) {
	           if (!o[this.name].push) {
	               o[this.name] = [o[this.name]];
	           }
	           o[this.name].push(this.value || '');
	       } else {
	           o[this.name] = this.value || '';
	       }
	   });
	   return o;
	};
	
	
	function preZero(s){
		s += "";
		if(s.length < 2){
			s = "0" + s;
		}
		return s;
	}
	$(document).on("click","#addCatBtn",function(){
		$("#newCatForm").show();
		$("#addCatBtn").hide();
	});
	$(document).on("click","#editCatBtn",function(){
		$("#editCatForm").show();
		//getCategory (id)
		$("#editCatBtn").hide();
	});
	$(document).on("click","#toggleCatBtn",function(event){
		$("#newCatForm").hide();
		
		$("#addCatBtn").show();
	});

   // Save category
	$('#newCatForm').submit(function(e) {
		  $(".response").text("");
	  	  $('.error').text("");
    	  var data = $(this).serializeObject();
    	  // will pass the form data and parse it to json string
    	  $.ajax({
    		  url: baseUrl+'/api/category/admin/add_category',
    		  data: JSON.stringify(data),
    		  contentType:'application/json',
    		  accept:'application/json',
    		  processData:false,
    		  type: 'POST',
    		  complete: function(response) {
  				if(response.status == 200){
  	    			// clear values
  				    $(':input','#newCatForm')
  						.not(':button, :submit, :reset, :hidden')
  						.val('');
  		    	    $('.response').text(response.responseText);
  		    	    refreshTable();
  				}
				
    		  }, error: function(response){
    			if(response.status != 200){
        			var responseJSON = response.responseJSON;
        			
        	  	   	if(typeof responseJSON != 'undefined'){
        	  	   		var errors = '';
        	  	   		
            	  	   	for(var i = 0; i < responseJSON.fieldErrors.length; i ++){
                	  	   	errors += (responseJSON.fieldErrors[i].message); 
                	  	   	errors += '<br>';
            	  	   	}
            	  	  	
            	  	   	$('.error').append(errors);

        	  	   	} else {
            	  	   	$('.error').text(response.responseText); 
        	  	   	}
    			}
    	  	   	
    		  }
    	  });
	   
	  e.preventDefault(); // prevent actual form submit and page reload
	});
 
	
	//kunna posta datan i formul√§ret f√∂r att skapa ny cat, kunna markera nya poster och ta bort eller ÔøΩndra
	
	$("#addCatBtn").on("click", function(){
		//$("newCatForm").show();
	});
	
	var d = new Date();
	$("input[name$='date']").val(d.getFullYear() + "-" + preZero(d.getMonth()+1) + "-" + preZero(d.getDate()) + " " + preZero(d.getHours()) + ":" + preZero(d.getMinutes())).prop('disabled', true);
	
	function getCategory (id){
		//Diven t√∂ms p√• information och sedan laddas om
			//$("#categoryTableDiv").html("<table id=\"categoryTable\" class=\"listtable\"><tr><th>Kategorier</th><th>&nbsp;</th></tr></table>");
		
		//H√§mtar all data fr√•n kategorier i db:n
		$.ajax({
			type: "GET",
			url: "/royalspades/api/category/"+id,
			dataType: "text",
			success: function (data, textStatus, jqXHR) {
				var arr = JSON.parse(data);
				//startar en tbody-tag
				//loopar igenom all data och l‰gger i en tabell
				for(var i = 0; i < arr.length; i++){
					row = arr[i].name;
				}
				
			},
			error: function (data, textStatus, jqXHR) {
				alert("Error: " + textStatus + ", " + jqXHR);
			}
		});
		}
	
	function refreshTable (){
	//Diven t√∂ms p√• information och sedan laddas om
		$("#categoryTableDiv").html("<table id=\"categoryTable\" class=\"listtable\"><tr><th>Kategorier</th><th>&nbsp;</th></tr></table>");
	
	//H‰mtar all data frÂn kategorier i db:n
	$.ajax({
		type: "GET",
		url: "/royalspades/api/category/all/",
		dataType: "text",
		success: function (data, textStatus, jqXHR) {
			var arr = JSON.parse(data);
			//startar en tbody-tag
			$("#categoryTable").append("<tbody>");
			//loopar igenom all data och l‰gger i en tabell
			for(var i = 0; i < arr.length; i++){
				var row = "<tr><td>";
				row += arr[i].name;
				row += '</td><td style="text-align:center;">';
				row += '<input id="'+arr[i].id+'" type="checkbox">';
				row += "</td></tr>";
				$("#categoryTable").append(row);
			}
			
			$("#categoryTable").append("</tbody>");
			
		},
		error: function (data, textStatus, jqXHR) {
			alert("Error: " + textStatus + ", " + jqXHR);
		}
	});
	} 
	refreshTable();
	
});
</script>