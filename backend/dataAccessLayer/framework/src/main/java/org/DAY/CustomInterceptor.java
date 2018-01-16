/********************************************************************************
 * Copyright (c) 2015-2016 GE Digital. All rights reserved.                     *
 *                                                                              *
 * The copyright to the computer software herein is the property of GE Digital. *
 * The software may be used and/or copied only with the written permission of   *
 * GE Digital or in accordance with the terms and conditions stipulated in the  *
 * agreement/contract under which the software has been supplied.               *
 ********************************************************************************/

package org.DAY;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.DAY.utility.SessionTimeoutException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 204048703 on 1/12/2018.
 */
@Component
public class CustomInterceptor implements HandlerInterceptor {

    public String decodeToken(String encodedToken){
        String decodedStringToken = null;
        byte[] authToken = encodedToken.getBytes();
        byte[] decodedToken = Base64.getDecoder().decode(authToken);
        try {
            decodedStringToken = new String(decodedToken, "utf-8");
        } catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return  decodedStringToken;
    }

    private boolean validateToken(String decodedToken) {
        boolean isTokenValid = false;
        Long currentDtInMilliseconds = new Date().getTime();
        String[] parsedToken = decodedToken.split("#");
        Long dateInMilliseconds = new Long(parsedToken[0]);
        Long timeInMin = new Long(parsedToken[1]);
        Long timeInMilliseconds = timeInMin * 60 * 1000;
        Long newDateInMilliseconds = dateInMilliseconds + timeInMilliseconds;

        Date dt = new Date(dateInMilliseconds);
        Date newDt = new Date(newDateInMilliseconds);
//        System.out.println("***************Auth token Date = " + dt);
//        System.out.println("***************Auth token time = " + timeInMin);
//        System.out.println("***************Auth token new Date = " + newDt);
//        System.out.println("***************new Date in milli= " + newDateInMilliseconds);
//        System.out.println("***************cur Date in milli= " + currentDtInMilliseconds);
        if(newDateInMilliseconds < currentDtInMilliseconds){
            System.out.println("********request timeout");
            isTokenValid = false;
        } else {
            System.out.println("********request Good");
            isTokenValid = true;
        }
        return isTokenValid;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
        ServletException, IOException, SessionTimeoutException{
        boolean retVal = true;
        String decodedToken;
        String authToken = request.getHeader("auth-token");
        System.out.println("***************Auth token in interceptor = " + authToken);
        if(authToken != null && !authToken.trim().isEmpty()){
            decodedToken = decodeToken(authToken);
            System.out.println("***************Auth token Decoded = " + decodedToken);
            boolean isTokenValid = validateToken(decodedToken);
            if(!isTokenValid){
                throw new SessionTimeoutException();
                //response.sendError(440, "Request timeout");
            }
        } else if (!request.getRequestURI().equals("/internal/authorize")){
            throw new SessionTimeoutException();
        }
        return retVal;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView){
//        System.out.println("************* postHandle **************");
//        response.addHeader("testing", "1234567890");
//        System.out.println("Header = " + response.getHeaderNames());
//    }
}
