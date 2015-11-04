package com.lyc.http.test;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientGetTest
{

	public static void main(String[] args)
	{
		new Thread(new Runnable() {
			
			@Override
			public void run()
			{
				HttpClient httpClient = HttpClients.createDefault(); 
				HttpGet get = new HttpGet("http://www.baidu.com");
				
				try
				{
					HttpResponse response = httpClient.execute(get);
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
		}).start();

	}

}
