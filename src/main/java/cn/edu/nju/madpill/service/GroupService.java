package cn.edu.nju.madpill.service;

import cn.edu.nju.madpill.custommapper.GroupAssistantMapper;
import cn.edu.nju.madpill.domain.Group;
import cn.edu.nju.madpill.domain.User;
import cn.edu.nju.madpill.domain.UserGroup;
import cn.edu.nju.madpill.dto.GroupBriefDTO;
import cn.edu.nju.madpill.mapper.GroupMapper;
import cn.edu.nju.madpill.mapper.UserGroupMapper;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static cn.edu.nju.madpill.mapper.GroupDynamicSqlSupport.group;
import static cn.edu.nju.madpill.mapper.UserGroupDynamicSqlSupport.userGroup;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * <p>
 * <p>
 *
 * @author Charles
 * @date 2020/2/24
 */
@Service
public class GroupService {

    private final GroupMapper groupMapper;
    private final UserGroupMapper userGroupMapper;
    private final GroupAssistantMapper groupAssistantMapper;

    public GroupService(GroupMapper groupMapper, UserGroupMapper userGroupMapper, GroupAssistantMapper userAssistantMapper) {
        this.groupMapper = groupMapper;
        this.userGroupMapper = userGroupMapper;
        this.groupAssistantMapper = userAssistantMapper;
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

        return groupAssistantMapper.selectGroups(selectStatementProvider);
    }

    public Long newGroup(String name, User curUser, boolean firstGroup) {
        Group group = new Group();
        group.setCreateAt(LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai"))));
        group.setCreateBy(curUser.getId());
        group.setName(name);
        group.setCanDelete(firstGroup);

        groupMapper.insert(group);
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupId(group.getId());
        userGroup.setUserId(group.getCreateBy());
        userGroup.setAlias(name);
        userGroupMapper.insert(userGroup);
        return group.getId();
    }
}
