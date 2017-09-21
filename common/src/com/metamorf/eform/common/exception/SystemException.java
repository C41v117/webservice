package com.metamorf.eform.common.exception;

import java.util.LinkedList;
import java.util.List;

import com.metamorf.eform.common.util.StringFunction;

public class SystemException extends RuntimeException  {
	
	private static final long serialVersionUID = -7912772858921407612L;
	private List<ErrorHolder> errors = new LinkedList<ErrorHolder>();

	public SystemException(ErrorHolder errorHolder) {
		getErrors().add(errorHolder);
	}
	
	public SystemException(Throwable e) {
		super(e);
	}

	public SystemException(List<ErrorHolder> errors) {
		this.errors = errors;
	}
	
	public List<ErrorHolder> getErrors() {
		return errors;
	}
	
	public SystemException(String message) {
		super(message);
	}

	public Boolean isEmpty() {
		return errors.size() == 0;
	}

	@Override
	public String getMessage() {
		String msg = super.getMessage();
		if(StringFunction.isEmpty(msg)){
			if(getErrors()!=null && getErrors().size()>0){
				StringBuilder builder = new StringBuilder();
				int i = 1;
				for (ErrorHolder errhole : getErrors()) {
					builder.append("error(").append(i++).append(")[").append(errhole.getError()).append("]:[");
					// print out the parameters, watch out for exception while printing parameters.
					try {
						for (Object param: errhole.getParameter()) {
							builder.append(param).append(",");
						}
					} catch(Exception e) {
						// spit the stacktrace on console
						e.printStackTrace();
					}
					builder.append("]");
				}
				return builder.toString();
			}else{
				return "error(0)["+msg+"]";
			}
		}else{
			return msg;
		}
	}
}
