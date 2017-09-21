/**
 * Customized by Faisal Reza  
 */

package com.metamorf.eform.common.captcha;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;

public class JcaptchaServlet extends HttpServlet implements Servlet{
	private final static Logger LOGGER = LoggerFactory.getLogger(JcaptchaServlet.class);
	
	private static final long serialVersionUID = 2621728607977576103L;
	public static ImageCaptchaService service = new RSCFManageableImageCaptchaService();

	  protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	    throws ServletException, IOException
	  {
	    httpServletResponse.setDateHeader("Expires", 0L);

	    httpServletResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

	    httpServletResponse.addHeader("Cache-Control", "post-check=0, pre-check=0");

	    httpServletResponse.setHeader("Pragma", "no-cache");

	    httpServletResponse.setContentType("image/jpeg");

	    BufferedImage bi = service.getImageChallengeForID(httpServletRequest.getSession(true).getId());

	    ServletOutputStream out = httpServletResponse.getOutputStream();

	    ImageIO.write(bi, "jpg", out);
	    try
	    {
	      out.flush();
	    }
	    finally
	    {
	      out.close();
	    }
	  }

	  public static boolean validateResponse(HttpServletRequest request, String userCaptchaResponse)
	  {
	    if (request.getSession(false) == null) return false;

	    boolean validated = false;
	    try {
	      validated = service.validateResponseForID(request.getSession().getId(), userCaptchaResponse).booleanValue();
	    } catch (CaptchaServiceException e) {
	      LOGGER.error(e.getMessage());
	    }
	    return validated;
	  }

}
