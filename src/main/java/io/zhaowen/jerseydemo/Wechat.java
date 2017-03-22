package io.zhaowen.jerseydemo;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.Map;

/**
 * Created by wen on 3/19/17.
 */

@Path("/weixin")
public class Wechat {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String initialAuthenticate(@Context UriInfo uriInfo) {
        Map<String, String> map = SignUtil.getQueryMap(uriInfo.getRequestUri().getQuery());
        String signature = map.get("signature");
        String timestamp = map.get("timestamp");
        String nonce = map.get("nonce");
        String echostr = map.get("echostr");
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return null;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String reply(@Context UriInfo uriInfo, String rawRequest) {

        Map<String, String> map = XmlHelperUtils.toMap(rawRequest);

        String response = "<xml>\n" +
                "<ToUserName><![CDATA["+map.get("FromUserName")+"]]></ToUserName>\n" +
                "<FromUserName><![CDATA["+map.get("ToUserName")+"]]></FromUserName>\n" +
                "<CreateTime>"+Long.toString(System.currentTimeMillis() / 1000)+"</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA["+"Hello Wechat!"+"]]></Content>\n" +
                "</xml>";

        return response;
    }
}
