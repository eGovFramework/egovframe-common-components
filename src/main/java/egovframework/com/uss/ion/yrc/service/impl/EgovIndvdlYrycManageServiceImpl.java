package egovframework.com.uss.ion.yrc.service.impl;

import java.util.List;

import egovframework.com.uss.ion.yrc.service.EgovIndvdlYrycManageService;
import egovframework.com.uss.ion.yrc.service.IndvdlYrycManage;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * 개요
 * - 연차관리에 대한 ServiceImpl 클래스를 정의한다.
 *
 * 상세내용
 * - 연차관리에 대한 등록, 수정, 삭제, 조회 기능을 제공한다.
 * - 연차관리의 조회기능은 목록조회로 구분된다.
 * @author 이기하
 * @version 1.0
 * @created 2014.11.14
 */

@Service("egovIndvdlYrycManageService")
public class EgovIndvdlYrycManageServiceImpl extends EgovAbstractServiceImpl implements EgovIndvdlYrycManageService {

	@Resource(name="indvdlYrycDAO")
    private IndvdlYrycDAO indvdlYrycDAO;

	/**
	 * 개인별 연차를 조회 처리한다.
	 * @param indvdlYrycManage - 연차관리 model
	 */
	@Override
	public List<IndvdlYrycManage> selectIndvdlYrycManageList(IndvdlYrycManage indvdlYrycManage) throws Exception {
		List<IndvdlYrycManage> result = indvdlYrycDAO.selectIndvdlYrycManageList(indvdlYrycManage);
		return result;
	}

	/**
	 * 개인별 연차 리스트 갯수를 조회 처리한다.
	 * @param indvdlYrycManage - 연차관리 model
	 */
	@Override
	public int selectIndvdlYrycManageListTotCnt(IndvdlYrycManage indvdlYrycManage) throws Exception {
		return indvdlYrycDAO.selectIndvdlYrycManageListTotCnt(indvdlYrycManage);
	}

	/**
	 * 개인별 연차를 입력 처리한다.
	 * @param indvdlYrycManage - 연차관리 model
	 */
	@Override
	public void insertIndvdlYrycManage(IndvdlYrycManage indvdlYrycManage) throws Exception {
		indvdlYrycDAO.insertIndvdlYrycManage(indvdlYrycManage);
	}

	/**
	 * 개인별 연차를 수정 처리한다.
	 * @param indvdlYrycManage - 연차관리 model
	 */
	@Override
	public void updtIndvdlYrycManage(IndvdlYrycManage indvdlYrycManage) throws Exception {
		indvdlYrycDAO.updtIndvdlYrycManage(indvdlYrycManage);
	}

	/**
	 * 개인별 연차를 삭제 처리한다.
	 * @param indvdlYrycManage - 연차관리 model
	 */
	@Override
	public void deleteIndvdlYrycManage(IndvdlYrycManage indvdlYrycManage) throws Exception {
		indvdlYrycDAO.deleteIndvdlYrycManage(indvdlYrycManage);
	}

}
