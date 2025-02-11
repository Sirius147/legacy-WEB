package com.beyond.legacyweb.user.controller;

import com.beyond.legacyweb.user.model.service.UserService;
import com.beyond.legacyweb.user.model.service.UserServiceImpl;
import com.beyond.legacyweb.user.model.vo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serial;


@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 123456789L;

    private final UserService userService;

    public LoginServlet(){
        userService = new UserServiceImpl();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = null;
        String userId = request.getParameter("userId");
        String userPwd   = request.getParameter("userPwd");

        System.out.println(userId+" "+userPwd);

        User loginUser = userService.login(userId, userPwd);

        if ( loginUser != null ){
            // loginUser 객체를 세션에 저장
            session = request.getSession();

            session.setAttribute("loginUser", loginUser);
            response.sendRedirect(request.getContextPath() + "/");
        }
        else {
            // 로그인 실패 메시지 출력 및 메인 화면 이동

            // 1. 공용으로 사용하는 에러 메시지 출력 페이지에
            // 전달할 메시지와 메시지 출력 후 이동할 페이지를 request 객체에 저장한다.
            request.setAttribute("msg", "아이디나 비밀번호가 일치하지 않습니다");
            request.setAttribute("location", "/");


            // 2. request 객체의 데이터를 유지해서 에러 메시지 출력 페이지에 전달하기 위해 forward를 실행한다.
            request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
        }

//        System.out.println(loginUser);

    }

}
