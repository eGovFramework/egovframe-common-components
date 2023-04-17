package egovframework.com.stream.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
 *  2021.07.26  신용호          최초 생성 ( JDK8 Stream API - List Map Filter 구현 )
 *
 * </pre>
 */

public class TestStreamFilter2 {

	public static void main(String[] args) {
		// 
		Map<String, Object> map1 = new HashMap<>();
		map1.put("name", "junho85");
		map1.put("nickname", "June Kim");
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("name", "shin");
		map2.put("nickname", "June Shin");

		Map<String, Object> map3 = new HashMap<>();
		map3.put("name", "Park");
		map3.put("nickname", "June Park");

		Map<String, Object> map4 = new HashMap<>();
		map4.put("name", "Ahn");
		map4.put("nickname", "June Ahn");
		
		Map<String, Object> map5 = new HashMap<>();
		map5.put("name", "Yun");
		map5.put("nickname", "June Yun");
		
		ArrayList<Map<String, Object>> list = new ArrayList<>(Arrays.asList(map1, map2, map3, map4, map5));
		List<Map<String, Object>> newList1 = list.stream().filter(item->item.get("name").toString().equals("shin")).collect(Collectors.toList());;
		List<Map<String, Object>> newList2 = list.stream().filter(item->!item.get("name").toString().equals("shin")).collect(Collectors.toList());;

		System.out.println("list size1 = "+newList1.size());
		System.out.println("list size2 = "+newList2.size());
		
	}

}
