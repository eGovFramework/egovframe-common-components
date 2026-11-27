package egovframework.com.sym.sym.bak.service;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 백업스케줄요일에 대한 model 클래스
 *
 * @author 김진만
 * @version 1.0
 * @see
 * <pre>
 * == 개정이력(Modification Information) ==
 *
 *   수정일       수정자           수정내용
 *  -------     --------    ---------------------------
 *  2010.09.01   김진만     최초 생성
 *  2025.05.21   dasomel    Lombok @Getter/@Setter 적용
 * </pre>
 */
@Getter
@Setter
public class BackupSchdulDfk implements Serializable {

	private static final long serialVersionUID = -6208617298024325398L;

	/**
	 * 백업작업ID
	 */
	private String backupOpertId;

	/**
	 * 실행스케줄요일
	 */
	private String executSchdulDfkSe;

	/**
	 * 실행스케줄요일명
	 */
	private String executSchdulDfkSeNm;

}