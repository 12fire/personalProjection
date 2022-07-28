package com.example.shangchuanserve;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.shangchuanserve.bean.HomeWork;
import com.example.shangchuanserve.bean.User;
import com.example.shangchuanserve.bean.UserCourse;
import com.example.shangchuanserve.common.util.DateUtil;
import com.example.shangchuanserve.common.util.FileUtil;
import com.example.shangchuanserve.common.util.MyThreadLocalUtil;
import com.example.shangchuanserve.service.FileService;
import com.example.shangchuanserve.service.UserCourseService;
import com.example.shangchuanserve.service.UserService;
import com.example.shangchuanserve.service.homeworkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ShangchuanserveApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
        User user = new User();
        user.setUserId("191543132");
        user.setPassWord("123456");
        user.setUserName("ycx");

        System.out.println(userService.insertUser(user));
//        User wzx = userService.getUserByName("wzx");
//        System.out.println(wzx);
//        User userByUsername = userService.getUserByUsername("191543130");
//        System.out.println(userByUsername);
//        User userByUsername1 = userService.getUserByUsername("191543130");
//        System.out.println(userByUsername1);

//        System.out.println(userService.getUserByUser(user));

    }

    @Autowired
    UserCourseService userCourseService;
    @Test
    public void UserCourseTest(){
//        List<String> userByCouserId = userCourseService.findUserByCouserId("100001");
//        System.out.println(userByCouserId);
        UserCourse user = new UserCourse();
//        user.setCourseId("");
        user.setUserId("191543132");
//        boolean b = userCourseService.insertUserCourse(user);

        List courseByUserId = userCourseService.findCourseByUserId("191543132");
//        boolean b = userCourseService.delUserCourse(user);
        for(int i= 0; i < courseByUserId.size(); i++){
            System.out.println(courseByUserId.get(i));
        }




    }

    @Autowired
    private homeworkService homeworkS;
    @Test
    public void homeworkTest(){
        HomeWork homeWork = new HomeWork();
////        homeWork.setHomeworkDeadtime();
////        Calendar calendar = Calendar.getInstance();
////        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 3);
////        Date today = calendar.getTime();
////        System.out.println(today);
////        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
////        String format1 = format.format(today);
////        System.out.println(format1);
        homeWork.setHomeworkId(1);
        homeWork.setDdl(1);
        homeWork.setHomeworkName("实验er");
        homeWork.setFormat(".docx");
        homeWork.setCourseId("100001");
//        boolean b = homeworkS.insertHomework(homeWork);
//        System.out.println(b);

//        List<HomeWork> allhomework = homeworkS.findAllhomework();
//        System.out.println(allhomework);
//        boolean b = homeworkS.deletHomework("3");
//        System.out.println(b);
//        HomeWork homeWork1 = homeworkS.findinfobyhomeworkId("3");
//        System.out.println(homeWork1);
        homeworkS.updateHomework(homeWork);
    }
    @Autowired
    private FileService fileService;
    @Test
    public void FileTest() throws IOException {
        String path = "D:\\虚拟货币";
       File file = new File(path);
        String[] list = file.list();
        ArrayList<String> arr = new ArrayList<>();
        for(String name : list){
            arr.add(name);
            System.out.println(name);
        }
        System.out.println(arr);



    }
    @Test
    public void strTest() throws FileNotFoundException {
        FileOutputStream fos1 = new FileOutputStream(new File("D:/2021java/springboot_project/zip/test01.zip"));
        FileUtil.toZip("D:/2021java/springboot_project/1/计算机网络实验一", fos1,true);

    }

//    @Autowired
//    private UserCourseService userCourseService;
    @Test
    public void del() {
        User user1 = new User();
        user1.setUserName("user1");
        User user2 = new User();
        user1.setUserName("user2");
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        MyThreadLocalUtil.put(user1);
        System.out.println(MyThreadLocalUtil.get().hashCode());
        MyThreadLocalUtil.put(user2);
        System.out.println(MyThreadLocalUtil.get().hashCode());

    }


}
