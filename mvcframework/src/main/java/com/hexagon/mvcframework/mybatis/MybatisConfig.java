package com.hexagon.mvcframework.mybatis;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MybatisConfig {
    //Mybatis 에서 쿼리문을 수행하기 위해서 필요한 SqlSession 을 저장 및 관리하는 팩토리객체
    //따라서 쿼리문을 수행하고 싶은 者 는 , 이 팩토리로부터 SqlSession을 얻어가면 된다..
    private SqlSessionFactory sqlSessionFactory;
    private static MybatisConfig instance;

    private MybatisConfig() {
        String resource = "com/hexagon/mvcframework/mybatis/mybatis-config.xml";

        try(InputStream inputStream = Resources.getResourceAsStream(resource)){
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        
            log.debug("sqlSessionFactory is{}", sqlSessionFactory);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    //이 메서드를 호출하면 인스턴스를 하나만 만들수 있음
    public static MybatisConfig getInstance() {
        if(instance ==null) {
            instance = new MybatisConfig();
        }
        return instance;
    }

    //팩토리에서  SqlSession  하나 꺼내기!!
    public SqlSession getSqlSession() {
        SqlSession sqlSession = null;
        sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }

}

