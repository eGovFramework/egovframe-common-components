package egovframework.com.cmm.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.FileVO;
import jakarta.annotation.Resource;

/**
 * @Class Name : FileManageDAO.java
 * @Description : 파일정보 관리를 위한 데이터 처리 클래스
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 3. 25.     이삼섭    최초생성
 *    2025.05.28.      이백행    @EgovMapper 인터페이스 방식으로 전환
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 25.
 * @version
 * @see
 *
 */
@Repository("FileManageDAO")
public class FileManageDAO {

	@Resource(name = "fileMngMapper")
	private FileMngMapper fileMngMapper;

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 * @param fileList
	 * @return
	 * @throws Exception
	 */
	public String insertFileInfs(List<FileVO> fileList) throws Exception {
		FileVO vo = fileList.get(0);
		String atchFileId = vo.getAtchFileId();

		fileMngMapper.insertFileMaster(vo);

		Iterator<FileVO> iter = fileList.iterator();
		while (iter.hasNext()) {
			vo = iter.next();
			fileMngMapper.insertFileDetail(vo);
		}

		return atchFileId;
	}

	/**
	 * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
	 *
	 * @param vo
	 * @throws Exception
	 */
	public void insertFileInf(FileVO vo) throws Exception {
		fileMngMapper.insertFileMaster(vo);
		fileMngMapper.insertFileDetail(vo);
	}

	/**
	 * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
	 *
	 * @param fileList
	 * @throws Exception
	 */
	public void updateFileInfs(List<FileVO> fileList) throws Exception {
		Iterator<FileVO> iter = fileList.iterator();
		while (iter.hasNext()) {
			fileMngMapper.insertFileDetail(iter.next());
		}
	}

	/**
	 * 여러 개의 파일을 삭제한다.
	 *
	 * @param fileList
	 * @throws Exception
	 */
	public void deleteFileInfs(List<FileVO> fileList) throws Exception {
		Iterator<FileVO> iter = fileList.iterator();
		while (iter.hasNext()) {
			fileMngMapper.deleteFileDetail(iter.next());
		}
	}

	/**
	 * 하나의 파일을 삭제한다.
	 *
	 * @param fvo
	 * @throws Exception
	 */
	public void deleteFileInf(FileVO fvo) throws Exception {
		fileMngMapper.deleteFileDetail(fvo);
	}

	/**
	 * 파일에 대한 목록을 조회한다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<FileVO> selectFileInfs(FileVO vo) throws Exception {
		return fileMngMapper.selectFileList(vo);
	}

	/**
	 * 파일 구분자에 대한 최대값을 구한다.
	 *
	 * @param fvo
	 * @return
	 * @throws Exception
	 */
	public int getMaxFileSN(FileVO fvo) throws Exception {
		return fileMngMapper.getMaxFileSN(fvo);
	}

	/**
	 * 파일에 대한 상세정보를 조회한다.
	 *
	 * @param fvo
	 * @return
	 * @throws Exception
	 */
	public FileVO selectFileInf(FileVO fvo) throws Exception {
		return fileMngMapper.selectFileInf(fvo);
	}

	/**
	 * 전체 파일을 삭제한다.
	 *
	 * @param fvo
	 * @throws Exception
	 */
	public void deleteAllFileInf(FileVO fvo) throws Exception {
		fileMngMapper.deleteCOMTNFILE(fvo);
	}

	/**
	 * 파일명 검색에 대한 목록을 조회한다.
	 *
	 * @param fvo
	 * @return
	 * @throws Exception
	 */
	public List<FileVO> selectFileListByFileNm(FileVO fvo) throws Exception {
		return fileMngMapper.selectFileListByFileNm(fvo);
	}

	/**
	 * 파일명 검색에 대한 목록 전체 건수를 조회한다.
	 *
	 * @param fvo
	 * @return
	 * @throws Exception
	 */
	public int selectFileListCntByFileNm(FileVO fvo) throws Exception {
		return fileMngMapper.selectFileListCntByFileNm(fvo);
	}

	/**
	 * 이미지 파일에 대한 목록을 조회한다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<FileVO> selectImageFileList(FileVO vo) throws Exception {
		return fileMngMapper.selectImageFileList(vo);
	}
}
