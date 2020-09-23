package cn.tll.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tll
 * @create 2020/9/18 14:00
 * 登录拦截器
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    /**
     * 进入页面预处理   如果登录过得 则可以访问到该页面，如果未登录的就访问不到界面
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (request.getSession().getAttribute("user")==null){
            response.sendRedirect("/admin");
            //不往下继续执行
            return false;
        }
        return true;
    }
}
