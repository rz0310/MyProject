package com.mybatis.dao;

import com.mybatis.domain.Account;
import com.mybatis.domain.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IAccountDao {
//    查询所有账户，并且获取每个账户所属的用户信息
    @Select("select * from account")
    @Results(id="accountMap",value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "money", column = "money"),
            //此处的column指定uid是获取的user的查询条件,one指的是多对一中的一(即user),最后按照IUserDao中的findById(参数为uid)方法查询，最后得到的是user对象
//            一般多对一和一对一采用立即加载，而多对多和一对多采用延迟加载
            @Result(property = "user",column = "uid",one = @One(select = "com.mybatis.dao.IUserDao.findById",fetchType = FetchType.EAGER))
    })
    List<Account> findAll();

//    根据id查询账户信息
    @Select("select * from account where uid=#{userId}")
    public List<Account> findByUid(Integer id);
}
