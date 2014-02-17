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
	<button id="addCatBtn">Lägg till ny kategori</button><br>
	<form id="editCatForm" style="display: none">
	<input type="text" type="text" name="name" placeholder = "Skriv in din nya varukategori." >
	<button submit="" id="toggleeditCatBtn">Spara</button>
</form>
	<button id= editCatBtn >Redigera</button><br>
	<button id= deleteCatBtn >Ta bort</button>
	
	<script>
$( document ).ready(function() {	
	
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
	

    // Save category AJAX Form Submit
    $('#newCatForm').submit(function(e) {
       e.preventDefault(); // prevent actual form submit and page reload

  	  $("#response").text("");
  	  
      // will pass the form data using the jQuery serialize function
      $.post(baseUrl+'/api/category/admin/add_category', $(this).serialize(), function(response) {
		  console.log(response);
        // clear values
        $(':input','#newCatForm')
			.not(':button, :submit, :reset, :hidden')
			.val('')
			.removeAttr('selected');
      		refreshTable();
        $('#response').text(response);
      });
       
    });
	
	//kunna posta datan i formuläret för att skapa ny cat, kunna markera nya poster och ta bort eller �ndra
	
	$("#addCatBtn").on("click", function(){
		//$("newCatForm").show();
	});
	
	var d = new Date();
	$("input[name$='date']").val(d.getFullYear() + "-" + preZero(d.getMonth()+1) + "-" + preZero(d.getDate()) + " " + preZero(d.getHours()) + ":" + preZero(d.getMinutes())).prop('disabled', true);
	
	function getCategory (id){
		//Diven töms på information och sedan laddas om
			//$("#categoryTableDiv").html("<table id=\"categoryTable\" class=\"listtable\"><tr><th>Kategorier</th><th>&nbsp;</th></tr></table>");
		
		//Hämtar all data från kategorier i db:n
		$.ajax({
			type: "GET",
			url: "/royalspades/api/category/"+id,
			dataType: "text",
			success: function (data, textStatus, jqXHR) {
				var arr = JSON.parse(data);
				//startar en tbody-tag
				//loopar igenom all data och lägger i en tabell
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
	//Diven töms på information och sedan laddas om
		$("#categoryTableDiv").html("<table id=\"categoryTable\" class=\"listtable\"><tr><th>Kategorier</th><th>&nbsp;</th></tr></table>");
	
	//Hämtar all data från kategorier i db:n
	$.ajax({
		type: "GET",
		url: "/royalspades/api/category/all/",
		dataType: "text",
		success: function (data, textStatus, jqXHR) {
			var arr = JSON.parse(data);
			//startar en tbody-tag
			$("#categoryTable").append("<tbody>");
			//loopar igenom all data och lägger i en tabell
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