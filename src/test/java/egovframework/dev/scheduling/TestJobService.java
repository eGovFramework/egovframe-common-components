package egovframework.dev.scheduling;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


/**
 * 게시물 통계 집계를 위한 스케줄링 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.04.16
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일         수정자        수정내용
 *  ----------   --------    ---------------------------
 *  2024.10.16   신용호        최초 생성
 *
 *  </pre>
 */

@Service("testJobService")
@Slf4j
public class TestJobService {

	/**
	 * 게시물 통계를 위한 집계를 하루단위로 작업하는 배치 프로그램
	 * @exception Exception
	 */
	public void runJob() throws Exception {
		log.debug("===>>> Run Job(Invoke) ~~~!!!");
	}
}
