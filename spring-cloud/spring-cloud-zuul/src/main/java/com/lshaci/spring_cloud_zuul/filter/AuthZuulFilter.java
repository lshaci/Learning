package com.lshaci.spring_cloud_zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

@Component
public class AuthZuulFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        String uri = RequestContext.getCurrentContext().getRequest().getRequestURI();
        // 静态资源不过该filter
        return !uri.matches(".*/static/.+\\..+$");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();

        if (Objects.equals("websocket", request.getHeader("Upgrade"))) {
            ctx.addZuulRequestHeader("connection", "Upgrade");
        }
        HttpSession session = request.getSession();
        System.err.println("SessionId: " + session.getId());
        System.err.println("SessionUser: " + session.getAttribute("user"));

        session.setAttribute("user", "aaa");

        return null;
    }
}
