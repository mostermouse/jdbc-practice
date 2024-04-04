package org.jdbc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;


public class UserDaoTest {
    @BeforeEach
    void setUp() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db_schema.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManger.getDataSource());
    }

    @Test
    void createTest() throws SQLException {
        UserDao userDao = new UserDao();

        userDao.create(new User("asd123", "password", "조정인", "email"));

        User user = userDao.findByUserId("asd123");

        assertThat(user).isEqualTo(new User("asd123", "password", "조정인", "email"));
    }

    @Test
    void deleteTest() throws SQLException {
        UserDao userDao = new UserDao();

        userDao.delete(new User("asd123", "password", "조정인", "email"));


    }


    @Test
    void updateTest() throws SQLException {
        UserDao userDao = new UserDao();

        // 새로운 사용자 생성
        userDao.create(new User("최유순", "password", "name", "email"));

        // 새로운 사용자 정보 생성
        User user = new User("최유순", "password", "name", "email");

        // 사용자 정보 업데이트
        userDao.update(user);

        // 업데이트된 사용자 정보 확인
        User updatedUser = userDao.findByUserId("최유순");

        // 새로운 정보와 일치하는지 확인
        assertThat(updatedUser).isEqualTo(user);
    }


    @Test
    void selectTest() throws SQLException {
        UserDao userDao = new UserDao();

        // 새로운 사용자 생성
        User user = new User("홍길동", "password", "name", "email");

        // 새로운 사용자를 먼저 생성
        userDao.create(user);

        // 사용자 정보 조회
        User selectedUser = userDao.findByUserId("홍길동");

        // 조회된 사용자 정보가 생성한 사용자 정보와 일치하는지 확인
        assertThat(selectedUser).isEqualTo(user);
    }
}

