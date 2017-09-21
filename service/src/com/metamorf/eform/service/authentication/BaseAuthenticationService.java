package com.metamorf.eform.service.authentication;

import com.metamorf.eform.common.core.LoginSession;
import com.metamorf.eform.entity.user.User;
import com.metamorf.eform.interfaces.authentication.AuthenticationService;


public abstract class BaseAuthenticationService implements AuthenticationService<LoginSession> {

	protected LoginSession buildLoginSession(User user) {
		LoginSession loginSession = new LoginSession();
		loginSession.setUserName(user.getUserName());
		loginSession.setFullName(user.getFullName());
		loginSession.setBranches(user.getBranches());
		loginSession.setTitleDesc(user.getTitle().getDescription());
		loginSession.setTitleCode(user.getTitle().getCode());
		loginSession.setRoleName(user.getRole().getName());
		loginSession.setUserId(user.getId());
		loginSession.setFirstName(user.getFirstName());
		loginSession.setLastName(user.getLastName());
		loginSession.setLastLogInDate(user.getLastLogInDate());
		loginSession.setLob(user.getLob());
		loginSession.setLobBase(user.getLob());
		loginSession.setRoleId(user.getRole().getId());
		loginSession.setMustChangePassword(user.getMustChangePassword());
		loginSession.setLocationId(user.getLocationId());
		loginSession.setDivision(user.getDivision());
		return loginSession;
	}
}
