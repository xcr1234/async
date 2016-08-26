package example;

import com.oralcewdp.async.AsyncServlet;
import com.oralcewdp.async.ServletCallAble;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1秒后跳转到{@link BaiduServlet}
 */
@WebServlet(value = "/dispatch",asyncSupported = true)
public class TestDispach extends AsyncServlet<Void> {
    private static final long serialVersionUID = -2967708971523246835L;

    @Override
    public ServletCallAble<Void> getCallable() {
        return new ServletCallAble<Void>() {
            @Override
            public Void call() throws InterruptedException {
                Thread.sleep(1000);
                getAsyncContext().dispatch("/baidu");
                return null;
            }
        };
    }

    @Override
    public long getTimeout() {
        return 1500;
    }

    @Override
    public void onComplete(AsyncContext asyncContext, ServletCallAble<Void> callAble, Void result) throws IOException {
        HttpServletResponse response = callAble.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        response.getWriter().print("跳转失败！");
    }
}
