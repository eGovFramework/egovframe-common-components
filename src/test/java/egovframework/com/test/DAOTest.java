package egovframework.com.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:egovframework/spring/com/context-*.xml",
		"classpath*:egovframework/spring/com/idgn/context-*.xml" })
@ActiveProfiles({ "mysql", "dummy" })
public class DAOTest {
}
