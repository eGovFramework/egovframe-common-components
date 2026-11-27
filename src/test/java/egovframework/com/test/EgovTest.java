package egovframework.com.test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:egovframework/spring/com/context-*.xml",
		"classpath*:egovframework/spring/com/idgn/context-*.xml" })
@ActiveProfiles({ "mysql", "dummy" })
@Transactional
public class EgovTest {
}