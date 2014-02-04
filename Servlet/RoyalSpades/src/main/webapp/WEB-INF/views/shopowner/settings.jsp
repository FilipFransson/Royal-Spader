<script>
	window.location.hash = "p=" + '${pageUid}';
</script>
<h2>
	Inställningar
</h2>

<fieldset class="accountSettings">
	<legend>Kontoinställningar</legend>
	<form name="account" action="" method="post" id="settings">
		<table class="formtable">
			<tr>
				<td>
					<label for="first_name">Namn: </label>
				</td>
				<td>
					<input type="text" id="last_name" name="last_name" value="Kalle Kula">
				</td>
			</tr>
			<tr>
				<td>
					<label for="last_name">Namn: </label>
				</td>
				<td>
					<input type="text" id="last_name" name="last_name" value="Kalle Kula">
				</td>
			</tr>
			<tr><td>&nbsp;</td><td></td></tr>
			<tr>
				<td>
					<label for="email">Mailadress: </label>
				</td>
				<td>
					<input type="text" id="email" name="email" value="cool_kalle_04@hotmail.com">
				</td>
			</tr>
			<tr><td>&nbsp;</td><td></td></tr>
			<tr>
				<td>
					<label for="nowPass">Nuvarande lösenord: </label>
				</td>
				<td>
					<input type="password" id="mail" value="asdasd">
				</td>
			</tr>
			<tr>
				<td>
					<label for="newPass">Nytt lösenord: </label>
				</td>
				<td>
					<input type="password" id="newPass" name="newPass" value="">
				</td>
			</tr>
			<tr>
				<td>
					<label for="newPassAgain">Upprepa nytt lösenord: </label>
				</td>
				<td>
					<input type="password" id="newPassAgain" name="newPassAgain" value="">
				</td>
			</tr>
			<tr><td>&nbsp;</td><td></td></tr>
			<tr>
				<td>
					<input type="Submit" value="Spara" onclick="e.preventDefault();">
				</td>
				<td>
				</td>
			</tr>
		</table>
	</form>
</fieldset>
<script>
	$("#settings").submit(function (e){
		e.preventDefault();
		$.post('', $(this).serialize(), function(response){
			alert("funkar yo");
		});
		e.preventDefault();
	});
</script>