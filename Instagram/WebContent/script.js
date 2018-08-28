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
    

// Login, Signup
$(document).ready(function() {
    $('.ui.form').form({
        inline : true,
        on     : 'blur',
        fields: {
            email: {
                identifier: 'email',
                rules: [{
                        type: 'empty',
                        prompt: '이메일을 입력하세요.'
                    },
                    {
                        type: 'email',
                        prompt: '올바른 이메일 형식을 입력하세요.'
                    }
                ]
            },
            password: {
                identifier: 'password',
                rules: [{
                        type: 'empty',
                        prompt: '비밀번호를 입력하세요.'
                    },
                    {
                        type: 'minLength[8]',
                        prompt: '적어도 8자 이상이어야 합니다.'
                    },
                    {
                        type: 'regExp[^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).+$]',
                        prompt: '하나 이상의 영문 및 숫자, 특수 기호를 포함해야 합니다.'
                    }
                ]
            },
            passwordCheck: {
                identifier: 'password-check',
                rules: [{
                        type: 'empty',
                        prompt: '한 번 더 입력하세요.'
                    },
                    {
                        type: 'match[password]',
                        prompt: '동일한 비밀번호를 입력해주세요.'
                    }
                ]
            },
            username: {
                identifier: 'username',
                rules: [{
                        type: 'empty',
                        prompt: '이름을 입력하세요.'
                    },
                    {
                        type: 'minLength[2]',
                        prompt: '적어도 2자 이상이어야 합니다.'
                    },
                    {
                        type: 'regExp[^([가-힣])+$]',
                        prompt: '한글만 입력할 수 있습니다.'
                    }
                ]
            },
            nickname: {
                identifier: 'nickname',
                rules: [{
                        type: 'empty',
                        prompt: '별명을 입력하세요.'
                    },
                    {
                        type: 'maxLength[12]',
                        prompt: '최소 4자, 최대 12자를 입력해주세요.'
                    },
                    {
                        type: 'regExp[^[a-zA-Z]{1}[a-zA-Z0-9_]{3,11}$]',
                        prompt: '영문으로 시작해야 하며, 한글은 허용되지 않습니다.'
                    },
                ]
            }
        }
    });
});