package egovframework.com.sec.rnc.mip.mva.sp.comm.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.TrxInfoVO;

/**
 * @Project : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.dao
 * @FileName : TrxInfoDAO.java
 * @Author : Min Gi Ju
 * @Date : 2022. 6. 7.
 * @Description : 거래정보 DAO
 * 
 *              <pre>
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 7.    Min Gi Ju        최초생성
 *   2024.08.09  이백행          시큐어코딩 Exception 제거
 *              </pre>
 */
@Repository("TrxInfoDAO")
public class TrxInfoDAO extends EgovAbstractMapper {

	@Override
	@Resource(name = "egov.sqlSession")
	public void setSqlSessionFactory(SqlSessionFactory sqlSession) {
		super.setSqlSessionFactory(sqlSession);
	}

	/**
	 * 거래정보 조회
	 * 
	 * @MethodName : selectTrxInfo
	 * @param trxcode 거래코드
	 * @return 거래정보
	 */
	public TrxInfoVO selectTrxInfo(String trxcode) {
		return (TrxInfoVO) selectOne("selectTrxInfo", trxcode);
	}

	/**
	 * 거래정보 등록
	 * 
	 * @MethodName : insertTrxInfo
	 * @param trxInfo 거래정보
	 */
	public int insertTrxInfo(TrxInfoVO trxInfo) {
		return insert("insertTrxInfo", trxInfo);
	}

	/**
	 * 거래정보 수정
	 * 
	 * @MethodName : updateTrxInfo
	 * @param trxInfo 거래정보
	 */
	public int updateTrxInfo(TrxInfoVO trxInfo) {
		return update("updateTrxInfo", trxInfo);
	}

	/**
	 * 거래정보 삭제
	 * 
	 * @MethodName : deleteTrxInfo
	 * @param trxcode 거래코드
	 */
	public int deleteTrxInfo(String trxcode) {
		return delete("deleteTrxInfo", trxcode);
	}

	/**
	 * VP 정보 저장
	 * 
	 * @MethodName : insertVp
	 * @param VP value
	 */
	public int insertVp(String vpName) {
		return insert("insertVp", vpName);
	}
}
