package application.filter;

import application.model.RequestData;
import com.netflix.client.http.HttpResponse;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * User: militer
 * Date: 03.06.2017.
 */
public class PerformancePostFilter extends ZuulFilter {
    private static final List<RequestData> requests = new ArrayList<>();

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = ctx.getRequest();
        RequestData requestData = (RequestData) httpServletRequest.getAttribute(PerformancePreFilter.requestDataAttribute);
        if (requestData != null) {
            HttpResponse response = (HttpResponse)ctx.get("ribbonResponse");
            URI server = response.getRequestedURI();
            requestData.setServerInstanceUrl(server.toString());

            requestData.setEndTime(System.currentTimeMillis());
            synchronized (this) {
                requests.add(requestData);
            }
        }
        return null;
    }

    public static List<RequestData> getRequests() {
        return requests;
    }
}
