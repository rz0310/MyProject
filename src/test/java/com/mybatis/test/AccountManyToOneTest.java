package com.mybatis.test;

import com.mybatis.dao.IAccountDao;
import com.mybatis.dao.IUserDao;
import com.mybatis.domain.Account;
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
import java.util.List;

public class AccountManyToOneTest {
    InputStream inputStream;
    SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession;
    IAccountDao accountDao;
    @Before
    public void init() throws IOException {
        inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
        accountDao = sqlSession.getMapper(IAccountDao.class);
    }
    @After
    public void destory() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        inputStream.close();
    }
    @Test
    public void findByUidTest(){
        List<Account> accounts = accountDao.findByUid(1);
        for (Account account:accounts){
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }
    @Test
    public void findAllTest(){
        List<Account> accounts = accountDao.findAll();
        for (Account account:accounts){
            System.out.println(account);
            System.out.println(account.getUser());
        }
        System.out.println(accounts);
    }
}
