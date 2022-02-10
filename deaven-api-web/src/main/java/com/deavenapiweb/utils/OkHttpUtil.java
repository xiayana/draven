package com.deavenapiweb.utils;

import okhttp3.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static okhttp3.MultipartBody.FORM;

/**
 * OkHttpUtil
 *
 * @author xy
 * @since 2021/7/2 16:58
 */
public class OkHttpUtil {

    public static final int READ_TIMEOUT = 100;
    public static final int CONNECT_TIMEOUT = 60;
    public static final int WRITE_TIMEOUT = 60;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType XML = MediaType.parse("application/xml; charset=utf-8");
    private static final byte[] LOCKER = new byte[0];
    private static final OkHttpUtil mInstance = new OkHttpUtil();
    private OkHttpClient mOkHttpClient;
    //证书类型
    private static final String KEY_STORE_TYPE_P12 = "PKCS12";
    //ca.p12证书密码（客户端证书密码）
    private static final String KEY_STORE_PASSWORD = "csrysd200628";

    private OkHttpUtil() {
    }

    private OkHttpClient getOkHttpClient() throws Exception {
        if (mOkHttpClient != null) {
            return mOkHttpClient;
        }
        // 启用https, 客户端证书(双向认证，需银行提供客户端证书)
        // KeyStore keyStore = KeyStore.getInstance(KEY_STORE_TYPE_P12);
        // keyStore.load(new FileInputStream("ca.p12"), KEY_STORE_PASSWORD.toCharArray());

        // KeyManagerFactory证书管理类
        // KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        // keyManagerFactory.init(keyStore, KEY_STORE_PASSWORD.toCharArray());

        TrustManager[] trustManagers = new TrustManager[]{new TrustAllCerts()};

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagers, new SecureRandom());

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        //读取超时
        clientBuilder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS);
        //连接超时
        clientBuilder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        //写入超时
        clientBuilder.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        //支持HTTPS请求，跳过证书验证
        clientBuilder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManagers[0]);
        clientBuilder.hostnameVerifier((hostname, session) -> true);
        return mOkHttpClient = clientBuilder.build();
    }

    /**
     * 单例模式获取OkHttpUtil
     *
     * @return
     */
    public static OkHttpUtil getInstance() {
        return mInstance;
    }

    /**
     * get请求
     *
     * @param url
     * @return
     */
    public String getData(String url) throws Exception {
        //1 构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(url).build();
        Response response = getOkHttpClient().newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * get请求
     *
     * @param url
     * @return
     */
    public String getData(String url, Map<String, String> header) throws Exception {
        //1 构造Request
        Request.Builder builder = new Request.Builder();
        if (!header.isEmpty()) {
            for (Map.Entry<String, String> stringStringEntry : header.entrySet()) {
                builder.header(stringStringEntry.getKey(), stringStringEntry.getValue());
            }
        }
        Request request = builder.get().url(url).build();
        Response response = getOkHttpClient().newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }


    /**
     * delete
     *
     * @param url
     * @return
     */
    public String delete(String url, Map<String, String> header) throws Exception {
        //1 构造Request
        Request.Builder builder = new Request.Builder();
        if (!header.isEmpty()) {
            for (Map.Entry<String, String> stringStringEntry : header.entrySet()) {
                builder.header(stringStringEntry.getKey(), stringStringEntry.getValue());
            }
        }
        Request request = builder.delete().url(url).build();
        Response response = getOkHttpClient().newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }


    /**
     * put
     *
     * @param url
     * @return
     */
    public String putJson(String url, String json, Map<String, String> header) throws Exception {
        RequestBody body = RequestBody.create(JSON, json);
        //1 构造Request
        Request.Builder builder = new Request.Builder();
        if (!header.isEmpty()) {
            for (Map.Entry<String, String> stringStringEntry : header.entrySet()) {
                builder.header(stringStringEntry.getKey(), stringStringEntry.getValue());
            }
        }
        Request request = builder.put(body).url(url).build();
        Response response = getOkHttpClient().newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * put
     *
     * @param url
     * @return
     */
    public String putData(String url, byte[] content, Map<String, String> header) throws Exception {
        RequestBody body = RequestBody.create(FORM, content);
        //1 构造Request
        Request.Builder builder = new Request.Builder();
        if (!header.isEmpty()) {
            for (Map.Entry<String, String> stringStringEntry : header.entrySet()) {
                builder.header(stringStringEntry.getKey(), stringStringEntry.getValue());
            }
        }
        Request request = builder.put(body).url(url).build();
        Response response = getOkHttpClient().newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * post请求，同步方式，提交数据，是在主线程中执行的，需要新起线程，将其放到子线程中执行
     *
     * @param url
     * @param bodyParams
     * @return
     */
    public String postData(String url, Map<String, String> bodyParams) throws Exception {
        //1构造RequestBody
        RequestBody body = setRequestBody(bodyParams);
        //2 构造Request
        Request.Builder requestBuilder = new Request.Builder();
        Request request = requestBuilder.post(body).url(url).build();
        Response response = getOkHttpClient().newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * post的请求参数，构造RequestBody
     *
     * @param bodyParams
     * @return
     */
    private RequestBody setRequestBody(Map<String, String> bodyParams) {
        RequestBody body = null;
        FormBody.Builder formEncodingBuilder = new FormBody.Builder();
        if (bodyParams != null) {
            Iterator<String> iterator = bodyParams.keySet().iterator();
            String key = "";
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                formEncodingBuilder.add(key, bodyParams.get(key));
                // log.info("post_Params=== {} ==== {}" + key, bodyParams.get(key));
            }
        }
        body = formEncodingBuilder.build();
        return body;

    }

    public String postXml(String url, String xml) throws Exception {
        RequestBody body = RequestBody.create(XML, xml);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = getOkHttpClient().newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    public String postJson(String url, String json) throws Exception {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = getOkHttpClient().newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }

    /**
     * post请求， 参数是json
     *
     * @param url
     * @param json
     * @param header
     * @return Response
     * @throws Exception
     */
    public String postJson(String url, String json, Map<String, String> header) throws Exception {
        RequestBody body = RequestBody.create(JSON, json);
        Request.Builder requestBuild = new Request.Builder()
                .url(url)
                .post(body);
        if (!header.isEmpty()) {
            for (Map.Entry<String, String> stringStringEntry : header.entrySet()) {
                requestBuild.header(stringStringEntry.getKey(), stringStringEntry.getValue());
            }
        }
        Response response = getOkHttpClient().newCall(requestBuild.build()).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }


    /**
     * 用于信任所有证书
     */
    class TrustAllCerts implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
