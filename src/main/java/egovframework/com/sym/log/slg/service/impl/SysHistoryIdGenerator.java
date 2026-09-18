package egovframework.com.sym.log.slg.service.impl;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 시스템 이력 식별자를 생성한다.
 *
 * <p>기존 <code>HT_yyyyMMddHHmmssSSS</code> 형식을 그대로 유지하면서, 이미 사용한
 * 밀리초를 다시 내주지 않도록 다음 밀리초로 넘겨 동일 밀리초의 중복 생성을 막는다.</p>
 */
final class SysHistoryIdGenerator {

	private static final String PREFIX = "HT_";

	private static final DateTimeFormatter TIMESTAMP_FORMATTER =
			DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS", Locale.KOREA);

	/** 마지막으로 내준 밀리초. 시각이 뒤로 조정되어도 값이 되돌아가지 않는다. */
	private final AtomicLong lastIssuedMillis = new AtomicLong(Long.MIN_VALUE);

	String getNextId() {
		Instant issuedAt = Instant.ofEpochMilli(nextMillis());

		return PREFIX + TIMESTAMP_FORMATTER.format(issuedAt.atZone(ZoneId.systemDefault()));
	}

	private long nextMillis() {
		long previous;
		long next;

		do {
			previous = lastIssuedMillis.get();
			next = Math.max(System.currentTimeMillis(), previous + 1);
		} while (!lastIssuedMillis.compareAndSet(previous, next));

		return next;
	}
}
