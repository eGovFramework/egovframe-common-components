/*
 * eGovFrame Easy Batch
 * Copyright The eGovFrame Open Community (http://open.egovframe.go.kr)).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author 서경석(슈퍼개발자K3)
 */
package egovframework.com.ext.easybatch.item;

import java.util.List;

import egovframework.rte.bat.core.item.database.EgovJdbcBatchItemWriter;
import egovframework.rte.bat.core.item.database.support.EgovMethodMapItemPreparedStatementSetter;
import egovframework.rte.bat.core.item.file.transform.EgovFieldExtractor;
import egovframework.rte.bat.core.item.file.transform.EgovFixedLengthLineAggregator;

import javax.sql.DataSource;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * @author 서경석
 * @since 2014.11.05
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일        수정자           수정내용
 *  -------       --------          ---------------------------
 *   2014.11.05    서경석           최초 생성
 *   2014.11.28    표준프레임워크	공통컴포넌트 추가 적용 (패키지 변경)
 *
 * </pre>
 */
public class DefaultItemWriter<T> implements ItemStreamWriter<T>{

	// Output Resource Type - key
	private static final String XML_CONF_FLAG_KEY = ".writer.xml.conf.flag";
	
	private static final String WRITER_RESOURCE_TYPE_KEY = ".writer.resource.type";

	private static final String WRITER_RESOURCE_NAME_KEY = ".writer.resource.name";
	private static final String WRITER_FIELD_NAMES_KEY = ".writer.field.names";
	private static final String WRITER_FIELD_RANGES_KEY = ".writer.field.ranges";
	private static final String WRITER_DELIMITER_KEY = ".writer.delimiter";

	private static final String WRITER_SQL_KEY = ".writer.sql";
	private static final String WRITER_PARAMS_KEY = ".writer.params";
	
	// Output Resource Type - Value
	private static final String DELIMITED_FILE_TYPE = "delimitedFile";
	private static final String FIXED_LENGTH_FILE_TYPE = "fixedLengthFile";
	private static final String JDBC_DB_TYPE = "jdbcDb";
	
	// XML 설정 내용을 출력하기 위한 설정
	boolean printXmlConf = false;
	
	// 실제 동작하는 Reader
	private ItemWriter<T> writer;
	
	// 공통 설정
	private String stepName;
	private JobParameters jobParameters;
	private String writerResourceType;
	
	// File 입력인 경우 사용되는 설정
	private Resource resource;		// 공통
	private String resourceName;		// 공통
	private String[] fieldNames;	// 공통
	private String names;	// 공통
	private String delimiter;		// delimited 방식인 경우
	private int[] fieldRanges;		// fixedLength 방식인 경우
	private String ranges;
	
	// DB 입력인 경우 사용되는 설정
	private DataSource dataSource;
	private String sql;
	private String[] params;
	private String tempParams;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) throws ClassNotFoundException{
	    this.stepName = stepExecution.getStepName();
	    this.jobParameters = stepExecution.getJobParameters();
	    
	    String flag = jobParameters.getString(stepName + XML_CONF_FLAG_KEY);
	    if((flag != null) && "true".equalsIgnoreCase(flag)) {
	    	printXmlConf = true;
	    }
	    
	    // Input Resource Type에 따라 필요한 설정 값 세팅
	    makeWriterConfigValue();
	}
	
	
	@Override
	public void open(ExecutionContext executionContext)
			throws ItemStreamException {
		// ItemReader 생성
		makeItemWriter();
		
		if(this.writer instanceof ItemStream) {
			((ItemStream) this.writer).open(executionContext);
		}
	}

	@Override
	public void update(ExecutionContext executionContext)
			throws ItemStreamException {
		if(this.writer instanceof ItemStream) {
			((ItemStream) this.writer).update(executionContext);
		}
	}

	@Override
	public void close() throws ItemStreamException {
		if(this.writer instanceof ItemStream) {
			((ItemStream) this.writer).close();
		}
	}

	@Override
	public void write(List<? extends T> items) throws Exception {
		this.writer.write(items);
	}

	private void makeWriterConfigValue() {
		if(jobParameters.getString(stepName + WRITER_RESOURCE_TYPE_KEY) != null) {
			this.writerResourceType = jobParameters.getString(stepName + WRITER_RESOURCE_TYPE_KEY);
			
			if(DELIMITED_FILE_TYPE.equalsIgnoreCase(this.writerResourceType) || FIXED_LENGTH_FILE_TYPE.equalsIgnoreCase(this.writerResourceType)) {
				
		    	// 입력 리소스가 File인 경우 공통 처리 부분
		    	this.resourceName= jobParameters.getString(stepName + WRITER_RESOURCE_NAME_KEY);
		    	this.names = jobParameters.getString(stepName + WRITER_FIELD_NAMES_KEY);

		    	if(DELIMITED_FILE_TYPE.equalsIgnoreCase(this.writerResourceType)){
		    		this.delimiter = jobParameters.getString(stepName + WRITER_DELIMITER_KEY);
		    		if(this.resourceName == null || this.delimiter == null || this.names == null ) {
				    	throw new RuntimeException(stepName + "스텝의 Writer 설정에서 resourceName, delimiter, names는 필수입니다. 다음 처럼 설정하세요.\n" 
				    			+ stepName + ".writer.resourceName=file:./inputs/csvData.csv " + stepName + ".writer.delimiter=, " + stepName + ".writer.fieldNames=name,age ");
				    }
		    	} else {
			    	this.ranges = jobParameters.getString(stepName + WRITER_FIELD_RANGES_KEY);
			    	if(this.resourceName == null || ranges == null || this.names == null ) {
				    	throw new RuntimeException(stepName + "스텝의 Reader 설정에서 resourceName, fieldRanges, names는 필수입니다. 다음 처럼 설정하세요.\n"
				    			+ stepName + ".writer.resourceName=file:./target/test-outputs/txtOutput.txt " + stepName + ".writer.fieldRanges=9,2 " + stepName + ".writer.fieldNames=name,age ");
				    } 
				    
				    String[] rangeArray = ranges.split(",");
				    this.fieldRanges = new int[rangeArray.length];
				    for(int idx=0; idx<rangeArray.length; idx++) {
				    	fieldRanges[idx] = Integer.parseInt(rangeArray[idx]);
				    }
		    	}
		    	
		    	this.resource = new FileSystemResource(resourceName);
			    this.fieldNames = names.split(",");
		    	
			} else if(JDBC_DB_TYPE.equalsIgnoreCase(this.writerResourceType)){
				this.sql = jobParameters.getString(stepName + WRITER_SQL_KEY);
				tempParams = jobParameters.getString(stepName + WRITER_PARAMS_KEY);
				
				if(this.sql == null || tempParams == null) {
			    	throw new RuntimeException(stepName + "스텝의 Writer 설정에서 sql, params는 필수입니다. 다음 처럼 설정하세요.\n"
			    			+ stepName + ".writer.sql=UPDATE CUSTOMER set credit =? where name =? " + stepName + ".writer.params=credit,name ");
			    }
				
				this.params = tempParams.split(",");
			}
			
		} else {
		    throw new RuntimeException(stepName + ".writerResourceType=delimitedFile'처럼, 출력 리소스 타입을 Job 파라미터로 입력하세요.\n"
					+ "리소스 타입 종류) delimitedFile, fixedLengthFile, jdbcDb");
		}
	}
	
	private DelimitedLineAggregator<T> makeDelimitedLineAggregator(FieldExtractor<T> fieldExtractor) {
		DelimitedLineAggregator<T> lineAggregator = new DelimitedLineAggregator<T>();
		lineAggregator.setDelimiter(this.delimiter);
		lineAggregator.setFieldExtractor(fieldExtractor);
		return lineAggregator;
	}
	
	private EgovFixedLengthLineAggregator<T> makeEgovFixedLengthLineAggregator(FieldExtractor<T> fieldExtractor) {
		EgovFixedLengthLineAggregator<T> lineAggregator = new EgovFixedLengthLineAggregator<T>();
		lineAggregator.setFieldExtractor(fieldExtractor);
		lineAggregator.setFieldRanges(fieldRanges);
		return lineAggregator;
	}
	
	private FieldExtractor<T> makeFieldExtractor() {
		EgovFieldExtractor<T> fieldExtractor = new EgovFieldExtractor<T>();
		fieldExtractor.setNames(this.fieldNames);
		fieldExtractor.afterPropertiesSet();
		return fieldExtractor;
	}
	
	private void makeItemWriter() {
		if(DELIMITED_FILE_TYPE.equalsIgnoreCase(this.writerResourceType) || FIXED_LENGTH_FILE_TYPE.equalsIgnoreCase(this.writerResourceType)) {
			
			FieldExtractor<T> fieldExtractor = makeFieldExtractor();
			
			LineAggregator<T> lineAggregator = null;
			
			if(DELIMITED_FILE_TYPE.equalsIgnoreCase(this.writerResourceType)) {
				lineAggregator = makeDelimitedLineAggregator(fieldExtractor);
			} else {
				lineAggregator = makeEgovFixedLengthLineAggregator(fieldExtractor);
			}
			
			this.writer = new FlatFileItemWriter<T>();
			((FlatFileItemWriter<T>)this.writer).setResource(this.resource);
			((FlatFileItemWriter<T>)this.writer).setLineAggregator(lineAggregator);
			
			try {
				((FlatFileItemWriter<T>)this.writer).afterPropertiesSet();
			} catch (Exception e) {
				throw new RuntimeException(this.writerResourceType + " 타입의 File을 write 하기 위한 FlatFileItemWriter 생성에 실패 하였습니다.");
			}
		} else if(JDBC_DB_TYPE.equalsIgnoreCase(this.writerResourceType)){

			EgovMethodMapItemPreparedStatementSetter<T> preparedStatementSetter = new EgovMethodMapItemPreparedStatementSetter<T>();
			
			this.writer = new EgovJdbcBatchItemWriter<T>();
			((EgovJdbcBatchItemWriter<T>)this.writer).setDataSource(this.dataSource);
			((EgovJdbcBatchItemWriter<T>)this.writer).setParams(this.params);
			((EgovJdbcBatchItemWriter<T>)this.writer).setSql(this.sql);
			((EgovJdbcBatchItemWriter<T>)this.writer).setItemPreparedStatementSetter(preparedStatementSetter);
			((EgovJdbcBatchItemWriter<T>)this.writer).setAssertUpdates(true);
			
			try {
				((EgovJdbcBatchItemWriter<T>)this.writer).afterPropertiesSet();
			} catch (Exception e) {
				throw new RuntimeException(this.writerResourceType + " 타입의 File을 write 하기 위한 FlatFileItemWriter 생성에 실패 하였습니다.");
			}
		}
		
		printXmlConfig();
	}
	
	private void printXmlConfig() {
		if(printXmlConf) {
			if(DELIMITED_FILE_TYPE.equalsIgnoreCase(this.writerResourceType)) {
				System.out.println("======= " + stepName + " WRITER 설정(XML 버전) =========\n"
					+ "<bean id=\"" + stepName + ".writer\" class=\"org.springframework.batch.item.file.FlatFileItemWriter\" scope=\"step\">\n"
					+ "  <property name=\"resource\" value=\"" + this.resourceName + "\" />\n"
					+ "  <property name=\"lineAggregator\">\n"
					+ "    <bean class=\"org.springframework.batch.item.file.transform.DelimitedLineAggregator\">\n"
					+ "      <property name=\"delimiter\" value=\"" + this.delimiter + "\" />\n"
					+ "      <property name=\"fieldExtractor\">\n"
					+ "        <bean class=\"egovframework.rte.bat.core.item.file.transform.EgovFieldExtractor\">\n"
					+ "          <property name=\"names\" value=\"" + this.names + "\" />\n"
					+ "        </bean>\n"
					+ "      </property>\n"
					+ "    </bean>\n"
					+ "  </property>\n"
					+ "</bean>\n"
					+ "================================================");
			} else if(FIXED_LENGTH_FILE_TYPE.equalsIgnoreCase(this.writerResourceType)) {
				System.out.println("======= " + stepName + " Writer 설정(XML 버전) =========\n"
					+ "<bean id=\"" + stepName + ".writer\" class=\"org.springframework.batch.item.file.FlatFileItemWriter\" scope=\"step\">\n"
					+ "  <property name=\"resource\" value=\"" + this.resourceName + "\" />\n"
					+ "  <property name=\"lineAggregator\">\n"
					+ "    <bean class=\"egovframework.rte.bat.core.item.file.transform.EgovFixedLengthLineAggregator\">\n"
					+ "      <property name=\"fieldRanges\" value=\"" + this.ranges + "\" />\n"
					+ "      <property name=\"fieldExtractor\">\n"
					+ "        <bean class=\"egovframework.rte.bat.core.item.file.transform.EgovFieldExtractor\">\n"
					+ "          <property name=\"names\" value=\"" + this.names + "\" />\n"
					+ "        </bean>\n"
					+ "      </property>\n"
					+ "    </bean>\n"
					+ "  </property>\n"
					+ "</bean>\n"
					+ "================================================");
			} else if(JDBC_DB_TYPE.equalsIgnoreCase(this.writerResourceType)){
				System.out.println("======= " + stepName + " Writer 설정(XML 버전) =========\n"
					+ "<bean id=\"" + stepName + ".writer\" class=\"egovframework.rte.bat.core.item.database.EgovJdbcBatchItemWriter\">\n"
					+ "  <property name=\"assertUpdates\" value=\"true\" />\n"
					+ "  <property name=\"itemPreparedStatementSetter\">\n"
					+ "    <bean class=\"egovframework.rte.bat.core.item.database.support.EgovMethodMapItemPreparedStatementSetter\" />\n"
					+ "  </property>\n"
					+ "  <property name=\"sql\" value=\"" + this.sql + "\" />\n"
					+ "  <property name=\"params\" value=\"" + this.tempParams + "\" />\n"
					+ "  <property name=\"dataSource\" ref=\"dataSource\" />\n"
					+ "</bean>\n"
					+ "================================================");
			}
		}
	}
}
