package example;

import com.oralcewdp.async.AsyncServlet;
import com.oralcewdp.async.ServletCallAble;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/test3",asyncSupported = true)
public class TestTimeout extends AsyncServlet<String> {


    private static final long serialVersionUID = 6021653105737005166L;

    @Override
    public long getTimeout() {
        return 2000;
    }



    @Override
    public void onComplete(AsyncContext asyncContext, ServletCallAble callAble, String result) throws IOException {
        HttpServletResponse response = callAble.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        response.getWriter().print(result);
    }

    @Override
    public void onTimeout(AsyncContext asyncContext, ServletCallAble<String> callAble) throws IOException {
        HttpServletResponse response = callAble.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        response.getWriter().print("请求超时，耗时:"+getAsyncTime()+"毫秒");
    }

    @Override
    public void onError(AsyncContext asyncContext, ServletCallAble<String> callAble, Throwable throwable) throws IOException {
        HttpServletResponse response = callAble.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        response.getWriter().print(throwable.getMessage());
    }

    @Override
    public ServletCallAble<String> getCallable() {
        return new ServletCallAble<String>() {
            @Override
            public String call() throws InterruptedException {
                Thread.sleep(3000);
                return "测试成功";
            }
        };
    }
}
