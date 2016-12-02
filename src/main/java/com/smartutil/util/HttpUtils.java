package com.smartutil.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Map.Entry;

/**
 * http工具类
 */
@SuppressWarnings("deprecation")
public class HttpUtils {
	private static MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
	private static HttpClient client = new HttpClient(connectionManager);
	static {
		client.getHttpConnectionManager().getParams().setConnectionTimeout(30 * 1000);
		client.getHttpConnectionManager().getParams().setMaxConnectionsPerHost(client.getHostConfiguration(),2000);
		client.getHttpConnectionManager().getParams().setDefaultMaxConnectionsPerHost(2000);
		client.getHttpConnectionManager().getParams().setMaxTotalConnections(2000);
		client.getHttpConnectionManager().getParams().setSoTimeout(30 * 1000);
		client.setTimeout(30 * 1000);
		client.getParams().setConnectionManagerTimeout(5*1000);
	}

	/**
	 * 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param xml
	 *            待发送内容。
	 * @param sendCode
	 *            待发送内容编码格式 默认UTF-8。
	 * @param reCode
	 *            响应内容读取编码格式 默认UTF-8。
	 * @return 所代表远程资源的响应结果
	 */

	public static String sendXML(String url, String xml, String sendCode, String reCode) throws Exception {
		String responseString = null;
		int i = 0;
		while (true) {
			if (responseString != null)
				break;
			try {
				i++;
				responseString = HttpUtils.httpPostBody(url, xml, sendCode, reCode);
			} catch (IllegalStateException e) {
				Thread.sleep(2000);
				if (i >= 3) {
					throw e;
				}
			} catch (Exception e) {
				Thread.sleep(2000);
				if (i >= 3) {
					throw e;
				}
			}
		}
		return responseString;
	}

	public static String httpPostBody(String url, String body, String writerCharset, String readerCharset) throws Exception {
		String responseBody = null;
		if (writerCharset == null) {
			writerCharset = "UTF-8";
		}
		if (readerCharset == null) {
			readerCharset = "UTF-8";
		}

		PostMethod method = new PostMethod(url);
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, writerCharset);
		method.setRequestBody(body);

		int status = client.executeMethod(method);
		if (status != HttpStatus.SC_OK) {
			throw new IllegalStateException("Method failed: " + method.getStatusLine());
		}
		responseBody = IOUtils.toString(method.getResponseBodyAsStream(), readerCharset);
		method.releaseConnection();
		return responseBody;
	}

	public static String httpPost(String url, Map<String, String> paras, String writerCharset, String readerCharset) throws Exception {
		String responseBody = null;
		if (writerCharset == null) {
			writerCharset = "UTF-8";
		}
		if (readerCharset == null) {
			readerCharset = "UTF-8";
		}
		PostMethod method = new PostMethod(url);
		if (paras != null) {
			for (Entry<String, String> entry : paras.entrySet()) {
				method.setParameter(entry.getKey(), entry.getValue().toString());
			}
		}
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, writerCharset);

		int status = client.executeMethod(method);
		if (status != HttpStatus.SC_OK) {

			throw new IllegalStateException("Method failed: " + method.getStatusLine());
		}

		responseBody = IOUtils.toString(method.getResponseBodyAsStream(), readerCharset);
		method.releaseConnection();
		return responseBody;
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @return URL所代表远程资源的响应
	 * @throws Exception
	 */

	public static String sendGet(String url) throws Exception {
		String result = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 建立实际的连接
			conn.connect();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		} catch (Exception e) {
			throw new Exception("发送GET请求出现异常！", e);
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 * @throws Exception
	 */
	public static String sendPost(String url, String param) throws Exception {
		PrintWriter out = null;
		BufferedReader in = null;
		HttpURLConnection conn = null;
		StringBuffer result = new StringBuffer();
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			conn = (HttpURLConnection) realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			// int len = -1;
			// char[] c = new char[512];
			// while ((len = in.read(c)) != -1) {
			// result.append(c, 0, len);
			// }
		} catch (IOException e) {
			throw new Exception("发送 POST 请求出现异常！", e);
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result.toString();
	}

	/******
	 * @param strURL
	 *            发送地址
	 * @param params
	 *            发送的参数
	 * 
	 * ******/
	public static String sendMessage(String strURL, Map<String, String> params) {
		PostMethod httpPost = null;
		try {
			client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			httpPost = new PostMethod(strURL);
			if (!params.isEmpty()) {
				int i = 0;
				NameValuePair[] data = new NameValuePair[params.size()];
				for (Entry<String, String> entry : params.entrySet()) {
					data[i] = new NameValuePair(entry.getKey(), entry.getValue());
					i++;
				}
				httpPost.setRequestBody(data);
			}
			String output1 = "";
			client.executeMethod(httpPost);
			output1 = getResponseBodyAsString(httpPost);
			return output1;
		} catch (HttpException e) {
			e.printStackTrace();
			return "Read timed out";
		} catch (IOException e) {
			e.printStackTrace();
			return "Read timed out";
		} catch (Exception e) {
			e.printStackTrace();
			return "Read timed out";
		} finally {
			httpPost.releaseConnection();
		}
	}
	private static String getResponseBodyAsString(PostMethod httpPost) {
		InputStream resStream = null;
		BufferedReader br = null;;
		try {
			resStream = httpPost.getResponseBodyAsStream();
			br = new BufferedReader(new InputStreamReader(resStream));
			StringBuffer resBuffer = new StringBuffer();
			String resTemp = "";
			while ((resTemp = br.readLine()) != null) {
				resBuffer.append(resTemp);
			}
			String response = resBuffer.toString();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (resStream != null) {
				try {
					resStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}


	public static String sendJSON(String urlString, JSONObject jsonObject) {

		BufferedReader reader = null;
		HttpURLConnection connection = null;
		try {
			// 创建连接
			URL url = new URL(urlString);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/json");

			connection.connect();

			// POST请求
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(jsonObject.toString());
			out.flush();
			out.close();

			// 读取响应
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String lines;
			StringBuffer sb = new StringBuffer("");
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sb.append(lines);
			}
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;

	}
}
