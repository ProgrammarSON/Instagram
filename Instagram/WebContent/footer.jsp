<script>
/* navbar 좋아한 글 */
$(function(){
    $.ajax({
        url: "likesfeed.html",
        type: "GET",
        dataType: "html",
        success: function(data) {
            $("#liked").popup({
                popup: $(".custom.popup").html(data),
                on: 'click'
            });
        },
        error: function(err, val, msg) {
            alert(err.responseText);
        }
    });
});
</script>

</body>
</html>