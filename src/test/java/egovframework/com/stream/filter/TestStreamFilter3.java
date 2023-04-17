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
 *  2021.07.26  신용호          최초 생성 ( JDK8 Stream API - List Map 중복제거 및 정렬 구현 )
 *
 * </pre>
 */


public class TestStreamFilter3 {

	public static void main(String[] args) {
		// 
		Map<String, Object> map1 = new HashMap<>();
		map1.put("name", "junho85");
		map1.put("nickname", "June Kim");
		map1.put("order", "03");
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("name", "shin");
		map2.put("nickname", "June Shin");
		map2.put("order", "02");

		Map<String, Object> map3 = new HashMap<>();
		map3.put("name", "Park");
		map3.put("nickname", "June Park");
		map3.put("order", "01");

		Map<String, Object> map4 = new HashMap<>();
		map4.put("name", "Yun");
		map4.put("nickname", "June Yun");
		map4.put("order", "04");
		
		Map<String, Object> map5 = new HashMap<>();
		map5.put("name", "Ahn");
		map5.put("nickname", "June Ahn");
		map5.put("order", "05");
		
		Map<String, Object> map6 = new HashMap<>();
		map6.put("name", "Yun");
		map6.put("nickname", "June Yun");
		map6.put("order", "04");
		
		ArrayList<Map<String, Object>> list = new ArrayList<>(Arrays.asList(map1, map2, map3, map4, map5, map6));
		List<Map<String, Object>> newList1 = list.stream().filter(item->item.get("name").toString().equals("shin")).collect(Collectors.toList());;

		System.out.println("list size1 = "+newList1.size());

		System.out.println("=== 지정된 항목 제거 ");
		List<Map<String, Object>> newList2 = list.stream().filter(item->!item.get("name").toString().equals("shin")).collect(Collectors.toList());;
		System.out.println("list size2 = "+newList2.size());
		newList2.stream().forEach(item -> System.out.println("name = " + item.get("name") + ""));
		
		System.out.println("=== 중복제거 ");
		newList2.stream().distinct().forEach(item -> System.out.println("name = " + item.get("name") + ""));
		
		System.out.println("=== 정렬 (Reverse) ");
		newList2.stream()
		.sorted( (o1, o2) -> o2.get("order").toString().compareTo(o1.get("order").toString())  )
		.forEach(item -> System.out.println("name = " + item.get("name") + ""));

		System.out.println("=== 정렬 (Forward) ");
		newList2.stream()
		.sorted( (o1, o2) -> o1.get("order").toString().compareTo(o2.get("order").toString())  )
		.forEach(item -> System.out.println("name = " + item.get("name") + ""));

	}

}
