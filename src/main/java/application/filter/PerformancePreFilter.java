package application.filter;

import application.model.RequestData;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * User: militer
 * Date: 03.06.2017.
 */
public class PerformancePreFilter extends ZuulFilter {
    private static final String[] staticResourcesSuffixes = new String[]{".css", ".js", ".html", ".svg", ".jpg", ".map"};
    public static final String requestDataAttribute = "gatewayRequestData";
    private static String id = "0";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = ctx.getRequest();
        String requestURI = httpServletRequest.getRequestURI();
        if (!requestURI.endsWith("/request-monitor") && !isStaticResource(requestURI)) {
            synchronized (this) {
                long idValue = Long.parseLong(id);
                id = String.valueOf(++idValue);
            }
            httpServletRequest.setAttribute(requestDataAttribute, new RequestData(id, httpServletRequest.getRequestURI(), httpServletRequest.getMethod(), System.currentTimeMillis()));
        }
        return null;
    }

    private static boolean isStaticResource(String requestURI) {
        for(String staticResourcesSuffix : staticResourcesSuffixes){
            if (requestURI.endsWith(staticResourcesSuffix)){
                return true;
            }
        }
        return false;
    }
}
