package com.metamorf.eform.rest.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metamorf.eform.common.util.AES;
import com.metamorf.eform.entity.user.RuntimeUser;
import com.metamorf.eform.entity.user.User;
import com.metamorf.eform.interfaces.user.IRuntimeUserService;
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
				LOGGER.info("Failed - Login (Invalid username), Username: [{}], Ref No: [{}]", username, loginReq.getReferenceNo());
				return new Response(Constant.ERROR, properties.getProperty("login.error"));
			}else{
				if(!passwordAuthenticationService.authenticateLogin(user, password)){
					LOGGER.info("Failed - Login (Invalid password), Username: [{}], Ref No: [{}]", username, loginReq.getReferenceNo());
					return new Response(Constant.ERROR, properties.getProperty("login.error"));
				}else if(user.getLock()){
					LOGGER.info("Failed - Login (User is locked), Username: [{}], Ref No: [{}]", username, loginReq.getReferenceNo());
					return new Response(Constant.ERROR, properties.getProperty("login.error"));
				}else if(!user.getVerify()){
					LOGGER.info("Failed - Login (User hasn't verified), Username: [{}], Ref No: [{}]", username, loginReq.getReferenceNo());
					return new Response(Constant.ERROR, properties.getProperty("login.error"));
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
			}
		}catch(Exception e){
			LOGGER.error("Error Exception - Login from  User Name : [{}], reference no: [{}]. Exception: [{}]",
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
				return new Response(Constant.SUCCESS, properties.getProperty("signup.usernameExist"));
			}else{
				User newUser = new User();
				newUser.setCreatedDate(new Date());
				newUser.setEmail(email);
				newUser.setUsername(username);
				newUser.setPassword(passwordAuthenticationService.generatePassword(password));
				newUser.setLock(false);
				newUser.setVerify(false);
				
				userService.saveOrUpdate(newUser);

				LOGGER.info("Success - Signup, Username: [{}], Ref No: [{}]", username, req.getReferenceNo());
				return new Response(Constant.SUCCESS, properties.getProperty("signup.success"));
			}
			
		}catch(Exception e){
			LOGGER.error("Error Exception - Signup from  User Name : [{}], reference no: [{}]. Exception: [{}]",
					username, req.getReferenceNo(), e.getMessage());
			return new Response(Constant.ERROR, properties.getProperty("server.exception"));
		}
	}
}
