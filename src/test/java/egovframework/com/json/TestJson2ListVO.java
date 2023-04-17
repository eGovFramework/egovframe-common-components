package egovframework.com.json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class TestJson2ListVO {

	//@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		/*
		String jsonStr = "["
				+ "	{"
				+ "		\"title\":\"squat1\","
				+ "		\"description\":\"this is squat1\","
				+ "		\"trainClass\":\"squat1 class\","
				+ "		\"dataSetFile\":\"/squat1.csv\""
				+ "	},"
				+ "	{"
				+ "		\"title\":\"squat2\","
				+ "		\"description\":\"this is squat2\","
				+ "		\"trainClass\":\"squat2 class\","
				+ "		\"dataSetFile\":\"/squat2.csv\""
				+ "	},"
				+ "	{"
				+ "		\"title\":\"squat3\","
				+ "		\"description\":\"this is squat3\","
				+ "		\"trainClass\":\"squat3 class\","
				+ "		\"dataSetFile\":\"/squat3.csv\""
				+ "	},"
				+ "	{"
				+ "		\"title\":\"squat4\","
				+ "		\"description\":\"this is squat34\","
				+ "		\"trainClass\":\"squat4 class\","
				+ "		\"dataSetFile\":\"/squat4.csv\""
				+ "	},"
				+ "	{"
				+ "		\"title\":\"squat5\","
				+ "		\"description\":\"this is squat5\","
				+ "		\"trainClass\":\"squat5 class\","
				+ "		\"dataSetFile\":\"/squat5.csv\""
				+ "	}"
				+ "]"
				+ "";
		*/
		
		BufferedReader reader = null;
		String jsonStr = "";
		String path = TestJson2ListVO.class.getResource("").getPath();
		
		try {
			//reader = new BufferedReader(new FileReader("C:\\eGovFrameDev-4.0.0-64bit\\workspace\\egovframework-all-in-one-AllNew\\src\\test\\java\\egovframework\\com\\json\\list.json"));
			reader = new BufferedReader(new FileReader(path + "list.json"));
			String str;
			
			while ((str = reader.readLine()) != null) {
				System.out.println(str);
				jsonStr += str;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			List<DataSetInfo> readValue = objectMapper.readValue(jsonStr,
					new TypeReference<List<DataSetInfo>>() {
					});

			for (DataSetInfo vo : readValue) {
				//MapUtils.debugPrint(System.out, "map", vo);
				System.out.println(vo.getTitle());
				System.out.println(vo.getDescription());
				System.out.println(vo.getTrainClass());
				System.out.println(vo.getDataSetFile());
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
