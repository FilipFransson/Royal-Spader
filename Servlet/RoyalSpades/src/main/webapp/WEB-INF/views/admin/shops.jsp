<script>
	window.location.hash = "p=" + '${pageUid}';
</script>
<h2> 
	Butiker
</h2>
<a class="link" href="newShop">Ny shop</a>
<br />
<table id="dataTable" class="shopTable listtable groceryTable">
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
<<<<<<< Updated upstream
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
		url: "/royalspades/api/store/all/",
		headers: {
			'Accept':"application/json",
			'Content-Type':"application/json"
		},
		dataType: "json",
		success: function (data, textStatus, jqXHR) {
			var arr = JSON.parse(data);
			
			$(".shopTable").append("<tbody>");
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
				row += '<a class="link" href="editShop/?id=' + arr[i].id + '">Redigera</a>';
				row += "</td></tr>";
				$(".shopTable").append(row);
			}
			
			$(".shopTable").append("</tbody>");
			
			$('.shopTable').dataTable({
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
=======
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
            url: "/api/store/all/",
            dataType: "text",
            success: function (data, textStatus, jqXHR) {
                var arr = JSON.parse(data);

                $('.groceryTable').append('<tbody>');
                for(var i = 0; i < arr.length; i++){
                    if(arr[i]["id"] != undefined){
                        var row = "<tr><td>";
                        row += arr[i].name;
                        row += '</td><td style="text-align:right;">';
                        row += arr[i].address;
                        row += "</td><td>";
                        row += arr[i].orgNumber;
                        row += "</td><td>";
                        row += arr[i].phone;
                        row += "</td><td>";
                        row += arr[i].user.firstName + " " + arr[i].user.lastName;
                        row += '</td><td style="text-align:center;">';
                        row += '<input type="checkbox">';
                        row += "</td></tr>";
                        $(".groceryTable").append(row);
                    }
                }

                $(".groceryTable").append("</tbody>");

                $('.groceryTable').dataTable({
                    "aLengthMenu": [
                        [25, 50, 100, -1],
                        [25, 50, 100, "All"]],
                    "iDisplayLength" : -1,
                    "bScrollInfinite": true,
                    "bScrollCollapse": false,
                    "sScrollY": "300px",
                    "oLanguage": {
                        "sLengthMenu": "Visar _MENU_ produkter per sida",
                        "sZeroRecords": "Hittade inget - tyvÃ¤rr",
                        "sInfo": "Visar _START_ till _END_ av _TOTAL_ varor",
                        "sInfoEmpty": "Visar 0 av 0 varor",
                        "sInfoFiltered": "(filtrerat frÃ¥n _MAX_ varor)",
                        "sSearch": "Filtrera: "
                    }
                });
            },
            error: function (data, textStatus, jqXHR) {
                alert("Error: " + textStatus + ", " + jqXHR);
            }
        });
    });
>>>>>>> Stashed changes
</script>