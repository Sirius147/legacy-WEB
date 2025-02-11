package com.beyond.legacyweb.user.model.service;

import static com.beyond.legacyweb.common.jdbc.JDBCTemplate.getConnection;
import static com.beyond.legacyweb.common.jdbc.JDBCTemplate.close;
import static com.beyond.legacyweb.common.jdbc.JDBCTemplate.commit;
import static com.beyond.legacyweb.common.jdbc.JDBCTemplate.rollback;

import com.beyond.legacyweb.common.jdbc.JDBCTemplate;
import com.beyond.legacyweb.user.model.dao.UserDao;
import com.beyond.legacyweb.user.model.dao.UserDaoImpl;
import com.beyond.legacyweb.user.model.vo.User;

import java.sql.Connection;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    @Override
    public User login(String userID, String userPW) {
        Connection connection = getConnection();
        User user = userDao.getUserById(connection, userID);

        if (user == null || !user.getPassword().equals(userPW)) {
            return null;
        }

        close(connection);

        return user;
    }

    @Override
    public int save(User user) {
        int result = 0;
        Connection connection = getConnection();

        if (user.getNo() > 0) {
            // update
            result = userDao.updateUser(connection, user);
        } else{
            result = userDao.insertUser(connection,user);
        }
        // 여러 테이블에서 작업을 하므로 서비스에서 한번에 모든 작업의 commit rollback을 한다
        if (result > 0) {
            commit(connection);
        } else {
            rollback(connection);
        }

        close(connection);
        return result;
    }

    @Override
    public int delete(int no) {

        int result = 0;
        Connection connection = getConnection();
//        result = userDao.delete(connection, no);
        result = userDao.updateUserStatus(connection, no, "N");
        if ( result > 0) {
            commit(connection);
        } else {
            rollback(connection);
        }
        close(connection);
        return result;
    }
}
