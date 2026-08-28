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
    void 주격_가(){
        // 받침 없는 명사는 주격 조사 "가"를 부착한다
        String expect = EgovStringUtil.getSubjectParticle("철수");
        Assertions.assertEquals( expect , "철수가" );
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

    // === 세 조사 메서드 공통 계약: null/빈문자열/비한글 일관성 ===

    @Test
    void 보조사_null_은_null(){
        Assertions.assertNull(EgovStringUtil.getAuxiliaryParticle(null));
    }

    @Test
    void 주격_null_은_null(){
        Assertions.assertNull(EgovStringUtil.getSubjectParticle(null));
    }

    @Test
    void 목적격_null_은_null(){
        Assertions.assertNull(EgovStringUtil.getObjectParticle(null));
    }

    @Test
    void 주격_빈문자열_받침없음_가(){
        Assertions.assertEquals(EgovStringUtil.getSubjectParticle(""), "가");
    }

    @Test
    void 주격_비한글_받침없음_가(){
        Assertions.assertEquals(EgovStringUtil.getSubjectParticle("PC"), "PC가");
    }

    @Test
    void 보조사_빈문자열_받침없음_는(){
        Assertions.assertEquals(EgovStringUtil.getAuxiliaryParticle(""), "는");
    }

    // === split(String, String): 구분자 길이별 동작 검증 ===

    // 한글자 구분자: 기존 동작 유지 확인(회귀 방지)
    @Test
    void splitWithSingleCharSeparator(){
        String[] result = EgovStringUtil.split("a,b,c", ",");
        Assertions.assertArrayEquals(new String[]{"a", "b", "c"}, result);
    }

    // 수정 전엔 index+1로 이동해 구분자의 두번째 글자가 다음 필드에 남았음
    @Test
    void splitWithTwoCharSeparator_noResidualChar(){
        String[] result = EgovStringUtil.split("a::b::c", "::");
        Assertions.assertArrayEquals(new String[]{"a", "b", "c"}, result);
    }

    // 동일한 수정을 3글자 구분자로도 검증
    @Test
    void splitWithThreeCharSeparator_noResidualChar(){
        String[] result = EgovStringUtil.split("a###b###c", "###");
        Assertions.assertArrayEquals(new String[]{"a", "b", "c"}, result);
    }

    // 구분자가 없으면 원본 문자열이 단일 요소로 반환되어야 함
    @Test
    void splitReturnsWholeStringWhenSeparatorNotFound(){
        String[] result = EgovStringUtil.split("abc", "::");
        Assertions.assertArrayEquals(new String[]{"abc"}, result);
    }

    // === split(String, String, int): 구분자 길이별 동작 검증 ===

    // 다중글자 구분자 + 배열 길이가 전체 필드 수와 정확히 일치하는 경우
    @Test
    void splitWithLimit_twoCharSeparator(){
        String[] result = EgovStringUtil.split("a::b::c", "::", 3);
        Assertions.assertArrayEquals(new String[]{"a", "b", "c"}, result);
    }

    // 배열 길이를 초과하는 나머지는 구분자를 포함한 채 마지막 필드에 남아야 함
    @Test
    void splitWithLimit_remainderKeptInLastField(){
        String[] result = EgovStringUtil.split("a::b::c::d", "::", 2);
        Assertions.assertArrayEquals(new String[]{"a", "b::c::d"}, result);
    }

}
