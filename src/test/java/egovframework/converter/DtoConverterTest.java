package egovframework.converter;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;

import egovframework.com.cmm.converter.DtoConverter;
import egovframework.com.cmm.converter.strategy.dto.DtoConvertStrategy;
import egovframework.converter.dto.SubDto;
import egovframework.converter.dto.TestDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DtoConverterTest {

    public void nullCheckTest() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
//        list.add(null);

        log.debug("{}", list.contains(null));
    }

    @Test
    public void dtoConvertTest() throws Exception {
        //
        StopWatch stopWatch = new StopWatch("dtoTest");
        stopWatch.start();

        SubDto sub1 = new SubDto();
        sub1.setParam1("param1");
        sub1.setParam2("param2");

        List<SubDto> subList = new ArrayList<>();
        SubDto sv1 = new SubDto();
        SubDto sv2 = new SubDto();
        BeanUtils.copyProperties(sub1, sv1);
        BeanUtils.copyProperties(sub1, sv2);
        subList.add(sv1);
        subList.add(sv2);

        List<String> strList = new ArrayList<>();
        strList.add("1");
        strList.add("2");
        strList.add("3");

        TestDto dto = new TestDto();
        dto.setId("id");
        dto.setPassword("password");
        dto.setTmpInt(999);
        dto.setSubDto(sub1);
        dto.setSubList(subList);
        dto.setSubList2(subList.toArray(new SubDto[0]));
        dto.setStrList(strList);
        dto.setStrList2(strList.toArray(new String[0]));

        log.debug("dto  {}", dto);

        DtoConvertStrategy strategy = new DtoConvertStrategy() {

            @Override
            public Object convertValue(final PropertyDescriptor pd, final String key, final Object value) {
//                log.debug("what {}, {}", key, value);
//                log.debug("??   {}"    , (pd == null ? null : pd.getName()));
                if( String.class.isInstance(value) ) {
                    return value + "^??";
                }

                return value;
            }
        };

        DtoConverter.convertValue(dto, strategy, strategy);
        log.debug("dto  {}", dto);

        List<TestDto> list = new ArrayList<>();
        list.add(dto);

        log.debug("list  {}", list);


        DtoConverter.convertValue(list, strategy, strategy);

        log.debug("list  {}", list);

        stopWatch.stop();
        String rstTime = stopWatch.prettyPrint();
        log.debug("{}", rstTime);
        TaskInfo[] ti = stopWatch.getTaskInfo();
        for(TaskInfo info : ti) {
            log.debug("{}", info.getTaskName());
            log.debug("{}", info.getTimeNanos());
            log.debug("{}", info.getTimeSeconds());
        }
    }
}
