package com.ynrd.api;

import com.alibaba.fastjson.JSONObject;
import com.ynrd.util.URLConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class ApiController {

    /**
     * 抖音去水印视频连接解析
     * @param orgUrl 抖音复制出的分享链接
     * @return 该处返回的连接可以使用手机浏览器打开，直接用电脑浏览器打不开
     */
    @RequestMapping(value = "/api/getPlayUrl", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public JSONObject getPalyUrl(@RequestParam("orgUrl") String orgUrl){
        JSONObject resObj = new JSONObject();
        try {
            URL Url = new URL(orgUrl);
            HttpURLConnection conn=(HttpURLConnection)Url.openConnection();
            conn.getResponseCode();
            String realUrl = conn.getURL().toString();//跳转后所返回的链接
            String url = "https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=" + realUrl.split("/")[5];
            String reqObj = URLConnection.processRequest(url, "get", null);
            JSONObject object = (JSONObject) JSONObject.parse(reqObj);
            JSONObject itemObj = (JSONObject) object.getJSONArray("item_list").get(0);
            JSONObject videoObj = (JSONObject) itemObj.get("video");
            JSONObject playObj = (JSONObject) videoObj.get("play_addr");
            String palyUrl = ((String) playObj.getJSONArray("url_list").get(0)).replace("/playwm/?", "/play/?");
            resObj.put("status", "1");
            resObj.put("url", palyUrl);
            resObj.put("msg", "success");
            return resObj;
        }catch (Exception e){
            resObj.put("status", "0");
            resObj.put("url", null);
            resObj.put("msg", "fail");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orgUrl", "https://v.douyin.com/JLFcD7V");

        String url = "http://47.105.244.104:8888/springboot/api/getPlayUrl";
        String reqObj = URLConnection.processRequest(url, "get", jsonObject);
        System.out.println(reqObj);
    }
}
