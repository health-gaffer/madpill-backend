package cn.edu.nju.madpill.service;

import cn.edu.nju.madpill.config.CodecConfig;
import cn.edu.nju.madpill.domain.User;
import cn.edu.nju.madpill.exception.BaseException;
import cn.edu.nju.madpill.mapper.UserMapper;
import cn.edu.nju.madpill.utils.Base64XORCodec;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static cn.edu.nju.madpill.mapper.UserDynamicSqlSupport.user;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;

/**
 * @author Aneureka
 * @createdAt 2020-02-14 20:18
 * @description
 **/

@Service
public class UserService {

    private final UserMapper userMapper;

    private final CodecConfig codecConfig;

    @Autowired
    public UserService(UserMapper userMapper, CodecConfig codecConfig) {
        this.userMapper = userMapper;
        this.codecConfig = codecConfig;
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

    public String token2openId(String token) {
        try {
            String[] data = Base64XORCodec.decrypt(token, codecConfig.getKey()).split(" ");
            if (data.length == 2) {
                return data[0];
            } else {
                throw new IllegalArgumentException("Invalid token.");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid token.");
        }
    }

    public String generateToken(String openId, String sessionKey) {
        String data = String.format("%s %s", openId, sessionKey);
        return Base64XORCodec.encrypt(data, codecConfig.getKey());
    }
}
