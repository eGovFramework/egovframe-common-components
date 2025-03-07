package egovframework.config;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EgovConfigIdGenTable {
/*
    @Bean(name="tableIdGnrService", destroyMethod = "destroy")
    public EgovTableIdGnrServiceImpl tableIdGnrService(@Qualifier("egov.dataSource") DataSource dataSource, EgovIdGnrStrategyImpl prefixIdGnrStrategy) {
        EgovTableIdGnrServiceImpl egovTableIdGnrService = new EgovTableIdGnrServiceImpl();
        egovTableIdGnrService.setDataSource(dataSource);
        egovTableIdGnrService.setTable("COMTECOPSEQ");
        egovTableIdGnrService.setTableName("TEST_ID");
        egovTableIdGnrService.setBlockSize(10);
        egovTableIdGnrService.setStrategy(prefixIdGnrStrategy);
        return egovTableIdGnrService;
    }

    @Bean
    public EgovIdGnrStrategyImpl prefixIdGnrStrategy() {
        EgovIdGnrStrategyImpl egovIdGnrStrategy = new EgovIdGnrStrategyImpl();
        egovIdGnrStrategy.setPrefix("TEST");
        egovIdGnrStrategy.setCipers(20);
        egovIdGnrStrategy.setFillChar('0');
        return egovIdGnrStrategy;
    }
*/
}