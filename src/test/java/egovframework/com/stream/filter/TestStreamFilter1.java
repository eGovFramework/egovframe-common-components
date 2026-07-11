package egovframework.com.stream.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * JDK 8 Stream API Test 구현
 * @author 표준프레임워크 신용호
 * @since 2021.07.26
 * @version 4.0
 * @see
 * <pre>
 *
 *  수정일              수정자          수정내용
 *  ----------  --------  ---------------------------
 *   2021.07.26  신용호          최초 생성 ( JDK8 Stream API - Filter 구현 )
 *   2026.07.11  이백행          [2026년 컨트리뷰션] 디버그 출력에 log.debug 적용
 *
 * </pre>
 */

@Slf4j
public class TestStreamFilter1 {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>(Arrays.asList("Apple","Banana","Melon","Grape","Strawberry"));
		List<String> newList = list.stream().filter(t->t.length()>5).collect(Collectors.toList());
		log.debug("list size = {}", newList.size());
		
	}

}
