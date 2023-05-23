package main.filter;



import com.alibaba.fastjson.JSON;
import main.common.BaseContext;
import main.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")  //
@Slf4j
public class LoginCheckFilter implements Filter {


    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        String requestURI = request.getRequestURI();
        log.info("Request URL：{}", requestURI);

        // 2.判断本次请求是否需要处理
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/user/login",
                "/user/sendMsg"
        };

        boolean needProcess = check(urls, requestURI);


        if (needProcess) {
            log.info("skip", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        //
        Object employeeId = request.getSession().getAttribute("employee");
        if (employeeId != null) {



            BaseContext.setCurrentId((Long) employeeId);

            filterChain.doFilter(request, response);
            return;
        }


        if (request.getSession().getAttribute("user") != null) {


            Long userId = (Long) request.getSession().getAttribute("user");

            BaseContext.setCurrentId(userId);

            filterChain.doFilter(request, response);
            return;
        }


        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));


    }



    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            if (PATH_MATCHER.match(url, requestURI)) {
                return true;
            }
        }
        return false;
    }

}
