package egovframework.com.uat.uia.onepass.service.impl;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * 디티털원패스 연동을 처리하는 비즈니스 DAO 클래스
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
@Repository("egovOnepassDAO")
public class EgovOnepassDAO extends EgovComAbstractDAO {

	/**
	 * 입력한 사용자아이디의 중복여부를 체크하여 사용가능여부를 확인
	 * @param id
	 * @return LoginVO
	 * @exception Exception
	 */
	public int onePassCheckIdDplct(String checkId){
        return (Integer)selectOne("egovOnepassDAO.onePassCheckIdDplct", checkId);
    }

}
