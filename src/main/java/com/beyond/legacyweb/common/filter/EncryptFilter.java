package com.beyond.legacyweb.common.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.LogRecord;

/*
 servlet filter는 - Request, Response가 서블릿이나 JSP에 도달하기 전에 필요한 전/후 처리 작업을 실행한다.
 filter chain을 통해서 여러 개의 필터를 연속적으로 사용할 수 있다.

 servlet filter 구현하는 방법
  - Filter interface를 구현하는 클래스를 생성한다.

 서블릿 필터를 등록하는 방법
  - web.xml 파일에 필터를 등록해서 사용할 수 있다.
  - @WebFilter annotation으로 필터를 등록해서 사용할 수 있다.

 */

@WebFilter(filterName = "encryptFilter",
//        urlPatterns = {"/login", "/user/enroll"}
        servletNames = {"loginServlet", "enrollServlet"}
)
public class EncryptFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 컨테이너가 필터를 생성할 때, 초기화를 위해서 호출한다.
        System.out.println(filterConfig.getFilterName() + " 필터가 생성되어 초기화 진행");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // 전처리 작업
        System.out.println("before Servlet");

        // 다음 필터를 호출하거나, 서블릿, JSP를 호출한다.
        // 사용자가 보낸 요청과 응답 객체를 임의로 수정할 수 없지만
        // wrapper를 통해 수정 가능
        EncryptPasswordWrapper wrapper = new EncryptPasswordWrapper((HttpServletRequest) request);
        filterChain.doFilter(wrapper, response);

//        ((HttpServletResponse) request).setCharacterEncoding("utf-8");
        // 후처리 작업
        System.out.println("after Servlet");
    }

    @Override
    public void destroy() {
        // 컨테이너가 필터를 제거할 때, 호출한다.
        System.out.println("필터가 제거 됨");
    }

}
