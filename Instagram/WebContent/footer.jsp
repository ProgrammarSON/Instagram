<script>
/* navbar 좋아한 글 */
$(function(){
    $('#show_modal_follow').click(function(){
        $('.ui.modal')
        .modal({
            selector: {
                approve: '.ok',
                deny: '.cancel'
            },
            closable: false,
            onDeny: function() {
                return true;
            },
            onApprove: function() {
                window.alert('wow');
            }
        })
        .modal('show');
    });
});
</script>
</body>
</html>