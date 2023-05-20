package main.filter;

import com.alibaba.fastjson.JSON;
import com.fubukiss.rikky.common.BaseContext;
import com.fubukiss.rikky.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Project: rikky-takeaway - LoginCheckFilter Custom filter to check if user is logged in
 * <p>Powered by Riverify On 12-16-2022 22:59:51
 *
 * <p>Register the filter into the Servlet container through the @WebFilter annotation
 * <br>The urlPatterns attribute specifies the request paths intercepted by the filter. "/*" means intercept all requests.
 * <br>The filterName attribute specifies the name of the filter.
 * <p>Through the @Slf4j annotation, the log object is automatically injected.
 *
 * <p>
The processing logic of this filter:<br>
* 1. Get the url of this processing. <br>
 * 2. Determine whether this request needs to be processed. <br>
 * 3. If no processing is required, it will be released directly. <br>
 * 4. Judging the login status, if it is logged in, then release it. <br>
 * 5. If not logged in, return the result of not logged in. <br>
 * <p/>
 *
 * @author Riverify
 * @version 1.0
 * @since JDK8
 */
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")  // "/*"表示拦截所有请求
@Slf4j
public class    LoginCheckFilter implements Filter {

   // path matcher
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // Convert ServletRequest and ServletResponse to HttpServletRequest and HttpServletResponse
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        
        // 1. Get the url processed this time
        String requestURI = request.getRequestURI();
        log.info("Request path:{}", requestURI);                                        // Slf4j log output

        
        // 2. Determine whether this request needs to be processed
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/user/login",
                "/user/sendMsg"
        };                              // URLs that do not need to be processed

        boolean needProcess = check(urls, requestURI);  // Determine whether this request needs to be processed

        // 3.If no processing is required, release directly
        if (needProcess) {
            log.info("This request {} does not need to be processed, just let it go", requestURI);                    
            filterChain.doFilter(request, response);    
            return;                                     
        }

        // 4-1.Determine the login status of the employee, if it is logged in, then release
        Object employeeId = request.getSession().getAttribute("employee");        
        if (employeeId != null) {
            log.info("This request is {}, user id={}, has logged in, and is allowed directly", requestURI, employeeId);   

          
            BaseContext.setCurrentId((Long) employeeId);

            filterChain.doFilter(request, response);    
            return;                                     
        }

        // 4-2.Determine the user's login status, if it is logged in, release it
        if (request.getSession().getAttribute("user") != null) {
            log.info("This request is {}, user id={}, has logged in, and is allowed directly", requestURI, request.getSession().getAttribute("user"));   // Slf4j的日志输出

            Long userId = (Long) request.getSession().getAttribute("user");
            
            BaseContext.setCurrentId(userId);

            filterChain.doFilter(request, response);    
            return;                                     
        }

        // 5. If not logged in, return the result of not logged in

        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));   
        log.info("This request {} user is not logged in, return the result of not logged in", requestURI);                 

    }


    /**
     * path matching<br>
     * Determine whether this request needs to be processed.
     *
     * @param urls URLs that do not need to be processed
     * @param requestURI The url of this request
     * @return true: The url of this request matches one of the urls that do not need to be processed, and will not be processed; false: Otherwise, it needs to be processed
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            if (PATH_MATCHER.match(url, requestURI)) {
                return true;
            }
        }
        return false;
    }

}
