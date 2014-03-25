package fr.gfi.android.quiz.webservices;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import android.util.Log;
import fr.gfi.quiz.json.entite.Quiz;
import fr.gfi.quiz.json.moteur.JsonBuilder;



public class MyRestClient {
	private ArrayList<NameValuePair> _params = null;
	private ArrayList<NameValuePair> _headers = null;
	private String _url_to_send = null;
	private int _responseCode;
	private String _response;
	private String _message;

	public MyRestClient(String url) {
		try {
			this._url_to_send = url;
			_params = new ArrayList<NameValuePair>();
			_headers = new ArrayList<NameValuePair>();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<NameValuePair> get_params() {
		return _params;
	}
	public void set_params(ArrayList<NameValuePair> _params) {
		this._params = _params;
	}
	public ArrayList<NameValuePair> get_headers() {
		return _headers;
	}
	public void set_headers(ArrayList<NameValuePair> _headers) {
		this._headers = _headers;
	}
	public int get_responseCode() {
		return _responseCode;
	}
	public void set_responseCode(int _responseCode) {
		this._responseCode = _responseCode;
	}
	public String get_response() {
		return _response;
	}
	public void set_response(String _response) {
		this._response = _response;
	}
	public String get_message() {
		return _message;
	}
	public void set_message(String _message) {
		this._message = _message;
	}


	public String Execute(RequestMethod method) throws Exception {
		String combinedParams = "";
		if (!_params.isEmpty()) {
			combinedParams += "?";
			for (NameValuePair p : _params) {
				String paramString = p.getName() + "="
						+ URLEncoder.encode(p.getValue(), "UTF-8");

				if (combinedParams.length() > 1) {
					combinedParams += "&" + paramString;
				} else {
					combinedParams += paramString;
				}
			}
		}
		String finalUrl = _url_to_send;
		HttpRequestBase request = null;
		if(method == RequestMethod.GET){
			request = new HttpGet(finalUrl);
		}else{
			request = new HttpPost(finalUrl);
		}
		Log.i("URL TO SEND", finalUrl);
		return executeRequest(request);
	}


	private String executeRequest(HttpUriRequest request) {
		DefaultHttpClient client = getClient();

		try {

			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			_response = EntityUtils.toString(entity);
			Log.i("JSON RESPONSE", _response);
			return _response;

		} catch (ClientProtocolException e) {
			Log.e("MyRestClient.executeRequest()", "erreur de protocol");
			client.getConnectionManager().shutdown();
			e.printStackTrace();
			return "";

		} catch (IOException e) {
			Log.e("MyRestClient.executeRequest()", "erreur d'IO");
			client.getConnectionManager().shutdown();
			e.printStackTrace();
			return "";
		}
	}

	public boolean postJson(Quiz quiz){
		DefaultHttpClient client = getClient();
		HttpPost post = new HttpPost(_url_to_send);
		try  {
			post.setEntity(new StringEntity(JsonBuilder.getJsonStringFromBean(quiz)));
			post.setHeader("Content-Type", "application/json; charset=utf-8");
			Log.i("JSON ENVOI", post.toString());
			HttpResponse response = client.execute(post);
			if(response != null){
				if (response.getStatusLine().getStatusCode() == 200) {
					Log.i("JSON ENVOI", "OK");
					return true;
				}else{
					Log.i("JSON ENVOI", "KO : " +response.getStatusLine().getStatusCode());
					return false;
				}	
			}else{
				return false;
			}
			
		}catch (ClientProtocolException e) {
			Log.e("MyRestClient.postJson()", "erreur de protocol");
			client.getConnectionManager().shutdown();
			e.printStackTrace();
			return false;

		} catch (IOException e) {
			Log.e("MyRestClient.postJson()", "erreur d'IO");
			client.getConnectionManager().shutdown();
			e.printStackTrace();
			return false;
		}

	}

	private DefaultHttpClient getClient() {

		DefaultHttpClient ret = null;
		try {
			// sets up parameters
			final HttpParams params = new BasicHttpParams();

			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, "utf-8");
			params.setBooleanParameter("http.protocol.expect-continue", false);

			int timeoutConnection = 10000;
			HttpConnectionParams
			.setConnectionTimeout(params, timeoutConnection);

			int timeoutSocket = 10000;
			HttpConnectionParams.setSoTimeout(params, timeoutSocket);

			// registers schemes for both http and https
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			final SSLSocketFactory sslSocketFactory = SSLSocketFactory
					.getSocketFactory();
			sslSocketFactory
			.setHostnameVerifier(SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			registry.register(new Scheme("https", sslSocketFactory, 443));

			ThreadSafeClientConnManager manager = new ThreadSafeClientConnManager(
					params, registry);
			ret = new DefaultHttpClient(manager, params);
			return ret;
		} catch (Exception e) {
			TacheAppelsReseaux.canGetConnected = false;
		}
		return ret;
	}

	public enum RequestMethod{
		GET,POST;
	}


}
