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
 * Simple little @Controller that invokes Facebook and renders the result.
 * The injected {@link Facebook} reference is configured with the required authorization credentials for the current user behind the scenes.
 * @author Keith Donald
 */
package egovframework.com.uss.ion.fbk.web;

import javax.inject.Inject;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.cmm.annotation.IncludedInfo;

/**
 * Facebook을 처리하는 Controller Class 구현
 * @author 표준프레임워크센터
 * @since 2014.11.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일     	수정자          		      수정내용
 *  -----------    --------------------    ---------------------------
 *  2014.11.10		표준프레임워크센터		      최초 생성
 *  2018.10.02		신용호		      profile 호출 수정
 *  </pre>
 */
@Controller
public class EgovFacebookController {

	private final Facebook facebook;

	@Inject
	public EgovFacebookController(Facebook facebook) {
		this.facebook = facebook;
	}

	@Inject
	private ConnectionRepository connectionRepository;

	/**
	 * facebook 연동을 위한 목록을 보여준다.
	 * @return String - 리턴 Url
	 */
	@IncludedInfo(name="Facebook 연동",order = 831 ,gid = 50)
	@RequestMapping(value = "/uss/ion/fbk/facebook.do", method = RequestMethod.GET)
	public String home() {
		return "egovframework/com/uss/ion/fbk/EgovFacebookHome";
	}

	/**
	 * facebook 담벼락 목록을 보여준다.
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/uss/ion/fbk/feed.do", method=RequestMethod.GET)
	public String showFeed(Model model) {
		model.addAttribute("feed", facebook.feedOperations().getFeed());
		return "egovframework/com/uss/ion/fbk/EgovFacebookFeed";
	}

	/**
	 * facebook 담벼락 내용을 입력한다.
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/uss/ion/fbk/feed.do", method=RequestMethod.POST)
	public String postUpdate(String message) {
		facebook.feedOperations().updateStatus(message);
		return "redirect:/uss/ion/fbk/feed.do";
	}

	/**
	 * facebook 앨범 목록을 보여준다.
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/uss/ion/fbk/albums.do", method=RequestMethod.GET)
	public String showAlbums(Model model) {
		model.addAttribute("albums", facebook.mediaOperations().getAlbums());
		return "egovframework/com/uss/ion/fbk/EgovFacebookAlbums";
	}

	/**
	 * facebook 앨범을 보여준다.
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/uss/ion/fbk/album/{albumId}", method=RequestMethod.GET)
	public String showAlbum(@PathVariable("albumId") String albumId, Model model) {
		model.addAttribute("album", facebook.mediaOperations().getAlbum(albumId));
		model.addAttribute("photos", facebook.mediaOperations().getPhotos(albumId));
		return "egovframework/com/uss/ion/fbk/EgovFacebookAlbum";
	}

	/**
	 * facebook profile을 보여준다.
	 * @return String - 리턴 Url
	 */
	@RequestMapping(value="/uss/ion/fbk/profile.do", method=RequestMethod.GET)
	public String profile(Model model) {
		Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
		if (connection == null) {
			return "redirect:/connect/facebook";
		}
		//model.addAttribute("profile", connection.getApi().userOperations().getUserProfile());
		String [] fields = { "id", "email",  "first_name", "last_name" };
		User userProfile = facebook.fetchObject("me", User.class, fields);
		model.addAttribute("profile", userProfile);
		return "egovframework/com/uss/ion/fbk/EgovFacebookProfile";
	}

}
