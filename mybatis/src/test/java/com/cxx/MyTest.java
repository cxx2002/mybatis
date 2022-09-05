package com.cxx;

import com.cxx.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author 陈喜喜
 * @date 2022-07-28 15:33
 */
/**
 * 让动态代理类取调用StudentMapper.xml的标签，也就是xml文件做为动态代理接口的实现类
 * 动态代理Class uMapper = sqlSession.getMapper(动态代理Class.class);
 */
public class MyTest {
    @Test
    public void testGetAll() throws IOException {
        //使用文件流读取核心配置文件SqlMapConfig.xml
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");//getResourceAsStream会抛IO异常
        //创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //取出sqlSession的对象
        SqlSession sqlSession = factory.openSession();
        //完成查询操作
        List<Object> list = sqlSession.selectList("cxx.getAll");
        list.forEach(student -> System.out.println(student));
        //关闭sqlSession
        sqlSession.close();
    }
    @Test
    public void testGetById() throws Exception {
        //使用文件流读取核心配置文件SqlMapConfig.xml
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");//getResourceAsStream会抛IO异常
        //创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //取出sqlSession的对象
        SqlSession sqlSession = factory.openSession();
        //完成查询操作
        Student student = sqlSession.selectOne("cxx.getById",1);//id=1,后面那个是参数
        System.out.println(student);
        //关闭sqlSession
        sqlSession.close();
    }
    @Test
    public void testGetByName() throws Exception {
        //使用文件流读取核心配置文件SqlMapConfig.xml
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");//getResourceAsStream会抛IO异常
        //创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //取出sqlSession的对象
        SqlSession sqlSession = factory.openSession();
        //完成查询操作
        List<Student> list = sqlSession.selectList("cxx.getByName","四");
        list.forEach(student -> System.out.println(student));
        //关闭sqlSession
        sqlSession.close();
    }
    @Test
    public void testInsert() throws Exception {
        //使用文件流读取核心配置文件SqlMapConfig.xml
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //取出sqlSession的对象
        SqlSession sqlSession = factory.openSession();
        //调用方法
        sqlSession.insert("cxx.insert",new Student("小王","wang@126.com",33));
        //切记 ：在所有增删改后都必须手动提交事务！！！！！
        sqlSession.commit();
        //查询结果验证
        List<Object> list = sqlSession.selectList("cxx.getAll");
        list.forEach(student -> System.out.println(student));
        //关闭sqlSession
        sqlSession.close();
    }
    @Test
    public void testDelete() throws Exception {
        //使用文件流读取核心配置文件SqlMapConfig.xml
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //取出sqlSession的对象
        SqlSession sqlSession = factory.openSession();
        //调用方法
        sqlSession.insert("cxx.delete","小红");
        //切记 ：在所有增删改后都必须手动提交事务！！！！！
        sqlSession.commit();
        //查询结果验证
        List<Object> list = sqlSession.selectList("cxx.getAll");
        list.forEach(student -> System.out.println(student));
        //关闭sqlSession
        sqlSession.close();
    }
    @Test
    public void testUpdate() throws Exception {
        //使用文件流读取核心配置文件SqlMapConfig.xml
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //取出sqlSession的对象
        SqlSession sqlSession = factory.openSession();
        /**
         * 让动态代理类取调用StudentMapper.xml的标签，也就是xml文件做为动态代理接口的实现类
         * 动态代理Class uMapper = sqlSession.getMapper(动态代理Class.class);
         */
        //调用方法
        sqlSession.insert("cxx.update",new Student(4,"老六","laoliou@126.com",33));
        //切记 ：在所有增删改后都必须手动提交事务！！！！！
        sqlSession.commit();
        //查询结果验证
        List<Object> list = sqlSession.selectList("cxx.getAll");
        list.forEach(student -> System.out.println(student));
        //关闭sqlSession
        sqlSession.close();
    }
}
