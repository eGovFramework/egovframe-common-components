package egovframework.com.cmm.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.exception.BaseRuntimeException;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.service.CmmnDetailCode;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.test.EgovAbstractTestJUnit4;
import lombok.extern.slf4j.Slf4j;

/**
 * 공통코드등 전체 업무에서 공용해서 사용해야 하는 서비스를 정의하기 위한 서비스 인터페이스 Test
 * 
 * @author 이백행
 * @since 2025.07.15
 * @version 4.3.0
 * @see
 *
 *      <pre>
 *  == 개정이력(Modification Information) ==
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2025.07.15  이백행          2025년 컨트리뷰션 최초 생성
 *
 *      </pre>
 */
@Slf4j
public class EgovCmmUseServiceImplTest extends EgovAbstractTestJUnit4 {

	@Autowired
	private EgovCmmUseService egovCmmUseService;

	@Autowired
	private FileManageDAO fileManageDAO;

	@Autowired
	private SqlSessionTemplate egovBatchSqlSessionTemplate;

	@Autowired
	private EgovIdGnrService egovFileIdGnrService;

	@Autowired
	private EgovMessageSource egovMessageSource;

	@Test
	public void test1selectCmmCodeDetail() {
		// given
		ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
		comDefaultCodeVO.setCodeId("COM001");

		if (log.isDebugEnabled()) {
			log.debug("comDefaultCodeVO={}", comDefaultCodeVO);
			log.debug("getCodeId={}", comDefaultCodeVO.getCodeId());
		}

		// when
		List<CmmnDetailCode> resultList = egovCmmUseService.selectCmmCodeDetail(comDefaultCodeVO);

		// then
		assertEquals(comDefaultCodeVO.getCodeId(), resultList.get(0).getCodeId());

		if (log.isDebugEnabled()) {
			log.debug("comDefaultCodeVO={}", comDefaultCodeVO);
			log.debug("getCodeId={}", comDefaultCodeVO.getCodeId());

			log.debug("resultList={}", resultList);
			log.debug("size={}", resultList.size());
		}

		for (CmmnDetailCode result : resultList) {
			if (log.isDebugEnabled()) {
				log.debug("result={}", result);
				log.debug("getCodeId={}", result.getCodeId());
				log.debug("getCode={}", result.getCode());
				log.debug("getCodeNm={}", result.getCodeNm());
				log.debug("getCodeDc={}", result.getCodeDc());
			}
		}
	}

	@Test
	public void test2selectCmmCodeDetails() {
		// given
		List<ComDefaultCodeVO> comDefaultCodeVOs = new ArrayList<>();
		ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
		comDefaultCodeVO.setCodeId("COM001");
		comDefaultCodeVOs.add(comDefaultCodeVO);

		comDefaultCodeVO = new ComDefaultCodeVO();
		comDefaultCodeVO.setCodeId("COM002");
		comDefaultCodeVOs.add(comDefaultCodeVO);

		// when
		Map<String, List<CmmnDetailCode>> resultList = egovCmmUseService.selectCmmCodeDetails(comDefaultCodeVOs);

		// then
		assertEquals(comDefaultCodeVOs.get(0).getCodeId(),
				resultList.get(comDefaultCodeVOs.get(0).getCodeId()).get(0).getCodeId());

		if (log.isDebugEnabled()) {
			log.debug("result={}", resultList);
			log.debug("size={}", resultList.size());
		}

		resultList.forEach((key, value) -> {
			if (log.isDebugEnabled()) {
				log.debug("key={}", key);
				log.debug("value={}", value);
			}

			value.forEach(result -> {
				if (log.isDebugEnabled()) {
					log.debug("result={}", result);
					log.debug("getCodeId={}", result.getCodeId());
					log.debug("getCode={}", result.getCode());
					log.debug("getCodeNm={}", result.getCodeNm());
					log.debug("getCodeDc={}", result.getCodeDc());
				}
			});
		});
	}

	@Test
	public void test3selectOgrnztIdDetail() {
		// given
		ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
		comDefaultCodeVO.setTableNm("COMTNORGNZTINFO");
		comDefaultCodeVO.setHaveDetailCondition("Y");
		comDefaultCodeVO.setDetailCondition("ORGNZT_0000000000000");

		if (log.isDebugEnabled()) {
			log.debug("comDefaultCodeVO={}", comDefaultCodeVO);
			log.debug("getTableNm={}", comDefaultCodeVO.getTableNm());
			log.debug("getHaveDetailCondition={}", comDefaultCodeVO.getHaveDetailCondition());
			log.debug("getDetailCondition={}", comDefaultCodeVO.getDetailCondition());
		}

		// when
		List<CmmnDetailCode> resultList = egovCmmUseService.selectOgrnztIdDetail(comDefaultCodeVO);

		// then
		assertEquals(comDefaultCodeVO.getTableNm(), resultList.get(0).getCodeId());

		if (log.isDebugEnabled()) {
			log.debug("comDefaultCodeVO={}", comDefaultCodeVO);
			log.debug("getTableNm={}", comDefaultCodeVO.getTableNm());
			log.debug("getHaveDetailCondition={}", comDefaultCodeVO.getHaveDetailCondition());
			log.debug("getDetailCondition={}", comDefaultCodeVO.getDetailCondition());

			log.debug("resultList={}", resultList);
			log.debug("size={}", resultList.size());
		}

		for (CmmnDetailCode result : resultList) {
			if (log.isDebugEnabled()) {
				log.debug("result={}", result);
				log.debug("getCodeId={}", result.getCodeId());
				log.debug("getCode={}", result.getCode());
				log.debug("getCodeNm={}", result.getCodeNm());
				log.debug("getCodeDc={}", result.getCodeDc());
			}
		}
	}

	@Test
	public void test4selectGroupIdDetail() {
		// given
		ComDefaultCodeVO comDefaultCodeVO = new ComDefaultCodeVO();
		comDefaultCodeVO.setTableNm("COMTNAUTHORGROUPINFO");
		comDefaultCodeVO.setHaveDetailCondition("Y");
		comDefaultCodeVO.setDetailCondition("GROUP_00000000000000");

		if (log.isDebugEnabled()) {
			log.debug("comDefaultCodeVO={}", comDefaultCodeVO);
			log.debug("getTableNm={}", comDefaultCodeVO.getTableNm());
			log.debug("getHaveDetailCondition={}", comDefaultCodeVO.getHaveDetailCondition());
			log.debug("getDetailCondition={}", comDefaultCodeVO.getDetailCondition());
		}

		// when
		List<CmmnDetailCode> resultList = egovCmmUseService.selectGroupIdDetail(comDefaultCodeVO);

		// then
		assertEquals(comDefaultCodeVO.getTableNm(), resultList.get(0).getCodeId());

		if (log.isDebugEnabled()) {
			log.debug("comDefaultCodeVO={}", comDefaultCodeVO);
			log.debug("getTableNm={}", comDefaultCodeVO.getTableNm());
			log.debug("getHaveDetailCondition={}", comDefaultCodeVO.getHaveDetailCondition());
			log.debug("getDetailCondition={}", comDefaultCodeVO.getDetailCondition());

			log.debug("resultList={}", resultList);
			log.debug("size={}", resultList.size());
		}

		for (CmmnDetailCode result : resultList) {
			if (log.isDebugEnabled()) {
				log.debug("result={}", result);
				log.debug("getCodeId={}", result.getCodeId());
				log.debug("getCode={}", result.getCode());
				log.debug("getCodeNm={}", result.getCodeNm());
				log.debug("getCodeDc={}", result.getCodeDc());
			}
		}
	}

	/**
	 * 마이바티스 배치 테스트 EgovSqlSessionTemplate
	 */
	@Test
//	@Commit
	public void test9batchEgovSqlSessionTemplate() {
		String atchFileId;
		try {
			atchFileId = egovFileIdGnrService.getNextStringId();
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}

//		final int MAX_COUNT = 1; // 일
//		final int MAX_COUNT = 10; // 십
//		final int MAX_COUNT = 100; // 백
//		final int MAX_COUNT = 1_000; // 천
//		final int MAX_COUNT = 10_000; // 만
		final int MAX_COUNT = 100_000; // 십만, 93.2714451 초
//		final int MAX_COUNT = 1000_000; // 백만
//		final int MAX_COUNT = 10_000_000; // 천만
//		final int MAX_COUNT = 100_000_000; // 억
//		final int MAX_COUNT = 1_000_000_000; // 조

		List<FileVO> fileList = new ArrayList<>();

		for (int i = 1; i < MAX_COUNT; i++) {
			FileVO fileVO = new FileVO();
			fileVO.setAtchFileId(atchFileId);
			fileVO.setFileSn(String.valueOf(i));

			fileVO.setFileMg(String.valueOf(0));

//			if (i == 2) {
//				fileVO.setFileMg("");
//			}

			fileList.add(fileVO);
		}

		String resultAtchFileId;
		try {
			resultAtchFileId = fileManageDAO.insertFileInfs(fileList);
		} catch (BaseRuntimeException e) {
			throw new BaseRuntimeException(egovMessageSource.getMessage("fail.common.insert"));
		} catch (Exception e) {
			throw new BaseRuntimeException(egovMessageSource.getMessage("fail.common.insert"));
		}

		if (log.isDebugEnabled()) {
			log.debug("resultAtchFileId={}", resultAtchFileId);
		}

//		egovBatchSqlSessionTemplate.flushStatements();
	}

	/**
	 * 마이바티스 배치2 테스트 EgovBatchSqlSessionTemplate
	 */
	@Test
//	@Commit
	public void test9batch2EgovBatchSqlSessionTemplate() {
		String atchFileId;
		try {
			atchFileId = egovFileIdGnrService.getNextStringId();
		} catch (FdlException e) {
			throw new BaseRuntimeException(e);
		}

		// insertFileMaster
		FileVO fileVO = new FileVO();
//		fileVO.setAtchFileId(null);
		fileVO.setAtchFileId(atchFileId);

		if (egovBatchSqlSessionTemplate.insert("insertFileMaster", fileVO) == 0) {
			throw new BaseRuntimeException(egovMessageSource.getMessage("fail.common.msg"));
		}

		// insertFileDetail

//		final int MAX_COUNT = 1; // 일
//		final int MAX_COUNT = 10; // 십
//		final int MAX_COUNT = 100; // 백
//		final int MAX_COUNT = 1_000; // 천
//		final int MAX_COUNT = 10_000; // 만
		final int MAX_COUNT = 100_000; // 십만, 48.0607165 초
//		final int MAX_COUNT = 1000_000; // 백만
//		final int MAX_COUNT = 10_000_000; // 천만
//		final int MAX_COUNT = 100_000_000; // 억
//		final int MAX_COUNT = 1_000_000_000; // 조

		for (int i = 1; i < MAX_COUNT; i++) {
			fileVO = new FileVO();
			fileVO.setAtchFileId(atchFileId);
			fileVO.setFileSn(String.valueOf(i));

			fileVO.setFileMg(String.valueOf(0));

//			if (i == 2) {
//				fileVO.setFileMg("");
//			}

			if (egovBatchSqlSessionTemplate.insert("insertFileDetail", fileVO) == 0) {
				throw new BaseRuntimeException(egovMessageSource.getMessage("fail.common.insert"));
			}

//			if (i == 1_000) {
//				egovBatchSqlSessionTemplate.flushStatements();
//			}
		}

//		egovBatchSqlSessionTemplate.flushStatements();
	}

}