package egovframework.com.utl.fcc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * EgovFormBasedUUID 단위 테스트
 *
 * @author 공통컴포넌트
 * @since 2026.05.28
 */
public class EgovFormBasedUUIDTest {

    @Test
    void randomUUID_생성결과가_null이_아님() {
        EgovFormBasedUUID uuid = EgovFormBasedUUID.randomUUID();
        assertNotNull(uuid);
    }

    @Test
    void randomUUID_toString_형식이_8_4_4_4_12() {
        String uuidStr = EgovFormBasedUUID.randomUUID().toString();
        // xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
        assertTrue(uuidStr.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"),
                "UUID 문자열 형식이 맞지 않음: " + uuidStr);
    }

    @Test
    void randomUUID_버전은_4() {
        EgovFormBasedUUID uuid = EgovFormBasedUUID.randomUUID();
        assertEquals(4, uuid.version());
    }

    @Test
    void randomUUID_variant는_2() {
        EgovFormBasedUUID uuid = EgovFormBasedUUID.randomUUID();
        assertEquals(2, uuid.variant());
    }

    @Test
    void randomUUID_연속_생성시_서로_다름() {
        EgovFormBasedUUID uuid1 = EgovFormBasedUUID.randomUUID();
        EgovFormBasedUUID uuid2 = EgovFormBasedUUID.randomUUID();
        assertNotEquals(uuid1.toString(), uuid2.toString());
    }

    @Test
    void nameUUIDFromBytes_배열범위초과_버그_확인() {
        // nameUUIDFromBytes 내부에서 md5Bytes(length=8)의 인덱스 8에 접근하는 버그 존재
        // (md5Bytes[8] &= 0x3f 라인 — ArrayIndexOutOfBoundsException 발생)
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> EgovFormBasedUUID.nameUUIDFromBytes("test".getBytes()));
    }

    @Test
    void fromString_올바른_문자열_파싱() {
        String input = "550e8400-e29b-41d4-a716-446655440000";
        EgovFormBasedUUID uuid = EgovFormBasedUUID.fromString(input);
        assertNotNull(uuid);
        assertEquals(input, uuid.toString());
    }

    @Test
    void fromString_잘못된_형식_예외발생() {
        assertThrows(IllegalArgumentException.class,
                () -> EgovFormBasedUUID.fromString("invalid-uuid"));
    }

    @Test
    void fromString_구분자_부족시_예외발생() {
        assertThrows(IllegalArgumentException.class,
                () -> EgovFormBasedUUID.fromString("550e8400-e29b-41d4-a716"));
    }

    @Test
    void getLeastSignificantBits와_getMostSignificantBits_값_확인() {
        long msb = 0x550e8400e29b41d4L;
        long lsb = 0xa716446655440000L;
        EgovFormBasedUUID uuid = new EgovFormBasedUUID(msb, lsb);
        assertEquals(msb, uuid.getMostSignificantBits());
        assertEquals(lsb, uuid.getLeastSignificantBits());
    }

    @Test
    void equals_동일_비트값이면_true() {
        long msb = 0x550e8400e29b41d4L;
        long lsb = 0xa716446655440000L;
        EgovFormBasedUUID uuid1 = new EgovFormBasedUUID(msb, lsb);
        EgovFormBasedUUID uuid2 = new EgovFormBasedUUID(msb, lsb);
        assertTrue(uuid1.equals(uuid2));
    }

    @Test
    void equals_다른_비트값이면_false() {
        EgovFormBasedUUID uuid1 = new EgovFormBasedUUID(1L, 2L);
        EgovFormBasedUUID uuid2 = new EgovFormBasedUUID(3L, 4L);
        assertFalse(uuid1.equals(uuid2));
    }

    @Test
    void equals_null이면_false() {
        EgovFormBasedUUID uuid = new EgovFormBasedUUID(1L, 2L);
        assertFalse(uuid.equals(null));
    }

    @Test
    void equals_타입이_다르면_false() {
        EgovFormBasedUUID uuid = new EgovFormBasedUUID(1L, 2L);
        assertFalse(uuid.equals("not-a-uuid"));
    }

    @Test
    void hashCode_동일_비트값이면_동일() {
        long msb = 0x550e8400e29b41d4L;
        long lsb = 0xa716446655440000L;
        EgovFormBasedUUID uuid1 = new EgovFormBasedUUID(msb, lsb);
        EgovFormBasedUUID uuid2 = new EgovFormBasedUUID(msb, lsb);
        assertEquals(uuid1.hashCode(), uuid2.hashCode());
    }

    @Test
    void compareTo_같은_값이면_0() {
        EgovFormBasedUUID uuid1 = new EgovFormBasedUUID(10L, 20L);
        EgovFormBasedUUID uuid2 = new EgovFormBasedUUID(10L, 20L);
        assertEquals(0, uuid1.compareTo(uuid2));
    }

    @Test
    void compareTo_msb_큰쪽이_양수() {
        EgovFormBasedUUID smaller = new EgovFormBasedUUID(1L, 0L);
        EgovFormBasedUUID larger  = new EgovFormBasedUUID(2L, 0L);
        assertTrue(larger.compareTo(smaller) > 0);
        assertTrue(smaller.compareTo(larger) < 0);
    }

    @Test
    void compareTo_msb_동일하고_lsb_큰쪽이_양수() {
        EgovFormBasedUUID smaller = new EgovFormBasedUUID(5L, 1L);
        EgovFormBasedUUID larger  = new EgovFormBasedUUID(5L, 2L);
        assertTrue(larger.compareTo(smaller) > 0);
        assertTrue(smaller.compareTo(larger) < 0);
    }

    @Test
    void timestamp_version1_아니면_예외() {
        // randomUUID는 version 4이므로 timestamp() 호출 시 예외
        EgovFormBasedUUID uuid = EgovFormBasedUUID.randomUUID();
        assertThrows(UnsupportedOperationException.class, uuid::timestamp);
    }

    @Test
    void clockSequence_version1_아니면_예외() {
        EgovFormBasedUUID uuid = EgovFormBasedUUID.randomUUID();
        assertThrows(UnsupportedOperationException.class, uuid::clockSequence);
    }

    @Test
    void node_version1_아니면_예외() {
        EgovFormBasedUUID uuid = EgovFormBasedUUID.randomUUID();
        assertThrows(UnsupportedOperationException.class, uuid::node);
    }
}
