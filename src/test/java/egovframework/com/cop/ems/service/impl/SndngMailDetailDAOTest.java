package egovframework.com.cop.ems.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.egovframe.rte.fdl.cmmn.exception.FdlException;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.com.cmm.service.impl.EgovFileMngServiceImpl;
import egovframework.com.cmm.service.impl.FileManageDAO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.cop.ems.service.SndngMailVO;
import egovframework.com.test.EgovTestAbstractDAO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 발송메일 상세조회 DAO 단위 테스트
 *
 * @author 주레피
 *
 */

@ContextConfiguration(classes = { EgovTestAbstractDAO.class, SndngMailDetailDAOTest.class, })

@Configuration

@ImportResource({

        "classpath*:egovframework/spring/com/idgn/context-idgn-MailMsg.xml",

        "classpath*:egovframework/spring/com/idgn/context-idgn-File.xml",

        "classpath*:egovframework/spring/com/idgn/context-idgn-FileSysMntrng.xml",

})

@ComponentScan(

        useDefaultFilters = false,

        basePackages = {

                "egovframework.com.cop.ems.service.impl",

                "egovframework.com.cmm.service",

        },

        includeFilters = {

                @Filter(

                        type = FilterType.ASSIGNABLE_TYPE,

                        classes = {

                                EgovFileMngServiceImpl.class,

                                EgovFileMngUtil.class,

                                FileManageDAO.class,

                                SndngMailRegistDAO.class,

                                SndngMailDetailDAO.class,

                        }

                )

        }

)

@RequiredArgsConstructor
@Slf4j
// @Commit
public class SndngMailDetailDAOTest extends EgovTestAbstractDAO {
    /** EgovFileMngService */
    @Autowired
    @Qualifier("EgovFileMngService")
    private EgovFileMngService fileMngService;

    /** EgovFileMngUtil */
    @Autowired
    @Qualifier("EgovFileMngUtil")
    private EgovFileMngUtil fileUtil;

    /** File ID Generation */
    @Autowired
    @Qualifier("egovFileIdGnrService")
    private EgovIdGnrService egovFileIdGnrService;

    /** Message ID Generation */
    @Autowired
    @Qualifier("egovMailMsgIdGnrService")
    private EgovIdGnrService egovMailMsgIdGnrService;

    /** SndngMailRegistDAO */
    @Autowired
    private SndngMailRegistDAO sndngMailRegistDAO;

    /** SndngMailRegistDAO */
    @Autowired
    private SndngMailDetailDAO sndngMailDetailDAO;

    /**
     * 첨부파일(Mocking) 데이터 생성
     *
     */
    private MockMultipartFile getMockMultipartFile(String fileName, String contentType, String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        return new MockMultipartFile(fileName, fileName + "." + contentType, contentType, fileInputStream);
    }

    /**
     * 메일, 첨부파일 데이터 생성
     *
     */
    private void testData(final SndngMailVO sndngMailVO, final LoginVO loginVO) {
        // 메시지ID 설정
        String mssageId = "";
        try {
            mssageId = egovMailMsgIdGnrService.getNextStringId();
        } catch (FdlException eFdl) {
            log.error("FdlException egovMailMsgIdGnrService");
            fail("FdlException egovMailMsgIdGnrService");
        }
        sndngMailVO.setMssageId(mssageId);
        /**
         * 발송결과코드(CDK-COM-024) 설정
         * R   요청
         * F   실패
         * C   완료
         **/
        if (StringUtils.defaultIfBlank(sndngMailVO.getSndngResultCode(), "").isEmpty()) {
            sndngMailVO.setSndngResultCode("C");
        }
        sndngMailVO.setSj("[테스트메일] 단위 테스트"); // 제목 설정

        List<FileVO> _result = new ArrayList<FileVO>();
        String _atchFileId = "";
        MockMultipartFile mockMultipartFile = null;
        try {
            // 이미 존재하는 sample.png 파일로 첨부파일 mocking
            mockMultipartFile = getMockMultipartFile("sample.png", "png", "src/test/resources/egovframework/data/sample.png");
        } catch (IOException eIO) {
            log.error("IOException MultipartFile create");
            fail("IOException MultipartFile create");
        }

        final Map<String, MultipartFile> files = new HashMap<String, MultipartFile>();
        files.put("sample.png", mockMultipartFile);

        // 첨부파일 생성
        try {
            _result = fileUtil.parseFileInf(files, "MSG_", 0, "", "");
            _atchFileId = fileMngService.insertFileInfs(_result);
        } catch (Exception e) {
            log.error("Exception Mail attach file insert");
            fail("Exception test sndngMailVO attach file insert");
        } // 파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.

        String orignlFileList = "";
        for (int i = 0; i < _result.size(); i++) {
            FileVO fileVO = _result.get(i);
            orignlFileList = fileVO.getOrignlFileNm();
        }

        // 발신자 설정
        sndngMailVO.setDsptchPerson(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getId()));
        // 수신자 설정
        sndngMailVO.setRecptnPerson("open.egovframe@gmail.com"); // 수신자 설정
        sndngMailVO.setAtchFileId(_atchFileId); // 첨부파일ID 설정
        // 첨부파일명 설정
        sndngMailVO.setOrignlFileNm(orignlFileList);

        // 발송메일을 등록
        try {
            sndngMailRegistDAO.insertSndngMail(sndngMailVO);
        } catch (Exception e) {
            log.error("Exception test sndngMailVO insert");
            fail("Exception test sndngMailVO insert");
        }
    }

    /**
     * 발송메일 상세조회 테스트
     *
     */
    @Test
    public void testSelectSndngMail() {
        // given
        final SndngMailVO sndngMailVO = new SndngMailVO();
        sndngMailVO.setSndngResultCode("R"); // 발송결과코드를 '요청'으로 설정
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        testData(sndngMailVO , loginVO);

        // when
        SndngMailVO resultSndngMailVO = new SndngMailVO();
        try {
            resultSndngMailVO = sndngMailDetailDAO.selectSndngMail(sndngMailVO);
        } catch (Exception e) {
            log.error("Exception selectSndngMail");
            fail("Exception selectSndngMail");
            // error(e);
        }

        if (log.isDebugEnabled()) {
            log.debug("sndngMailVO, resultSndngMailVO = {}, {}", sndngMailVO, resultSndngMailVO);
            log.debug("getMssageId = {}, {}", sndngMailVO.getMssageId(), resultSndngMailVO.getMssageId());
            log.debug("getSndngResultCode = {}, {}", sndngMailVO.getSndngResultCode(), resultSndngMailVO.getSndngResultCode());
        }

        // then
        assert1(sndngMailVO, resultSndngMailVO);
    }

    private void assert1(final SndngMailVO sndngMailVO, final SndngMailVO resultSndngMailVO) {
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), sndngMailVO.getMssageId(), resultSndngMailVO.getMssageId());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), "R", sndngMailVO.getSndngResultCode());
        assertEquals(egovMessageSource.getMessage(FAIL_COMMON_SELECT), "요청", resultSndngMailVO.getSndngResultCode());
    }

    /**
     * 발송메일 삭제 테스트
     *
     */
    @Test
    public void testDeleteSndngMail() {
        // given
        final SndngMailVO sndngMailVO = new SndngMailVO();
        final LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        testData(sndngMailVO , loginVO);

        // when
        int result = 0;
        try {
            result = sndngMailDetailDAO.deleteSndngMail(sndngMailVO);
        } catch (Exception e) {
          // e.printStackTrace();
          log.error("Exception deleteSndngMail");
          // error(e);
          result = 0;
        }

        // then
        assertEquals(egovMessageSource.getMessage("fail.common.delete"), 1, result);
    }
}
