$('.form-shortener').on("submit", function (e){
    e.preventDefault();

    if($("#shorten-button").html() == "Shorten"){
        $(document).ready(function () {
            $.ajax({
                type: "POST",
                url: "/create",
                data: {url: $('#url').val()},
                statusCode: {
                    400: function() {
                        $("#fail-p").html("Invalid URL");
                    }
                },
                success: function (data){
                    $("#fail-p").html("");
                    $("#url").val("lshrt.herokuapp.com/g/"+data);
                    $("#shorten-button").html("Copy");
                }
            })
        });
    }else{
        document.getElementById("url").select();
        document.execCommand("copy");
    }
});

$("#url").on("input", function (){
    $("#shorten-button").html("Shorten");
});