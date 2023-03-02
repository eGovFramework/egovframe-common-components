package egovframework.com.sec.rnc.mip.mva.sp.comm.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.SvcVO;
import egovframework.com.sec.rnc.mip.mva.sp.comm.vo.TrxInfoSvcVO;

/**
 * @Project     : 모바일 운전면허증 서비스 구축 사업
 * @PackageName : mip.mva.sp.comm.dao
 * @FileName    : SvcDAO.java
 * @Author      : Min Gi Ju
 * @Date        : 2022. 6. 7.
 * @Description : 서비스 DAO
 * ==================================================
 * DATE            AUTHOR           NOTE
 * ==================================================
 * 2022. 6. 7.    Min Gi Ju        최초생성
 */
@Repository("SvcDAO")
public class SvcDAO extends EgovAbstractMapper {

	@Resource(name="egov.sqlSession")
	public void setSqlSessionFactory(SqlSessionFactory sqlSession) {
		super.setSqlSessionFactory(sqlSession);
	}
	
	/**
	 * 서비스 조회
	 * 
	 * @MethodName : selectSvc
	 * @param svcCode
	 * @return 서비스정보
	 * @throws Exception
	 */
	public SvcVO selectSvc(String svcCode) {
		return (SvcVO) selectOne("selectSvc", svcCode);
	}

	/**
	 * 서비스 등록
	 * 
	 * @MethodName : insertSvc
	 * @param svc 서비스정보
	 * @throws Exception
	 */
	public void insertSvc(SvcVO svc) {
		insert("insertSvc", svc);
	}

	/**
	 * 거래 & 서비스정보 조회
	 * 
	 * @MethodName : selectTrxInfoSvc
	 * @param trxcode 거래코드
	 * @return 거래 & 서비스정보
	 * @throws Exception
	 */
	public TrxInfoSvcVO selectTrxInfoSvc(String trxcode) {
		return (TrxInfoSvcVO) selectOne("selectTrxInfoSvc", trxcode);
	}
}
