package egovframework.com.json;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class TestJson2ListMap {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String jsonStr = "[{\"name\":\"김씨\", \"friends\":[\"a\", \"b\"]},{\"name\":\"최씨\", \"friends\":[\"c\", \"d\"]}]";

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			List<Map<String, Object>> readValue = objectMapper.readValue(jsonStr,
					new TypeReference<List<Map<String, Object>>>() {
					});

			for (Map<String, Object> map : readValue) {
				MapUtils.debugPrint(System.out, "map", map);
				List<String> friends = (List<String>) map.get("friends");
				for (String friend : friends) {
					System.out.println(friend);
				}
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
