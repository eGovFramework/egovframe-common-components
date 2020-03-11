package egovframework.com.uss.olp.cns.service.impl;

import java.util.List;

import egovframework.com.uss.olp.cns.service.CnsltManageDefaultVO;
import egovframework.com.uss.olp.cns.service.CnsltManageVO;
import egovframework.com.uss.olp.cns.service.EgovCnsltManageService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


/**
 *
 * 상담내용을 처리하는  구현 클래스
 * @author 공통서비스 개발팀 박정규
 * @since 2009.04.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.01  박정규          최초 생성
 *
 * </pre>
 */
@Service("CnsltManageService")
public class EgovCnsltManageServiceImpl extends EgovAbstractServiceImpl implements
        EgovCnsltManageService {

    @Resource(name="CnsltManageDAO")
    private CnsltManageDAO cnsltManageDAO;

    /** ID Generation */
	@Resource(name="egovCnsltManageIdGnrService")
	private EgovIdGnrService idgenService;


    /**
	 * 상담내용 글을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
    @Override
	public CnsltManageVO selectCnsltListDetail(CnsltManageVO vo) throws Exception {
        CnsltManageVO resultVO = cnsltManageDAO.selectCnsltListDetail(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

	/**
	 * 상담내용 글을 수정한다.(조회수를 수정)
	 * @param vo
	 * @exception Exception
	 */
    @Override
	public void updateCnsltInqireCo(CnsltManageVO vo) throws Exception {
    	cnsltManageDAO.updateCnsltInqireCo(vo);
    }

    /**
	 * 상담내용 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 * @exception Exception
	 */
    @Override
	public List<?> selectCnsltList(CnsltManageDefaultVO searchVO) throws Exception {
        return cnsltManageDAO.selectCnsltList(searchVO);
    }

    /**
	 * 상담내용 글 총 갯수를 조회한다.
	 * @param searchVO
	 * @return 글 총 갯수
	 */
    @Override
	public int selectCnsltListTotCnt(CnsltManageDefaultVO searchVO) {
		return cnsltManageDAO.selectCnsltListTotCnt(searchVO);
	}

	/**
	 * 상담내용 글을 등록한다.
	 * @param vo
	 * @exception Exception
	 */
    @Override
	public void insertCnsltDtls(CnsltManageVO vo) throws Exception {
    	String	cnsltId = idgenService.getNextStringId();

		vo.setCnsltId(cnsltId);

    	cnsltManageDAO.insertCnsltDtls(vo);
    }

    /**
	 * 작성비밀번호를 확인한다.
	 * @param vo
	 * @return 글 총 갯수
	 */
    @Override
	public int selectCnsltPasswordConfirmCnt(CnsltManageVO vo) {
		return cnsltManageDAO.selectCnsltPasswordConfirmCnt(vo);
	}

	/**
	 * 상담내용 글을 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    @Override
	public void updateCnsltDtls(CnsltManageVO vo) throws Exception {
    	cnsltManageDAO.updateCnsltDtls(vo);
    }

	/**
	 * 상담내용 글을 삭제한다.
	 * @param vo
	 * @exception Exception
	 */
    @Override
	public void deleteCnsltDtls(CnsltManageVO vo) throws Exception {
    	cnsltManageDAO.deleteCnsltDtls(vo);
    }


    /**
	 * 상담답변 글을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 * @exception Exception
	 */
    @Override
	public CnsltManageVO selectCnsltAnswerListDetail(CnsltManageVO vo) throws Exception {
        CnsltManageVO resultVO = cnsltManageDAO.selectCnsltAnswerListDetail(vo);
        if (resultVO == null)
            throw processException("info.nodata.msg");
        return resultVO;
    }

    /**
	 * 상담답변 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 * @exception Exception
	 */
    @Override
	public List<?> selectCnsltAnswerList(CnsltManageDefaultVO searchVO) throws Exception {
        return cnsltManageDAO.selectCnsltAnswerList(searchVO);
    }

    /**
	 * 상담답변 글 총 갯수를 조회한다.
	 * @param searchVO
	 * @return 글 총 갯수
	 */
    @Override
	public int selectCnsltAnswerListTotCnt(CnsltManageDefaultVO searchVO) {
		return cnsltManageDAO.selectCnsltListTotCnt(searchVO);
	}

	/**
	 * 상담답변 글을 수정한다.
	 * @param vo
	 * @exception Exception
	 */
    @Override
	public void updateCnsltDtlsAnswer(CnsltManageVO vo) throws Exception {
    	cnsltManageDAO.updateCnsltDtlsAnswer(vo);
    }

}
