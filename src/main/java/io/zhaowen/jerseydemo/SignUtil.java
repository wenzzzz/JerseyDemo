package io.zhaowen.jerseydemo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wen on 3/19/17.
 */
public class SignUtil {
    public static final String TOKEN = ""; // 输入微信后台设置的Token值

    /**
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     * @Author:lulei
     * @Description: 微信权限验证
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[] { TOKEN, timestamp, nonce };
        //按字典排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        //加密并返回验证结果
        return signature == null ? false : signature.equals(Encrypt.encrypt(content.toString(), "SHA-1"));
    }

    public static Map<String, String> getQueryMap(String query)
    {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<String, String>();
        for (String param : params)
        {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }


}