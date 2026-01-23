package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;

@Component
public class PersonRepository {
    private HashMap<String, Person> persons = new HashMap<>();
    @Autowired DataSource dataSource; // HikariCP datasource

    public PersonRepository(){
        Person p1 = new Person();
        p1.setName("A");
        p1.setAge(10);
        Person p2 = new Person();
        p2.setName("B");
        p2.setAge(20);
        Person p3 = new Person();
        p3.setName("C");
        p3.setAge(30);
        persons.put("A", p1);
        persons.put("B", p2);
        persons.put("C", p3);
    }

    // 가지고 있는 모든 사람들을 반환하는 기능
    HashMap<String, Person> get_persons(){
        return persons;
    }

    // 가지고 있는 사람들 목록에 새로운 사람을 추가하는 기능
    boolean insert_person(Person person){
        String name = person.getName();
        if(!persons.containsKey(name)){
            persons.put(name,person); // 회원가입한 회원을 저장해주기!
            return true;
        }
        return false;
    }

    // 가지고 있는 사람들 목록에서 사람 정보를 변경하는 기능
    boolean update_person(String name, Integer age){
        if(persons.containsKey(name)){
            persons.get(name).setAge(age); // 기존 회원을 가져와서 age를 변경하기!
            return true;
        }
        return false;
    }

    // 가지고 있는 사람들 목록에서 사람을 제거하는 기능
    boolean delete_person(String name){
        if(persons.containsKey(name)){
            persons.remove(name); // 기존 회원을 가져와서 age를 변경하기!
            return true;
        }
        return false;
    }







}
