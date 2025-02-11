package com.beyond.legacyweb.user.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serial;

@WebServlet(name = "logoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 123456789L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // logout
        // 1. 세션 객체 열어둔다

        HttpSession session = request.getSession();
        // 2. 세션 종료
        session.invalidate();
        response.sendRedirect(request.getContextPath() +"/");
    }
}
