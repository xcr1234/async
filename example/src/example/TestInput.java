package example;

import com.oralcewdp.async.AsyncServlet;
import com.oralcewdp.async.ServletCallAble;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/input.do",asyncSupported = true)
public class TestInput extends AsyncServlet<String> {
    private static final long serialVersionUID = 3607411260816556291L;

    @Override
    public boolean doAsync(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //在doAsync方法中检查用户的输入.
        request.setCharacterEncoding("UTF-8");
        String user = request.getParameter("user");
        if(user==null||user.isEmpty()){
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain");
            response.getWriter().print("您的输入有误！");
            return false;  //false表示不进行异步操作，service直接结束
        }
        return true;  //true表示启动线程进入异步操作。
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
            public String call() throws InterruptedException {
                Thread.sleep(1000);
                HttpServletRequest request = getRequest();
                String user = request.getParameter("user");
                return "您输入的用户名是："+user;
            }
        };
    }
}
