package example;

import com.oralcewdp.async.ServletCallAble;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/test4",asyncSupported = true)
public class TestErrorPage extends TestErrorServlet {
    private static final long serialVersionUID = -8642326464340964484L;

    @Override
    public void onError(AsyncContext asyncContext, ServletCallAble callAble, Throwable throwable) throws IOException {
        //错误显示到页面上。
        HttpServletResponse response = callAble.getResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        response.getWriter().print("发生了错误："+throwable.getClass().getName()+","+throwable.getLocalizedMessage());
    }
}
