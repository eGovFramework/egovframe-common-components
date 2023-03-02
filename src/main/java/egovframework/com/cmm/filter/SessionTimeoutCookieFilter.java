/*
 * Copyright 2008-2009 MOPAS(MINISTRY OF SECURITY AND PUBLIC ADMINISTRATION).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.com.cmm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*
* SessionTimeoutCookieFilter 
* @author 공통컴포넌트 팀 신용호
* @since 2020.06.17
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*  수정일               수정자           수정내용
*  ----------   --------   ---------------------------
*  2020.06.17   신용호            최초 생성
*
*/

public class SessionTimeoutCookieFilter implements Filter{

	@SuppressWarnings("unused")
	private FilterConfig config;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        long serverTime = System.currentTimeMillis();
        long sessionExpireTime = serverTime + httpRequest.getSession().getMaxInactiveInterval() * 1000;
        Cookie cookie = new Cookie("egovLatestServerTime", "" + serverTime);
        boolean secure = request.isSecure();
        if ( secure ) cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        httpResponse.addCookie(cookie);
        cookie = new Cookie("egovExpireSessionTime", "" + sessionExpireTime);
        if ( secure ) cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        
//        Date dateServer = new java.util.Date(serverTime);
//        Date dateExpiry = new java.util.Date(sessionExpireTime);
//        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//        String serverYMD = format.format(dateServer);
//        String expiryYMD = format.format(dateExpiry);
        //System.out.println("=====>>> serverYMD = "+serverYMD);
        //System.out.println("=====>>> expiryYMD = "+expiryYMD);
        //System.out.println("=====>>> server TimeStamp = "+serverTime);
        //System.out.println("=====>>> expire TimeStamp = "+sessionExpireTime);
        
        httpResponse.addCookie(cookie);

        chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public void destroy() {

	}
}
