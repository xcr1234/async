package example;

import com.oralcewdp.async.AsyncServlet;
import com.oralcewdp.async.ServletCallAble;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/test1",asyncSupported = true)
public class TestServlet extends AsyncServlet<String> {
    private static final long serialVersionUID = 2128613965122986203L;

    @Override
    public long getTimeout() {
        return 5000;
    }



    @Override
    public void onComplete(AsyncContext asyncContext, ServletCallAble<String> callAble, String result) throws IOException {
        HttpServletResponse response = callAble.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        response.getWriter().print(result);
    }

    @Override
    public ServletCallAble<String> getCallable() {
        return new ServletCallAble<String>() {
            @Override
            public String call() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return "测试正常,过程耗时："+getAsyncTime()+"毫秒。";
            }
        };
    }
}
