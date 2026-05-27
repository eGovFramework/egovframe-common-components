package egovframework.com.cmm.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.EgovMapper;

import egovframework.com.cmm.service.FileVO;

/**
 * @Class Name : FileMngMapper.java
 * @Description : 파일정보 관리를 위한 MyBatis 매퍼 인터페이스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2025.05.28.    이백행       @EgovMapper 인터페이스 방식으로 전환
 *
 * @author 공통 서비스 개발팀
 * @since 2025.05.28.
 * @version 1.0
 * @see
 *
 */
@EgovMapper("fileMngMapper")
public interface FileMngMapper {

	/**
	 * 파일 마스터 정보를 등록한다.
	 *
	 * @param vo
	 */
	void insertFileMaster(FileVO vo);

	/**
	 * 파일 상세 정보를 등록한다.
	 *
	 * @param vo
	 */
	void insertFileDetail(FileVO vo);

	/**
	 * 파일 상세 정보를 삭제한다.
	 *
	 * @param vo
	 */
	void deleteFileDetail(FileVO vo);

	/**
	 * 파일에 대한 목록을 조회한다.
	 *
	 * @param vo
	 * @return
	 */
	List<FileVO> selectFileList(FileVO vo);

	/**
	 * 파일 구분자에 대한 최대값을 구한다.
	 *
	 * @param fvo
	 * @return
	 */
	int getMaxFileSN(FileVO fvo);

	/**
	 * 파일에 대한 상세정보를 조회한다.
	 *
	 * @param fvo
	 * @return
	 */
	FileVO selectFileInf(FileVO fvo);

	/**
	 * 전체 파일을 삭제한다. (USE_AT = 'N' 처리)
	 *
	 * @param fvo
	 */
	void deleteCOMTNFILE(FileVO fvo);

	/**
	 * 파일명 검색에 대한 목록을 조회한다.
	 *
	 * @param fvo
	 * @return
	 */
	List<FileVO> selectFileListByFileNm(FileVO fvo);

	/**
	 * 파일명 검색에 대한 목록 전체 건수를 조회한다.
	 *
	 * @param fvo
	 * @return
	 */
	int selectFileListCntByFileNm(FileVO fvo);

	/**
	 * 이미지 파일에 대한 목록을 조회한다.
	 *
	 * @param vo
	 * @return
	 */
	List<FileVO> selectImageFileList(FileVO vo);

}
