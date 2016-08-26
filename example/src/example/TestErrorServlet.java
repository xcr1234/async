package example;

import com.oralcewdp.async.AsyncServlet;
import com.oralcewdp.async.ServletCallAble;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet(value = "/test2",asyncSupported = true)
public class TestErrorServlet extends AsyncServlet {
    private static final long serialVersionUID = 6164519875270842429L;


    @Override
    public long getTimeout() {
        return 3000;
    }




    @Override
    public ServletCallAble getCallable() {
        return new ServletCallAble() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(1000);
                throw new RuntimeException("测试发生错误");
            }
        };
    }
}
