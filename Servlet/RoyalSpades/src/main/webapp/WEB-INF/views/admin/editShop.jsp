<script>
	window.location.hash = "p=" + '${pageUid}';
</script>
<h2> 
	Ändra butik
</h2>

<form>
	<table>
		<tr>
			<td>
				<label for="Name">Namn på butik: </label>
			</td>
			<td>
				<input name="Name" id="Name" value="${oldName}"><br />
			</td>
		</tr>
		<tr>
			<td>
				<label for="Adress">Address: </label>
			</td>
			<td>
				<input name="Adress" id="Adress" value="${oldAdress}"><br />
			</td>
		</tr>
	</table>
</form>