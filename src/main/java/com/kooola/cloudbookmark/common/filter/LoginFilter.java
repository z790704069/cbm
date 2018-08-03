package com.kooola.cloudbookmark.common.filter;

import com.kooola.cloudbookmark.common.RestResponseModel;
import com.kooola.cloudbookmark.common.UserThreadLoacl;
import com.kooola.cloudbookmark.common.constants.ResultConstant;
import com.kooola.cloudbookmark.dao.UserMapper;
import com.kooola.cloudbookmark.domain.User;
import com.kooola.cloudbookmark.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by march on 2018/7/31.
 */
@WebFilter(urlPatterns = { "/*" }, filterName = "loginFilter")
public class LoginFilter implements Filter{
    private static final Logger LOG = LoggerFactory.getLogger(LoginFilter.class);
    private static final String USER_AGENT = "user-agent";
    private static Set<String> greenUrlSet = new HashSet<String>();

    @Autowired
    private UserMapper userMapper;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        greenUrlSet.add("/login");
        greenUrlSet.add("/register");
        greenUrlSet.add("/");
        greenUrlSet.add("/about");
        greenUrlSet.add("/favicon.ico");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        LOG.info("http request: {}, ip: {}, UserAgent: {}", uri,
                HttpUtil.getIpAddrByRequest(request), request.getHeader(USER_AGENT));

        //不需要检查权限的接口，直接进行调用
        if(greenUrlSet.contains(uri)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        User user = (User) HttpUtil.getLoginUser(request);
        if(null == user){
            HttpUtil.outputObject((HttpServletResponse)servletResponse,
                    new RestResponseModel(ResultConstant.CBM_NOT_LOGIN));
            return;
//            Integer uid = HttpUtil.getUidFromCookie(request);
//            if(null != uid){
//                user = userMapper.selectByPrimaryKey(uid);
//                request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
//            }
        }
        UserThreadLoacl.setValue(user);
        filterChain.doFilter(servletRequest,servletResponse);
        return;
    }

    @Override
    public void destroy() {

    }
}
