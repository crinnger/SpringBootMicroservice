package br.com.crinnger.SpringBootMicroservice.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate; 
 
@Component
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer{ 
	
	private final Integer maxConnection;
	private final Integer maxPerRoute;
	private final Integer timeout;
	private final Integer socketTimeout;
	
	public BlockingRestTemplateCustomizer(
		@Value("${sfg.maxconnection}") Integer maxConnection, 
		@Value("${sfg.maxperroute}") Integer maxPerRoute, 
		@Value("${sfg.timeout}") Integer timeout,
		@Value("${sfg.sockettimeout}") Integer socketTimeout) {
		super();
		this.maxConnection = maxConnection;
		this.maxPerRoute = maxPerRoute;
		this.timeout = timeout;
		this.socketTimeout = socketTimeout;
	}

	public ClientHttpRequestFactory clientHttpRequestFacoty() {
		PoolingHttpClientConnectionManager connectionManager=new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(this.maxConnection);
		connectionManager.setDefaultMaxPerRoute(this.maxPerRoute);
		
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(this.timeout)
				.setSocketTimeout(this.socketTimeout)
				.build();
		CloseableHttpClient httpClient= HttpClients
				.custom()
				.setConnectionManager(connectionManager)
				.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
				.setDefaultRequestConfig(requestConfig)
				.build();
		return new HttpComponentsClientHttpRequestFactory(httpClient);
	}
	
	@Override
	public void customize(RestTemplate restTemplate) {
		// TODO Auto-generated method stub
		restTemplate.setRequestFactory(this.clientHttpRequestFacoty());
	}

}
