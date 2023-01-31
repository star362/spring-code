package com.star.hanLP.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Description: <br>
 * Create Date: 2020-11-25 18:06 <br>
 *
 * @author wangyu@mvtech.com.cn
 */
@WebService()
public interface HelloWebService {
    @WebMethod
    public String Hello(@WebParam(name="name") String name);
}
