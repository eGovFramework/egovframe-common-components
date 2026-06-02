package egovframework.com.sec.ram.service;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 권한관리에 대한 Vo 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이문준          최초 생성
 *
 * </pre>
 */
@Getter
@Setter
public class AuthorManageVO extends AuthorManage {

	private static final long serialVersionUID = 1L;

	List<AuthorManageVO> authorManageList;

}