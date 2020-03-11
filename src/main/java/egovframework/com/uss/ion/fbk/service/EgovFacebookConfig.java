/*
 * Copyright 2014 the original author or authors.
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
 *
 * Spring Social Configuration.
 * @author Keith Donald
 */
package egovframework.com.uss.ion.fbk.service;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.uss.ion.fbk.service.impl.EgovSimpleConnectionSignUp;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

/**
 * @Class Name : EgovFacebookConfig.java
 * @Description : facebook을 위한 JavaConfig
 * @Modification Information
 *
 *   수정일     	수정자          		      수정내용
 *  -----------    --------------------    ---------------------------
 *  2014.11.10		표준프레임워크센터		      최초 생성
 * @author 표준프레임워크센터
 * @since 2014.11.10
 * @version 3.5
 */


@Configuration
public class EgovFacebookConfig {

	private String facebookId = EgovProperties.getProperty("facebook.appId");

	private String facebookappSecret = EgovProperties.getProperty("facebook.appSecret");

	@Resource(name="social.dataSource")
	private DataSource dataSource;

	/**
	 * When a new provider is added to the app, register its {@link ConnectionFactory} here.
	 * @see FacebookConnectionFactory
	 */
	@Bean
	@Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new FacebookConnectionFactory(facebookId, facebookappSecret));
		return registry;
	}

	/**
	 * Singleton data access object providing access to connections across all users.
	 */
	@Bean
	@Scope(value="singleton", proxyMode=ScopedProxyMode.INTERFACES)
	public UsersConnectionRepository usersConnectionRepository() {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
				connectionFactoryLocator(), Encryptors.noOpText());
		repository.setConnectionSignUp(new EgovSimpleConnectionSignUp());
		return repository;
	}

	/**
	 * Request-scoped data access object providing access to the current user's connections.
	 */
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository() {
	    FacebookUser user = FacebookSecurityContext.getCurrentUser();
	    return usersConnectionRepository().createConnectionRepository(user.getId());
	}

	/**
	 * A proxy to a request-scoped object representing the current user's primary Facebook account.
	 * @throws NotConnectedException if the user is not connected to facebook.
	 */
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public Facebook facebook() {
	    return connectionRepository().getPrimaryConnection(Facebook.class).getApi();
	}

}
