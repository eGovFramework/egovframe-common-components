package egovframework.com.utl.pao.service.impl;

import egovframework.com.utl.pao.service.EgovPrntngOutpt;
import egovframework.com.utl.pao.service.PrntngOutptVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 
 * 관인이미지에 대한 서비스 구현클래스를 정의한다
 * @author 공통서비스 개발팀 이중호
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  이중호          최초 생성
 *
 * </pre>
 */
@Service("PrntngOutpt")
public class EgovPrntngOutptImpl extends EgovAbstractServiceImpl implements EgovPrntngOutpt {
	 
	@Resource(name="PrntngOutptDAO")
	private PrntngOutptDAO prntngOutptDAO;
	
	/**
	 * 관인이미지를 조회한다.
	 */
	public PrntngOutptVO selectErncsl(PrntngOutptVO searchVO) throws Exception {
		return prntngOutptDAO.selectErncsl(searchVO);
	}

}
