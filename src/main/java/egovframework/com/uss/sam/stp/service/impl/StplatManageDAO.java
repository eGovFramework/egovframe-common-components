package egovframework.com.uss.sam.stp.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;
import egovframework.com.uss.sam.stp.service.StplatManageDefaultVO;
import egovframework.com.uss.sam.stp.service.StplatManageVO;


/**
 *
 * 약관내용을 처리하는 DAO 클래스
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
 *   2016.06.13  장동한          표준프레임워크 v3.6 개선
 *   2026.06.18  이백행          [2026년 컨트리뷰션] 불필요한 예외(throws Exception) 제거
 *
 * </pre>
 */
@Repository("StplatManageDAO")
public class StplatManageDAO extends EgovComAbstractDAO {


    /**
	 * 약관정보 글 목록에 대한 상세내용을 조회한다.
	 * @param vo
	 * @return 조회한 글
	 */
    public StplatManageVO selectStplatDetail(StplatManageVO vo) {

        return (StplatManageVO) selectOne("StplatManage.selectStplatDetail", vo);

    }

    /**
	 * 약관정보 글 목록을 조회한다.
	 * @param searchVO
	 * @return 글 목록
	 */
    public List<StplatManageVO> selectStplatList(StplatManageDefaultVO searchVO) {

        return selectList("StplatManage.selectStplatList", searchVO);

    }

    /**
	 * 약관정보 글 총 개수를 조회한다.
	 * @param searchVO
	 * @return 글 총 개수
	 */
    public int selectStplatListTotCnt(StplatManageDefaultVO searchVO) {

        return (Integer)selectOne("StplatManage.selectStplatListTotCnt", searchVO);

    }

	/**
	 * 약관정보 글을 등록한다.
	 * @param vo
	 */
    public void insertStplatCn(StplatManageVO vo) {

        insert("StplatManage.insertStplatCn", vo);

    }

	/**
	 * 약관정보 글을 수정한다.
	 * @param vo
	 */
    public void updateStplatCn(StplatManageVO vo) {

        update("StplatManage.updateStplatCn", vo);

    }

	/**
	 * 약관정보 글을 삭제한다.
	 * @param vo
	 */
    public void deleteStplatCn(StplatManageVO vo) {

        delete("StplatManage.deleteStplatCn", vo);

    }

}
