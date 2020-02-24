package cn.edu.nju.madpill.service;

import cn.edu.nju.madpill.config.CodecConfig;
import cn.edu.nju.madpill.custommapper.UserAssistantMapper;
import cn.edu.nju.madpill.domain.Group;
import cn.edu.nju.madpill.domain.Tag;
import cn.edu.nju.madpill.domain.User;
import cn.edu.nju.madpill.domain.UserGroup;
import cn.edu.nju.madpill.dto.GroupBriefDTO;
import cn.edu.nju.madpill.dto.TagDTO;
import cn.edu.nju.madpill.mapper.UserGroupMapper;
import cn.edu.nju.madpill.mapper.UserMapper;
import cn.edu.nju.madpill.utils.Base64XORCodec;
import org.apache.ibatis.annotations.InsertProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Generated;
import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static cn.edu.nju.madpill.custommapper.GroupAssistantDynamicSqlSupport.buildInsert;
import static cn.edu.nju.madpill.mapper.GroupDynamicSqlSupport.group;
import static cn.edu.nju.madpill.mapper.UserDynamicSqlSupport.user;
import static cn.edu.nju.madpill.mapper.UserGroupDynamicSqlSupport.userGroup;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @author Aneureka
 * @createdAt 2020-02-14 20:18
 * @description
 **/

@Service
@Transactional
public class UserService {

    private final UserMapper userMapper;
    private final UserGroupMapper userGroupMapper;
    private final UserAssistantMapper userAssistantMapper;

    private final CodecConfig codecConfig;

    public UserService(UserMapper userMapper, UserAssistantMapper userAssistantMapper, UserGroupMapper userGroupMapper, CodecConfig codecConfig) {
        this.userMapper = userMapper;
        this.userAssistantMapper = userAssistantMapper;
        this.userGroupMapper = userGroupMapper;
        this.codecConfig = codecConfig;
    }

    public void addUserIfAbsent(String openId) {
        // check if the user exists
        if (!getUserByOpenId(openId).isPresent()) {
            User record = new User();
            record.setOpenId(openId);
            record.setCreatedAt(LocalDateTime.now());
            userMapper.insert(record);
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

    public List<GroupBriefDTO> getGroups(User curUser) {
        Long selectedId = curUser.getId();
        SelectStatementProvider selectStatementProvider = select(group.id.as("group_id"), group.name.as("group_name"))
                .from(userGroup)
                .join(group)
                .on(userGroup.groupId, equalTo(group.id))
                .where(userGroup.userId, isEqualTo(selectedId))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        return userAssistantMapper.selectGroups(selectStatementProvider);
    }

    public Long newGroup(String name, User curUser) {
        Group group = new Group();
        group.setCreateAt(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))));
        group.setCreateBy(curUser.getId());
        group.setName(name);

        userAssistantMapper.insert(buildInsert(group));
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupId(group.getId());
        userGroup.setUserId(group.getCreateBy());
        userGroupMapper.insert(userGroup);
        return group.getId();
    }
}
