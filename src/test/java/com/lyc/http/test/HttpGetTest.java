package com.lyc.http.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HttpGetTest
{
	public static void main(String[] args)
	{
		new Thread(new Runnable() {
			// http://fanyi.youdao.com/openapi.do?keyfrom=fantuandai&key=344543253&type=data&doctype=<doctype>&version=1.1&q=要翻译的文本
			@Override
			public void run()
			{
				//JDK api 访问Http
				BufferedReader reader = null;
				InputStream is = null;
				URLConnection urlCon = null;
				URL url = null;
				try
				{
					url = new URL(
							"http://fanyi.youdao.com/openapi.do?keyfrom=fantuandai&key=344543253&type=data&doctype=json&version=1.1&q=stream");
					urlCon = url.openConnection();
					is = urlCon.getInputStream();
					reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
					String line = null;
					while (null != (line = reader.readLine()))
					{
						System.out.println(line);
					}
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
