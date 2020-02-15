package cn.edu.nju.madpill.service;

import cn.edu.nju.madpill.domain.Drug;
import cn.edu.nju.madpill.domain.User;
import cn.edu.nju.madpill.dto.DrugDTO;
import cn.edu.nju.madpill.exception.BaseException;
import cn.edu.nju.madpill.mapper.UserMapper;
import org.apache.tomcat.jni.Local;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static cn.edu.nju.madpill.mapper.DrugTagDynamicSqlSupport.drugTag;
import static cn.edu.nju.madpill.mapper.TagDynamicSqlSupport.tag;
import static cn.edu.nju.madpill.mapper.TagDynamicSqlSupport.userId;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import static cn.edu.nju.madpill.mapper.UserDynamicSqlSupport.user;

/**
 * @author Aneureka
 * @createdAt 2020-02-14 20:18
 * @description
 **/

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void addUserIfAbsent(String openId) throws BaseException {
        // check if the user exists
        SelectStatementProvider selectStatement = select(user.id, user.openId)
                .from(user)
                .where(user.openId, isEqualTo(openId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<User> users = userMapper.selectMany(selectStatement);
        // if not exists
        if (users.isEmpty()) {
            User record = new User();
            record.setOpenId(openId);
            record.setCreatedAt(LocalDateTime.now());
            userMapper.insert(record);
        }
    }

}
