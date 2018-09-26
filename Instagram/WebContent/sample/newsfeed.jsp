<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="semantic/dist/semantic.min.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <script src="semantic/dist/semantic.min.js"></script>

    <!-- Custom -->
    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="js/script.js"></script>
    <title>Yestagram</title>
</head>

<body>
    <div class="ui container">
     


        <div class="ui three stackable cards">
            <div class="card">
                <div class="content">
                    <div class="right floated meta">14h</div>
                    <img class="ui avatar image" src="./images/avatar/large/elliot.jpg"> ProgrammerSON
                </div>
                <div class="image">

                    <img src="./images/avatar/large/elliot.jpg">

                </div>
                <div class="content">
                    <div class="description">
                        웹개발 어렵다
                    </div>

                </div>
                <div class="extra content">
                    <span class="right floated">
                        <i class="heart outline like icon"></i> 좋아요 9
                    </span>
                    <i class="comment icon"></i> 댓글 6
                </div>
            </div>

            <div class="card">
                <div class="content">
                    <div class="right floated meta">14h</div>
                    <img class="ui avatar image" src="./images/avatar/large/elliot.jpg"> Jipro
                </div>
                <div class="image">

                    <img src="./images/avatar/large/elliot.jpg">

                </div>
                <div class="content">
                    <div class="description">
                        Semantic-UI 적용해보자
                    </div>

                </div>
                <div class="extra content">
                    <span class="right floated">
                            <i class="heart outline like icon"></i> 좋아요 10
                        </span>
                    <i class="comment icon"></i> 댓글 4
                </div>
            </div>

            <div class="card">
                <div class="content">
                    <div class="right floated meta">16h</div>
                    <img class="ui avatar image" src="./images/avatar/large/elliot.jpg"> ProgrammerSON
                </div>
                <div class="image">

                    <img src="./images/avatar/large/elliot.jpg">

                </div>
                <div class="content">
                    <div class="description">
                        웹개발 어렵다
                    </div>

                </div>
                <div class="extra content">
                    <span class="right floated">
                                <i class="heart outline like icon"></i> 좋아요 17
                            </span>
                    <i class="comment icon"></i> 댓글 3
                </div>
            </div>

            <div class="card">
                <div class="content">
                    <div class="right floated meta">14h</div>
                    <img class="ui avatar image" src="./images/avatar/large/elliot.jpg"> ProgrammerSON
                </div>
                <div class="image">

                    <img src="./images/avatar/large/elliot.jpg">

                </div>
                <div class="content">
                    <div class="description">
                        웹개발 어렵다
                    </div>

                </div>
                <div class="extra content">
                    <span class="right floated">
                                    <i class="heart outline like icon"></i> 좋아요 17
                                </span>
                    <i class="comment icon"></i> 댓글 3
                </div>
            </div>

            <div class="card">
                <div class="content">
                    <div class="right floated meta">14h</div>
                    <img class="ui avatar image" src="./images/avatar/large/elliot.jpg"> ProgrammerSON
                </div>
                <div class="image">

                    <img src="./images/avatar/large/elliot.jpg">

                </div>
                <div class="content">
                    <div class="description">
                        웹개발 어렵다
                    </div>

                </div>
                <div class="extra content">
                    <span class="right floated">
                                        <i class="heart outline like icon"></i> 좋아요 17
                                    </span>
                    <i class="comment icon"></i> 댓글 3
                </div>
            </div>
        </div>
    </div>

    <script>
    $(function(){
        $.ajax({
            async: false,
            url: "likesfeed.html",
            type: "GET",
            dataType: "html",
            success: function(data) {
                $("#liked").popup({
                    popup: $(".custom.popup").html(data),
                    on: 'click',
                    delay: {
                        show: 300, hide: 800
                    }
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