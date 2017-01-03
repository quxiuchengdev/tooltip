package com.qxcwl.tooltip.webservice.user;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * @author 曲修成
 * @className UserWebservice
 * @description
 * @date 2017-01-03 16:31:00
 */
@WebService
public class UserWebservice {

	@WebMethod
	public String sayHello(@WebParam(name = "name") String name){
		return name + "say : Hello World";
	}
}
