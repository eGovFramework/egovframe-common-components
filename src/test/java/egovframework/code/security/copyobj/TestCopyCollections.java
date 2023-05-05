package egovframework.code.security.copyobj;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TestCopyCollections Test Class 구현
 * Catch a list of specific exception subtypes instead.
 * 
 * @author 표준프레임워크 신용호
 * @since 2022.11.11
 * @version 4.0
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *  2022.11.11  신용호          최초 생성
 *
   
 * </pre>
 */

public class TestCopyCollections {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestCopyCollections.class);
	
	public static void main(String[] args) {
		
		// JDK2
		//List<String> supplierNames = Arrays.asList("sup1", "sup2", "sup3");
		// JDK8
		List<String> supplierNames = Stream.of("sup1", "sup2", "sup3").collect(Collectors.toList());

		//List<String> copyNames = new ArrayList<>();
		List<String> copyNames;
		copyNames = Collections.unmodifiableList(supplierNames);
		//copyNames.addAll(supplierNames);
		//supplierNames.set(0,"sup1-edit");
		
		try {
			copyNames.set(0,"copy-edit");
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LOGGER.debug(supplierNames.get(0));
		LOGGER.debug(""+supplierNames.hashCode());
		LOGGER.debug(copyNames.get(0));
		LOGGER.debug(""+copyNames.hashCode());
		
	}

}
