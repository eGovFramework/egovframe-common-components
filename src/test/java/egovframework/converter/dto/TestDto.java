package egovframework.converter.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class TestDto {

    private String id;

    private String password;

    private int tmpInt;

    private List<SubDto> subList;
    private SubDto[] subList2;

    private SubDto subDto;

    private List<String> strList;
    private String[] strList2;
}
