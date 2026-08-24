/*
 * Copyright The eGovFrame Open Community (http://open.egovframe.go.kr)).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.com.uss.ion.ans.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import egovframework.com.uss.ion.ans.service.AnnvrsryManageVO;

/**
 * {@link EgovAnnvrsryManageServiceImpl#getDateCount}의 D-day 계산 fallback 경로가
 * 월 오프셋·자릿수 오류로 StringIndexOutOfBoundsException 또는 잘못된 D-day를
 * 내지 않는지 검증한다.
 */
class EgovAnnvrsryManageServiceImplTest {

    private static long callGetDateCount(AnnvrsryManageVO vo) throws Exception {
        EgovAnnvrsryManageServiceImpl service = new EgovAnnvrsryManageServiceImpl();
        Method m = EgovAnnvrsryManageServiceImpl.class.getDeclaredMethod("getDateCount", AnnvrsryManageVO.class);
        m.setAccessible(true);
        return (long) m.invoke(service, vo);
    }

    @Test
    void getDateCount_yearlyRepeatWithoutStoredDate_usesTodayWithoutException() {
        // 매년반복이고 저장된 날짜가 없으면 오늘 날짜(월/일)로 조립한다.
        // 수정 전에는 0-based 월·미패딩으로 "2026621"(7자) 같은 문자열이 만들어져
        // substring(6,8)에서 StringIndexOutOfBoundsException이 발생했다.
        AnnvrsryManageVO vo = new AnnvrsryManageVO();
        vo.setReptitSe("1");
        vo.setAnnvrsryDe(null);

        long dday = assertDoesNotThrow(() -> callGetDateCount(vo),
                "저장 날짜가 없는 매년반복 기념일은 예외 없이 계산되어야 한다");
        assertEquals(0L, dday, "오늘 날짜로 조립되므로 D-day는 0이어야 한다");
    }

    @Test
    void getDateCount_emptyDateFallback_usesTodayMonth() throws Exception {
        // 반복이 아니고 날짜가 비어 있으면 오늘 날짜로 대상일을 설정한다.
        // 수정 전에는 Calendar.set의 month에 +1이 잘못 붙어 한 달 뒤로 설정되었다.
        AnnvrsryManageVO vo = new AnnvrsryManageVO();
        vo.setReptitSe("0");
        vo.setAnnvrsryDe("");

        assertEquals(0L, callGetDateCount(vo), "오늘로 설정되므로 D-day는 0이어야 한다(월 +1 오류 없음)");
    }

    @Test
    void getDateCount_storedEightDigitDate_unaffected() throws Exception {
        // 정상 8자리 저장 날짜 경로는 기존 동작을 유지한다(오늘이면 D-day 0).
        java.util.Calendar today = java.util.Calendar.getInstance();
        String todayStr = String.format("%04d%02d%02d",
                today.get(java.util.Calendar.YEAR),
                today.get(java.util.Calendar.MONTH) + 1,
                today.get(java.util.Calendar.DATE));

        AnnvrsryManageVO vo = new AnnvrsryManageVO();
        vo.setReptitSe("1");
        vo.setAnnvrsryDe(todayStr);

        assertEquals(0L, callGetDateCount(vo), "오늘 날짜(8자리) 기념일의 D-day는 0이어야 한다");
    }
}
