/**
 * Created by Artwar on 2014-02-18.
 */
var dataModule = [];
function parseJSON(key, value){
    var found = false;
    var object = ["category", "product", "user", "brand", "store"];
    for (var i = 0; i < object.length; i++){
        if(object[i] == key){
            found = true;
            break;
        }
    }
    if (found){
        if (isNaN(value)){
            dataModule.push(value);
        } else {
            for(var i = 0; i < dataModule.length; i++){
                var d = dataModule[i];
                if (d["@id"] == value){
                    return d
                }
            }
        }
    }

    return value;
}