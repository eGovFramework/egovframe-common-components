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
package egovframework.com.ext.ldapumt.service;

import java.io.Serializable;
import java.util.ArrayList;

/**
*
* Ldap데이터를 HTML jsTree프레임워크에서 사용할 수 있도록 변환한 객체
* @author 전우성
* @since 2014.10.12
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
*   수정일      수정자           수정내용
*  -------    --------    ---------------------------
*   2014.10.12  전우성          최초 생성
*
* </pre>
*/
public class LdapTreeObject implements Serializable {
	private static final long serialVersionUID = 1L;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public ArrayList<Child> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Child> children) {
		this.children = children;
	}

	public String text;
	public String id;
	public Icon icon = Icon.dept;

	public State state = new State();
	public ArrayList<Child> children = new ArrayList<Child>();

	enum Icon {
		user, dept
	}

	@SuppressWarnings("unused")
	private class State implements Serializable {
		private static final long serialVersionUID = 9002883980244257854L;
		boolean opened = true;

		public boolean isOpened() {
			return opened;
		}

		public void setOpened(boolean opened) {
			this.opened = opened;
		}

		public boolean isDisabled() {
			return disabled;
		}

		public void setDisabled(boolean disabled) {
			this.disabled = disabled;
		}

		boolean disabled = false;
	}

	public LdapTreeObject(String text, String id) {
		this.text = text;
		this.id = id;
	}

	@SuppressWarnings("unused")
	private class Child implements Serializable {
		private static final long serialVersionUID = 5457240443272184153L;

		public Child(String dn, String ou, boolean hasChildren, Icon icon) {
			this.id = dn;
			this.text = ou;
			this.children = hasChildren;
			this.icon = icon;
		}

		String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public boolean isChildren() {
			return children;
		}

		public void setChildren(boolean children) {
			this.children = children;
		}

		public Icon getIcon() {
			return icon;
		}

		public void setIcon(Icon icon) {
			this.icon = icon;
		}

		String text;
		boolean children = false;
		Icon icon = Icon.dept;
	}

	public void addChild(UcorgVO vo, boolean b) {
		children.add(new Child(vo.getDn(), vo.getOu(), b, Icon.dept));
	}

	public void addChild(UserVO vo) {
		children.add(new Child(vo.getDn(), vo.getCn(), false, Icon.user));

	}
}
