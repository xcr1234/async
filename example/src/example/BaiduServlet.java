package example;

import com.oralcewdp.async.AsyncServlet;
import com.oralcewdp.async.ServletCallAble;

import javax.imageio.ImageIO;
import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;



/**
 * 从百度上下载一张图片并打印。
 */
@WebServlet(value = "/baidu",asyncSupported = true)
public class BaiduServlet extends AsyncServlet<BufferedImage> {
    private static final long serialVersionUID = 6671055227194618783L;

    @Override
    public void onComplete(AsyncContext asyncContext, ServletCallAble<BufferedImage> callAble, BufferedImage result) throws IOException {
        HttpServletResponse response = callAble.getResponse();
        response.setContentType("image/jpeg");
        ImageIO.write(result,"JPEG",response.getOutputStream());
    }

    @Override
    public ServletCallAble<BufferedImage> getCallable() {
        return new ServletCallAble<BufferedImage>() {
            @Override
            public BufferedImage call() throws IOException {
                URL url = new URL("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
                URLConnection urlConnection = url.openConnection();
                InputStream inputStream = null;
                try {
                    inputStream = urlConnection.getInputStream();
                    return ImageIO.read(inputStream);
                }finally {
                    if(inputStream!=null){
                        try {
                            inputStream.close();
                        }catch (IOException e){}
                    }
                }

            }
        };
    }
}
