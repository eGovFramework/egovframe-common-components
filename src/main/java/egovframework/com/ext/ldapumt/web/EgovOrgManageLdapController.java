/*
 * eGovFrame LDAP조직도관리
 * Copyright The eGovFrame Open Community (http://open.egovframe.go.kr)).
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
 * @author 전우성(슈퍼개발자K3)
 */
package egovframework.com.ext.ldapumt.web;

import java.util.Map;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.ext.ldapumt.service.EgovOrgManageLdapService;
import egovframework.com.ext.ldapumt.service.UcorgVO;
import egovframework.com.ext.ldapumt.service.UserVO;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EgovOrgManageLdapController {

	@Autowired
	private EgovOrgManageLdapService orgManageLdapService;

    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

    /**
     * 부서의 하위 부서 목록을 조회 한다.
     * @param dn
     * @param model
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "/ext/ldapumt/dpt/getDeptManageSublist.do")
	public ModelAndView selectDeptManageSublist(@RequestParam("dn") String dn, ModelMap model) throws Exception {
		model.addAttribute("deptManage", orgManageLdapService.selectDeptManageSubList(dn));

		ModelAndView modelAndView = new ModelAndView("jsonView", model);
		return modelAndView;
	}

	/**
	 * 등록된 부서의 상세정보를 조회한다.
	 * @param dn
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ext/ldapumt/dpt/getDeptManage.do")
	public ModelAndView selectDeptManage(@RequestParam("dn") String dn, ModelMap model) throws Exception {
		model.addAttribute("deptManage", orgManageLdapService.selectDeptManage(dn));

		ModelAndView modelAndView = new ModelAndView("jsonView", model);
		return modelAndView;
	}

	/**
	 * 등록된 사용자의 상세정보를 조회한다.
	 * @param dn
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ext/ldapumt/dpt/getUserManage.do")
	public ModelAndView selectUserManage(@RequestParam("dn") String dn, ModelMap model) throws Exception {
		model.addAttribute("userManage", orgManageLdapService.selectUserManage(dn));

		ModelAndView modelAndView = new ModelAndView("jsonView", model);
		return modelAndView;
	}

	/**
	 * 부서를 등록한다.
	 * @param parentDn 등록될 부서의 상위 부서
	 * @param ou 등록될 부서명
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ext/ldapumt/dpt/createNode.do")
	public ModelAndView createDeptManage(@RequestParam("dn") String parentDn, @RequestParam("text") String ou, ModelMap model) throws Exception {
		Map<String, Object> map = orgManageLdapService.insertDeptManage(parentDn, ou);

		model.addAttribute("deptManage", map);

		ModelAndView modelAndView = new ModelAndView("jsonView", model);

		return modelAndView;
	}

	/**
	 * 사용자를 등록한다.
	 * @param parentDn 등록될 사용자의 상위 부서
	 * @param ou 등록될 사용자명
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ext/ldapumt/dpt/createUserNode.do")
	public ModelAndView createUserManage(@RequestParam("dn") String parentDn, @RequestParam("text") String cn, ModelMap model) throws Exception {
		Map<String, Object> map = orgManageLdapService.insertUserManage(parentDn, cn);

		model.addAttribute("deptManage", map);

		ModelAndView modelAndView = new ModelAndView("jsonView", model);

		return modelAndView;
	}

	/**
	 * 부서를 삭제한다.
	 * @param dn 삭제할 부서의 DN
	 * @param model
	 * @return
	 * @throws Exception
	 * 하위부서까지 모두 삭제된다.
	 */
	@RequestMapping(value = "/ext/ldapumt/dpt/deleteNode.do")
	public ModelAndView removeDeptManage(@RequestParam("dn") String dn, ModelMap model) throws Exception {
		orgManageLdapService.deleteDeptManage(dn);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.delete"));

		ModelAndView modelAndView = new ModelAndView("jsonView", model);

		return modelAndView;
	}

	/**
	 * 부서의 이름을 변경한다.
	 * @param dn 변경될 부서의 DN
	 * @param name 변경될 이름
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ext/ldapumt/dpt/renameNode.do")
	public ModelAndView renameDeptManage(@RequestParam("id") String dn, @RequestParam("text") String name, ModelMap model) throws Exception {
		orgManageLdapService.renameDeptManage(dn, name);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));

		ModelAndView modelAndView = new ModelAndView("jsonView", model);

		return modelAndView;
	}

	/**
	 * 사용자의 이름을 변경한다.
	 * @param dn 변경될 사용자의 DN
	 * @param name 변경될 이름
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ext/ldapumt/dpt/renameUserNode.do")
	public ModelAndView renameUserManage(@RequestParam("id") String dn, @RequestParam("text") String name, ModelMap model) throws Exception {
		orgManageLdapService.renameUserManage(dn, name);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));

		ModelAndView modelAndView = new ModelAndView("jsonView", model);

		return modelAndView;
	}

	/**
	 * 조직을 이동한다.
	 * @param dn 이동할 대상 DN
	 * @param parentDn 이동될 DN
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ext/ldapumt/dpt/moveOrgNode.do")
	public ModelAndView moveOrgManage(@RequestParam("id") String dn, @RequestParam("parent") String parentDn, ModelMap model) throws Exception {
		orgManageLdapService.moveOrgManage(dn, parentDn);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));

		ModelAndView modelAndView = new ModelAndView("jsonView", model);

		return modelAndView;
	}

	/**
	 * 부서정보를 변경한다.
	 * @param ucorgVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ext/ldapumt/dpt/modifyDeptManage.do")
	public ModelAndView modifyDeptManage(@ModelAttribute("ucorgVO") UcorgVO ucorgVO,
            ModelMap model) throws Exception {
		orgManageLdapService.modifyDeptManage(ucorgVO);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));

		ModelAndView modelAndView = new ModelAndView("jsonView", model);

		return modelAndView;
	}

	/**
	 * 사용자 정보를 변경한다.
	 * @param userVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ext/ldapumt/dpt/modifyUserManage.do")
	public ModelAndView modifyUserManage(@ModelAttribute("userVO") UserVO userVO,
			ModelMap model) throws Exception {
		orgManageLdapService.modifyUserManage(userVO);

		model.addAttribute("message", egovMessageSource.getMessage("success.common.update"));

		ModelAndView modelAndView = new ModelAndView("jsonView", model);
		return modelAndView;
	}

	/**
	 * 조직도 트리화면으로 이동
	 * @return
	 * @throws Exception
	 */
	@IncludedInfo(name="LDAP 조직도 트리",order = 3100 ,gid = 100)
    @RequestMapping("/ext/ldapumt/dpt/selectDeptManageTreeView.do")
    public String selectDeptManageTreeView() throws Exception {

        return "egovframework/com/ext/ldapumt/EgovDeptManageTree";
    }

    /**
     * 조직도 그래프로 이동
     * @return String
     * @exception Exception
     */
	@IncludedInfo(name="LDAP 조직도 그래프",order = 3110 ,gid = 100)
    @RequestMapping("/ext/ldapumt/dpt/selectDeptManageOrgChartView.do")
    public String selectDeptManageOrgChartView() throws Exception {

    	return "egovframework/com/ext/ldapumt/EgovDeptManageOrgChart";
    }

}
