package com.beyond.legacyweb.user.model.service;

import com.beyond.legacyweb.user.model.vo.User;

public interface UserService {

    User login(String userID, String userPW);

    int save(User user);

    int delete(int no);
}
