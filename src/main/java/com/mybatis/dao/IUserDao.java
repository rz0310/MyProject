package com.mybatis.dao;

import com.mybatis.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 *
 * */
@CacheNamespace(blocking = true)//使用二级缓存
public interface IUserDao {
//    查询所有用户
    @Select("select * from user")
//    建立实体类和数据库表中的对应关系
    @Results(id = "userMap", value = {
//            此处的id为设置为true，表示主键
            @Result(id = true, column = "id", property = "userId"),
            @Result(column = "username", property = "userName"),
            @Result(column = "address", property = "userAddress"),
            @Result(column = "sex", property = "userSex"),
            @Result(column = "birthday", property = "userBirthday"),
            /*properties指定要查询的实体类型
            column指定的是按照哪个字段查询(根据user的id字段来查询所有的账户信息)
            many：因为是一对多，故采用many
            select指定要操作的方法的全限定方法名，然后根据column来查询，获得最终的实体对象(account)
            fetchType指定的是加载的时机*/
            @Result(property = "accounts", column = "id", many = @Many(select = "com.mybatis.dao.IAccountDao.findByUid", fetchType = FetchType.LAZY))
    })
    public List<User> findAll();

//    根据id查询用户
    @Select("select * from user where id=#{id}")
    @ResultMap(value={"userMap"})
    public User findById(Integer id);

//    根据用户名称模糊查询
    @Select("select * from user where username like #{username}")
    @ResultMap("userMap")
    public List<User> findByName(String name);
}
