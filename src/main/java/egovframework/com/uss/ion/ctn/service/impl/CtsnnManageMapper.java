package egovframework.com.uss.ion.ctn.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovMapper;

import egovframework.com.uss.ion.ctn.service.CtsnnManageVO;

/**
 * 경조관리에 대한 Mapper 인터페이스
 * @author 이용
 * @version 1.0
 */
@EgovMapper("ctsnnManageMapper")
public interface CtsnnManageMapper {

	List<CtsnnManageVO> selectCtsnnManageList(CtsnnManageVO ctsnnManageVO);

	int selectCtsnnManageListTotCnt(CtsnnManageVO ctsnnManageVO);

	CtsnnManageVO selectCtsnnManage(CtsnnManageVO ctsnnManageVO);

	void insertCtsnnManage(CtsnnManageVO ctsnnManageVO);

	void updateCtsnnManage(CtsnnManageVO ctsnnManageVO);

	void deleteCtsnnManage(CtsnnManageVO ctsnnManageVO);

	List<CtsnnManageVO> selectCtsnnManageConfmList(CtsnnManageVO ctsnnManageVO);

	int selectCtsnnManageConfmListTotCnt(CtsnnManageVO ctsnnManageVO);

	void updtCtsnnManageConfm(CtsnnManageVO ctsnnManageVO);
}
