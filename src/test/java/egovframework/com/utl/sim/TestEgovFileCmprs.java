package egovframework.com.utl.sim;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import egovframework.com.utl.sim.service.EgovFileCmprs;
import lombok.extern.slf4j.Slf4j;

/**
 * commons-csv 테스트
 * 
 * @author 이백행
 * @since 2023-09-15
 */
@Slf4j
public class TestEgovFileCmprs {

    /**
     * commons-csv 테스트
     */
    @Test
    public void test() {
        // 행정표준코드 관리시스템

        // 기관코드 전체자료
        String specSource = "https://www.code.go.kr/etc/codeFullDown.do?codeseId=00001";
        String pathnameDestination = "target/test/code.go.kr/기관코드 전체자료.zip";
        String readLinesFilePathname = "target/test/code.go.kr/기관코드 전체자료/기관코드 전체자료.txt";

        // 법정동코드 전체자료
//        String specSource = "https://www.code.go.kr/etc/codeFullDown.do?codeseId=00002";
//        String pathnameDestination = "target/test/code.go.kr/법정동코드 전체자료.zip";

        // 도로명코드 전체자료
//        String specSource = "https://www.code.go.kr/etc/codeFullDown.do?codeseId=00321";
//        String pathnameDestination = "target/test/code.go.kr/도로명코드 전체자료.zip";
        // ???θ??ڵ?_??ü.txt (파일 이름, 디렉터리 이름 또는 볼륨 레이블 구문이 잘못되었습니다)
        // 압축이 잘못된 것 같음

        codeFullDown(specSource, pathnameDestination);
        decmprsFile(pathnameDestination);
//        header(readLinesFilePathname);
        sql00001(readLinesFilePathname);
    }

    private void codeFullDown(String specSource, String pathnameDestination) {
        URL source = null;
        try {
            source = new URL(specSource);
        } catch (MalformedURLException e) {
//            e.printStackTrace();
            log.error("MalformedURLException URL");
            fail("MalformedURLException URL");
        }

        File destination = new File(pathnameDestination);

        int connectionTimeoutMillis = 10_000;

        int readTimeoutMillis = connectionTimeoutMillis;

        try {
            FileUtils.copyURLToFile(source, destination, connectionTimeoutMillis, readTimeoutMillis);
        } catch (IOException e) {
//            e.printStackTrace();
            log.error("IOException copyURLToFile");
            fail("IOException copyURLToFile");
        }
    }

    private void decmprsFile(String source) {
        String target = source.substring(0, source.lastIndexOf("."));
        boolean result = false;
        try {
            result = EgovFileCmprs.decmprsFile(source, target);
        } catch (ArchiveException e) {
//            e.printStackTrace();
            log.error("ArchiveException decmprsFile");
            fail("ArchiveException decmprsFile");
        } catch (IOException e) {
//            e.printStackTrace();
            log.error("IOException decmprsFile");
            fail("IOException decmprsFile");
        }
        log.debug("result={}", result);
    }

    void header(String readLinesFilePathname) {
        Iterable<CSVRecord> records = null;
        try {
            records = CSVParser.parse(new File(readLinesFilePathname), Charset.forName("EUC-KR"), CSVFormat.TDF);
        } catch (IOException e) {
//            e.printStackTrace();
            log.error("IOException parse");
            fail("IOException parse");
        }
        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        for (CSVRecord record : records) {
            log.debug("getRecordNumber={}", record.getRecordNumber());
            if (record.getRecordNumber() > 1) {
                break;
            }
            int i = 0;
            for (String s : record.toList()) {
                log.debug(s);
                sb.append("String header");
                sb.append(i);
                sb.append(" = \"");
                sb.append(s);
                sb.append("\";\n");

                sb2.append(", header");
                sb2.append(i);

                i++;
            }
        }

        log.debug(sb.toString());
        log.debug(sb2.toString());
    }

    /**
     * 기관코드 전체자료 sql
     * 
     * @param pathnameDestination
     */
    private void sql00001(String readLinesFilePathname) {
        String header0 = "기관코드";
        String header1 = "전체기관명";
        String header2 = "최하위기관명";
        String header3 = "차수";
        String header4 = "서열";
        String header5 = "소속기관차수";
        String header6 = "차상위기관코드";
        String header7 = "최상위기관코드";
        String header8 = "대표기관코드";
        String header9 = "유형분류_대";
        String header10 = "유형분류_중";
        String header11 = "유형분류_소";
        String header12 = "우편번호";
        String header13 = "행정동코드";
        String header14 = "소재지코드";
        String header15 = "나머지주소";
        String header16 = "지번";
        String header17 = "전화번호";
        String header18 = "팩스번호";
        String header19 = "생성일자";
        String header20 = "폐지일자";
        String header21 = "변경일자";
        String header22 = "존폐여부";
        String header23 = "이전기관코드";

        Iterable<CSVRecord> records = null;
        try {
//            records = CSVFormat.TDF.parse(in);
            records = CSVParser.parse(new File(readLinesFilePathname), Charset.forName("EUC-KR"),
                    CSVFormat.TDF.builder()
                            .setHeader(header0, header1, header2, header3, header4, header5, header6, header7, header8,
                                    header9, header10, header11, header12, header13, header14, header15, header16,
                                    header17, header18, header19, header20, header21, header22, header23)
                            .setSkipHeaderRecord(true).build());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        sb.append("/* 기관코드 */\n\n");
        sb.append("TRUNCATE TABLE comtninsttcode;\n\n");
        sb.append("DELETE FROM comtninsttcode;\n\n");
        for (CSVRecord record : records) {
            String insttCode = record.get(header0);
            String allInsttNm = record.get(header1);
            String lowestInsttNm = record.get(header2);
//            String instt_abrv_nm = record.get(header0);
            String odr = record.get(header3);
            String ord = record.get(header4);
            String insttOdr = record.get(header5);
            String upperInsttCode = record.get(header6);
            String bestInsttCode = record.get(header7);
            String reprsntInsttCode = record.get(header8);
            String insttTyLclas = record.get(header9);
            String insttTyMlsfc = record.get(header10);
            String insttTySclas = record.get(header11);
            String telno = record.get(header17);
            String fxnum = record.get(header18);
            String creatDe = record.get(header19);
            String ablDe = record.get(header20);
            String ablEnnc = record.get(header22);
            String changeDe = record.get(header21);
//            String change_time = record.get(header0);
            String bsisDe = record.get(header19);
//            String sort_ordr = record.get(header0);
//            String frst_register_id = record.get(header0);
//            String frst_regist_pnttm = record.get(header0);
//            String last_updusr_id = record.get(header0);
//            String last_updt_pnttm = record.get(header0);

            sb.append("INSERT INTO comtninsttcode");
            sb.append(
                    "(instt_code, all_instt_nm, lowest_instt_nm, instt_abrv_nm, odr, ord, instt_odr, upper_instt_code, best_instt_code, reprsnt_instt_code, instt_ty_lclas, instt_ty_mlsfc, instt_ty_sclas, telno, fxnum, creat_de, abl_de, abl_ennc, change_de, change_time, bsis_de, sort_ordr, frst_register_id, frst_regist_pnttm, last_updusr_id, last_updt_pnttm)");
//            sb.append("VALUES('', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', 0, '', '', '', '');");
            sb.append("VALUES(");

            // instt_code 기관코드
            if ("NULL".equals(insttCode)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(insttCode);
                sb.append("'");
            }
            sb.append(",");

            // all_instt_nm 전체기관명
            if ("NULL".equals(allInsttNm)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(allInsttNm);
                sb.append("'");
            }
            sb.append(",");

            // lowest_instt_nm 최하위기관명
            if ("NULL".equals(lowestInsttNm)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(lowestInsttNm);
                sb.append("'");
            }
            sb.append(",");

            // instt_abrv_nm 기관약어명
            sb.append("NULL,");

            // odr 차수
            if ("NULL".equals(odr)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(odr);
                sb.append("'");
            }
            sb.append(",");

            // ord 서열
            if ("NULL".equals(ord)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(ord);
                sb.append("'");
            }
            sb.append(",");

            // instt_odr 기관차수
            if ("NULL".equals(insttOdr)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(insttOdr);
                sb.append("'");
            }
            sb.append(",");

            // upper_instt_code 상위기관코드
            if ("NULL".equals(upperInsttCode)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(upperInsttCode);
                sb.append("'");
            }
            sb.append(",");

            // best_instt_code 최상위기관코드
            if ("NULL".equals(bestInsttCode)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(bestInsttCode);
                sb.append("'");
            }
            sb.append(",");

            // reprsnt_instt_code 대표기관코드
            if ("NULL".equals(reprsntInsttCode)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(reprsntInsttCode);
                sb.append("'");
            }
            sb.append(",");

            // instt_ty_lclas 기관유형대분류
            if ("NULL".equals(insttTyLclas)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(insttTyLclas);
                sb.append("'");
            }
            sb.append(",");

            // instt_ty_mlsfc 기관유형중분류
            if ("NULL".equals(insttTyMlsfc)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(insttTyMlsfc);
                sb.append("'");
            }
            sb.append(",");

            // instt_ty_sclas 기관유형소분류
            if ("NULL".equals(insttTySclas)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(insttTySclas);
                sb.append("'");
            }
            sb.append(",");

            // telno 전화번호
            if ("NULL".equals(telno)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(telno);
                sb.append("'");
            }
            sb.append(",");

            // fxnum 팩스번호
            if ("NULL".equals(fxnum)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(fxnum);
                sb.append("'");
            }
            sb.append(",");

            // creat_de 생성일
            if ("NULL".equals(creatDe)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(creatDe);
                sb.append("'");
            }
            sb.append(",");

            // abl_de 폐지일
            if ("NULL".equals(ablDe)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(ablDe);
                sb.append("'");
            }
            sb.append(",");

            // abl_ennc 폐지유무
            if ("NULL".equals(ablEnnc)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(ablEnnc);
                sb.append("'");
            }
            sb.append(",");

            // change_de 변경일
            if ("NULL".equals(changeDe)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(changeDe);
                sb.append("'");
            }
            sb.append(",");

            // change_time 변경시간
            sb.append("NULL,");

            // bsis_de 기초일
            if ("NULL".equals(changeDe)) {
                sb.append("NULL");
            } else {
                sb.append("'");
                sb.append(bsisDe);
                sb.append("'");
            }
            sb.append(",");

            // sort_ordr 정렬순서
            sb.append("NULL,");

            // frst_register_id 최초등록자ID
            sb.append("NULL,");

            // frst_regist_pnttm 최초등록시점
            sb.append("NULL,");

            // last_updusr_id 최종수정자ID
            sb.append("NULL,");

            // last_updt_pnttm 최종수정시점
            sb.append("NULL");

            sb.append(");");
            sb.append("");
            sb.append("\n");
        }

        String data = sb.toString();
        log.debug(data);

        try {
            FileUtils.writeStringToFile(new File(readLinesFilePathname + ".sql"), data, StandardCharsets.UTF_8);
        } catch (IOException e) {
//            e.printStackTrace();
            log.error("IOException writeStringToFile");
            fail("IOException writeStringToFile");
        }

        log.debug(
                "\n실행\nset PGCLIENTENCODING=UTF8\npsql -U com -d com -a -f \"D:\\EGOVFRAME2\\eGovFrameDev-4.1.0-64bit\\workspace\\egovframe-common-components\\target\\test\\code.go.kr\\기관코드 전체자료\\기관코드 전체자료.txt.sql\"");
    }

}
