package com.metamorf.eform.service.rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;
import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.core.SystemParameter;
import com.metamorf.eform.common.util.DateTimeFunction;
import com.metamorf.eform.entity.rest.BtpnRequestToken;
import com.metamorf.eform.service.validator.JsonDateSerielizerGson;

public class SimpleRestCaller<T> {
   private static final Logger LOGGER = LoggerFactory.getLogger(SimpleRestCaller.class);
	
	ObjectMapper mapper = new ObjectMapper();
	RestTemplate restTemplate;
	HttpHeaders headers = new HttpHeaders();
	private Class<T> typeResponseClass;
	private static BtpnRequestToken btpnRequestToken;
	private Gson gson = new GsonBuilder().registerTypeAdapter(Date.class,new JsonDateSerielizerGson()).create();
	private Parser parserJson = Parser.MAPPER;

	/*
	 * Keys: > APIKEY: 8428c326-ae66-47c7-9b06-1797ac513911 > Secret:
	 * 2fc06110-fd5e-4156-a52e-f560fadf65cb > > ClientID :
	 * 5d6219ca-66c2-4543-9d83-7a0433f01eea > 
	 * b2078da4-75e3-4d04-bf2b-9f418e9da2b6
	 */
	/**
	 * APPAPISIT02
	 * 
	 * Client ID 7a84f964-17ce-4353-8bf8-c4ee63f5cd16
	 * 
	 * CLIENT Secret 1ae22ff2-2bd1-414c-ace0-bb51f627f9f7
	 * 
	 * API Key ffb120d9-37f3-40a9-8a6a-e1fe7e3ecf63
	 * 
	 * API Secret 48ef96fb-af1b-4f02-81ca-00ec935cb379
	 */
	// TODO: Should be store somewhere SAFE. dev options only
	private static final String CLIENT_ID = "7a84f964-17ce-4353-8bf8-c4ee63f5cd16";
	private static final String CLIENT_SECRET = "1ae22ff2-2bd1-414c-ace0-bb51f627f9f7";
	private static final String API_SECRET = "48ef96fb-af1b-4f02-81ca-00ec935cb379";
	public static enum Parser {MAPPER,GSON};

	public SimpleRestCaller(Class<T> typeResponseClass) {
		this.typeResponseClass = typeResponseClass;
		headers.setContentType(MediaType.APPLICATION_JSON);
		setDateFormat(new SimpleDateFormat(SystemConstant.TO_OUTSYSTEM));
		if (SystemParameter.API_GATEWAY_TLS_1_2) {
			restTemplate = new RestTemplate();
			final ClientHttpRequestFactory clientHttpRequestFactory =
					new MyCustomClientHttpRequestFactory(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			restTemplate.setRequestFactory(clientHttpRequestFactory);
		}
		else {
			LOGGER.info("------ DISABLE TLS 1.2 ------");
			restTemplate = new RestTemplate(createSimpleClientHttpRequestFactory());
		}
	}

	public SimpleRestCaller(Class<T> typeResponseClass, Parser mapper) {
		this.typeResponseClass = typeResponseClass;
		headers.setContentType(MediaType.APPLICATION_JSON);
		setDateFormat(new SimpleDateFormat(SystemConstant.TO_OUTSYSTEM));
		if (SystemParameter.API_GATEWAY_TLS_1_2) {
			restTemplate = new RestTemplate();
			final ClientHttpRequestFactory clientHttpRequestFactory =
			        new MyCustomClientHttpRequestFactory(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		    restTemplate.setRequestFactory(clientHttpRequestFactory);
		}
		else {
			LOGGER.info("------ DISABLE TLS 1.2 ------");
			restTemplate = new RestTemplate(createSimpleClientHttpRequestFactory());
		}
		parserJson = mapper;
	}
	
	public class MyCustomClientHttpRequestFactory  extends SimpleClientHttpRequestFactory {

		private final HostnameVerifier hostNameVerifier;

		public MyCustomClientHttpRequestFactory (final HostnameVerifier hostNameVerifier) {
		    this.hostNameVerifier = hostNameVerifier;
		}

		@Override
		protected void prepareConnection(final HttpURLConnection connection, final String httpMethod)
		    throws IOException {
		    if (connection instanceof HttpsURLConnection) {
		        ((HttpsURLConnection) connection).setHostnameVerifier(hostNameVerifier);
		        ((HttpsURLConnection) connection).setSSLSocketFactory(initSSLContext().getSocketFactory());
		    }
		    super.prepareConnection(connection, httpMethod);
		}

		private SSLContext initSSLContext() {
		    try {
		        System.setProperty("https.protocols", "TLSv1.2");

		        // Set ssl trust manager. Verify against our server thumbprint
		        final SSLContext ctx = SSLContext.getInstance("TLSv1.2");
		        
		        X509TrustManager easyTrustManager = new X509TrustManager() {
					public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
						LOGGER.info("checkClientTrusted");
					}
					public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
						boolean isValid = true;
//						for (int i = 0; i < chain.length; i++) {
//							RSAPublicKey pubkey = (RSAPublicKey) chain[i].getPublicKey();
//							String encoded = new BigInteger(1, pubkey.getEncoded()).toString(16);
//							System.out.println(encoded);
//							if (MTFConstants.SSL_PUBLIC_KEY.equalsIgnoreCase(encoded)) {
//								isValid = true;
//								break;
//							}
//						}
						if(!isValid) {
							throw new IllegalArgumentException("public key invalid");
						}
					}
					public X509Certificate[] getAcceptedIssuers() {
						return new X509Certificate[0];
					}
				};
		        ctx.init(null, new X509TrustManager[] { easyTrustManager }, null);
		        return ctx;
		    } catch (final Exception ex) {
		        LOGGER.error(
		            "An exception was thrown while trying to initialize HTTP security manager.", ex);
		        return null;
		    }
		}
	}
	
//	private HttpComponentsClientHttpRequestFactory createClientHttpRequestFactory() {
//		SSLContext sslContext = SSLContext.getInstance("TLSv1");
//
//	    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
//
//	    CloseableHttpClient httpClient = HttpClients.custom()
//	            .setSSLSocketFactory(csf)
//	            .build();
//		
//		HttpComponentsClientHttpRequestFactory requestFactory =
//	            new HttpComponentsClientHttpRequestFactory();
//
//	    requestFactory.setHttpClient(httpClient);
//	    
//	    return requestFactory;
//	}
	
	private SimpleClientHttpRequestFactory createSimpleClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		simpleClientHttpRequestFactory.setConnectTimeout(SystemParameter.WEB_SERVICE_CONNECT_TIMEOUT);
		simpleClientHttpRequestFactory.setReadTimeout(SystemParameter.WEB_SERVICE_REQUEST_TIMEOUT);
		return simpleClientHttpRequestFactory;
	}
	
	public List<T> doGetRestCall(String restUrl) throws Exception {
		return doGetRestCall(restUrl, null);
	}

	public List<T> doGetRestCall(String restUrl, Map<String, String> map) throws Exception{
		if (map!=null) {
			restUrl +="?";
			int i=0;
			for (Map.Entry<String, String> entry : map.entrySet())
			{
				if (i!=0) {
					restUrl+="&";
				}
				restUrl+=entry.getKey()+"="+entry.getValue();
				i++;
			}
		}
		
		String json = restTemplate.getForObject(restUrl, String.class);
		return mapper.readValue(json,TypeFactory.defaultInstance().constructCollectionType(List.class, typeResponseClass));
	}
	
	
	
	public T doGetObject(String restUrl, Map<String, String> param) throws SystemException {
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		ResponseEntity<T> response = restTemplate.exchange(restUrl,HttpMethod.GET, entity, typeResponseClass, param);
		return (T) response.getBody();
	}
	
	/**
	 * T Object request and object class response are same
	 * 
	 * @param restUrl
	 * @param obj
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public T doPostRestCall(String restUrl, T obj) throws JsonGenerationException, JsonMappingException, IOException {
		HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(obj), headers);

		ResponseEntity<T> responseEntity = restTemplate.exchange(restUrl, HttpMethod.POST, entity, typeResponseClass);
//		ResponseEntity<T> responseEntity = restTemplate.postForEntity(restUrl, null, typeParameterClass);
		return (T) responseEntity.getBody();
	}
	
	/**
	 * Object request and object class response are different
	 * 
	 * @param requestObj
	 * @param restUrl
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public T doPostRestCall(Object requestObj, String restUrl)
			throws JsonGenerationException, JsonMappingException, IOException {
		HttpEntity<String> httpEntityReq = new HttpEntity<String>(mapper.writeValueAsString(requestObj), headers);

		ResponseEntity<T> responseEntity = restTemplate.exchange(restUrl, HttpMethod.POST, httpEntityReq,
				typeResponseClass);
		return (T) responseEntity.getBody();
	}

	
	
	/**
	 * String value as a request and T responseClassObject as a response.
	 * 
	 * @param restUrl
	 * @param responseTypeClass
	 * @param requestAsString
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public T doPostRestCall(String restUrl, String requestAsString)
			throws JsonGenerationException, JsonMappingException, IOException {
		HttpEntity<String> httpEntityReq = new HttpEntity<String>(requestAsString, headers);

		ResponseEntity<T> responseEntity = restTemplate.exchange(restUrl, HttpMethod.POST, httpEntityReq, typeResponseClass);
		return (T) responseEntity.getBody();
	}

	protected List<T> resolveList(@SuppressWarnings("rawtypes") ResponseEntity<List> responseEntity){
		List<T> list = new ArrayList<T>();
		if(responseEntity!=null){
			String jsonObj;
		    try {
		    	jsonObj = mapper.writeValueAsString(responseEntity.getBody());
		    	TypeFactory t = TypeFactory.defaultInstance();
				list = mapper.readValue(jsonObj, t.constructCollectionType(ArrayList.class,typeResponseClass));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	private void setDateFormat(DateFormat dateFormat){
//		mapper.setDateFormat(dateFormat);
//		gson = new GsonBuilder().setDateFormat(SystemConstant.TO_OUTSYSTEM).create();
	}
	
	/**
	 * HTTP Method : POST for Entity
	 * 
	 * @param relativeURL
	 *            EXCLUDE hostURL. e.g /rest/btpnchecking/xxxxx
	 * @param requestObject
	 * @param trxId
	 *            uniqueTransaction ID for debugging purpose
	 * @return
	 * @throws Exception
	 */
	public T doPostForEntityBTPNHost(String relativeURL, Object requestObject, String trxId) throws Exception {
		String jsonString = "";
		if (requestObject instanceof String) {
			jsonString = (String) requestObject;
		} else {
			jsonString = mapper.writeValueAsString(requestObject);
			if (parserJson.equals(Parser.MAPPER)) {
				jsonString = mapper.writeValueAsString(requestObject);
			} else {
				jsonString = gson.toJson(requestObject);
			}
		}
		
		String BTPN_REST_URL_API_GATEWAY = SystemParameter.BTPN_REST_URL_API_GATEWAY;
		
		LOGGER.info("START TrxId: [{}], relativeURL : [{}], request : [{}]", new Object[] {trxId, BTPN_REST_URL_API_GATEWAY+relativeURL, jsonString });
//		System.out.println("START TrxId: [{}], relativeURL : [{}], request : [{}]" +trxId+"||||"+BTPN_REST_URL_API_GATEWAY+relativeURL+"|||||"+ jsonString);
		HttpEntity<String> reqHttpEntity = null;
		
		setHeaders(headers, HttpMethod.POST, relativeURL, jsonString);
		reqHttpEntity = new HttpEntity<>(jsonString, headers);
		ResponseEntity<T> response;

		try {
			response = restTemplate.exchange(BTPN_REST_URL_API_GATEWAY+ relativeURL, HttpMethod.POST,
					reqHttpEntity, typeResponseClass);
		} catch (HttpClientErrorException exhc) {
			LOGGER.error(exhc.getMessage());
			if (exhc.getStatusCode() == HttpStatus.BAD_REQUEST || exhc.getStatusCode() == HttpStatus.FORBIDDEN) {
				LOGGER.error(exhc.getResponseBodyAsString());
			}
			throw exhc;
		} catch (Exception e) {
			LOGGER.error("Exception is ", e);
			throw e;
		}
		/*
		 * no need to call get access token 
		if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
			response = retryRestCaller(relativeURL, HttpMethod.POST, jsonString);
		}
		 * */
		LOGGER.info("END TrxId: [{}], relativeURL : [{}], response : [{}]", new Object[] { trxId, relativeURL, mapper.writeValueAsString(response.getBody())});
		return response.getBody();
	}
	
	/**
	 * Http Method : GET for Entity
	 * 
	 * @param relativeURL
	 *            EXCLUDE hostURL. e.g /rest/btpnchecking/xxxxx
	 * @param mapParamsPaths
	 *            map of req params or Path variable
	 * @param gettype
	 *            type of map Params or Path variable
	 * @return
	 * @throws Exception
	 */
	public T doGetForEntityBTPNHost(String relativeURL, Map<String, String> mapParamsPaths, String gettype, String referenceNo) throws Exception {
		if (mapParamsPaths == null) {
			mapParamsPaths = new HashMap<String, String>();
		}
		if (referenceNo != null) {
			mapParamsPaths.put("referenceNo", referenceNo);
		}
		
		if (mapParamsPaths != null ) {
			if (SystemConstant.RequestMethodGET.PATH_VAR.equals(gettype)) {
				relativeURL += "/";
				int i = 0;
				for (Map.Entry<String, String> entry : mapParamsPaths.entrySet()) {
					if (i != 0) {
						relativeURL += "/";
					}
					relativeURL += entry.getValue();
					i++;
				}
			} else if (SystemConstant.RequestMethodGET.REQUEST_PARAM.equals(gettype)) {
				relativeURL += "?";
				int i = 0;
				for (Map.Entry<String, String> entry : mapParamsPaths.entrySet()) {
					if (i != 0) {
						relativeURL += "&";
					}
					relativeURL += entry.getKey() + "=" + entry.getValue();
					i++;
				}
			}
		}
		
		setHeaders(headers, HttpMethod.GET, relativeURL, null);
		HttpEntity<String> reqHttpEntity = new HttpEntity<>(headers);
		ResponseEntity<T> response;

		try {
			LOGGER.info("START TrxId: [{}], relativeURL : [{}], request : [{}]", referenceNo , SystemParameter.BTPN_REST_URL_API_GATEWAY + relativeURL, mapper.writeValueAsString(reqHttpEntity) );
			response = restTemplate.exchange(SystemParameter.BTPN_REST_URL_API_GATEWAY + relativeURL, HttpMethod.GET,
					reqHttpEntity, typeResponseClass);
		} catch (HttpClientErrorException exhc) {
			LOGGER.error("HTTP Client exception. Exception is ", exhc);
			if (exhc.getStatusCode() == HttpStatus.BAD_REQUEST || exhc.getStatusCode() == HttpStatus.FORBIDDEN) {
				LOGGER.error(exhc.getResponseBodyAsString());
			}
			throw exhc;
		} catch (Exception e) {
			LOGGER.error("Exception is ", e);
			throw e;
		}
		
		/*
		 *  no need call get access token
		if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
			response = retryRestCaller(relativeURL, HttpMethod.GET, null);
		}
		 * */
		LOGGER.info("END GET METHOD TrxId [{}] [{}]", referenceNo, mapper.writeValueAsString(response.getBody()));
		return response.getBody();
	
	}
	/**
	 * Http GET method for BTPN
	 * 
	 * @param relativeURL
	 *            EXCLUDE hostURL. e.g /rest/btpnchecking/xxxxx
	 * @param mapRequestParam
	 *            map of req parameters
	 * @param referenceNo TODO
	 * @return
	 * @throws Exception
	 */
	public T doGetForEntityBTPNHost(String relativeURL, Map<String, String> mapRequestParam, String referenceNo) throws Exception {
		return doGetForEntityBTPNHost(relativeURL, mapRequestParam, SystemConstant.RequestMethodGET.REQUEST_PARAM, referenceNo);
	}
	
//	public T doGetForEntityBTPNHost(String relativeURL, Map<String, String> mapRequestParam, String referenceNo) throws Exception {
//		if (mapRequestParam==null) {
//			mapRequestParam = new HashMap<>();
////			mapRequestParam.put("referenceNo", refNo);
//		}
//		return doGetForEntityBTPNHost(relativeURL, mapRequestParam, SystemConstant.RequestMethodGET.REQUEST_PARAM);
//	}
	
	private ResponseEntity<T> retryRestCaller(String relativeURL, HttpMethod httpMethod, String jsonString) {
		LOGGER.info("Token invalid/expired , retry requesting token...");
		btpnRequestToken = null;
		setHeaderAccessTokenBTPN(headers);
		HttpEntity<String> reqHttpEntity = new HttpEntity<>(jsonString, headers);
		LOGGER.info("Retrying REST calling...");
		return restTemplate.exchange(SystemParameter.BTPN_REST_URL_API_GATEWAY + relativeURL, httpMethod, reqHttpEntity,
				typeResponseClass);
	}

	private static void setHeaderAccessTokenBTPN(HttpHeaders headers) {
		headers.set("Authorization", getAccessTokenBTPN().getTokenType()+" " + getAccessTokenBTPN().getAccessToken());
//		headers.set("Authorization", "Basic YWRtaW46YWRtaW4=");		
	}

	/**
	 * Generate BTPN Signature
	 * 
	 * @param httpMethod
	 *            POST or GET
	 * @param relativeURL
	 *            relativeURL is without HOST URL
	 * @param reqBodyString
	 *            requestBody in string format (e.g json, requestParams)
	 * @return
	 */
	private String generateBTPNSignature(HttpMethod httpMethod, String relativeURL, String reqBodyString,
			Date timeStamp) throws Exception {
		StringBuilder signatureStrBuilder = new StringBuilder();
		signatureStrBuilder.append(httpMethod.toString()).append(SystemConstant.DELIMITER_COLON).append(relativeURL).append(SystemConstant.DELIMITER_COLON);
		signatureStrBuilder.append(SystemParameter.BTPN_API_GATEWAY_KEY).append(SystemConstant.DELIMITER_COLON).append(DateTimeFunction.date2String(timeStamp, SystemConstant.API_GATEWAY_DATETIME));
		if (httpMethod == HttpMethod.POST) {
			signatureStrBuilder.append(SystemConstant.DELIMITER_COLON).append(reqBodyString);
		}
		String signatureStr = signatureStrBuilder.toString().replaceAll(" ", "");
		LOGGER.debug("BTPN-Signature before encrypt: {}", signatureStr);
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(new SecretKeySpec(API_SECRET.getBytes(), "HmacSHA256"));
		byte[] byteResult = mac.doFinal(signatureStr.getBytes());
		String btpnSignature = new String(Base64.encodeBase64(byteResult));
		LOGGER.debug("BTPN-Signature after encrypt : {}", btpnSignature);
		return btpnSignature;
	}

	/**
	 * Set common header API for Oauth
	 * 
	 * @param headers
	 * @param httpMethod
	 *            POST or GET
	 * @param relativeURL
	 *            exclude the host URL
	 * @param reqBodyString
	 *            json string or reqParameters in String
	 * @throws Exception
	 */
	private void setHeaders(HttpHeaders headers, HttpMethod httpMethod, String relativeURL, String reqBodyString)
			throws Exception {
		Date timeStamp = new Date();
//		headers.set("BTPN-Signature", generateBTPNSignature(httpMethod, relativeURL, reqBodyString, timeStamp)); no need generate hmac signature
		headers.set("BTPN-ApiKey", SystemParameter.BTPN_API_GATEWAY_KEY);
		headers.set("BTPN-Timestamp", DateTimeFunction.date2String(timeStamp, SystemConstant.API_GATEWAY_DATETIME));
//		setHeaderAccessTokenBTPN(headers); no need generate hmac signature
		LOGGER.debug("====================================HEADER===================================================");
		for (Entry<String, List<String>> entry : headers.entrySet()) {
			LOGGER.debug(entry.getKey()+":"+entry.getValue());
		}
		LOGGER.debug("====================================HEADER===================================================");
	}
	
	/**
	 * Request Access Token from BTPN oAuth if token null. Will store to static
	 * if success for next calling API
	 * 
	 * @return
	 */
	public static BtpnRequestToken getAccessTokenBTPN() {
		if (btpnRequestToken == null) {
			LOGGER.info("Start request accessToken...");
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("grant_type", "client_credentials");
			HttpHeaders header = getHeaderAuthenticationToken();
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, header);
			ResponseEntity<BtpnRequestToken> response = restTemplate
					.postForEntity(SystemParameter.HOST_REST_BTPN_OAUTH_TOKEN_URL, request,
					BtpnRequestToken.class);
			btpnRequestToken = response.getBody();
			LOGGER.info("End request accessToken... " + btpnRequestToken.getAccessToken());
			return btpnRequestToken;
		}
		LOGGER.info("Using existing accessToken..." + btpnRequestToken.getAccessToken());
		return btpnRequestToken;
	}

	private static HttpHeaders getHeaderAuthenticationToken() {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		byte[] bytes = Base64.encodeBase64((CLIENT_ID + ":" + CLIENT_SECRET).getBytes());
		header.set("Authorization", "Basic " + new String(bytes)); // generate
		LOGGER.debug(new String(bytes));
		return header;
	}
	
	
	public ObjectMapper getMapper() {
		return mapper;
	}

	public HttpHeaders getHeaders() {
		return headers;
	}
	
	public Gson getGson() {
		return gson;
	}
}
