package egovframework.config;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EgovConfigIdGenSequence {
/*
    @Bean(name="sequenceIdGnrService", destroyMethod = "destroy")
    public EgovSequenceIdGnrServiceImpl sequenceIdGnrService(@Qualifier("egov.dataSource") DataSource dataSource) {
    	EgovSequenceIdGnrServiceImpl egovSequenceIdGnrService = new EgovSequenceIdGnrServiceImpl();
        egovSequenceIdGnrService.setDataSource(dataSource);
        egovSequenceIdGnrService.setQuery("SELECT 1 FROM DUAL");
        egovSequenceIdGnrService.setUseBigDecimals(true);
        return egovSequenceIdGnrService;
    }
*/
}