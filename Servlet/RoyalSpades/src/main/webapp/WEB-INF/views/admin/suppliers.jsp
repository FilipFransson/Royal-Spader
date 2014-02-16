<script>
	window.location.hash = "p=" + '${pageUid}';
</script>
<h2> 
	Leverantör
</h2>
<a class="link" href="newSupplier">Ny leverantör</a>
<br />
<table id="dataTable" class="supplierTable listtable">
	<thead>
		<tr>
			<th>
				Namn
			</th>
			<th>
				Adress
			</th>
			<th>
				Org. nr
			</th>
			<th>
				Telefonnummer
			</th>
			<th>
				Kontaktperson
			</th>
			<th>
				
			</th>
		</tr>	
	</thead>
</table>

<script>
$( document ).ready(function() {	
	function preZero(s){
		s += "";
		if(s.length < 2){
			s = "0" + s;
		}
		return s;
	}
	var d = new Date();
	$("input[name$='date']").val(d.getFullYear() + "-" + preZero(d.getMonth()+1) + "-" + preZero(d.getDate()) + " " + preZero(d.getHours()) + ":" + preZero(d.getMinutes())).prop('disabled', true);
	
	
	$.ajax({
		type: "GET",
		url: "/royalspades/api/brand/all/",
		headers: {
			'Accept':"application/json",
			'Content-Type':"application/json"
		},
		dataType: "json",
		success: function (data, textStatus, jqXHR) {
			var arr = JSON.parse(data);
			
			$(".supplierTable").append("<tbody>");
			for(var i = 0; i < arr.length; i++){
				var row = "<tr><td>";
				row += arr[i].name;
				row += '</td><td style="text-align:right;">';
				row += arr[i].address;
				row += "</td><td>";
				row += arr[i].orgNumber;
				row += "</td><td>";
				row += arr[i].phone;
				row += "</td><td>";
				row += arr[i].user.firstName + " " + arr[i].user.lastName + " (" + arr[i].user.email + ")";
				row += '</td><td style="text-align:center;">';
				row += '<a class="link" href="editSupplier/?id=' + arr[i].id + '">Redigera</a>';
				row += "</td></tr>";
				$(".supplierTable").append(row);
			}
			
			$(".supplierTable").append("</tbody>");
			
			$('.supplierTable').dataTable({
				"aLengthMenu": [
		            [25, 50, 100, -1],
		            [25, 50, 100, "All"]],
				"iDisplayLength" : -1,
		        "bScrollInfinite": true,
		        "bScrollCollapse": false,
		        "sScrollY": "300px",
				"oLanguage": {
					"sLengthMenu": "Visar _MENU_ produkter per sida",
					"sZeroRecords": "Hittade inget - tyvärr",
					"sInfo": "Visar _START_ till _END_ av _TOTAL_ varor",
					"sInfoEmpty": "Visar 0 av 0 varor",
					"sInfoFiltered": "(filtrerat från _MAX_ varor)",
					"sSearch": "Filtrera: "
				}		
			});
		},
		error: function (data, textStatus, jqXHR) {
			alert("Error: " + textStatus + ", " + jqXHR);
		}
	});
});
</script>