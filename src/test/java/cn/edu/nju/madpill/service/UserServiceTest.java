package cn.edu.nju.madpill.service;

import lombok.extern.java.Log;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@Log
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@DirtiesContext
class UserServiceTest {

    @Autowired
    UserService tested;

    @Test
    void testAddUserIfAbsent() {
        String openId = "open_id_for_test";
        tested.addUserIfAbsent(openId);
    }

    @Test
    void testSelectUserByOpenId() {
        String openId = "open_id_for_test";
        tested.addUserIfAbsent(openId);
        tested.getUserByOpenId("open_id_for_test");
    }
}