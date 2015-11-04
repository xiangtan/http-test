package com.lyc.http.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpPostTest
{
	// http://fanyi.youdao.com/openapi.do?keyfrom=fantuandai&key=344543253&type=data&doctype=<doctype>&version=1.1&q=要翻译的文本
	public static void main(String[] args)
	{
		new Thread(new Runnable() {

			@Override
			public void run()
			{
				// JDK api Post 方式访问Http
				URL url = null;
				BufferedReader reader = null;
				InputStream is = null;
				URLConnection urlCon = null;
				HttpURLConnection httpUrlCon =null;
				OutputStream os =null;
				BufferedWriter writer = null;
				try
				{
					url = new URL("http://fanyi.youdao.com/openapi.do");
					urlCon = url.openConnection();//打开互联网url链接
					httpUrlCon =(HttpURLConnection) urlCon; //使用post 需要HttpURLConnection
					httpUrlCon.setRequestMethod("POST");//访问方式
					httpUrlCon.setDoInput(true);//从网络获取数据
					httpUrlCon.setDoOutput(true);//向网络传输数据
					httpUrlCon.addRequestProperty("encoding", "UTF-8");//编码
					
					os = httpUrlCon.getOutputStream();
					writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
					writer.write("keyfrom=fantuandai&key=344543253&type=data&doctype=json&version=1.1&q=stream");
					writer.flush();
					
					is = httpUrlCon.getInputStream();
					reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
					String line = null;
					while (null != (line = reader.readLine()))
					{
						System.out.println(line);
					}
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
					try
					{
						if (is != null)
						{
							is.close();
						}
						if (reader != null)
						{
							reader.close();
						}
						if (os != null)
						{
							os.close();
						}
						if (writer != null)
						{
							writer.close();
						}
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
