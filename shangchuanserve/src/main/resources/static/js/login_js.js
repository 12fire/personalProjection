$.extend({
    input_eye: function (flag, object) {
        if (flag === 0) {
            $(object).html('');
            $(object).parent().siblings('input').prop('type', 'text');
            return 1;
        } else {
            $(object).html('');
            $(object).parent().siblings('input').prop('type', 'password');
            return 0;
        }
    }
})


$(document).ready(function () {
    $(".box h1 button").click(function () {
        $(this).addClass("btn_color").siblings().removeClass("btn_color");
        if ($(this).index() === 0) {

            $(".login").stop().show();
            $('.register').stop().hide();
        } else if ($(this).index() === 1) {
            $(".login").stop().hide();
            $('.register').stop().show();
        }
    });
    var flag1 = 0;
    // 登录眼睛
    $('.box .login ul li i a').click(function () {
        flag1 = $.input_eye(flag1, this);

    })
    //注册眼睛
    var flag2 = 0;
    $('.box .register ul li i a').eq(0).on('click', function () {
        flag2 = $.input_eye(flag2, this);
        // console.log(this);
    })
    var flag3 = 0;
    $('.box .register ul li i a').eq(1).on('click', function () {
        flag3 = $.input_eye(flag3, this);

    })



    $('.box .login button').mousedown(function () {
        $(this).attr({ 'style': 'background-color: rgba(0, 0, 0, 0.5);' })

    });
    $('.box .login button').mouseup(function () {
        $(this).attr('style', 'background-color: rgba(255, 255, 255, 0.8)')
    });
    // 验证码刷新

    $('.yzm').click(function () {
        $.get('/vc.png', function (res) {
            $(this).children('img').attr('src', res);
        })
    })

    //登录请求
    $('.box .login button').click(function () {
        var userId = $('.box .login ul li').eq(0).children('input').val();
        var passWord = $('.box .login ul li').eq(1).children('input').val();
        var yzm = $('.yzm input').val();
        if (userId != '' && passWord != '' && yzm != '') {
            $.post('/login',
                {
                    'userId': userId,
                    'passWord': passWord,
                    'yzm': yzm
                }, function (res) {
                    if (res.flag == 1) {
                        $('.login p').html('登录成功'+res.data.userType);
                        localStorage.setItem('token', res.token);
                        if (res.data.userType == 1 || res.data.userType == '1') {
                            window.location.href = "../main_studnet.html";
                        } else if(res.data.userType == 2 || res.data.userType == '2'){
                            window.location.href = "../main_teacher.html";
                        }
                        
                        
                    } else {
                        $('.login p').html('密码或账号错误');
                        $('.login p').attr({ 'style': 'color: red;' })
                    }
                })
        } else {
            $('.login p').html('账号或密码或验证码不能为空');
            $('.login p').attr({ 'style': 'color: red;' })
        }
    });


})


