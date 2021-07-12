package egovframework.com.cmm.interceptor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import lombok.extern.slf4j.Slf4j;

@Intercepts(value = {

//		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
//				RowBounds.class, ResultHandler.class }),

		@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class, }),

})
@Slf4j
public class EgovMyBatisPlugin implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		log.debug("getTarget={}", invocation.getTarget());
		log.debug("getMethod={}", invocation.getMethod());
		log.debug("getArgs={}", invocation.getArgs());

		Object[] args = invocation.getArgs();
		Statement stmt = (Statement) args[0];
		ResultSet rs = stmt.getResultSet();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount() + 1;
		for (int column = 1; column < columnCount; column++) {
			String columnLabel = rsmd.getColumnLabel(column);
//			String columnName = rsmd.getColumnName(column);

			log.debug("columnLabel={}", columnLabel);
//			log.debug("columnName={}", columnName);
		}

		Object returnObject = invocation.proceed();

		log.debug("returnObject={}", returnObject);

		return returnObject;
	}

}
