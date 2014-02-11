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
				<label for="name">Namn p� butik: </label>
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
<button id="newShopButton">L�gg till</button>
<script>
	$("#newShopButton").click(function () {
		
		
		$.fn.serializeObject = function()
		{
		    var o = {};
		    var a = this.serializeArray();
		    $.each(a, function() {
		        if (o[this.name] !== undefined) {
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
		

		var data = JSON.stringify($("#newShopForm").serializeObject());
		console.log(data);
		
		
		$.ajax({
			headers: {
				'Accept':"application/json",
				'Content-Type':"application/json"
			},
			dataType: "json",
			type: "POST",
			url: "http://172.16.6.175:8080/royalspades/api/store/admin/add_store/",
			data: data,
			context: document.body
			}).done(function(data) {
				console.log(data);
		}).error(function(data) {
			console.log(data);
		});
	});
</script>