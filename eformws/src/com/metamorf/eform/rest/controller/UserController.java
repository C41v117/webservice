package com.metamorf.eform.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metamorf.eform.interfaces.settings.IAppFunctionService;
import com.metamorf.eform.interfaces.user.IRuntimeUserMobileService;
import com.metamorf.eform.interfaces.user.IUserService;
import com.metamorf.eform.rest.config.Constant;
import com.metamorf.eform.rest.model.LoginResponseModel;

@Controller
@RequestMapping("/user")
public class UserController extends BaseRestController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IRuntimeUserMobileService runtimeUserMobileService;
	@Autowired
	private IAppFunctionService appFunctionService;

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/ping", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody LoginResponseModel ping() throws Exception {
		return new LoginResponseModel(Constant.SUCCESS, "");
	}

}
