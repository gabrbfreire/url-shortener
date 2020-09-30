$('.form-shortener').on("submit", function (e){
    e.preventDefault();

    if($("#shorten-button").html() == "Shorten"){
        console.log($("#shorten-button").html())
        $(document).ready(function () {
            $.ajax({
                type: "POST",
                url: "/create",
                data: {url: $('#input-url').val()},
                success: function (data){
                    if(data == "Invalid URL"){
                        $("#fail-p").html(data);
                    }else{
                        $("#fail-p").html("");
                        $("#input-url").val("/g/"+data);
                        $("#shorten-button").html("Copy");
                    }
                }
            })
        });
    }else{
        document.getElementById("input-url").select();
        document.execCommand("copy");
    }
});

$("#input-url").focus(function (){
    $("#shorten-button").html("Shorten");
});