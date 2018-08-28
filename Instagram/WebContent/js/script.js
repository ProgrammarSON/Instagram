$(document).ready(function() {
    $('.ui .item').on('click', function() {
        $('.ui .item').removeClass('active');
        $(this).addClass('active');
    });


InlineEditor
    .create(document.querySelector('#editor'), {
        toolbar: {
            items: [
                'heading',
                '|',
                'bold',
                'italic',
                'link',
                'bulletedList',
                'numberedList',
                'imageUpload',
                'blockQuote',
                'undo',
                'redo'
            ]
        },
        image: {
            toolbar: [
                'imageStyle:full',
                'imageStyle:side',
                '|',
                'imageTextAlternative'
            ]
        },
        language: 'ko'
    })
    .catch(error => {
        console.error(error);
    });
});