package com.beyond.legacyweb.user.model.dao;

import com.beyond.legacyweb.user.model.vo.User;

import java.sql.Connection;

public interface UserDao {
    User getUserById(Connection connection, String userId);

    int insertUser(Connection connection, User user);

    int updateUser(Connection connection, User user);

    int delete(Connection connection, int no);

    int updateUserStatus(Connection connection, int no, String status);
}
