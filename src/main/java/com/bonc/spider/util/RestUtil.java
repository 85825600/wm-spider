package com.bonc.spider.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * REST请求工具类
 * 
 * @author andy
 * @date 2016-8-31
 * 
 * @since 1.0
 */
public class RestUtil {
	
	private static final Integer TIMEOUT = 10;
	private static final String METHOD = "GET";
	private static final String CONTENT_TYPE__DEFAULT = "application/json";
	private static final String CONTENT_TYPE__JSON = "application/json";
	private static final String CONTENT_TYPE__X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
	private static final String UTF_8 = "UTF-8";
	
	private static final Integer OK_CODE = 200;
	private static final Integer PERMANENTLY_MOVED_CODE = 301;
	private static final Integer TEMPORARILY_MOVED_CODE = 302;
	@SuppressWarnings("serial")
	private static final Set<Integer> OK_CODE_SET = new HashSet<Integer>(){
		{
			add(OK_CODE);
			add(PERMANENTLY_MOVED_CODE);
			add(TEMPORARILY_MOVED_CODE);
		}
	};
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject send(String uri) {
		JSONObject body = new JSONObject();
		return send(uri, METHOD, body);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param timeout 请求超时时长
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject send(String uri, Integer timeout) {
		JSONObject body = new JSONObject();
		return send(uri, METHOD, body, timeout);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param method 请求类型
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject send(String uri, String method) {
		JSONObject body = new JSONObject();
		return send(uri, method, body);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param method 请求类型
	 * @param timeout 请求超时时长
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject send(String uri, String method, Integer timeout) {
		JSONObject body = new JSONObject();
		return send(uri, method, body, timeout);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param method 请求类型
	 * @param body 请求数据
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject send(String uri, String method, JSONObject body) {
		Map<String, String> header = new HashMap<String, String>();
		return send(uri, method, header, body);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param method 请求类型
	 * @param body 请求数据
	 * @param timeout 请求超时时长
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject send(String uri, String method, JSONObject body, Integer timeout) {
		Map<String, String> header = new HashMap<String, String>();
		return send(uri, method, CONTENT_TYPE__DEFAULT, header, body, timeout);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param method 请求类型
	 * @param header 请求头
	 * @param body 请求数据
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject send(String uri, String method, Map<String, String> header, JSONObject body) {
		return send(uri, method, CONTENT_TYPE__DEFAULT, header, body, TIMEOUT*1000);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param method 请求类型
	 * @param contentType 内容格式
	 * @param header 请求头
	 * @param body 请求数据
	 * @param timeout 请求超时时长
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject send(String uri, String method, String contentType, Map<String, String> header, JSONObject body, Integer timeout) {
		JSONObject result = null;
		URL url = null;
		OutputStream os = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			// 请求路径地址
			url = new URL(uri);
			// 打开连接
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
			// 设置连接超时时间
			httpUrlConnection.setConnectTimeout(timeout);
			// 设置向httpUrlConnection输出
			httpUrlConnection.setDoOutput(true);
			// 设置从httpUrlConnection读入
			httpUrlConnection.setDoInput(true);
			// 设置请求类型
			httpUrlConnection.setRequestMethod(method);
			// 设置请求内容封装格式
			httpUrlConnection.setRequestProperty(HTTP.CONTENT_TYPE, contentType);
			// 封装请求头信息
			Set<String> keySet = header.keySet();
			for (String key : keySet) {
				httpUrlConnection.setRequestProperty(key, header.get(key));
			}
			osw = new OutputStreamWriter(httpUrlConnection.getOutputStream());
			bw = new BufferedWriter(osw);
			bw.write(body.toString());
			bw.flush();
			// 判断请求连接状态是否正常
			if (OK_CODE_SET.contains(httpUrlConnection.getResponseCode())) {
				is = httpUrlConnection.getInputStream();
				isr = new InputStreamReader(is, UTF_8);
				br = new BufferedReader(isr);
				String line = null;
				StringBuffer res = new StringBuffer();
				// 获取返回结果
				while ((line = br.readLine()) != null) {
					res.append(line);
				}
				// 将返回结果封装成返回值
				if(res.length() != 0){
					result = (JSONObject) JSONObject.parse(res.toString());
				}
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (osw != null) {
					osw.close();
				}
				if (bw != null) {
					bw.close();
				}
				if (is != null) {
					is.close();
				}
				if (isr != null) {
					isr.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param body 请求数据
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject post(String uri, JSONObject body) {
		Map<String, String> header = new HashMap<String, String>();
		List<Cookie> cookies = new ArrayList<Cookie>();
		return post(uri, header, cookies, body);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param body 请求数据
	 * @param timeout 请求超时时长
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject post(String uri, JSONObject body, Integer timeout) {
		Map<String, String> header = new HashMap<String, String>();
		List<Cookie> cookies = new ArrayList<Cookie>();
		return post(uri, header, cookies, body, timeout);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param header 请求头
	 * @param cookies Cookies
	 * @param body 请求数据
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject post(String uri, Map<String, String> header, List<Cookie> cookies, JSONObject body) {
		return post(uri, CONTENT_TYPE__DEFAULT, header, cookies, body);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param header 请求头
	 * @param cookies Cookies
	 * @param body 请求数据
	 * @param timeout 请求超时时长
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject post(String uri, Map<String, String> header, List<Cookie> cookies, JSONObject body, Integer timeout) {
		return post(uri, CONTENT_TYPE__DEFAULT, header, cookies, body, timeout);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param contentType 内容格式
	 * @param header 请求头
	 * @param cookies Cookies
	 * @param body 请求数据
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject post(String uri, String contentType, Map<String, String> header, List<Cookie> cookies, JSONObject body) {
		return post(uri, contentType, header, cookies, body, TIMEOUT*1000);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param contentType 内容格式
	 * @param header 请求头
	 * @param cookies Cookies
	 * @param body 请求数据
	 * @param timeout 请求超时时长
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject post(String uri, String contentType, Map<String, String> header, List<Cookie> cookies, JSONObject body, Integer timeout) {
		JSONObject result = null;
		try {
			// 请求路径地址
			HttpPost httpPost = new HttpPost(uri);
			
			// 封装请求头信息
			Set<String> keySet = header.keySet();
			for (String key : keySet) {
				httpPost.addHeader(key, header.get(key));
			}
			
			// 封装请求体信息
			switch(contentType.trim().toLowerCase()){
			case CONTENT_TYPE__X_WWW_FORM_URLENCODED:
				List<NameValuePair> nvps = new ArrayList <NameValuePair>();
				Set<String> set = body.keySet();
				for(String key : set){
					nvps.add(new BasicNameValuePair(key, (String)body.get(key)));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, UTF_8));
				break;
			case CONTENT_TYPE__JSON:
			default:
				StringEntity stringEntity = new StringEntity(body.toString(), UTF_8);
				stringEntity.setContentEncoding(UTF_8);
				stringEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE__JSON));
				httpPost.setEntity(stringEntity);
				break;
			}
			
			final HttpClient httpClient = new DefaultHttpClient();
			CookieStore cookieStore = ((AbstractHttpClient)httpClient).getCookieStore();
			for(Cookie cookie : cookies){
				cookieStore.addCookie(cookie);
			}
			// 服务器返回Cookie
			HttpClientParams.setCookiePolicy(httpClient.getParams(), CookiePolicy.BROWSER_COMPATIBILITY);
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
			ResponseHandler<JSONObject> handler = new ResponseHandler<JSONObject>() {
				public JSONObject handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
					if (OK_CODE_SET.contains(response.getStatusLine().getStatusCode())) {
						JSONObject res = new JSONObject();
						List<Cookie> cookies = ((AbstractHttpClient)httpClient).getCookieStore().getCookies();
						Header[] headers = response.getAllHeaders();
						Map<String, String> headerMap = new HashMap<String, String>();
						for(int i=0; i<headers.length; i++){
							String value = headerMap.get(headers[i].getName());
							if(EmptyUtil.isNotEmpty(value)){
								headerMap.put(headers[i].getName(), value+"|||"+headers[i].getValue());
							} else {
								headerMap.put(headers[i].getName(), headers[i].getValue());
							}
						}
						HttpEntity entity = response.getEntity();
						String body = "";
						if (entity != null) {
							body = EntityUtils.toString(entity);
						}
						res.put("cookies", cookies);
						res.put("headers", headerMap);
						res.put("body", body);
						return res;
					}
					return null;
				}
			};
			result = httpClient.execute(httpPost, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject get(String uri) {
		Map<String, String> params = new HashMap<String, String>();
		return get(uri, params);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param timeout 请求超时时长
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject get(String uri, Integer timeout) {
		Map<String, String> params = new HashMap<String, String>();
		return get(uri, params, timeout);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param params 请求参数
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject get(String uri, Map<String, String> params) {
		Map<String, String> header = new HashMap<String, String>();
		List<Cookie> cookies = new ArrayList<Cookie>();
		return get(uri, header, cookies, params);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param params 请求参数
	 * @param timeout 请求超时时长
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject get(String uri, Map<String, String> params, Integer timeout) {
		Map<String, String> header = new HashMap<String, String>();
		List<Cookie> cookies = new ArrayList<Cookie>();
		return get(uri, header, cookies, params, timeout);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param header 请求头
	 * @param cookies Cookies
	 * @param params 请求参数
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject get(String uri, Map<String, String> header, List<Cookie> cookies, Map<String, String> params) {
		return get(uri, CONTENT_TYPE__DEFAULT, header, cookies, params);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param header 请求头
	 * @param cookies Cookies
	 * @param params 请求参数
	 * @param timeout 请求超时时长
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject get(String uri, Map<String, String> header, List<Cookie> cookies, Map<String, String> params, Integer timeout) {
		return get(uri, CONTENT_TYPE__DEFAULT, header, cookies, params, timeout);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param contentType 内容格式
	 * @param header 请求头
	 * @param cookies Cookies
	 * @param params 请求参数
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject get(String uri, String contentType, Map<String, String> header, List<Cookie> cookies, Map<String, String> params) {
		return get(uri, contentType, header, cookies, params, TIMEOUT*1000);
	}
	
	/**
	 * 发送方法
	 * 
	 * @param uri 请求地址
	 * @param contentType 内容格式
	 * @param header 请求头
	 * @param cookies Cookies
	 * @param params 请求参数
	 * @param timeout 请求超时时长
	 * @return JSONObject
	 * 
	 * @since 1.0
	 */
	public static JSONObject get(String uri, String contentType, Map<String, String> header, List<Cookie> cookies, Map<String, String> params, Integer timeout) {
		JSONObject result = null;
		
		try {
			// 请求路径地址
			HttpGet httpGet = new HttpGet(uri);
			
			// 封装请求头信息
			Set<String> keySet = header.keySet();
			for (String key : keySet) {
				httpGet.addHeader(key, header.get(key));
			}
			
			// 封装请求体信息
			HttpParams httpParams = new BasicHttpParams();
			keySet = params.keySet();
			for (String key : keySet) {
				httpParams.setParameter(key, params.get(key));
			}
			httpGet.setParams(httpParams);
			
			final HttpClient httpClient = new DefaultHttpClient();
			CookieStore cookieStore = ((AbstractHttpClient)httpClient).getCookieStore();
			for(Cookie cookie : cookies){
				cookieStore.addCookie(cookie);
			}
			// 服务器返回Cookie
			HttpClientParams.setCookiePolicy(httpClient.getParams(), CookiePolicy.BROWSER_COMPATIBILITY);
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, timeout);
			ResponseHandler<JSONObject> handler = new ResponseHandler<JSONObject>() {
				public JSONObject handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
					if (OK_CODE_SET.contains(response.getStatusLine().getStatusCode())) {
						JSONObject res = new JSONObject();
				        List<Cookie> cookies = ((AbstractHttpClient)httpClient).getCookieStore().getCookies();
						Header[] headers = response.getAllHeaders();
						Map<String, String> headerMap = new HashMap<String, String>();
						for(int i=0; i<headers.length; i++){
							String value = headerMap.get(headers[i].getName());
							if(EmptyUtil.isNotEmpty(value)){
								headerMap.put(headers[i].getName(), value+"|||"+headers[i].getValue());
							} else {
								headerMap.put(headers[i].getName(), headers[i].getValue());
							}
						}
						HttpEntity entity = response.getEntity();
						String body = "";
						if (entity != null) {
							body = EntityUtils.toString(entity);
						}
						res.put("cookies", cookies);
						res.put("headers", headerMap);
						res.put("body", body);
						return res;
					}
					return null;
				}
			};
			result = httpClient.execute(httpGet, handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
