package egovframework.com.cmm.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class EgovMybatisUtilTest {

	protected Logger egovLogger = LoggerFactory.getLogger(EgovMybatisUtilTest.class);

	@Test
	void test_a10_isEmpty_Collection_null() {
		List<String> o = null;
		boolean isEmpty = EgovMybatisUtil.isEmpty(o);
		egovLogger.debug("isEmpty={}", isEmpty);
		assertEquals(isEmpty, true);
	}

	@Test
	void test_a20_isEmpty_Collection_true() {
		List<String> o = new ArrayList<String>();
		boolean isEmpty = EgovMybatisUtil.isEmpty(o);
		egovLogger.debug("isEmpty={}", isEmpty);
		assertEquals(isEmpty, true);
	}

	@Test
	void test_a30_isEmpty_Collection_false() {
		List<String> o = new ArrayList<String>();
		o.add("test 이백행 2023-05-01");
		boolean isEmpty = EgovMybatisUtil.isEmpty(o);
		egovLogger.debug("isEmpty={}", isEmpty);
		assertEquals(isEmpty, false);
	}

	@Test
	void test_b10_isEmpty_Map_null() {
		Map<String, Object> o = null;
		boolean isEmpty = EgovMybatisUtil.isEmpty(o);
		egovLogger.debug("isEmpty={}", isEmpty);
		assertEquals(isEmpty, true);
	}

	@Test
	void test_b20_isEmpty_Map_true() {
		Map<String, Object> o = new HashMap<>();
		boolean isEmpty = EgovMybatisUtil.isEmpty(o);
		egovLogger.debug("isEmpty={}", isEmpty);
		assertEquals(isEmpty, true);
	}

	@Test
	void test_b30_isEmpty_Map_false() {
		Map<String, Object> o = new HashMap<>();
		o.put("test", "test 이백행 2023-05-01");
		boolean isEmpty = EgovMybatisUtil.isEmpty(o);
		egovLogger.debug("isEmpty={}", isEmpty);
		assertEquals(isEmpty, false);
	}

}
