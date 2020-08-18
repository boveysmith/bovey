package com.ynrd.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class URLConnection
{

        /**
         * 处理URL请求
         * @param url 请求URL
         * @param method 请求方式 GET POST DELETE PUT 请求参数
         * @return result
         */

        public static String processRequest(String url, String method, JSONObject urlParam)
        {
            return processRequest(url, method, urlParam, new JSONObject());
        }

        /**
         * 处理URL请求
         * 
         * @param url 请求URL
         * @param method 请求方式 GET POST DELETE PUT
         * @param urlParam 请求参数，拼接在url后
         * @param param 请求参数            
         * @return result
         */
        public static String processRequest(String url, String method, JSONObject urlParam, JSONObject param)
        {
            String result = null;
            HttpURLConnection httpConnection = null;
            try {
                StringBuffer urlAppend = null;
                if (urlParam != null)
                {
                    urlAppend = new StringBuffer("?");
                    Set<String> keySet = urlParam.keySet();
                    for (String key : keySet)
                    {
                        urlAppend.append(key);
                        urlAppend.append("=");
                        urlAppend.append(urlParam.get(key));
                        urlAppend.append("&");
                    }
                }
                if(urlAppend != null)
                {
                    url += urlAppend.substring(0, urlAppend.length() - 1);
                }
                URL restServiceURL = new URL(url);
                httpConnection = (HttpURLConnection) restServiceURL.openConnection();
                // method 输入小写，转换成 GET POST DELETE PUT
                httpConnection.setRequestMethod(method.toUpperCase());
                if(param.getString("token") != null)
                {
                    httpConnection.setRequestProperty("Cookie", "JSESSIONID=" + param.getString("token"));
                }
                String accept = param.getString("Accept") == null ? "application/json" : param.getString("Accept");
                String contentType = param.getString("Content-Type") == null ? "application/json" : param.getString("Content-Type");
                httpConnection.setRequestProperty("Accept", accept);
                httpConnection.setRequestProperty("Content-Type", contentType);
                // setConnectTimeout：设置连接主机超时（单位：毫秒）
                httpConnection.setConnectTimeout(3*1000);
                // setReadTimeout：设置从主机读取数据超时（单位：毫秒）
//            httpConnection.setReadTimeout(3*1000);
                //得到响应码  
                int responseCode = httpConnection.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK)
                {
                    throw new RuntimeException("请求失败，错误码: "
                            + httpConnection.getResponseCode() + "错误信息："
                            + httpConnection.getResponseMessage());
                }
                StringBuffer sb = new StringBuffer();
                String readLine = new String();
                //得到响应流  
                InputStream inputStream = httpConnection.getInputStream();
                //获取响应  
                BufferedReader responseReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                while ((readLine = responseReader.readLine()) != null)
                {
                    sb.append(readLine);
                }
                result = sb.toString();

            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                httpConnection.disconnect();
            }
            return result;
        }
}
