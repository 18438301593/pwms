package com.weblogb.pwms.config;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.weblogb.pwms.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
* @author: Jiajiajia
* @Date: 2021/10/28
* @Description: 登录拦截
*/
public class LoginInterceptor implements  HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            User user=(User)request.getSession().getAttribute("user");
            if(user!=null){
                return true;
            }
            // 判断是否是ajax请求，是则返回json数据，否则跳转登录页面
            if(this.isAjax(request)){
                String res = "{\"data\":-1,\"flag\":false,\"msg\":\"test\"}";
                returnJson(response,res);
            }else{
                response.sendRedirect("/user/login");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 认证失败返回json数据
     * @param response
     * @param json
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private void returnJson(HttpServletResponse response, String json){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

    public boolean isAjax(HttpServletRequest request) {
        String param = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(param)) {
            return true;
        }else {
            return false;
        }
    }

}