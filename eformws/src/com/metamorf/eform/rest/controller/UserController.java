package com.metamorf.eform.rest.controller;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metamorf.eform.common.core.SystemParameter;
import com.metamorf.eform.common.enumer.EmailStatus;
import com.metamorf.eform.common.enumer.EmailType;
import com.metamorf.eform.common.util.AES;
import com.metamorf.eform.entity.user.RuntimeUser;
import com.metamorf.eform.entity.user.SendEmail;
import com.metamorf.eform.entity.user.User;
import com.metamorf.eform.interfaces.user.IRuntimeUserService;
import com.metamorf.eform.interfaces.user.ISendEmailService;
import com.metamorf.eform.interfaces.user.IUserService;
import com.metamorf.eform.rest.config.Constant;
import com.metamorf.eform.rest.model.CLVLoginReq;
import com.metamorf.eform.rest.model.CLVSignupReq;
import com.metamorf.eform.rest.model.Response;
import com.metamorf.eform.service.authentication.PasswordAuthenticationService;
import com.metamorf.eform.web.util.GeneratorToken;

@Controller
@RequestMapping("/user")
public class UserController extends BaseRestController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private ISendEmailService sendEmailService;
	@Autowired
	private IRuntimeUserService runtimeUserService;
	@Autowired
	private PasswordAuthenticationService passwordAuthenticationService;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/ping", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response ping() throws Exception {
		return new Response(Constant.SUCCESS);
	}

	@RequestMapping(value = "/verification", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String verification(
			@RequestParam(value="token",required = true) String tokens) throws Exception {
		try{
			/*String tokens = "";
			try{
				AES.generateKey();
				tokens = AES.decryptString(token);
			}catch(Exception e){
				LOGGER.info("Failed - Verification (Error Decrypt), Token: [{}]", token);
				return "redirect:/badUser.html";
			}*/
			
			User user = userService.findByVerificationToken(tokens);
			if(user == null){
				LOGGER.info("Failed - Verification (User not found), Token: [{}]", tokens);
		        return "redirect:/error400.jsp";
			}else{
				if(user.getVerify()){
					LOGGER.info("Success - Verification (User already verify), Token: [{}]", tokens);
					return "redirect:/verification/success.jsp";
				}else{
					user.setVerify(true);
					user.setVerifyDate(new Date());
					userService.saveOrUpdate(user);

					LOGGER.info("Success - Verification (First time verify), Token: [{}]", tokens);
					return "redirect:/verification/success.jsp";
				}
			}
		}catch(Exception e){
			LOGGER.error("Error Exception - Verification, Token: [{}]. Exception: [{}]", tokens, e.getMessage());
			return "redirect:/error400.html";
		}
	}
	
	@RequestMapping(value = "/submit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String submit(
			@RequestParam(value="token",required = true) String tokens,
			@RequestParam(value="password",required = true) String password) throws Exception {
		try{
			/*String tokens = "";
			try{
				AES.generateKey();
				tokens = AES.decryptString(token);
			}catch(Exception e){
				LOGGER.info("Failed - Verification (Error Decrypt), Token: [{}]", token);
				return "redirect:/badUser.html";
			}*/
			
			User user = userService.findByPasswordToken(tokens);
			if(user == null){
				LOGGER.info("Failed - Submit (User not found), Token: [{}]", tokens);
		        return "redirect:/error400.jsp";
			}else{
				if(user.getVerify()){
					LOGGER.info("Success - Submit (User already verify), Token: [{}]", tokens);
					return "redirect:/forgotPassword/success.jsp";
				}else{
					user.setPassword(passwordAuthenticationService.generatePassword(password));
					user.setPasswordToken("");
					userService.saveOrUpdate(user);

					LOGGER.info("Success - Submit (First time verify), Token: [{}]", tokens);
					return "redirect:/forgotPassword/success.jsp";
				}
			}
		}catch(Exception e){
			LOGGER.error("Error Exception - Submit, Token: [{}]. Exception: [{}]", tokens, e.getMessage());
			return "redirect:/error400.html";
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response login(@RequestBody CLVLoginReq loginReq) throws Exception {
		String username = "";
		String password = "";
		try{
			AES.generateKey();
			username = AES.decryptString(loginReq.getUsername());
			password = AES.decryptString(loginReq.getPassword());
		}catch(Exception e){
			LOGGER.info("Failed - Login (Error Decrypt), Username: [{}]", loginReq.getUsername());
			return new Response(Constant.ERROR, properties.getProperty("login.error"));
		}
		
		try{
			LOGGER.info("Begin - Login, Username: [{}], Ref No: [{}]", username, loginReq.getReferenceNo());
			
			User user = userService.findByUsername(username);
			if(user == null){
				user = userService.findByEmail(username);
				if(user == null){
					LOGGER.info("Failed - Login (Invalid username), Username: [{}], Ref No: [{}]", username, loginReq.getReferenceNo());
					return new Response(Constant.ERROR, properties.getProperty("login.error"));
				}
			}
			if(!passwordAuthenticationService.authenticateLogin(user, password)){
				LOGGER.info("Failed - Login (Invalid password), Username: [{}], Ref No: [{}]", username, loginReq.getReferenceNo());
				return new Response(Constant.ERROR, properties.getProperty("login.error"));
			}else if(user.getLock()){
				LOGGER.info("Failed - Login (User is locked), Username: [{}], Ref No: [{}]", username, loginReq.getReferenceNo());
				return new Response(Constant.ERROR, properties.getProperty("login.error"));
			}else if(!user.getVerify()){
				LOGGER.info("Failed - Login (User hasn't verified), Username: [{}], Ref No: [{}]", username, loginReq.getReferenceNo());
				return new Response(Constant.ERROR_VERIFY, properties.getProperty("login.error.verify"));
			}else{
				RuntimeUser runtimeUser = runtimeUserService.findRuntimeUserMobileByUsername(username);
				String token = null;
				if(runtimeUser != null){
					if(!runtimeUser.getDeviceUid().equals(loginReq.getDeviceUid()))
						runtimeUserService.delete(runtimeUser);
					else
						token = runtimeUser.getToken();
				}
				
				RuntimeUser runtime = null;
				
				if (token == null) {
					token = GeneratorToken.createToken(username, GeneratorToken.generateSecretClient());
					
					runtime = new RuntimeUser();
					runtime.setUsername(username);
					runtime.setCreatedDate(new Date());
					runtime.setToken(token);
					runtime.setDeviceUid(loginReq.getDeviceUid());
				}
				
				user.setAndroidVersion(loginReq.getAndroidVersion());
				user.setApkVersion(loginReq.getApkVersion());
				user.setModelNumber(loginReq.getModelNumber());
				user.setDeviceUid(loginReq.getDeviceUid());
				user.setToken(token);
				user.setLastLogInDate(new Date());
				
				runtimeUserService.updateRuntimeUserMobileAndUserWeb(runtime, user);
				
				LOGGER.info("Success - Login, Username: [{}], Ref No: [{}], Response: [{}]", username, loginReq.getReferenceNo());
				return new Response(Constant.SUCCESS, properties.getProperty("login.success"), gson.toJson(user));
			}
		}catch(Exception e){
			LOGGER.error("Error Exception - Login, Username: [{}], reference no: [{}]. Exception: [{}]",
					username, loginReq.getReferenceNo(), e.getMessage());
			return new Response(Constant.ERROR, properties.getProperty("server.exception"));
		}
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response signup(@RequestBody CLVSignupReq req) throws Exception {
		String username = "";
		String password = "";
		String email = "";
		try{
			AES.generateKey();
			username = AES.decryptString(req.getUsername());
			password = AES.decryptString(req.getPassword());
			email = AES.decryptString(req.getEmail());
		}catch(Exception e){
			LOGGER.info("Failed - Signup (Error Decrypt), Username: [{}]", req.getUsername());
			return new Response(Constant.ERROR, properties.getProperty("server.exception"));
		}
		
		try{
			LOGGER.info("Begin - Signup, Username: [{}], Ref No: [{}]", username, req.getReferenceNo());
			User user = userService.findByUsername(username);
			if(user != null){
				LOGGER.info("Failed - Signup (Username already exist), Username: [{}], Ref No: [{}]", username, req.getReferenceNo());
				return new Response(Constant.ERROR, properties.getProperty("signup.usernameExist"));
			}else{
				user = userService.findByEmail(email);
				if(user != null){
					LOGGER.info("Failed - Signup (Email already used), Username: [{}], Ref No: [{}]", username, req.getReferenceNo());
					return new Response(Constant.ERROR, properties.getProperty("signup.emailUsed"));
				}
			}
			User newUser = new User();
			newUser.setCreatedDate(new Date());
			newUser.setEmail(email);
			newUser.setUsername(username);
			newUser.setPassword(passwordAuthenticationService.generatePassword(password));
			newUser.setLock(false);
			newUser.setVerify(false);
	        String token = UUID.randomUUID().toString();
	        newUser.setVerificationToken(token);
			
			userService.saveOrUpdate(newUser);

			SendEmail sendEmail = new SendEmail();
			sendEmail.setUserId(newUser.getId());
			sendEmail.setUsername(newUser.getUsername());
			sendEmail.setEmail(newUser.getEmail());
			sendEmail.setCreatedDate(new Date());
			sendEmail.setEmailStatus(EmailStatus.QUEUE);
			sendEmail.setRetry(0);
			sendEmail.setMaxCountRetry(SystemParameter.MAX_RETRY_SEND_EMAIL);
			sendEmail.setType(EmailType.VERIFICATION);
			
			sendEmailService.saveOrUpdate(sendEmail);
			
			LOGGER.info("Success - Signup, Username: [{}], Ref No: [{}]", username, req.getReferenceNo());
			return new Response(Constant.SUCCESS, properties.getProperty("signup.success"));
		}catch(Exception e){
			LOGGER.error("Error Exception - Signup, Username: [{}], reference no: [{}]. Exception: [{}]",
					username, req.getReferenceNo(), e.getMessage());
			return new Response(Constant.ERROR, properties.getProperty("server.exception"));
		}
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response forgot(@RequestBody CLVSignupReq req) throws Exception {
		String email = "";
		try{
			AES.generateKey();
			email = AES.decryptString(req.getEmail());
		}catch(Exception e){
			LOGGER.info("Failed - Forgot (Error Decrypt), Email: [{}]", req.getEmail());
			return new Response(Constant.ERROR, properties.getProperty("server.exception"));
		}
		
		try{
			LOGGER.info("Begin - Forgot, Email: [{}], Ref No: [{}]", email, req.getReferenceNo());
			User user = userService.findByEmail(email);
			if(user == null){
				LOGGER.info("Failed - Forgot (User not found), Email: [{}], Ref No: [{}]", email, req.getReferenceNo());
				return new Response(Constant.ERROR, properties.getProperty("forgot.notFound"));
			}else{
				if(user.getLock()){
					LOGGER.info("Failed - Forgot (User is locked), Email: [{}], Ref No: [{}]", email, req.getReferenceNo());
					return new Response(Constant.ERROR, properties.getProperty("forgot.locked"));
				}else if(!user.getVerify()){
					LOGGER.info("Failed - Forgot (User hasn't verify yet), Email: [{}], Ref No: [{}]", email, req.getReferenceNo());
					return new Response(Constant.ERROR, properties.getProperty("forgot.verify"));
				}
			}
	        String token = UUID.randomUUID().toString();
	        user.setPasswordToken(token);
			
			userService.saveOrUpdate(user);

			SendEmail sendEmail = new SendEmail();
			sendEmail.setUserId(user.getId());
			sendEmail.setUsername(user.getUsername());
			sendEmail.setEmail(email);
			sendEmail.setCreatedDate(new Date());
			sendEmail.setEmailStatus(EmailStatus.QUEUE);
			sendEmail.setRetry(0);
			sendEmail.setMaxCountRetry(SystemParameter.MAX_RETRY_SEND_EMAIL);
			sendEmail.setType(EmailType.FORGOT_PASSWORD);
			
			sendEmailService.saveOrUpdate(sendEmail);
			
			LOGGER.info("Success - Forget, Email: [{}], Ref No: [{}]", email, req.getReferenceNo());
			return new Response(Constant.SUCCESS, properties.getProperty("forgot.success"));
		}catch(Exception e){
			LOGGER.error("Error Exception - Forget, Email : [{}], reference no: [{}]. Exception: [{}]",
					email, req.getReferenceNo(), e.getMessage());
			return new Response(Constant.ERROR, properties.getProperty("server.exception"));
		}
	}
	
	public static void main(String[] args){
		String username = "";
		String pwd = "";
		String email = "";
		try {
			AES.generateKey();
			username = AES.encryptString("53ed7dea-76b6-49eb-9670-697296c5ca35");
			pwd = AES.encryptString("test");
			email = AES.encryptString("calvin.olsen92@gmail.com");
		} catch (Exception e) {
			
		}
		System.out.println(username);
		System.out.println(pwd);
		System.out.println(email);
	}
}
