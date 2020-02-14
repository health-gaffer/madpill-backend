package cn.edu.nju.madpill.service;

import lombok.extern.java.Log;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Log
@SpringBootTest
@MapperScan("cn.edu.nju.madpill.*mapper")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    @Autowired
    UserService tested;

    @Test
    void addUserIfAbsent() {
        String openId = "open_id_for_test";
        tested.addUserIfAbsent(openId);
    }
}