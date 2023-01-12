$(document).ready(function(){
    console.log($("#prueba").text());
})

// Función que manda un nombre al servidor y lo duelve modificado al html
function sendName(){
    var name = $("#name").val();
    console.log(name);
    $.ajax({
       url: "http://localhost:8080/name",
       type: "post",
       data: {
                "name": name
       },
       dataType: "text",
       success: function (response) {
           $("#response").text(response);
       },
       error: function(jqXHR, textStatus, errorThrown) {
           console.log(textStatus, errorThrown);
       }
    });
}