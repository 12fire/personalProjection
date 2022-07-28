
$.extend({
    // 删除作业请求
    delhomework: function (homeworkId, token) {
        fetch('/delhomework?homeworkId=' + homeworkId, {
            method: 'get', // or 'PUT'
            headers: {
                'Content-Type': 'application/json',
                'token': token
            },
        }).then(response => response.json()).then(data => {
            if (data.flag === 1) {
                alert("删除成功");
                location.reload(true);
            } else {
                alert("删除失败");
            }


        })
    },

    // 获取已提交作业学生信息请求
    getStuHomework: function (homeworkId, token) {
        fetch('/getFinishedStuHomework?homeworkId=' + homeworkId, {
            method: 'get', // or 'PUT'
            headers: {
                'Content-Type': 'application/json',
                'token': token
            },
        }).then(response => response.json()).then(data => {
            $('.history_homework ul').hide();
            $('.history_homework div ol>li').remove();
            $('.history_homework div').show();

            for (var i = 0; i < data.data.length; i++) {
                $('.history_homework div ol .upload').before(
                    '<li>' +
                    '<span></span>' +
                    '<a href="javascript:void(0)">下载</a>' +
                    '</li>'
                )
                $('.history_homework div ol>li').eq(i).children('span').eq(0).html(data.data[i].userId + data.data[i].userName)
                    .attr("homeworkId", homeworkId).attr("userId", data.data[i].userId);

            }
            $('.history_homework>div ol .upload').attr("homeworkId", homeworkId)

            $('.history_homework div ol li a').click(function () {
                var userId = $(this).siblings('span').attr('userId');
                console.log(userId);
                var homeworkId = $(this).siblings('span').attr('homeworkId')
                $.downloadOne(token, userId, homeworkId);
            })


        })
    },

    //下载文件一个ajax
    downloadOne: function (token, userId, homeworkId) {
        var filename = '';
        fetch('/downloadOne', {
            method: 'post', // or 'PUT'
            headers: {
                'Content-Type': 'application/json',
                'token': token
            },
            body: JSON.stringify({
                'userId': userId,
                'homeworkId': homeworkId,

            }),
        }).then(res => {
            // console.log(res.headers.get('Content-Disposition').split('fileName='));
            filename = res.headers.get('Content-Disposition').split('fileName=')[1];
            filename = decodeURI(filename);
            res.blob().then(data => {
                // const data = data;
                const downloadURL = window.URL.createObjectURL(data);
                const a = document.createElement('a');
                a.style.display = 'none';
                a.href = downloadURL;
                a.download = filename;
                document.body.appendChild(a);
                a.click();
                document.body.removeChild(a);
                window.URL.revokeObjectURL(downloadURL);
            })
        })
    },
    //删除作业事件绑定
    delhomeworkOne: function (token) {
        var history_homework_list = $('.history_homework ul li');
        for (var i = 0; i < history_homework_list.length; i++) {
            history_homework_list.eq(i).children('div').children('a').eq(1).click(function () {
                // console.log('del');
                var homeworkId = $(this).parent().siblings('span').attr('homeworkId');
                // console.log(homeworkId);
                $.delhomework(homeworkId, token);
                // console.log($(this));
            })
            history_homework_list.eq(i).children('div').children('a').eq(2).click(function () {
                // console.log('del');
                var homeworkId = $(this).parent().siblings('span').attr('homeworkId');
                // console.log(homeworkId);
                $.getStuHomework(homeworkId, token);
                // console.log($(this));
            })
        }
    },
    // 获取所有作业请求
    gethomeworkMessage: function (courseId, token) {
        fetch('/gethomeworkMessageByCourseId?courseId=' + courseId, {
            method: 'get', // or 'PUT'
            headers: {
                'Content-Type': 'application/json',
                'token': token
            },
        }).then(response => response.json()).then(data => {
            $('.history_homework ul>li').remove();
            $('.history_homework ul').show();
            $('.history_homework>div').hide();
            for (var i = 0; i < data.data.length; i++) {
                $('.history_homework ul').append(
                    '<li>' +
                    '<span></span>' +
                    '<div>' +
                    '<a href="javascript:void(0);">修改</a>' +
                    '<a href="javascript:void(0);">删除</a>' +
                    '<a href="javascript:void(0);">查看</a>' +
                    // '<i>2022/6/6 17:55</i>' +
                    '</div>' +
                    '</li>'
                )
                $('.history_homework ul li span').eq(i).html(data.data[i].homeworkName)
                    .attr('homeworkId', data.data[i].homeworkId);
                // console.log(data.data[i]);
                // var date = new Date(data.data[i].ddl).Format("yyyy-MM-dd hh:mm:ss")
                // console.log(date);
                // $('.history_homework ul li div').eq(i).children('i').html(date);


            }
            $.delhomeworkOne(token);



        })
    },

    // 获取这门课程的所有学生
    getAllstudentCourse: function (courseId, token) {
        fetch('/getAllstudentCourse?courseId=' + courseId, {
            method: 'get', // or 'PUT'
            headers: {
                'Content-Type': 'application/json',
                'token': token
            },
        }).then(response => response.json()).then(data => {
            $('.student_list ul>li').remove();
            for (var i = 0; i < data.data.length; i++) {
                $('.student_list ul').append(
                    '<li>' +
                    '<span></span>' +
                    '<a href="javascript:void(0);">删除</a>' +
                    // '<i>2022/6/6 17:55</i>' +
                    '</li>'
                )
                $('.student_list ul li span').eq(i).html(data.data[i].userId+data.data[i].userName)
                    .attr('userId', data.data[i].userId);
                // console.log(data.data[i]);
                // var date = new Date(data.data[i].ddl).Format("yyyy-MM-dd hh:mm:ss")
                // console.log(date);
                // $('.history_homework ul li div').eq(i).children('i').html(date);


            }



        })
    }



})



$(document).ready(function () {
    const token = localStorage.getItem('token') || '';
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
    // 课程信息事件
    $('.nav .nav_left span').html(localStorage.getItem('courseName'));

    //左边导航栏事件
    $('.main_left ul>li').click(function () {
        $(this).addClass('changeColor').siblings().removeClass('changeColor');
        var i = $(this).index();
        // console.log($('this'));
        // console.log($('.main_right div').eq(i));
        $('.main_right>div').eq(i).show().siblings().hide();
        if (i == 1) {
            $.gethomeworkMessage(localStorage.getItem('courseId'), token);
            console.log($('.history_homework ul li'));
            console.log($('.history_homework ul li>div'));
        } else if (i === 2) {
            $.getAllstudentCourse(localStorage.getItem('courseId'), token)
        }

    })

    // 用户信息请求ajax
    fetch('/userMessage', {
        method: 'POST', // or 'PUT'
        headers: {
            'Content-Type': 'application/json',
            'token': token
        },
    }).then(response => response.json()).then(data => {
        console.log(data);
        $('.nav_right a span').html(data.data.userName);
    })

    //添加作业
    $('.new_homework button').click(function () {
        var homeworkName = $('.new_homework ul li').eq(0).children('input').val();
        var format = $('.new_homework ul li').eq(1).children('input').val();
        var ddl = $('.new_homework ul li').eq(2).children('input').val();
        if (ddl.indexOf('T') != -1) {
            ddl += ':00';
            ddl = ddl.replace('T', ' ');
            ddl = ddl.replace(new RegExp("-", "gm"), "/");
            ddl = (new Date(ddl)).getTime();
        }
        if (homeworkName != '' && format != '' && ddl != '') {
            fetch('/addHomework', {
                method: 'POST', // or 'PUT'
                headers: {
                    'Content-Type': 'application/json',
                    'token': token
                },
                body: JSON.stringify({
                    'courseId': localStorage.getItem('courseId'),
                    'homeworkName': homeworkName,
                    'format': format,
                    'ddl': ddl
                }),
            }).then(response => response.json()).then(data => {
                if (data.flag === 1) {
                    alert('添加作业成功');
                    location.reload(true);
                    $('.main_left ul li').trigger('click');
                } else {
                    alert('添加作业失败');
                }
            })
        } else {
            alert("不能为空");
        }

    })

    //查看已提交作业用户，返回作业列表
    $('.history_homework div ol .fanhui').click(function () {
        $('.history_homework ul').show();
        $('.history_homework>div').hide();
    })

    //打包下载
    $('.history_homework>div ol .upload').click(function () {
        var homeworkId = $(this).attr("homeworkId");
        var filename = '';
        if (homeworkId != null) {
            fetch('/downloadAll?homeworkId=' + homeworkId, {
                method: 'get', // or 'PUT'
                headers: {
                    'Content-Type': 'application/json',
                    'token': token
                },

            }).then(res => {
                // console.log(res.headers.get('Content-Disposition').split('fileName='));
                filename = res.headers.get('Content-Disposition').split('fileName=')[1];
                filename = decodeURI(filename);
                res.blob().then(data => {
                    // const data = data;
                    const downloadURL = window.URL.createObjectURL(data);
                    const a = document.createElement('a');
                    a.style.display = 'none';
                    a.href = downloadURL;
                    a.download = filename;
                    document.body.appendChild(a);
                    a.click();
                    document.body.removeChild(a);
                    window.URL.revokeObjectURL(downloadURL);
                })

            })

        } else {
            alert.log('没有文件');
        }
    })
})
