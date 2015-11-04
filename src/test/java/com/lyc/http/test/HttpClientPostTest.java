package com.lyc.http.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientPostTest
{
public static void main(String[] args)
{
	// http://fanyi.youdao.com/openapi.do?
	//	keyfrom=fantuandai&key=344543253&type=data&doctype=<doctype>&version=1.1&q=要翻译的文本
	new Thread(new Runnable() {
		
		@Override
		public void run()
		{
			HttpClient httpClient = HttpClients.createDefault();
			HttpPost post = new HttpPost("http://fanyi.youdao.com/openapi.do");
			try
			{
				List<NameValuePair> keyValue = new ArrayList<NameValuePair>();
				keyValue.add(new BasicNameValuePair("keyfrom", "fantuandai"));
				keyValue.add(new BasicNameValuePair("key", "344543253"));
				keyValue.add(new BasicNameValuePair("type", "data"));
				keyValue.add(new BasicNameValuePair("doctype", "json"));
				keyValue.add(new BasicNameValuePair("version", "1.1"));
				keyValue.add(new BasicNameValuePair("q", "json"));
				post.setEntity(new UrlEncodedFormEntity(keyValue));
				
				HttpResponse response = httpClient.execute(post);
				HttpEntity entity = response.getEntity();
				System.out.println(EntityUtils.toString(entity, "UTF-8"));
			}
			catch (ClientProtocolException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
		}
	}, "Thread -  Http Client POST").start();
}
}
