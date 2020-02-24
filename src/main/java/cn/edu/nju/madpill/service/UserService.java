package cn.edu.nju.madpill.service;

import cn.edu.nju.madpill.config.CodecConfig;
import cn.edu.nju.madpill.domain.User;
import cn.edu.nju.madpill.mapper.UserMapper;
import cn.edu.nju.madpill.utils.Base64XORCodec;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static cn.edu.nju.madpill.mapper.UserDynamicSqlSupport.user;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.select;

/**
 * @author Aneureka
 * @createdAt 2020-02-14 20:18
 * @description
 **/

@Service
@Transactional
public class UserService {

    private final GroupService groupService;

    private final UserMapper userMapper;

    private final CodecConfig codecConfig;

    public UserService(GroupService groupService, UserMapper userMapper, CodecConfig codecConfig) {
        this.groupService = groupService;
        this.userMapper = userMapper;
        this.codecConfig = codecConfig;
    }

    public void addUserIfAbsent(String openId) {
        // check if the user exists
        if (!getUserByOpenId(openId).isPresent()) {
            User record = new User();
            record.setOpenId(openId);
            record.setCreatedAt(LocalDateTime.now());
            userMapper.insert(record);
            groupService.newGroup("我的药箱", record, false);
        }
    }

    public Optional<User> getUserByToken(String token) {
        try {
            String openId = token2openId(token);
            return getUserByOpenId(openId);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<User> getUserByOpenId(String openId) {
        SelectStatementProvider selectStatement = select(user.id, user.openId)
                .from(user)
                .where(user.openId, isEqualTo(openId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return userMapper.selectOne(selectStatement);
    }

    public String generateToken(String openId, String sessionKey) {
        String data = String.format("%s %s", openId, sessionKey);
        return Base64XORCodec.encrypt(data, codecConfig.getKey());
    }

    private String token2openId(String token) {
        String[] data = Base64XORCodec.decrypt(token, codecConfig.getKey()).split(" ");
        if (data.length == 2) {
            return data[0];
        } else {
            throw new IllegalArgumentException("Invalid token.");
        }
    }
}
