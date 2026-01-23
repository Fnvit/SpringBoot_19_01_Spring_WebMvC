package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Controller
public class TestController2 {
    @GetMapping("/test23/test1")
    public String test1(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "addr", required = false) String addr
    ) {
        System.out.println("test1!");
        System.out.println("id: " + id);
        System.out.println("addr: " + addr);
        /// URL Encoding (% 퍼센트 인코딩)
        id = URLEncoder.encode(id, StandardCharsets.UTF_8);
        addr = URLEncoder.encode(id, StandardCharsets.UTF_8);
        System.out.println("encoded id: " + id);
        System.out.println("encoded addr: " + addr);
        // 리다이렉트!
        // test23/test1 로 이동하세요. 라고 브라우저에게 요청한다
        // 앞에 / 안붙으면 내 현재 경로 기준으로 이동하려고 함 /test23/test23/test1
        // 앞에 / 붙이면 Context 경로 (기본 경로) 기준으로 이동시킴 /test23/test1
        return "redirect:/test23/test2?id=" + id + "&addr=" + addr;
    }

    @GetMapping("/test23/test2")
    public void test2(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "addr", required = false) String addr
    ) {
        System.out.println("test2!");
        System.out.println("id: " + id);
        System.out.println("addr: " + addr);
    }


    @GetMapping("/test23/test3")
    public String test3(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "addr", required = false) String addr,
            RedirectAttributes redirectAttributes
    ) {
        System.out.println("test3!");
        System.out.println("id: " + id);
        System.out.println("addr: " + addr);
        /// addAttribute 하면 리다이렉트 대상에 파라미터를 실어서 보내줄 수 있음~
        // 리다이렉트 대상에 i 파라미터에는 id를, add 파라미터에는 addr 실어서 가라
        redirectAttributes.addAttribute("i", id);
        redirectAttributes.addAttribute("add", addr);
        // addFlashAttribute: URL에 파라미터로 전달되지 않음 (@RequestParam으로 못받음!)
        // 대신, Session이라는 곳에 잠깐 저장됨
        // 한번만 Model에 addAttribute 한 거라고 생각하면 된다 (새로고침하면 사라짐)
        redirectAttributes.addFlashAttribute("data", "I'm Here!!");
        return "redirect:/test23/test4";
    }

    @GetMapping("/test23/test4")
    public void test4(
            @RequestParam(value = "i", required = false) String id,
            @RequestParam(value = "add", required = false) String addr,
            @RequestParam(value = "data", required = false) String data
    ) {
        System.out.println("test4!");
        System.out.println("id: " + id);
        System.out.println("addr: " + addr);
        System.out.println("data: " + data);
    }





}
