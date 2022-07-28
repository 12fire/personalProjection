$(document).ready(function () {

    const token = localStorage.getItem('token') || '';

    //添加课程白色遮罩层
    $('.item .add').hover(function () {
        $(this).addClass('mark');
    }, function () {
        $(this).removeClass('mark');
    })
    //添加课程按钮
    var top = ($(window).height() - $(".addCourse").height()) / 2;
    var left = ($(window).width() - $(".addCourse").width()) / 2;
    var scrollTop = $(document).scrollTop();
    var scrollLeft = $(document).scrollLeft();
    $(".addCourse").css({ position: 'absolute', 'top': top + scrollTop, left: left + scrollLeft });
    $('.item .add').click(function () {
        // console.log(123);
        $('.bigmark').show();
        // $('.bigmark .addCourse').show();
    })
    $('.addCourse .addCourse_top a').click(function () {
        $('.bigmark').hide();
    })


    // 添加课程ajax
    $('.addCourse button').click(function () {
        var courseName = $('.addCourse_bottom input').val();
        // console.log(courseName);
        fetch('/addCouses', {
            method: 'POST', // or 'PUT'
            headers: {
                'Content-Type': 'application/json',
                'token': token
            },
            body: JSON.stringify({ 'courseName': courseName }),
        }).then(response => response.json()).then(data => {
            if (data.flag === 1) {
                alert("课程创建成功");
                $('.bigmark').hide();
                location.reload(true)
            } else {
                alert("课程创建失败");
                $('.bigmark').show();
            }
        })
    })





    //用户点击退出事件
    var flag_loginout = 0;
    $('.nav_right').click(function () {
        if (flag_loginout == 0) {
            $(this).children('.loginout').attr('style', 'display:block');
            flag_loginout = 1;
        } else {
            $(this).children('.loginout').attr('style', 'display:none');
            flag_loginout = 0;
        }

    })

    // 用户信息请求

    fetch('/userMessage', {
        method: 'POST', // or 'PUT'
        headers: {
            'Content-Type': 'application/json',
            'token': token
        },
    }).then(response => response.json()).then(data => {
        $('.nav_right a span').html(data.data.userName);
    })

    //获取课程信息ajax
    fetch('/getCourse', {
        method: 'POST', // or 'PUT'
        headers: {
            'Content-Type': 'application/json',
            'token': token
        },
    }).then(response => response.json()).then(data => {
        var str_list = data.data;
        console.log(str_list);
        for (var i = 0; i < str_list.length; i++) {
            $('.item').
                append('<div class="homework" homework=""><p>' + str_list[i].courseName + '</p><div></></div>');
            $('.item .homework').eq(i).attr('homework', str_list[i].courseId);

        }

        $('.item .homework').click(function () {
            localStorage.setItem("courseId", $(this).attr("homework"));
            localStorage.setItem("courseName", $(this).children('p').html());
            window.location.href = "../homework_teacher.html"
            console.log("2132131");
        })
        // 鼠标在作业盒子上的事件
        $('.item .homework').hover(function () {
            $(this).addClass('mark');
        }, function () {
            $(this).removeClass('mark');
        })
    })






})
