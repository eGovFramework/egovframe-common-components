/*
 * eGovFrame EasyBatch
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

import javax.sql.DataSource;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.egovframe.rte.bat.core.item.file.mapping.EgovDefaultLineMapper;
import org.egovframe.rte.bat.core.item.file.mapping.EgovObjectMapper;
import org.egovframe.rte.bat.core.item.file.transform.EgovDelimitedLineTokenizer;
import org.egovframe.rte.bat.core.item.file.transform.EgovFixedLengthTokenizer;
import org.egovframe.rte.bat.core.item.file.transform.EgovLineTokenizer;

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
public class DefaultItemReader<T> implements ItemStreamReader<T> {

	// Input Resource Type - key
	private static final String XML_CONF_FLAG_KEY = ".reader.xml.conf.flag";

	private static final String READER_RESOURCE_TYPE_KEY = ".reader.resource.type";

	private static final String READER_RESOURCE_NAME_KEY = ".reader.resource.name";
	private static final String READER_FIELD_NAMES_KEY = ".reader.field.names";
	private static final String READER_VO_TYPE_KEY = ".reader.vo.type";
	private static final String READER_DELIMITER_KEY = ".reader.delimiter";
	private static final String READER_COLUMNS_KEY = ".reader.columns";

	private static final String READER_SQL_KEY = ".reader.sql";
	private static final String READER_PARAMS_KEY = ".reader.params";

	// Input Resource Type - Value
	private static final String DELIMITED_FILE_TYPE = "delimitedFile";
	private static final String FIXED_LENGTH_FILE_TYPE = "fixedLengthFile";
	private static final String JDBC_DB_TYPE = "jdbcDb";

	// XML 설정 내용을 출력하기 위한 설정
	boolean printXmlConf = false;

	// 실제 동작하는 Reader
	ItemReader<T> reader;

	// 공통 설정
	String stepName;
	JobParameters jobParameters;
	String readerResourceType;

	// File 입력인 경우 사용되는 설정
	Resource resource; // 공통
	String resourceName;
	String[] fieldNames; // 공통
	String names;
	@SuppressWarnings("rawtypes")
	Class voType; // 공통
	String delimiter; // delimited 방식인 경우
	Range[] ranges; // fixedLength 방식인 경우
	String columns; // (fixedLength 방식인 경우)

	// DB 입력인 경우 사용되는 설정
	// DB 입력인 경우 사용되는 설정
	private DataSource dataSource;
	private String sql;
	@SuppressWarnings("unused")
	private String[] params;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		this.stepName = stepExecution.getStepName();
		this.jobParameters = stepExecution.getJobParameters();

		String flag = jobParameters.getString(stepName + XML_CONF_FLAG_KEY);
		if ((flag != null) && "true".equalsIgnoreCase(flag)) {
			printXmlConf = true;
		}

		// Input Resource Type에 따라 필요한 설정 값 세팅
		makeReaderConfigValue();
	}

	@Override
	public void close() throws ItemStreamException {
		if (this.reader instanceof ItemStream) {
			((ItemStream)this.reader).close();
		}
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		// ItemReader 생성
		makeItemReader();

		if (this.reader instanceof ItemStream) {
			((ItemStream)this.reader).open(executionContext);
		}
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		if (this.reader instanceof ItemStream) {
			((ItemStream)this.reader).update(executionContext);
		}
	}

	@Override
	public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return reader.read();
	}

	// Input Resource Type 별로 설정 값 세팅
	private void makeReaderConfigValue() {

		if (jobParameters.getString(stepName + READER_RESOURCE_TYPE_KEY) != null) {

			this.readerResourceType = jobParameters.getString(stepName + READER_RESOURCE_TYPE_KEY);

			if (DELIMITED_FILE_TYPE.equalsIgnoreCase(this.readerResourceType)
				|| FIXED_LENGTH_FILE_TYPE.equalsIgnoreCase(this.readerResourceType)) {

				// 입력 리소스가 File인 경우 공통 처리 부분
				this.resourceName = jobParameters.getString(stepName + READER_RESOURCE_NAME_KEY);
				this.names = jobParameters.getString(stepName + READER_FIELD_NAMES_KEY);
				String type = jobParameters.getString(stepName + READER_VO_TYPE_KEY);

				if (DELIMITED_FILE_TYPE.equalsIgnoreCase(this.readerResourceType)) {
					this.delimiter = jobParameters.getString(stepName + READER_DELIMITER_KEY);
					if (resourceName == null || this.delimiter == null || names == null || type == null) {
						throw new RuntimeException(
							stepName + "스텝의 Reader 설정에서 resourceName, delimiter, names, type 은 필수입니다. 다음 처럼 설정하세요.\n"
								+ stepName + READER_RESOURCE_NAME_KEY + "=./inputs/csvData.csv "
								+ stepName + READER_DELIMITER_KEY + "=, "
								+ stepName + READER_FIELD_NAMES_KEY + "=name,age "
								+ stepName + READER_VO_TYPE_KEY + "=aa.bb.TestVo");
					}
				} else if (FIXED_LENGTH_FILE_TYPE.equalsIgnoreCase(this.readerResourceType)) {
					this.columns = jobParameters.getString(stepName + READER_COLUMNS_KEY);
					if (resourceName == null || columns == null || names == null || type == null) {
						throw new RuntimeException(
							stepName + "스텝의 Reader 설정에서 resourceName, columns, names, type 은 필수입니다. 다음 처럼 설정하세요.\n"
								+ stepName + READER_RESOURCE_NAME_KEY + "=./inputs/csvData.csv "
								+ stepName + READER_COLUMNS_KEY + "=1-9,10-11 "
								+ stepName + READER_FIELD_NAMES_KEY + "=name,age "
								+ stepName + READER_VO_TYPE_KEY + "=aa.bb.TestVo");
					}

					String[] columnArray = columns.split(",");
					ranges = new Range[columnArray.length];
					for (int idx = 0; idx < columnArray.length; idx++) {
						ranges[idx] = new Range(Integer.parseInt(columnArray[idx].split("-")[0]),
							Integer.parseInt(columnArray[idx].split("-")[1]));
					}
				}

				this.resource = new FileSystemResource(resourceName);
				this.fieldNames = names.split(",");

				try {
					this.voType = Class.forName(type);
				} catch (ClassNotFoundException e) {
					throw new RuntimeException(e);
				}
			} else if (JDBC_DB_TYPE.equalsIgnoreCase(this.readerResourceType)) {
				this.sql = jobParameters.getString(stepName + READER_SQL_KEY);
				String tempParams = jobParameters.getString(stepName + READER_PARAMS_KEY);
				String type = jobParameters.getString(stepName + READER_VO_TYPE_KEY);

				if (this.sql == null || type == null) {
					throw new RuntimeException(stepName + "스텝의 Writer 설정에서 sql, type 는 필수입니다. 다음 처럼 설정하세요.\n"
						+ stepName + ".writer.sql=select ID, NAME, CREDIT from CUSTOMER " + stepName
						+ ".writer.params=credit,name " + stepName + READER_VO_TYPE_KEY + "=aa.bb.TestVo");
				}

				if (tempParams != null) {
					this.params = tempParams.split(",");
				}

				try {
					this.voType = Class.forName(type);
				} catch (ClassNotFoundException e) {
					throw new RuntimeException(e);
				}
			}
		} else {
			throw new RuntimeException(
				stepName + READER_RESOURCE_TYPE_KEY + "=delimitedFile'처럼, 입력 리소스 타입을 Job 파라미터로 입력하세요.\n"
					+ "리소스 타입 종류) delimitedFile, fixedLengthFile, jdbcDb");
		}
	}

	private EgovDelimitedLineTokenizer makeEgovDelimitedLineTokenizer() {
		EgovDelimitedLineTokenizer tokenizer = new EgovDelimitedLineTokenizer();
		tokenizer.setDelimiter(this.delimiter);
		return tokenizer;
	}

	private EgovFixedLengthTokenizer makeEgovFixedLengthTokenizer() {
		EgovFixedLengthTokenizer tokenizer = new EgovFixedLengthTokenizer();
		tokenizer.setColumns(this.ranges);
		return tokenizer;
	}

	@SuppressWarnings("unchecked")
	private EgovObjectMapper<T> makeEgovObjectMapper() {
		EgovObjectMapper<T> objectMapper = new EgovObjectMapper<T>();
		objectMapper.setNames(fieldNames);
		objectMapper.setType(voType);
		try {
			objectMapper.afterPropertiesSet();
		} catch (RuntimeException e) {//2022.01 "Exception" should not be caught when not required by called methods
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return objectMapper;
	}

	private EgovDefaultLineMapper<T> makeEgovDefaultLineMapper(EgovLineTokenizer<T> tokenizer,
		EgovObjectMapper<T> objectMapper) {
		EgovDefaultLineMapper<T> lineMapper = new EgovDefaultLineMapper<T>();
		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setObjectMapper(objectMapper);
		lineMapper.afterPropertiesSet();
		return lineMapper;
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	private void makeItemReader() {

		if (DELIMITED_FILE_TYPE.equalsIgnoreCase(this.readerResourceType)
			|| FIXED_LENGTH_FILE_TYPE.equalsIgnoreCase(this.readerResourceType)) {

			EgovLineTokenizer tokenizer;

			if (DELIMITED_FILE_TYPE.equalsIgnoreCase(this.readerResourceType)) {
				tokenizer = makeEgovDelimitedLineTokenizer();
			} else {
				tokenizer = makeEgovFixedLengthTokenizer();
			}

			EgovObjectMapper<T> objectMapper = makeEgovObjectMapper();
			EgovDefaultLineMapper<T> lineMapper = makeEgovDefaultLineMapper(tokenizer, objectMapper);

			this.reader = new FlatFileItemReader<T>();
			((FlatFileItemReader<T>)this.reader).setLineMapper(lineMapper);
			((FlatFileItemReader<T>)this.reader).setResource(resource);

			try {
				((FlatFileItemReader<T>)this.reader).afterPropertiesSet();
			} catch (Exception e) {
				throw new RuntimeException(
					this.readerResourceType + " 타입의 File을 read 하기 위한 FlatFileItemReader 생성에 실패 하였습니다.");
			}
		} else if (JDBC_DB_TYPE.equalsIgnoreCase(this.readerResourceType)) {

			BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper();
			rowMapper.setMappedClass(this.voType);

			this.reader = new JdbcCursorItemReader();
			try {
				((JdbcCursorItemReader)this.reader).setDataSource(this.dataSource);
				((JdbcCursorItemReader)this.reader).setRowMapper(rowMapper);
				((JdbcCursorItemReader)this.reader).setSql(this.sql);
				((JdbcCursorItemReader)this.reader).afterPropertiesSet();
			} catch (IllegalArgumentException e) {//KISA 보안약점 조치 (2018-10-29, 윤창원)
				throw new RuntimeException("Data source 또는 SQL properties가 설정되지 않았습니다.");
			} catch (Exception e) {
				throw new RuntimeException(
					this.readerResourceType + " 타입의 DB을 read 하기 위한 JdbcCursorItemReader 생성에 실패 하였습니다.");
			}
		}

		printXmlConfig();
	}

	private void printXmlConfig() {
		if (printXmlConf) {
			if (DELIMITED_FILE_TYPE.equalsIgnoreCase(this.readerResourceType)) {
				System.out.println("======= " + stepName + " READER 설정(XML 버전) =========\n"
					+ "<bean id=\"" + stepName
					+ ".reader\" class=\"org.springframework.batch.item.file.FlatFileItemReader\" scope=\"step\">\n"
					+ "  <property name=\"resource\" value=\"" + this.resourceName + "\" />\n"
					+ "  <property name=\"lineMapper\">\n"
					+ "    <bean class=\"org.egovframe.rte.bat.core.item.file.mapping.EgovDefaultLineMapper\">\n"
					+ "      <property name=\"lineTokenizer\">\n"
					+ "        <bean class=\"org.egovframe.rte.bat.core.item.file.transform.EgovDelimitedLineTokenizer\">\n"
					+ "          <property name=\"delimiter\" value=\"" + this.delimiter + "\" />\n"
					+ "        </bean>\n"
					+ "      </property>\n"
					+ "      <property name=\"objectMapper\">\n"
					+ "        <bean class=\"org.egovframe.rte.bat.core.item.file.mapping.EgovObjectMapper\">\n"
					+ "          <property name=\"type\" value=\"" + voType.getName() + "\" />\n"
					+ "          <property name=\"names\" value=\"" + this.names + "\" />\n"
					+ "        </bean>\n"
					+ "      </property>\n"
					+ "    </bean>\n"
					+ "  </property>\n"
					+ "</bean>\n"
					+ "================================================");
			} else if (FIXED_LENGTH_FILE_TYPE.equalsIgnoreCase(this.readerResourceType)) {
				System.out.println("======= " + stepName + " READER 설정(XML 버전) =========\n"
					+ "<bean id=\"" + stepName
					+ ".reader\" class=\"org.springframework.batch.item.file.FlatFileItemReader\" scope=\"step\">\n"
					+ "  <property name=\"resource\" value=\"" + this.resourceName + "\" />\n"
					+ "  <property name=\"lineMapper\">\n"
					+ "    <bean class=\"org.egovframe.rte.bat.core.item.file.mapping.EgovDefaultLineMapper\">\n"
					+ "      <property name=\"lineTokenizer\">\n"
					+ "        <bean class=\"org.egovframe.rte.bat.core.item.file.transform.EgovFixedLengthTokenizer\">\n"
					+ "          <property name=\"columns\" value=\"" + this.columns + "\" />\n"
					+ "        </bean>\n"
					+ "      </property>\n"
					+ "      <property name=\"objectMapper\">\n"
					+ "        <bean class=\"org.egovframe.rte.bat.core.item.file.mapping.EgovObjectMapper\">\n"
					+ "          <property name=\"type\" value=\"" + voType.getName() + "\" />\n"
					+ "          <property name=\"names\" value=\"" + this.names + "\" />\n"
					+ "        </bean>\n"
					+ "      </property>\n"
					+ "    </bean>\n"
					+ "  </property>\n"
					+ "</bean>\n"
					+ "================================================");
			} else if (JDBC_DB_TYPE.equalsIgnoreCase(this.readerResourceType)) {
				System.out.println("======= " + stepName + " READER 설정(XML 버전) =========\n"
					+ "<bean id=\"" + stepName
					+ ".reader\" class=\"org.springframework.batch.item.database.JdbcCursorItemReader\" scope=\"step\">\n"
					+ "  <property name=\"dataSource\" ref=\"dataSource\" />\n"
					+ "  <property name=\"sql\" value=\"" + this.sql + "\" />\n"
					+ "  <property name=\"rowMapper\">\n"
					+ "    <bean class=\"org.springframework.jdbc.core.BeanPropertyRowMapper\" />\n"
					+ "      <property name=\"mappedClass\" value=\"" + voType.getName() + "\">\n"
					+ "  </bean>\n"
					+ "</property>\n"
					+ "</bean>\n"
					+ "================================================");
			}
		}
	}
}
