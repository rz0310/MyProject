package com.mybatis.test;

import com.mybatis.dao.IUserDao;
import com.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserOneToManyTest {
    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;
    IUserDao userDao;
    @Before
    public void init() throws IOException {
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
    }
    @After
    public void destory() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }
    @Test
    public void findAllTest(){
        List<User> userList = userDao.findAll();
        for(User user:userList){
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }
    @Test
    public void findByIdTest(){
        User user = userDao.findById(3);
        System.out.println(user);

//        一级缓存测试
//        User user1 = userDao.findById(3);
//        System.out.println(user1);
//        System.out.println(user == user1);
    }
    @Test
    public void findByNameTest(){
        List<User> userList = userDao.findByName("%cong%");
        System.out.println(userList);
    }
}
