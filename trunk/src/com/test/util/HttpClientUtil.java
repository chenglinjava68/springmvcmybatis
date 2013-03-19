package com.test.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public final class HttpClientUtil {

	private static HttpClient instance;
	
	static {
		HttpParams params = new BasicHttpParams();//连接参数设置
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);//协议版本
		HttpProtocolParams.setContentCharset(params, "UTF-8");//编码方式
		HttpProtocolParams.setUseExpectContinue(params, true);
		HttpProtocolParams.setUserAgent(params, "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.52 Safari/537.17");
		HttpConnectionParams.setConnectionTimeout(params, 3000);//连接超时
		HttpConnectionParams.setSoTimeout(params, 3000);//读取数据超时
		ClientConnectionManager conman = new PoolingClientConnectionManager();//连接管理设置
		instance = new DefaultHttpClient(conman, params);
	}
	
	public static final HttpClient getHttpClient(){
		return instance;
	}
	
	public static final String sendAndReceive(HttpUriRequest req) {
		try {
			HttpEntity entity = getHttpClient().execute(req).getEntity();
			String str = EntityUtils.toString(entity, "UTF-8");
			EntityUtils.consume(entity);
			return str;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			req.abort();
		}
		return null;
	}
}