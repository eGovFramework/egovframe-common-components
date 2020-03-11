package egovframework.com.uss.sam.ipm.service.impl;

import java.util.List;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.uss.sam.ipm.service.EgovIndvdlInfoPolicyService;
import egovframework.com.uss.sam.ipm.service.IndvdlInfoPolicy;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 개인정보보호정책를 처리하는 ServiceImpl Class 구현
 * @author 공통서비스 장동한
 * @since 2009.07.03
 * @version 1.0
 * @see <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.07.03  장동한          최초 생성
 *
 * </pre>
 */
@Service("egovIndvdlInfoPolicyService")
public class EgovIndvdlInfoPolicyServiceImpl extends EgovAbstractServiceImpl
        implements EgovIndvdlInfoPolicyService {

    @Resource(name = "onlineIndvdlInfoPolicyDao")
    private IndvdlInfoPolicyDao dao;

    @Resource(name = "egovIndvdlInfoPolicyIdGnrService")
    private EgovIdGnrService idgenService;

    /**
     * 개인정보보호정책를(을) 목록을 조회 한다.
     * @param OnlinePoll 회정정보가 담김 VO
     * @return List
     * @throws Exception
     */
    @Override
	public List<?> selectIndvdlInfoPolicyList(ComDefaultVO searchVO) throws Exception {
        return dao.selectIndvdlInfoPolicyList(searchVO);
    }

    /**
     * 개인정보보호정책를(을) 목록 전체 건수를(을) 조회한다.
     * @param searchVO  조회할 정보가 담긴 VO
     * @return int
     * @throws Exception
     */
    @Override
	public int selectIndvdlInfoPolicyListCnt(ComDefaultVO searchVO) throws Exception {
        return dao.selectIndvdlInfoPolicyListCnt(searchVO);
    }

    /**
     * 개인정보보호정책를(을) 상세조회 한다.
     * @param searchVO 조회할 정보가 담긴 VO
     * @return List
     * @throws Exception
     */
    @Override
	public IndvdlInfoPolicy selectIndvdlInfoPolicyDetail( IndvdlInfoPolicy indvdlInfoPolicy) throws Exception {
        return dao.selectIndvdlInfoPolicyDetail(indvdlInfoPolicy);
    }

    /**
     * 개인정보보호정책를(을) 등록한다.
     * @param indvdlInfoPolicy 개인정보보호정책 정보가 담긴 VO
     * @throws Exception
     */
    @Override
	public void insertIndvdlInfoPolicy(IndvdlInfoPolicy indvdlInfoPolicy)throws Exception {
        String sMakeId = idgenService.getNextStringId();
        indvdlInfoPolicy.setIndvdlInfoId(sMakeId);
        dao.insertIndvdlInfoPolicy(indvdlInfoPolicy);
    }

    /**
     * 개인정보보호정책를(을) 수정한다.
     * @param indvdlInfoPolicy 개인정보보호정책 정보가 담긴 VO
     * @throws Exception
     */
    @Override
	public void updateIndvdlInfoPolicy(IndvdlInfoPolicy indvdlInfoPolicy) throws Exception {
        dao.updateIndvdlInfoPolicy(indvdlInfoPolicy);
    }

    /**
     * 개인정보보호정책를(을) 삭제한다.
     * @param indvdlInfoPolicy 개인정보보호정책 정보가 담긴 VO
     * @throws Exception
     */
    @Override
	public void deleteIndvdlInfoPolicy(IndvdlInfoPolicy indvdlInfoPolicy) throws Exception {
        dao.deleteIndvdlInfoPolicy(indvdlInfoPolicy);
    }

}
