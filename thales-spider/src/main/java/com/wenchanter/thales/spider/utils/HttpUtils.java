package com.wenchanter.thales.spider.utils;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpCoreContext;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wang_hui
 */
public class HttpUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static HttpClient httpClient = null;

    static {
        init();
    }

    private HttpUtils() {}

    /**
     * 异常自动恢复处理, 使用HttpRequestRetryHandler接口实现请求的异常恢复
     */
    private static HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {
        // 自定义的恢复策略
        @Override
        public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            // 设置恢复策略，在发生异常时候将自动重试3次
            if (executionCount >= 3) {
                // 如果连接次数超过了最大值则停止重试
                return false;
            }
            if (exception instanceof InterruptedIOException) {
                // Timeout
                return false;
            }
            if (exception instanceof UnknownHostException) {
                // Unknown host
                return false;
            }
            if (exception instanceof NoHttpResponseException) {
                // 如果服务器连接失败重试
                return true;
            }
            if (exception instanceof SSLHandshakeException) {
                // 不要重试ssl连接异常
                return false;
            }
            HttpRequest request = (HttpRequest) context.getAttribute(HttpCoreContext.HTTP_REQUEST);
            boolean idempotent = (request instanceof HttpEntityEnclosingRequest);
            if (!idempotent) {
                // 重试，如果请求是考虑幂等
                return true;
            }
            return false;
        }
    };

    private static void init() {
        try {
            // 需要通过以下代码声明对https连接支持
            SSLContext sslcontext =
                    SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy())
                            .build();
            HostnameVerifier hostnameVerifier =
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
            SSLConnectionSocketFactory sslsf =
                    new SSLConnectionSocketFactory(sslcontext, hostnameVerifier);
            Registry<ConnectionSocketFactory> socketFactoryRegistry =
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("http", PlainConnectionSocketFactory.getSocketFactory())
                            .register("https", sslsf).build();

            PoolingHttpClientConnectionManager cm =
                    new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            /* 每个Route的最大线程数 */
            cm.setDefaultMaxPerRoute(2000);
            /* 最大线程数 */
            cm.setMaxTotal(2000);

            RequestConfig config =
                    RequestConfig.custom().setConnectTimeout(5 * 1000)
                            .setConnectionRequestTimeout(5 * 1000).setSocketTimeout(10 * 1000)
                            .build();

            httpClient =
                    HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(config)
                            .setRetryHandler(requestRetryHandler).build();

        } catch (Exception e) {
            logger.error("httputil init error...", e);
        }
    }

    public static HttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * 返回来源地址.
     *
     * @param request 请求
     * @return 来源地址
     */
    public static String getReferer(HttpServletRequest request, String defaultUrl) {
        String referer = StringUtils.trimToEmpty(request.getHeader("referer"));
        if (StringUtils.isBlank(referer) || referer.endsWith("login") || referer.endsWith("logout")) {
            referer = defaultUrl;
        }
        return referer;
    }

    /**
     * GET方法获取接口返回值
     *
     * @param url 接口URL地址
     * @return 返回值
     */
    @SuppressWarnings("resource")
    public static String getApiResponse(String url) {
        HttpGet get = null;
        try {
            get = new HttpGet(url);
            CloseableHttpClient client = (CloseableHttpClient) getHttpClient();

            HttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (get != null) {
                get.releaseConnection();
            }
        }
        return "";
    }

    /**
     * 带Multipart参数的post数据提交.
     *
     * @param url URL地址
     * @param parts 参数
     * @return 调用结果
     */
    public static String postMultipartForm(String url, Map<String, ContentBody> parts) {
        // TODO 未测试
        HttpPost post = new HttpPost(url);
        try {
            HttpClient client = getHttpClient();

            MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
            for (String name : parts.keySet()) {
                reqEntity.addPart(name, parts.get(name));
            }
            post.setEntity(reqEntity.build());
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }
        return "";
    }

    /**
     * 带StringEntity参数的post数据提交.
     *
     * @param url 接口地址
     * @param parameters string entity 参数
     * @return 调用结果
     */
    public static String postWithStringEntity(String url, String parameters) {
        HttpPost post = new HttpPost(url);
        try {
            HttpClient client = getHttpClient();

            StringEntity reqEntity = new StringEntity(parameters, ContentType.APPLICATION_JSON);
            post.setEntity(reqEntity);
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }
        return "";
    }

    /**
     * 运用POST方法提交数据
     *
     * @param url 接口URL，参数封装在URL中
     * @return 提交结果
     */
    public static String postWrappedUrl(String url) {
        HttpPost post = new HttpPost(url);
        try {
            HttpClient client = getHttpClient();
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }
        return "";
    }
}
