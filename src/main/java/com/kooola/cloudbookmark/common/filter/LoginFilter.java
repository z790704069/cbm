package com.kooola.cloudbookmark.common.filter;

import com.kooola.cloudbookmark.common.RestResponseModel;
import com.kooola.cloudbookmark.common.UserThreadLocal;
import com.kooola.cloudbookmark.common.constants.ResultConstant;
import com.kooola.cloudbookmark.common.constants.WebConst;
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
import java.util.*;

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
        greenUrlSet.add("/user/active");
        greenUrlSet.add("/user/makeActivation");
        greenUrlSet.add("/");
        greenUrlSet.add("/about");
        greenUrlSet.add("/favicon.ico");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String uri = request.getRequestURI();

        String params = getRequestParamsStr(request);


        //不需要检查权限的接口，直接进行调用
        if(greenUrlSet.contains(uri)){
            LOG.info("http request: {}, params: {}, ip: {},http method: {}, UserAgent: {}",
                    uri, params, HttpUtil.getIpAddrByRequest(request), request.getMethod(),
                    request.getHeader(USER_AGENT));
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        User user = (User) HttpUtil.getLoginUser(request);

        //session中存在
        if(null != user){
            LOG.info("http request: {}, params: {}, ip: {},http method: {}, UserAgent: {}, User: {}",
                    uri, params, HttpUtil.getIpAddrByRequest(request), request.getMethod(),
                    request.getHeader(USER_AGENT), user.getEmail());
            UserThreadLocal.setValue(user);
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //session中不存在，从cookie中获取用户
        Integer uid = HttpUtil.getUidFromCookie(request);
        if(null != uid){
            user = userMapper.selectByPrimaryKey(uid);
            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, user);
            UserThreadLocal.setValue(user);
            LOG.info("http request: {}, params: {}, ip: {},http method: {}, UserAgent: {}, User: {}",
                    uri, params, HttpUtil.getIpAddrByRequest(request), request.getMethod(),
                    request.getHeader(USER_AGENT), user.getEmail());
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        HttpUtil.outputObject((HttpServletResponse)servletResponse,
                new RestResponseModel(ResultConstant.CBM_NOT_LOGIN));
        return;
    }


    private String getRequestParamsStr(HttpServletRequest request){

        Map<String, String> map = new HashMap();
        Map<String, String[]> params = request.getParameterMap();

        Iterator<String> it = params.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            String[] value = params.get(key);
            if (value != null) map.put(key, value[0]);
        }
        return map.toString();
    }


    @Override
    public void destroy() {

    }
}
