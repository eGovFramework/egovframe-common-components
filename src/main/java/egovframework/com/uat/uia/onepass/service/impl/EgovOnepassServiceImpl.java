package egovframework.com.uat.uia.onepass.service.impl;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.com.uat.uia.onepass.service.EgovOnepassService;

/**
 * 디티털원패스 연동을 처리하는 비즈니스 구현 클래스
 * @author 전자정부 표준프레임워크 정진오
 * @since 2021.05.30
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일       수정자           수정내용
 *  ----------   --------   ---------------------------
 *  2021.05.30   정진오           최초 생성
 *  
 *  </pre>
 */
@Service("egovOnepassService")
public class EgovOnepassServiceImpl extends EgovAbstractServiceImpl implements EgovOnepassService {

	@Resource(name="egovOnepassDAO")
	private EgovOnepassDAO egovOnepassDAO;

	/**
	 * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
	 * @param id
	 * @return LoginVO
	 * @exception Exception
	 */
    @Override
    public int onePassCheckIdDplct(String checkId) {
    	return egovOnepassDAO.onePassCheckIdDplct(checkId);
    }

}
