package com.metamorf.eform.web.util;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.owasp.csrfguard.CsrfGuard;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Randomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.metamorf.eform.common.core.LoginSession;
import com.metamorf.eform.common.core.SessionConstants;
import com.metamorf.eform.common.exception.ErrorHolder;
import com.metamorf.eform.common.exception.SystemException;
import com.metamorf.eform.entity.settings.Lookup;

public class WebGeneralFunction {

	private final static Logger LOGGER = LoggerFactory.getLogger(WebGeneralFunction.class);

	public static LoginSession getLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (null == session) {
			LOGGER.info("Empty HTTP Session");
			throw new SystemException(new ErrorHolder("error.session.invalidated"));
		}

		LoginSession loginSession = (LoginSession) session.getAttribute(SessionConstants.USER_OBJECT_KEY);
		if (null == loginSession) {
			LOGGER.info("Empty Login Session");
			throw new SystemException(new ErrorHolder("error.session.expired"));
		}

		return loginSession;
	}

	public static String toHTML(String string) {
		String result = string;
		result = StringUtils.replace(result, "\n\r", "\n");
		result = StringUtils.replace(result, "\n", "<br />");
		return result;
	}

	public static String generateNonce() {
		Randomizer rz = ESAPI.randomizer();
		Integer nonce = rz.getRandomInteger(0, 999999999);
		String randomres = nonce.toString();
		if (randomres.length() % 2 != 0) {
			randomres = randomres.substring(0, randomres.length() - 1);
		}
		return randomres;
	}

	/*
	 * public static boolean isSessionAvail(HttpServletRequest request,
	 * ActionErrors errors) { if ((LoginSession)
	 * request.getSession(false).getAttribute(SessionConstants.USER_OBJECT_KEY)
	 * != null) { return true; } errors.add(ActionErrors.GLOBAL_ERROR, new
	 * ActionError("error.global.session")); return false; }
	 */

	public static String stringToHTMLString(String string) {
		StringBuffer sb = new StringBuffer(string.length());
		// true if last char was blank
		boolean lastWasBlankChar = false;
		int len = string.length();
		char c;

		for (int i = 0; i < len; i++) {
			c = string.charAt(i);
			if (c == ' ') {
				// blank gets extra work,
				// this solves the problem you get if you replace all
				// blanks with &nbsp;, if you do that you loss
				// word breaking
				if (lastWasBlankChar) {
					lastWasBlankChar = false;
					sb.append("&nbsp;");
				} else {
					lastWasBlankChar = true;
					sb.append(' ');
				}
			} else {
				lastWasBlankChar = false;
				//
				// HTML Special Chars
				if (c == '"')
					sb.append("&quot;");
				else if (c == '&')
					sb.append("&amp;");
				else if (c == '<')
					sb.append("&lt;");
				else if (c == '>')
					sb.append("&gt;");
				else if (c == '\n')
					// Handle Newline
					sb.append("<br/>");
				else {
					int ci = 0xffff & c;
					if (ci < 160)
						// nothing special only 7 Bit
						sb.append(c);
					else {
						// Not 7 Bit use the unicode system
						sb.append("&#");
						sb.append(Integer.valueOf(ci).toString());
						sb.append(';');
					}
				}
			}
		}
		return sb.toString();
	}

	public static String getLangName(String code) {
		if (null != code) {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession(false);
			List<Lookup> langList = (List<Lookup>) session.getAttribute(SessionConstants.LANGUAGE_LIST);
			if (null != langList) {
				for (Lookup lang : langList) {
					if (lang.getCode().equals(code)) {
						return lang.getName();
					}
				}
			}
		}
		return null;
	}

	public static String getTokenName() {
		CsrfGuard csrfGuard = CsrfGuard.getInstance();
		String tokenName = csrfGuard.getTokenName();
		return tokenName;
	}

	public static void restPostCall(String url, MultiValueMap<String, String> map) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			LOGGER.debug("Creating HttpClient for location {} ... ", url);
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
				}
			} };

			try {
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			} catch (Exception e) {
			}
			restTemplate.postForObject(url, map, String.class);
		} catch (RestClientException rc) {
			LOGGER.error("Fail forceLogout at location {} reason {}", url, rc.getMessage());
			// do nothing
		}
	}
}
