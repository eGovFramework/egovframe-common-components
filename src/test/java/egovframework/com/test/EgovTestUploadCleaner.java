package egovframework.com.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import egovframework.com.cmm.service.FileVO;

/**
 * 테스트가 EgovFileMngUtil.parseFileInf() 로 저장한 첨부파일을 정리한다.
 *
 * DB 는 트랜잭션 롤백으로 되돌아가지만 저장소(Globals.fileStorePath)에 쓴 파일은 남는다.
 * 테스트 설정의 저장 경로는 Windows 형식("C:/egovframework/upload/")이라 그 외 OS 에서는
 * 프로젝트 루트 아래 "C:" 폴더가 생기므로, 파일을 지운 뒤 비어 있는 상대 경로 폴더도 함께 지운다.
 */
public final class EgovTestUploadCleaner {

	private final List<File> uploaded = new ArrayList<>();

	/** 정리 대상 파일을 등록한다. */
	public void track(List<FileVO> fileVOs) {
		if (fileVOs == null) {
			return;
		}
		for (FileVO fileVO : fileVOs) {
			uploaded.add(new File(fileVO.getFileStreCours(), fileVO.getStreFileNm()));
		}
	}

	/** 등록된 파일을 지우고, 비게 된 상대 경로 상위 폴더를 지운다. */
	public void clean() {
		for (File file : uploaded) {
			file.delete();
			// 절대 경로(실제 저장소)는 폴더를 건드리지 않는다. File.delete() 는 비어 있는 폴더만 지운다.
			File dir = file.getParentFile();
			while (dir != null && !dir.isAbsolute() && dir.delete()) {
				dir = dir.getParentFile();
			}
		}
		uploaded.clear();
	}
}
