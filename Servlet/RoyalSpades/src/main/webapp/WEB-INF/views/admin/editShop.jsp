<script>
	window.location.hash = "p=" + '${pageUid}';
</script>
<h2> 
	Ändra butik
</h2>
<br />
<div id="error"></div>

<form id="editShopForm">
	<table>
		<tr>
			<td>
				<label for="name">Namn på butik: </label>
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
				<label for="user">Administratör: </label>
			</td>
			<td>
				<select id="user"></select>
			</td>
		</tr>
	</table>
	<input type="hidden" value="${id}" name="id">
    <input type="submit" value="Lägg till">
</form>
<br />
<div id="response"></div>

<script>
$(document).ready(function() {
	$.getJSON("/royalspades/api/store/" + "${id}")
		.done(function(data) {
			$("input[name='name']").val(data.name);
			$("input[name='address']").val(data.address);
			$("input[name='orgNumber']").val(data.orgNumber);
			$("input[name='phone']").val(data.phone);
			$("input[name='user']").val(data.user.id);
		})
		.fail(function(jqxhr, textStatus, error) {
		    var err = textStatus + ", " + error;
	        $('#error').text("Något gick fel: " + err);
		});
	
	// fill the select box with users that can be a shop administrator
	$.getJSON("/royalspades/api/admin/user/shop_managers/")
	    .done(function(data) {
		    $("#user option").remove(); // Remove all <option> child tags.
		    $("#user").append( $("<option></option>") .text("Välj"));  
		    $.each(data, function(index, item) { // Iterates through a collection
		        $("#user").append( // Append an object to the inside of the select box
		            $("<option></option>")
		                .text(item.firstName + " " + item.lastName)
		                .val(item.id)
		        );
		    });
		})
		.fail(function(jqxhr, textStatus, error) {
		    var err = textStatus + ", " + error;
	        $('#error').text("Något gick fel: " + err);
		});
	
	
    // Save Shop AJAX Form Submit
    $('#editShopForm').submit(function(e) {
  	  $("#response").text("");
  	  // get userId from selected option
  	  var userId = $("#user option:selected").val();
  	  
      // will pass the form data using the jQuery serialize function
      $.post('/royalspades/api/store/admin/update_store/' + userId, $(this).serialize(), function(response) {
		  
        // clear values
        $(':input','#editShopForm')
			.not(':button, :submit, :reset, :hidden')
			.val('')
			.removeAttr('selected');
      	
        $('#response').text(response);
      });
       
      e.preventDefault(); // prevent actual form submit and page reload
    });
     
  });
</script>