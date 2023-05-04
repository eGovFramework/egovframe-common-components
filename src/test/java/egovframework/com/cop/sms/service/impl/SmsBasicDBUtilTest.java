package egovframework.com.cop.sms.service.impl;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsBasicDBUtilTest {

	protected Logger egovLogger = LoggerFactory.getLogger(SmsBasicDBUtilTest.class);

	@Test
	public void test() {
		egovLogger.debug("getJavaHome={}", SystemUtils.getJavaHome());

		final Duration timeBetweenEvictionRunsMillis = Duration.ofMillis(1000L * 60L * 1L);

		egovLogger.debug("timeBetweenEvictionRunsMillis={}", timeBetweenEvictionRunsMillis);

		long seconds = timeBetweenEvictionRunsMillis.getSeconds();

		egovLogger.debug("seconds={}", seconds);

		assertEquals(60, seconds);
	}

}
