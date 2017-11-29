package demo.timemout;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 演示超时10s
 *
 * @author mujf
 * @create 2017-11-29 14:04
 */
@Component
public class TimeoutDemo10sResponseFilter extends ZuulFilter {

    private static final String URI = "/static/10s";

    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        String path = RequestContext.getCurrentContext().getRequest().getRequestURI();
        if (checkPath(path))
            return true;
        if (checkPath("/" + path))
            return true;
        return false;
    }

    private boolean checkPath(String path) {
        return URI.equals(path);
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        try {
            TimeUnit.SECONDS.sleep(9);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Set the default response code for static filters to be 200
        ctx.setResponseStatusCode(HttpServletResponse.SC_OK);
        // first StaticResponseFilter instance to match wins, others do not set body
        // and/or status
        if (ctx.getResponseBody() == null) {
            ctx.setResponseBody("timeout 10s");
            ctx.setSendZuulResponse(false);
        }
        return null;
    }
}
