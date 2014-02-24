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
	<legend>
        <span id="shopProductShow" class="active"><a href="#">Varor</a></span>
        <span id="shopProductCreateNew"><a href="#">Lägg till Varor</a></span>
    </legend>
    <div class="shopProductPage">
        <table style="float: left" class="listtable productTable">
            <thead><tr>
                <th></th>
                <th></th>
                <th>
                    Namn <i class="fa fa-sort-down"></i>
                </th>
                <th>
                    Kategori <i class="fa fa-sort-down"></i>
                </th>
                <th>
                    Pris <i class="fa fa-sort-down"></i>
                </th>
                <th></th>
            </tr></thead>
            <tbody></tbody>
        </table>
    </div>
    <div id="shopSelectNewContainer" style="display: none">
        <div>
            <label for="selectNewProductsBrand">Brands</label>
            <select size="6" multiple style="width: 200px" id="selectNewProductsBrand">

            </select>
            <button id="shopNewProductsAdd">Lägg till</button>
            <button>Klart</button>
        </div>
        <div>
            <label for="selectNewProducts">Producter</label>
            <select size="6" multiple style="width: 200px" id="selectNewProducts">
                <option>Välj ett Brand</option>
            </select>
        </div>
        <div>
            <form>
                <table class="listtable shopTableNewProducts">
                    <thead>
                    <tr>
                        <th>Brand</th>
                        <th>Produkt</th>
                        <th>Categori</th>
                        <th>Pris</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Spendrups</td>
                        <td>Mineralvatten</td>
                        <td>LÃsk</td>
                        <td>9.98</td>
                        <td><i class="fa fa-times"></i></td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</fieldset>


<fieldset class="allCategories">
	<legend>Kategorier</legend>
	<table class="listtable categoryTable">
		<thead><tr>
			<th></th>
			<th></th>
			<th>
				Namn <i class="fa fa-sort-down"></i>
			</th>
		</tr></thead>
		<tbody></tbody>
	</table>
</fieldset>
<script>
    var allProducts;
    var allCategories;
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
            url: "/royalspades/api/product/all/",
            headers: {
                'Accept':"application/json",
                'Content-Type':"application/json"
            },
            dataType: "text",
            success: function (data, textStatus, jqXHR) {
                allProducts = parseJSON(data);
            },
            error: function (data, textStatus, jqXHR) {
                alert("Error: " + textStatus + ", " + jqXHR);
            }
        });
        $.ajax({
            type: "GET",
            url: "/royalspades/api/store/2/",
            headers: {
                'Accept':"application/json",
                'Content-Type':"application/json"
            },
            dataType: "text",
            success: function (data, textStatus, jqXHR) {
                //console.log(data);
                data = parseJSON(data);
                var arr = data["storeProduct"];
                $("#shopName").html(data["name"]);
                $("#shopAddress").html(data["address"]);

                //$(".shopTable").append("<tbody>");
                $(".productTable tbody").empty();
                for(var i = 0; i < arr.length; i++){
                    var row = '<tr>' +
                            '<td><a href="#"><i class="fa fa-sort-down"></i></a></td>' +
                            '<td><a href="#"><i class="fa fa-sort-up"></i></a></td>' +
                            '<td>';
                    row += arr[i]["product"]["name"];
                    row +=  '</td><td>';
                    row +=  arr[i]["category"]["name"];
                    row +=  '</td><td>';
                    row +=  arr[i]["price"];
                    row +=  '</td><td>' +
                            '<a href="editShop/?id=' + arr[i].id + '"><i class="fa fa-edit"></i></a>' +
                            '</td></tr>';

                    $(".productTable tbody").append(row);
                }
            },
            error: function (data, textStatus, jqXHR) {
                alert("Error: " + textStatus + ", " + jqXHR);
            }
        });
        $.ajax({
            type: "GET",
            url: "/royalspades/api/brand/all/",
            headers: {
                'Accept':"application/json",
                'Content-Type':"application/json"
            },
            dataType: "text",
            success: function (data, textStatus, jqXHR) {
                data = parseJSON(data);
                //console.log(data);
                //var arr = JSON.parse([data]);

                //$(".shopTable").append("<tbody>");
                $("#selectNewProductsBrand").empty();
                for(var i = 0; i < data.length; i++){
                    var row = '<option value="'+data[i]['id']+'">';
                    row +=  data[i]["name"];
                    row +=  '</option>';

                    $("#selectNewProductsBrand").append(row);
                }

                $(document).on("click", "#selectNewProductsBrand option", function(event){
                    //console.log(event.currentTarget.value);
                    for (var i = 0; i < data.length; i++){
                        var d = data[i];
                        if(d['id'] == event.currentTarget.value){
                            $("#selectNewProducts").empty();
                            if (d['brandProducts'].length == 0) {
                                $("#selectNewProducts").append('<option>Inga Producter</option>');
                                return;
                            }
                            for (var i = 0; i < d['brandProducts'].length; i++) {
                                var row = '<option value="'+d['brandProducts'][i]['id']+'">';
                                row +=  d['brandProducts'][i]["name"];
                                row +=  '</option>';

                                $("#selectNewProducts").append(row);
                                row = undefined;
                            }
                            return;
                        }
                    }
                });

            },
            error: function (data, textStatus, jqXHR) {
                alert("Error: " + textStatus + ", " + jqXHR);
            }
        });
        $.ajax({
            type: "GET",
            url: "/royalspades/api/category/all/",
            headers: {
                'Accept':"application/json",
                'Content-Type':"application/json"
            },
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                allCategories = data;
                //console.log(data);
                //data = parseJSON(data);


                $(".categoryTable tbody").empty();
                for(var i = 0; i < data.length; i++){
                    var row = '<tr>' +
                            '<td><a href="#"><i class="fa fa-sort-down"></i></a></td>' +
                            '<td><a href="#"><i class="fa fa-sort-up"></i></a></td>' +
                            '<td>';
                    row += data[i]["name"];
                    row += '</td></tr>';

                    $(".categoryTable tbody").append(row);
                }
            },
            error: function (data, textStatus, jqXHR) {
                alert("Error: " + textStatus + ", " + jqXHR);
            }
        });
        $( document).on("click", "#shopProductCreateNew", function(){
            $(".shopProductPage").slideUp();
            $("#shopSelectNewContainer").slideDown();

            $("#shopProductCreateNew").addClass('active');
            $("#shopProductShow").removeClass('active');
        });
        $( document).on("click", "#shopProductShow", function(){
            $(".shopProductPage").slideDown();
            $("#shopSelectNewContainer").slideUp();

            $("#shopProductCreateNew").removeClass('active');
            $("#shopProductShow").addClass('active');
        });
        $( document).on("click", "#shopNewProductsAdd", function(){
            var selected = $("#selectNewProducts").val();
            var tableBody = $(".shopTableNewProducts tbody");
            var deleteIcon = '<td><i class="fa fa-times"></i></td>';

            tableBody.empty();
            console.log(allProducts);

            for (var i = 0; i < selected.length; i++) {

                for (var j = 0; j < allProducts.length; j++) {
                    if (selected[i] == allProducts[j]['id']){
                        var d = allProducts[j];
                        console.log(d);
                        var row = '<tr>' +
                                '<td>'+d['brand']['name']+'</td>' +
                                '<td>'+d['name']+'</td>' +
                                '<td>'+createCategorySelect(d['category']['id'])+'</td>' +
                                '<td><input class="priceInput" name="productPrice" placeholder="Pris"></td>' +
                                deleteIcon + '</tr>';
                        tableBody.append(row);
                        break;
                    }
                }
            }
        });
    });
    function createCategorySelect(selected){
        var html = '<select name="category">';
        for (var i = 0; i < allCategories.length; i++) {
            var d = allCategories[i];
            var select = (selected == d['id']) ? "selected" : "";
            var row = '<option '+select+' value="'+d['id']+'">'+d['name']+'</option>';
            html += row;
        }
        html += '</select>';
        return html;
    }
</script>
