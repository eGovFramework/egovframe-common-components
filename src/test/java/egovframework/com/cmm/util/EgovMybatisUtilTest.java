package egovframework.com.cmm.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EgovMybatisUtilTest {

	protected Logger egovLogger = LoggerFactory.getLogger(EgovMybatisUtilTest.class);

	@Test
	public void test_a10_isEmpty_Collection_null() {
		List<String> o = null;
		boolean isEmpty = EgovMybatisUtil.isEmpty(o);
		egovLogger.debug("isEmpty={}", isEmpty);
		assertEquals(isEmpty, true);
	}

	@Test
	public void test_a20_isEmpty_Collection_true() {
		List<String> o = new ArrayList<String>();
		boolean isEmpty = EgovMybatisUtil.isEmpty(o);
		egovLogger.debug("isEmpty={}", isEmpty);
		assertEquals(isEmpty, true);
	}

	@Test
	public void test_a30_isEmpty_Collection_false() {
		List<String> o = new ArrayList<String>();
		o.add("test 이백행 2023-05-01");
		boolean isEmpty = EgovMybatisUtil.isEmpty(o);
		egovLogger.debug("isEmpty={}", isEmpty);
		assertEquals(isEmpty, false);
	}

	@Test
	public void test_b10_isEmpty_Map_null() {
		Map<String, Object> o = null;
		boolean isEmpty = EgovMybatisUtil.isEmpty(o);
		egovLogger.debug("isEmpty={}", isEmpty);
		assertEquals(isEmpty, true);
	}

	@Test
	public void test_b20_isEmpty_Map_true() {
		Map<String, Object> o = new HashMap<>();
		boolean isEmpty = EgovMybatisUtil.isEmpty(o);
		egovLogger.debug("isEmpty={}", isEmpty);
		assertEquals(isEmpty, true);
	}

	@Test
	public void test_b30_isEmpty_Map_false() {
		Map<String, Object> o = new HashMap<>();
		o.put("test", "test 이백행 2023-05-01");
		boolean isEmpty = EgovMybatisUtil.isEmpty(o);
		egovLogger.debug("isEmpty={}", isEmpty);
		assertEquals(isEmpty, false);
	}

}
