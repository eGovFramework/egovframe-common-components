package egovframework.com.json;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

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
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
