<script>
	window.location.hash = "p=" + '${pageUid}';
</script>
<h2> 
	�ndra leverant�r
</h2>

<form id="editSupplierForm" method="POST">
	<table>
		<tr>
			<td>
				<label for="name">Namn p� leverant�r: </label>
			</td>
			<td>
				<input name="name" id="name"><br />
			</td>
		</tr>
		<tr>
			<td>
				<label for="address">Address: </label>
			</td>
			<td>
				<input name="address" id="address"><br />
			</td>
		</tr>
		<tr>
			<td>
				<label for="orgNumber">Org.nr: </label>
			</td>
			<td>
				<input name="orgNumber" id="orgNumber"><br />
			</td>
		</tr>
		<tr>
			<td>
				<label for="phone">Telefonnummer: </label>
			</td>
			<td>
				<input name="phone" id="phone"><br />
			</td>
		</tr>
		<tr>
			<td>
				<label for="user">Administrat�r: </label>
			</td>
			<td>
				<select id="user"></select>
			</td>
		</tr>
	</table>
    <input type="hidden" value="${id}" type="text" name="id">
    <input type="submit" value="Spara">
</form>
<br />
<div class="response"></div>
<div class="error"></div>

<script>
$(document).ready(function() {
	var managerId;
	
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
	
	function getValues(){
		$.getJSON("/royalspades/api/brand/" + "${id}")
			.done(function(data) {
				$("input[name='name']").val(data.name);
				$("input[name='address']").val(data.address);
				$("input[name='orgNumber']").val(data.orgNumber);
				$("input[name='phone']").val(data.phone);
				$("input[name='user']").val(data.user.id);
	
				managerId = data.user.id;
			})
			.fail(function(jqxhr, textStatus, error) {
			    var err = textStatus + ", " + error;
		        $('.error').text("N�got gick fel: " + err);
			});
		
		// fill the select box with users that can be a supplier administrator
		$.getJSON("/royalspades/api/admin/user/brand_managers/")
		    .done(function(data) {
			    $("#user option").remove(); // Remove all <option> child tags.
			    $.each(data, function(index, item) { // Iterates through a collection
			        $("#user").append( // Append an object to the inside of the select box
			            $("<option></option>")
			                .text(item.firstName + " " + item.lastName)
			                .val(item.id)
			        );
			    });
			    $("#user").val(managerId);
			})
			.fail(function(jqxhr, textStatus, error) {
			    var err = textStatus + ", " + error;
		        $('.error').text("N�got gick fel: " + err);
			});
	}
	

	// get all values 
	getValues();
	
	// Edit Supplier
	$('#editSupplierForm').submit(function(e) {
		  $(".response").text("");
	  	  $('.error').text("");
	  	  // get userId from selected option
	  	  var userId = $("#user option:selected").val();

	  	  if(typeof userId != 'undefined'){
	    	  var data = $(this).serializeObject();
	    	  // will pass the form data and parse it to json string
	    	  $.ajax({
	    		  url:'/royalspades/api/brand/admin/edit_brand/' + userId,
	    		  data: JSON.stringify(data),
	    		  contentType:'application/json',
	    		  accept:'application/json',
	    		  processData:false,
	    		  type: 'PUT',
	    		  complete: function(response) {
	  				if(response.status == 200){
	  	    			// clear values
	  				    $(':input','#editSupplierForm')
	  						.not(':button, :submit, :reset, :hidden')
	  						.val('');
	  		    	    $('.response').text(response.responseText);
	  		    	    
	  		    	    getValues();
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
	  	  } else {
	  		  $('.error').text('Du m�ste v�lja en administrat�r!');
	  	  }

	   
	  e.preventDefault(); // prevent actual form submit and page reload
	});
  });
</script>