		<div id="register" class="animate form">
            <form id="signup" autocomplete="on" method="POST"> 
                <h1> Skapa konto </h1> 
                <p> 
                    <label for="firstName" class="uname" data-icon="u">Förnamn</label>
                    <input id="firstName" name="firstName" required="required" type="text" placeholder="Olle" />
                </p>
                <p> 
                    <label for="lastName" class="uname" data-icon="u">Efternam</label>
                    <input id="lastName" name="lastName" required="required" type="text" placeholder="Andersson" />
                </p>
                <p> 
                    <label for="username" class="uname" data-icon="u">Anv&auml;ndarnamn</label>
                    <input id="username" name="username" required="required" type="text" placeholder="mysuperusername690" />
                </p>
                <p> 
                    <label for="email" class="youmail" data-icon="e" > Email</label>
                    <input id="email" name="email" required="required" type="email" placeholder="mysupermail@mail.com"/> 
                </p>
                <p> 
                    <label for="password" class="youpasswd" data-icon="p">L&ouml;senord * </label>
                    <input id="password" name="password" required="required" type="password" placeholder="eg. X8df!90EO"/>
                </p>
   
                <p> 
                    <label for="passwordsignup_confirm" class="youpasswd" data-icon="p">Bekr&auml;fta l&ouml;senord * </label>
                    <input id="passwordsignup_confirm" name="passwordsignup_confirm" required="required" type="password" placeholder="eg. X8df!90EO"/>
                </p>
                <p class="sigin button" >
                    <input type="submit" value="Skapa konto">
                </p>
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
    	    $(':input','#signup').prop('disabled', true);
    	  	
    	    $('#response').text(response);
    	  });
      }
	   
	  e.preventDefault(); // prevent actual form submit and page reload
	});
 
});
</script>