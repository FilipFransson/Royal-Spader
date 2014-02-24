<script>
	window.location.hash = "p=" + "${pageUid}";
</script>
<h2>
	Min butik
</h2>
<br />
<h3 id="shopName">
	Ica Kvantum Norremark
</h3>
<p id="shopAddress">
	Norremarksvägen 5<br />
	35245 Växjö<br />
</p>
<a href="#"><i class="fa fa-edit"></i></a>
<br />
<fieldset class="allWares">
	<legend>Varor</legend>
	<table class="listtable categoryTable">
		<tr>
			<th>
				&nbsp;
			</th>
			<th>
				&nbsp;
			</th>
			<th>
				Namn &darr;
			</th>
			<th>
				Kategori &darr;
			</th>
			<th>
				&nbsp;
			</th>
		</tr>
		<tr>
			<td>
				<a href="#"><i class="fa fa-sort-down"></i></a>
			</td>
			<td>
				<a href="#"><i class="fa fa-sort-up"></i></a>
			</td>
			<td>
				Fanta
			</td>
			<td>
				Läskeblask
			</td>
			<td>
				<a href="#"><i class="fa fa-edit"></i></a>
			</td>
		</tr>
	</table>
</fieldset>


<fieldset class="allCategories">
	<legend>Kategorier</legend>
	<table class="listtable">
		<tr>
			<th>
				&nbsp;
			</th>
			<th>
				&nbsp;
			</th>
			<th>
				Namn &darr;
			</th>
		</tr>
		<tr>
			<td>
				<a href="#"><i class="fa fa-sort-down"></i></a>
			</td>
			<td>
				<!--<a href="#">&uarr;</a>-->
			</td>
			<td>
				Läskeblask
			</td>
		</tr>
		<tr>
			<td>
				<a href="#"><i class="fa fa-sort-down"></i></a>
			</td>
			<td>
				<a href="#"><i class="fa fa-sort-up"></i></a>
			</td>
			<td>
				Frukt och grönt
			</td>
		</tr>
		<tr>
			<td>
				<a href="#"><i class="fa fa-sort-down"></i></a>
			</td>
			<td>
				<a href="#"><i class="fa fa-sort-up"></i></a>
			</td>
			<td>
				Mejeri
			</td>
		</tr>
		<tr>
			<td>
				<!--<a href="#">&darr;</a>-->
			</td>
			<td>
				<a href="#"><i class="fa fa-sort-up"></i></a>
			</td>
			<td>
				Kolonial
			</td>
		</tr>
	</table>
</fieldset>
<script>
    $( document ).ready(function() {
        console.log("fail");
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
            url: "/api/store/2/",
            headers: {
                'Accept':"application/json",
                'Content-Type':"application/json",
                'converters':"* text"
            },
            dataType: "text",
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                data = JSON.parse(data, parseJSON);
                var arr = data["storeProduct"];
                $("#shopName").html(data["name"]);
                $("#shopAddress").html(data["address"]);
                //var arr = JSON.parse([data]);

                //$(".shopTable").append("<tbody>");
                $(".categoryTable tbody").empty();
                for(var i = 0; i < arr.length; i++){
                    var row = '<tr>' +
                            '<td><a href="#"><i class="fa fa-sort-down"></i></a></td>' +
                            '<td><a href="#"><i class="fa fa-sort-up"></i></a></td>' +
                            '<td>';
                    row += arr[i]["product"]["name"];
                    row +=  '</td><td>';
                    row +=  arr[i]["category"]["name"];
                    row +=  '</td><td>' +
                            '<a href="editShop/?id=' + arr[i].id + '"><i class="fa fa-edit"></i></a>' +
                            '</td></tr>';

                    $(".categoryTable tbody").append(row);
                }
                 console.log(row);
                //$(".shopTable").append("</tbody>");

                /*$('.shopTable').dataTable({
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
                });*/
            },
            error: function (data, textStatus, jqXHR) {
                alert("Error: " + textStatus + ", " + jqXHR);
            }
        });
    });
</script>
