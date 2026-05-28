package egovframework.com.uss.ion.yrc.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.uss.ion.yrc.service.IndvdlYrycManage;
import jakarta.annotation.Resource;

/**
 * 개요
 * - 연차관리에 대한 DAO 클래스를 정의한다.
 *
 * 상세내용
 * - 연차관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2014.11.14  이기하          최초 생성
 *   2026.05.28  dasomel         @EgovMapper 인터페이스 위임 방식으로 전환
 * </pre>
 *
 * @author 이기하
 * @version 1.0
 */
@Repository("indvdlYrycDAO")
public class IndvdlYrycDAO {

	@Resource(name = "indvdlYrycMapper")
	private IndvdlYrycMapper mapper;

	/**
	 * 연차를 조회처리한다.
	 * @param indvdlYrycManage - 연차관리 model
	 */
	public List<IndvdlYrycManage> selectIndvdlYrycManageList(IndvdlYrycManage indvdlYrycManage) throws Exception {
		return mapper.selectIndvdlYrycManageList(indvdlYrycManage);
	}

	/**
	 * 연차목록 총 개수를 조회한다.
	 * @param indvdlYrycManage - 연차관리 model
	 */
	public int selectIndvdlYrycManageListTotCnt(IndvdlYrycManage indvdlYrycManage) throws Exception {
		return mapper.selectIndvdlYrycManageListTotCnt(indvdlYrycManage);
	}

	/**
	 * 연차를 입력처리한다.
	 * @param indvdlYrycManage - 연차관리 model
	 */
	public void insertIndvdlYrycManage(IndvdlYrycManage indvdlYrycManage) throws Exception {
		mapper.insertIndvdlYrycManage(indvdlYrycManage);
	}

	/**
	 * 연차를 수정처리한다.
	 * @param indvdlYrycManage - 연차관리 model
	 */
	public void updtIndvdlYrycManage(IndvdlYrycManage indvdlYrycManage) throws Exception {
		mapper.updateIndvdlYrycManage(indvdlYrycManage);
	}

	/**
	 * 연차를 삭제처리한다.
	 * @param indvdlYrycManage - 연차관리 model
	 */
	public void deleteIndvdlYrycManage(IndvdlYrycManage indvdlYrycManage) throws Exception {
		mapper.deleteIndvdlYrycManage(indvdlYrycManage);
	}
}
