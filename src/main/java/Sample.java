import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.stream.FileImageInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class Sample {

    //设置APPID/AK/SK
    public static final String APP_ID = "15775598";
    public static final String API_KEY = "1G4LtriL87q4H4kqmawRAyW8";
    public static final String SECRET_KEY = "6okG0ia0hhV7ldapzcicPB91u2kuoezx";

    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
        sample(client);

    }

    /*
    通用文字识别
     */
    public static void sample(AipOcr client) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");


        //本地图片 方法一： 参数为本地路径
        String image = "G:\\图片素材\\timg.jpg";
        JSONObject res = client.basicGeneral(image, options);
        System.out.println(res.toString(2));

        //本地图片 方法二： 参数为二进制数组
//        byte[] file = readImageFile("C:\\Users\\Administrator\\Desktop\\photo\\1.jpg");
//        JSONObject res = client.basicGeneral(file, options);
//        System.out.println(res.toString(2));

        // 远程图片：通用文字识别, 图片参数为远程url图片
//        JSONObject res = client.basicGeneralUrl("https://img.glzy8.com/upfiles/ceo/soft/201711/30/1512028728616832.png", options);
//        System.out.println(res.toString(2));

    }

    /*
    通用文字识别（高精度版）
     */
//    public static void sample(AipOcr client) {
//        // 传入可选参数调用接口
//        HashMap<String, String> options = new HashMap<String, String>();
//        options.put("detect_direction", "true");
//        options.put("probability", "true");
//
//        // 方法一：参数为本地路径
//        String image = "C:\\Users\\Administrator\\Desktop\\photo\\1.jpg";
//        JSONObject res = client.basicAccurateGeneral(image, options);
//        System.out.println(res.toString(2));
//
//        // 方法二：参数为二进制数组
////        byte[] file = readImageFile("C:\\Users\\Administrator\\Desktop\\photo\\1.jpg");
////        res = client.basicAccurateGeneral(file, options);
////        System.out.println(res.toString(2));
//
//    }

    //图片到byte数组
    public static byte[] readImageFile(String path){
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        }
        catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        }
        catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

}
