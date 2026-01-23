package com.example.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TestController {
    @RequestMapping(
            path = {"/test1", "/test2"}
//            method = {RequestMethod.POST, RequestMethod.DELETE}
    )
    public void test(HttpServletRequest request){
        System.out.println("TEST1 실행됨!");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println("name: " + name);
        System.out.println("age: " + age);
    }
    // RequestMapping의 GET 버전
    // @RequestParam 이 붙은 변수는, Spring이 요청 파라미터에서 데이터를 가져와서 매핑해줌
    // name과 age의 파라미터를 받아와서 변수에 넣어줍니다!
    /// @RequestParam 은 기본이, 무조건 파라미터를 가져오려고 하기 때문에 파라미터가 없으면 오류난다
    /// required = false: 전달안받았을때는 null 넣어줌
    /// defaultValue = "KSW": 전달 안받았을때 기본값 넣어줌 (required = false 가 자동으로 붙음)
    // http://localhost:8080/test3?name=test&age=100
    /// ?name= :으로 전달하면 name 변수는 빈문자열("") 들어감
    /// ?age=1 :으로 name 자체를 빼고 요청하면 name에는 null들어감
    @GetMapping("/test3")
        public void test3(
                @RequestParam(value = "name", defaultValue = "KSW") String n, // name 파라미터 값 가져와
                @RequestParam(value = "age", required = false) Integer a // age파라미터 값 가져와
    ){
        System.out.println("TEST3!");
        System.out.println("name: " + n);
        System.out.println("age: " + a);
    }

    /// 경로에 {} 는 어떤 값이 들어간다는 얘기.
    /// @PathVariable: 경로에 있는 특정 값을 가져오겠다.
    /// b 위치에 있는 값을 pathValue 변수에 넣어주겠다
    /// /a/t1/c /a/t2/c
    @GetMapping("/a/{b}/c")
    public void test4(@PathVariable("b") String pathValue){
        System.out.println("TEST4!");
        System.out.println(pathValue);
    }

    // Person 객체 내부에 setter 가 존재하는 변수 중,
    // 파라미터의 이름과 변수명이 같으면 setter를 통해서 객체에 설정한다.
    // 아니면, 매개변수가 있는 생성자로도 가능함 (제약이 많음)
    @GetMapping("/test5")
    public void test4(Person person){
        System.out.println("TEST5!");
        System.out.println(person);
    }

    // void return이면, /test6 경로와 똑같은 이름의 파일을 찾아서 응답하려고 한다!
    // return "test6" 과 같음.
    @GetMapping("/test6")
    public String test6(){
        System.out.println("TEST6!");
//        return "test6"; // WEB-INF/views에 test6.jsp 찾아서 보여줄게~
        // WEB-INF/views 폴더의 test22폴더의 response.jsp 를 응답해라!
        return "/test22/response";
    }

    @GetMapping("/practice1")
    public String practice1(@RequestParam("data") Integer data){
        System.out.println("TEST6!");
//        return data < 10 ? "test22/min" : "max";
        if (data < 10) {
            return "test22/min";
        }
        return "max";
    }
    ////////////////////////////////////////
    @GetMapping("/test7")
    public ModelAndView test7(){
        // 옛날 방식.. 그냥 String return 합시다
        ModelAndView mv = new ModelAndView();
        mv.addObject("data", 10);
        mv.setViewName("test7"); // test8.jsp 를 화면에 보여줘
        return mv;
    }

    /// 클라이언트(사용자)에게 데이터를 전달하기! (응답에 데이터를 실어서 보내자)
    ///
    @GetMapping("/test8")
    public String test8(Model model){
        Person person = new Person();
        person.setName("KSW");
        person.setAge(30);
        // data라고 하는 이름으로 value를 binding(묶는다)
        model.addAttribute("data", "서버가 보낸 데이터에요~");
        model.addAttribute("arr1", new int[]{1,2,3,4});
        model.addAttribute("arr2", List.of(1,2,3,4));
        model.addAttribute("num", 100);
        model.addAttribute("person", person);
        return "test22/test8";
    }

}
