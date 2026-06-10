package egovframework.com.utl.fcc.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *   2024.10.29		Chung10Kr		명사에 맞는 조사 반환 기능 개발
 */

public class EgovStringUtilTest {

    @Test
    void 보조사_은(){
        String expect = EgovStringUtil.getAuxiliaryParticle("이름");
        Assertions.assertEquals( expect , "이름은" );
    }

    @Test
    void 보조사_는(){
        String expect = EgovStringUtil.getAuxiliaryParticle("나이");
        Assertions.assertEquals( expect , "나이는" );
    }

    @Test
    void 주격_이(){
        String expect = EgovStringUtil.getSubjectParticle("광현");
        Assertions.assertEquals( expect , "광현이" );
    }
    @Test
    void 주격_null(){
        String expect = EgovStringUtil.getSubjectParticle("철수");
        Assertions.assertEquals( expect , "철수" );
    }

    @Test
    void 목적격_을(){
        String expect = EgovStringUtil.getObjectParticle("생일");
        Assertions.assertEquals( expect , "생일을" );
    }
    @Test
    void 목적격_를(){
        String expect = EgovStringUtil.getObjectParticle("나이");
        Assertions.assertEquals( expect , "나이를" );
    }

    @Test
    void 목적격_빈문자열_예외없음(){
        // 수정 전엔 StringIndexOutOfBoundsException, 수정 후 종성 없음(false) 처리로 "를" 부착
        String expect = EgovStringUtil.getObjectParticle("");
        Assertions.assertEquals( expect , "를" );
    }

    @Test
    void 보조사_비한글_영문(){
        String expect = EgovStringUtil.getAuxiliaryParticle("PC");
        Assertions.assertEquals( expect , "PC는" );
    }

    @Test
    void 목적격_비한글_숫자(){
        String expect = EgovStringUtil.getObjectParticle("3");
        Assertions.assertEquals( expect , "3를" );
    }


}
