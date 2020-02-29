package cn.edu.nju.madpill.custommapper;

import cn.edu.nju.madpill.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Aneureka
 * @createdAt 2020-02-29 14:39
 * @description
 **/
@Mapper
public interface UserAssistantMapper {

    @Select("select * from `mp_user` where open_id=#{open_id} for update")
    List<User> selectUserByOpenId(@Param("open_id") String openId);
}
