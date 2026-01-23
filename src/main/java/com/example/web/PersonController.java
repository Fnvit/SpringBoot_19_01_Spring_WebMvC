package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class PersonController {
    @Autowired private PersonRepository personRepository;

    // regist.jsp 화면을 보여주는 용도의 Get
    @GetMapping("/regist")
    public String get_regist(){
        return "test23/regist";
    }

    @PostMapping("/regist")
    public String post_regist(
            Person person,
            RedirectAttributes redirectAttributes
    ){
        System.out.println("post_regist: " + person);
        // 내가 받은 사람 추가해줘!
        boolean result = personRepository.insert_person(person);
        if(result){
            return "redirect:/person";
        }else{
            redirectAttributes.addFlashAttribute("message", "Person already exists");
            return "redirect:/regist";
        }
    }
    // 사람의 정보를 받아와서, 수정하기!
    // name 사람이 이미 존재하면 age를 수정하자!
    @PostMapping("/update")
    public String update_person(
            @RequestParam("name") String name,
            @RequestParam("age") Integer age,
            RedirectAttributes redirectAttributes
    ){
        System.out.println("update_person name: " + name);
        System.out.println("update_person age: " + age);
        boolean result = personRepository.update_person(name, age);
        if(result){
            return "redirect:/person";
        }else{
            redirectAttributes.addFlashAttribute("message", "Person Not exists");
            return "redirect:/regist";
        }
    }

    // 사람의 정보를 받아와서, 삭제하기!
    // name 사람이 이미 존재하면 유저를 삭제하자!
    @PostMapping("/delete")
    public String delete_person(
            @RequestParam("name") String name,
            RedirectAttributes redirectAttributes
    ){
        System.out.println("delete_person name: " + name);
        boolean result = personRepository.delete_person(name);
        if(result){
            return "redirect:/person";
        }else{
            redirectAttributes.addFlashAttribute("message", "Person Not exists");
            return "redirect:/regist";
        }
    }

    // person.jsp 화면을 보여주는 용도의 Get
    @GetMapping("/person")
    public String person(Model model){
        var persons = personRepository.get_persons();
        model.addAttribute("persons", persons);
        return "test23/person";
    }
}
