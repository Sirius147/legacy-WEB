package com.beyond.legacyweb.user.controller;

import com.beyond.legacyweb.user.model.service.UserService;
import com.beyond.legacyweb.user.model.service.UserServiceImpl;
import com.beyond.legacyweb.user.model.vo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Serial;

@WebServlet(name = "enrollServlet", urlPatterns = "/user/enroll")
public class EnrollServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 123456789L;

    private final UserService userService;

    public EnrollServlet() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/user/enroll.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setId(request.getParameter("userId"));
        user.setName(request.getParameter("userName"));
        user.setEmail(request.getParameter("Email"));
        user.setPassword(request.getParameter("userPwd"));
        user.setPhone(request.getParameter("Phone"));
        user.setAddress(request.getParameter("address"));

        String hobbies = request.getParameter("hobby") != null ?
        String.join(",", request.getParameterValues("hobby")) : null;

        user.setHobby(hobbies);

        System.out.println(user);
        System.out.println(hobbies);

        int result = userService.save(user);

        if (result > 0) {
            // join complete
            request.setAttribute("msg", "join success");
            request.setAttribute("location", "/");
        } else {
            // join failed
            request.setAttribute("msg", "join fail");
            request.setAttribute("location", "/user/enroll");
        }

        request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
    }
}
