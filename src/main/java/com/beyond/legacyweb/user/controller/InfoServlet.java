package com.beyond.legacyweb.user.controller;

import com.beyond.legacyweb.user.model.vo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serial;

@WebServlet(name="infoServlet", urlPatterns = "/user/info")
public class InfoServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 12324342L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        HttpSession session = request.getSession();
//        User loginUser = (User) session.getAttribute("loginUser");

//        if (loginUser != null) {
            request.getRequestDispatcher("/views/user/info.jsp").forward(request, response);
//        } else {
//            request.setAttribute("msg", "로그인 후 사용해 주세요");
//            request.setAttribute("location", "/");
//            request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
//        }

    }
}
