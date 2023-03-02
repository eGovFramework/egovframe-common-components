package egovframework.com.sec.rnc.mip.mva.sp.comm.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.TrxInfoVO;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.dao
 * @FileName    : TrxInfoDAO.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 7.
 * @Description : 거래정보 DAO
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 7.    Min Gi Ju        최초생성
 */
@Repository("TrxInfoDAO")
public class TrxInfoDAO extends EgovAbstractMapper {

	@Resource(name="egov.sqlSession")
	public void setSqlSessionFactory(SqlSessionFactory sqlSession) {
		super.setSqlSessionFactory(sqlSession);
	}
	
	/**
	 * 거래정보 조회
	 * 
	 * @MethodName : selectTrxInfo
	 * @param trxcode 거래코드
	 * @return 거래정보
	 * @throws Exception
	 */
	public TrxInfoVO selectTrxInfo(String trxcode) {
		return (TrxInfoVO) selectOne("selectTrxInfo", trxcode);
	}

	/**
	 * 거래정보 등록
	 * 
	 * @MethodName : insertTrxInfo
	 * @param trxInfo 거래정보
	 * @throws Exception
	 */
	public void insertTrxInfo(TrxInfoVO trxInfo) {
		insert("insertTrxInfo", trxInfo);
	}

	/**
	 * 거래정보 수정
	 * 
	 * @MethodName : updateTrxInfo
	 * @param trxInfo 거래정보
	 * @throws Exception
	 */
	public void updateTrxInfo(TrxInfoVO trxInfo) {
		update("updateTrxInfo", trxInfo);
	}

	/**
	 * 거래정보 삭제
	 * 
	 * @MethodName : deleteTrxInfo
	 * @param trxcode 거래코드
	 * @throws Exception
	 */
	public void deleteTrxInfo(String trxcode) {
		delete("deleteTrxInfo", trxcode);
	}
	
	/**
	 * VP 정보 저장
	 * 
	 * @MethodName : insertVp
	 * @param VP value
	 * @throws Exception
	 */
	public void insertVp(String vpName) {
		insert("insertVp", vpName);
	}
}
