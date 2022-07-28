$(document).ready(function () {

    const token = localStorage.getItem('token') || '';

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

    //添加课程按钮
    var top = ($(window).height() - $(".addCourse").height()) / 2;
    var left = ($(window).width() - $(".addCourse").width()) / 2;
    var scrollTop = $(document).scrollTop();
    var scrollLeft = $(document).scrollLeft();
    $(".addCourse").css({ position: 'absolute', 'top': top + scrollTop, left: left + scrollLeft });






    //获取作业信息ajax
    function getHomework() {
        fetch('/getHomework', {
            method: 'get', // or 'PUT'
            headers: {
                'Content-Type': 'application/json',
                'token': token
            },
        }).then(response => response.json()).then(data => {
            var str_list = data.data;
            console.log(str_list);
            for (var i = 0; i < str_list.length; i++) {
                console.log(parseInt(new Date().valueOf()));


                $('.item').
                    append('<div class="homework" homeworkid=""><p>' + str_list[i].homeworkName + '</p><div></></div>');
                console.log('id' + str_list[i].homeworkId);
                console.log($('.item .homework').eq(i).attr('homeworkid'));
                $('.item .homework').eq(i).attr('homeworkid', str_list[i].homeworkId);

            }
            // 鼠标在作业盒子上的事件
            $('.item .homework').hover(function () {
                $(this).addClass('mark');
            }, function () {
                $(this).removeClass('mark');
            })

            //提交作业点击事件
            $('.item .homework').click(function () {
                // console.log(123);
                $('.bigmark').show();
                console.log("提交作业点击事件"+$(this).attr('homeworkid'))
                $('.addCourse button').attr('homeworkid', $(this).attr('homeworkid'));
                // $('.bigmark .addCourse').show();
            })
            $('.addCourse .addCourse_top a').click(function () {
                $('.bigmark').hide();
            })
        })
        uploaddocumnet();
    }
    getHomework();

    提交作业ajax
    function uploaddocumnet() {
        $('.addCourse button').click(function () {
            const formData = new FormData();
            // console.log(courseName);
            formData.append('File', document.getElementById('uploadFile').files[0]);

            fetch('/upload', {
                method: 'POST', // or 'PUT'
                headers: {
                    // 'Content-Type': 'application/json',
                    'token': token,
                    'homeworkId': $(this).attr('homeworkid')
                },
                body: formData
            }).then(response => response.json()).then(data => {
                if (data.flag === 1) {
                    alert("提交成功");
                    $('.bigmark').hide();
                    location.reload(true)
                } else {
                    alert("提交失败");
                    $('.bigmark').show();
                }
            })
        })
    }

    // 分片上传
    // function uploaddocumnet() {
    //     $('#upload-container').click(function (event) {
    //         $("#picker").find('input').click();
    //     });
    //     var uploader = WebUploader.create({
    //         auto: true,
    //         swf: 'js/webuploadall/Uploader.swf', //swf文件路径
    //         server: 'http://localhost:8080/upload',
    //         dnd: '#upload-container',
    //         pick: '#picker',  //内部根据当前运行创建
    //         multiple: true,     //选择多个
    //         chunked: true,      //开启分片
    //         threads: 20,        //并发数
    //         method: 'POST',
    //         fileSizeLimit: 1024 * 1024 * 1024 * 10, //单个文件大小限制
    //         fileSingleSizeLimit: 1024 * 1024 * 1024,  //总文件大小
    //         fileVal: 'upload'
    //     });
    //     uploader.on("beforeFileQueued", function (file) {
    //         console.log(file); //获取文件后缀
    //     });
    //     uploader.on('uploadBeforeSend', function (headers) {
    //         console.log($('.addCourse #picker').attr('homeworkid'))
    //         $.extend(headers, {
    //             "token": token+"",
    //             "homeworkId": $('.addCourse #picker').attr('homeworkid')
    //         });
    //     });
    //     uploader.on('fileQueued', function (file) {
    //         //选中文件要做的事
    //         console.log(file.ext);
    //         console.log(file.size);
    //         console.log(file.name);
    //         var html = '<div class="upload-item"><span>文件名：' + file.name + '</span><span data-file_id="' + file.id + '"class="btn-delete">删除</span><span data-file_id="' + file.id + '"class="btn-retry">重试</span><div class="percentage ' + file.id + '" style="width: 0%;"></div></div>'
    //         $('#upload-list').append(html);
    //         uploader.md5File(file)  //给文件定义唯一的md5值，当再次上传相同文件时，就不用传了  大文件秒传实际上是没传，直接拷贝之前文件地址
    //             //显示进度
    //             .progress(function (percentage) {
    //                 console.log('Percentage:', percentage);
    //             })
    //             //完成
    //             .then(function (val) {
    //                 console.log('md5 result', val);
    //             });
    //     });

    // }


})