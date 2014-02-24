		<div id="register" class="animate form">
            <form id="signup" autocomplete="on" method="POST"> 
                <h1> Skapa konto </h1> 
				<table>
					<tr>
						<td>
							<label for="firstName" data-icon="u">Förnamn</label>
						</td><td>
							<input id="firstName" name="firstName" required="required" type="text" placeholder="Olle" />
						</td>
					</tr>

					<tr>
						<td>
							<label for="lastName" data-icon="u">Efternam</label>
						</td><td>
							<input id="lastName" name="lastName" required="required" type="text" placeholder="Andersson" />
						</td>
					</tr>

					<tr>
						<td>
							<label for="lastName" data-icon="u">Efternam</label>
						</td><td>
							<input id="lastName" name="lastName" required="required" type="text" placeholder="Andersson" />
						</td>
					</tr>

					<tr>
						<td>
							<label for="username" data-icon="u">Anv&auml;ndarnamn</label>
						</td><td>
							<input id="username" name="username" required="required" type="text" placeholder="mysuperusername690" />
						</td>
					</tr>

					<tr>
						<td>
							<label for="email" data-icon="e" > Email</label>
						</td><td>
							<input id="email" name="email" required="required" type="email" placeholder="mysupermail@mail.com"/>
						</td>
					</tr>

					<tr>
						<td>
							<label for="password" data-icon="p">L&ouml;senord * </label>
						</td><td>
							<input id="password" name="password" required="required" type="password" placeholder="eg. X8df!90EO"/>
						</td>
					</tr>

					<tr>
						<td>
							<label for="passwordsignup_confirm" data-icon="p">Bekr&auml;fta l&ouml;senord * </label>
						</td><td>
							<input id="passwordsignup_confirm" name="passwordsignup_confirm" required="required" type="password" placeholder="eg. X8df!90EO"/>
						</td>
					</tr>
					
					<tr>
						<td>
							<input type="submit" value="Skapa konto" id="signUpButton">
						</td><td>
							&nbsp;
						</td>
					</tr>
				</table>
            </form>
            <br />
            <div id="response"></div>
        </div>
        
<script>
$(document).ready(function() {
	
	// Save Shop AJAX Form Submit
	$('#signup').submit(function(e) {
		  $("#response").text("");
		
      if($("#password").val() != $("#passwordsignup_confirm").val()) {
		  $("#response").text("Lösenord matchar inte!");
      } else {
    	  // will pass the form data using the jQuery serialize function
    	  $.post('/royalspades/api/admin/user/new_user', $(this).serialize(), function(response) {
    	    // clear values
    	    $(':input','#signup')
    			.not(':button, :submit, :reset, :hidden')
    			.val('')
    			.removeAttr('selected');
    	    $('#signUpButton').prop('disabled', true);
    	  	
    	    $('#response').text(response);
    	  });
      }
	   
	  e.preventDefault(); // prevent actual form submit and page reload
	});
 
});
</script>