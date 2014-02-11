<script>
	window.location.hash = "p=" + '${pageUid}';
</script>
<h2> 
	Ny butik
</h2>

<form id="newShopForm">
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
	</table>
</form>
<button id="newShopButton">Lägg till</button>
<script>
	$("#newShopButton").click(function () {
		console.log($("#newShopForm").serializeArray());
		
		$.ajax({
			headers: {
				'Accept':"application/json",
				'Content-Type':"application/json"
			},
			dataType: "json",
			type: "POST",
			url: "http://172.16.6.175:8080/royalspades/api/store/admin/add_store/",
			data: $("#newShopForm").serializeArray(),
			context: document.body
			}).done(function(data) {
				console.log(data);
		}).error(function(data) {
			console.log(data);
		});
	});
</script>