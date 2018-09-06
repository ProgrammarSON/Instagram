<script>
/* navbar 좋아한 글 */
$(function(){
    $('#show_modal_follow').click(function(){
        $('.ui.modal')
        .modal({
            closable: false,
            transition: 'fade',
//             duration: 200,
//             selector: {
//                 approve: '.ok',
//                 deny: '.cancel'
//             },
//             onDeny: function() {
//                 return true;
//             },
//             onApprove: function() {
//                 window.alert('wow');
//             }
        })
        .modal('show');
    });
});
</script>
</body>
</html>