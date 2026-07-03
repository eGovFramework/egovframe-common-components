package egovframework.com.uss.olp.cns.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.olp.cns.service.CnsltManageDefaultVO;
import egovframework.com.uss.olp.cns.service.CnsltManageVO;



/**
 *
 * 상담내용을 처리하는 DAO 클래스
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
 *   2026.06.18  이백행          [2026년 컨트리뷰션] 불필요한 예외 제거
 *
 * </pre>
 */
@Repository("CnsltManageDAO")
public class CnsltManageDAO extends EgovComAbstractDAO {


    /**
	 * 상담내용 글 목록에 대한 상세내용을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 */
    public CnsltManageVO selectCnsltListDetail(CnsltManageVO vo) {

        return (CnsltManageVO) selectOne("CnsltManageDAO.selectCnsltListDetail", vo);

    }

	/**
	 * 상담내용 글을 수정한다.(조회수를 수정)
	 * @param vo
	 */
    public void updateCnsltInqireCo(CnsltManageVO vo) {

        update("CnsltManageDAO.updateCnsltInqireCo", vo);

    }

    /**
	 * 상담내용 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 */
    public List<EgovMap> selectCnsltList(CnsltManageDefaultVO searchVO) {

    	return selectList("CnsltManageDAO.selectCnsltList", searchVO);

    }

    /**
	 * 상담내용 글 총 개수를 조회한다.
	 * @param searchVO
	 * @return 글 총 개수
	 */
    public int selectCnsltListTotCnt(CnsltManageDefaultVO searchVO) {

        return (Integer)selectOne("CnsltManageDAO.selectCnsltListTotCnt", searchVO);

    }

	/**
	 * 상담내용 글을 등록한다.
	 * @param vo
	 */
    public void insertCnsltDtls(CnsltManageVO vo) {

        insert("CnsltManageDAO.insertCnsltDtls", vo);

    }

    /**
	 * 작성비밀번호를 확인한다.
	 * @param vo
	 * @return 글 총 개수
	 */
    public int selectCnsltPasswordConfirmCnt(CnsltManageVO vo) {

        return (Integer)selectOne("CnsltManageDAO.selectCnsltPasswordConfirmCnt", vo);

    }

	/**
	 * 상담내용 글을 수정한다.
	 * @param vo
	 */
    public void updateCnsltDtls(CnsltManageVO vo) {

        update("CnsltManageDAO.updateCnsltDtls", vo);

    }

	/**
	 * 상담내용 글을 삭제한다.
	 * @param vo
	 */
    public void deleteCnsltDtls(CnsltManageVO vo) {

        delete("CnsltManageDAO.deleteCnsltDtls", vo);

    }


    /**
	 * 상담답변 글 목록에 대한 상세내용을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 */
    public CnsltManageVO selectCnsltAnswerListDetail(CnsltManageVO vo) {

        return (CnsltManageVO) selectOne("CnsltManageDAO.selectCnsltAnswerListDetail", vo);

    }


    /**
	 * 상담답변 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 */
    public List<EgovMap> selectCnsltAnswerList(CnsltManageDefaultVO searchVO) {

    	return selectList("CnsltManageDAO.selectCnsltAnswerList", searchVO);

    }

    /**
	 * 상담답변 글 총 개수를 조회한다.
	 * @param searchVO
	 * @return 글 총 개수
	 */
    public int selectCnsltAnswerListTotCnt(CnsltManageDefaultVO searchVO) {

        return (Integer)selectOne("CnsltManageDAO.selectCnsltAnswerListTotCnt", searchVO);

    }

	/**
	 * 상담답변 글을 수정한다.
	 * @param vo
	 */
    public void updateCnsltDtlsAnswer(CnsltManageVO vo) {

        update("CnsltManageDAO.updateCnsltDtlsAnswer", vo);

    }


}
