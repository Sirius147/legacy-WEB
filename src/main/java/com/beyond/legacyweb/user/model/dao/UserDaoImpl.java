package com.beyond.legacyweb.user.model.dao;

import com.beyond.legacyweb.common.jdbc.JDBCTemplate;
import com.beyond.legacyweb.user.model.vo.User;

import java.sql.*;

import static com.beyond.legacyweb.common.jdbc.JDBCTemplate.close;
public class UserDaoImpl implements UserDao {

    //    @Override
//    public User getUserById(Connection connection, String userId) {
//        User user = null;
//        Connection connection = null;
//        // Statement statement = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        // String query = "SELECT * FROM user WHERE id='admin' AND status='Y'";
//        // String query = "SELECT * FROM user WHERE id='" + userId + "' AND status='Y'";
//        String query = "SELECT * FROM user WHERE id = ? AND status = 'Y'";
//
//        try {
//            Class.forName("org.mariadb.jdbc.Driver");
//
//            connection = DriverManager.getConnection(
//                    "jdbc:mariadb://localhost:3306/web",
//                    "beyond",
//                    "beyond"
//            );
//            // statement = connection.createStatement();
//            statement = connection.prepareStatement(query);
//
//            statement.setString(1, userId);
//
//            // resultSet = statement.executeQuery(query);
//            resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                user = new User();
//
//                user.setNo(resultSet.getInt("no"));
//                user.setId(resultSet.getString("id"));
//                user.setPassword(resultSet.getString("password"));
//                user.setRole(resultSet.getString("role"));
//                user.setName(resultSet.getString("name"));
//                user.setPhone(resultSet.getString("phone"));
//                user.setEmail(resultSet.getString("email"));
//                user.setAddress(resultSet.getString("address"));
//                user.setHobby(resultSet.getString("hobby"));
//                user.setStatus(resultSet.getString("status"));
//                user.setEnrollDate(resultSet.getDate("enroll_date"));
//                user.setModifyDate(resultSet.getDate("modify_date"));
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                resultSet.close();
//                statement.close();
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return user;
//    }
@Override
public User getUserById(Connection connection, String userId) {
    User user = null;

    PreparedStatement statement = null;
    ResultSet resultSet = null;

    String query = "SELECT * FROM user WHERE id = ? AND status = 'Y'";

    try {
        statement = connection.prepareStatement(query);

        statement.setString(1, userId);

        // resultSet = statement.executeQuery(query);
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            user = new User();

            user.setNo(resultSet.getInt("no"));
            user.setId(resultSet.getString("id"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));
            user.setName(resultSet.getString("name"));
            user.setPhone(resultSet.getString("phone"));
            user.setEmail(resultSet.getString("email"));
            user.setAddress(resultSet.getString("address"));
            user.setHobby(resultSet.getString("hobby"));
            user.setStatus(resultSet.getString("status"));
            user.setEnrollDate(resultSet.getDate("enroll_date"));
            user.setModifyDate(resultSet.getDate("modify_date"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {

            JDBCTemplate.close(resultSet);
            JDBCTemplate.close(statement);
            // connection은 서비스에서

    }

    return user;
}

    @Override
    public int insertUser(Connection connection, User user) {
//        int result = 0;
//        Connection connection = null;
//        PreparedStatement statement = null;
//        String query = "INSERT INTO user VALUES(NULL, ?,?,DEFAULT,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)";
//
//        try {
//            Class.forName("org.mariadb.jdbc.Driver");
//
//            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/web",
//                        "beyond", "beyond");
//
////            connection.setAutoCommit(false);
//
//            statement = connection.prepareStatement(query);
//
//            statement.setString(1, user.getId());
//            statement.setString(2, user.getPassword());
//            statement.setString(3, user.getName());
//            statement.setString(4, user.getPhone());
//            statement.setString(5, user.getEmail());
//            statement.setString(6, user.getAddress());
//            statement.setString(7, user.getHobby());
//
//            result = statement.executeUpdate();
//
////            if (result > 0) {
////                connection.commit();
////            } else {
////                connection.rollback();
////            }
//
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//                e.printStackTrace();
//        } finally {
//            try {
//                statement.close();
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return result;
//    }
        int result = 0;

        PreparedStatement statement = null;
        String query = "INSERT INTO user VALUES(NULL, ?,?,DEFAULT,?,?,?,?,?,DEFAULT,DEFAULT,DEFAULT)";

        try {

            statement = connection.prepareStatement(query);

            statement.setString(1, user.getId());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getAddress());
            statement.setString(7, user.getHobby());

            result = statement.executeUpdate();


        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
        }
        return result;
    }
    @Override
    public int updateUser(Connection connection, User user) {
        int result = 0;
        PreparedStatement statement = null;
        String query = "UPDATE user SET name =?,phone=?,email=?,address=?,hobby=?,modify_date=CURDATE() WHERE no=?";

        try {
            statement = connection.prepareStatement(query);

            statement.setString(1, user.getName());
            statement.setString(2, user.getPhone());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getHobby());
            statement.setInt(6, user.getNo());

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            close(statement);
        }
        return result;
    }

    @Override
    public int delete(Connection connection, int no) {
        int result = 0;
        PreparedStatement statement = null;
        String query = "DELETE FROM user WHERE no=?";

        try {
            statement = connection.prepareStatement(query);


            statement.setInt(1, no);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
        }


        return result;
    }

    @Override
    public int updateUserStatus(Connection connection, int no, String status) {
        int result = 0;
        PreparedStatement statement = null;
        String query = "UPDATE user SET status=? WHERE no=?";

        try {
            statement = connection.prepareStatement(query);

            statement.setString(1, status);
            statement.setInt(2, no);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
        }
        return result;
    }
}
