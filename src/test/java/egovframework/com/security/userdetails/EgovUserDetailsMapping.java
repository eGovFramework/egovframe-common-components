package egovframework.com.security.userdetails;

import org.egovframe.rte.fdl.security.userdetails.EgovUserDetails;
import org.egovframe.rte.fdl.security.userdetails.jdbc.EgovUsersByUsernameMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author sjyoon
 */
public class EgovUserDetailsMapping extends EgovUsersByUsernameMapping {

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovUserDetailsMapping.class);

    public EgovUserDetailsMapping(DataSource ds, String usersByUsernameQuery) {
        super(ds, usersByUsernameQuery);
    }

    @Override
    protected EgovUserDetails mapRow(ResultSet rs, int rownum) throws SQLException {
        LOGGER.debug("### EgovUsersByUsernameMapping mapRow() ");

        String userid = rs.getString("user_id");
        String password = rs.getString("password");
        boolean enabled = rs.getBoolean("enabled");

        String username = rs.getString("user_name");
        String birthDay = rs.getString("birth_day");
        String ssn = rs.getString("ssn");

        EgovUserDetailsVO userVO = new EgovUserDetailsVO();
        userVO.setUserId(userid);
        userVO.setPassWord(password);
        userVO.setUserName(username);
        userVO.setBirthDay(birthDay);
        userVO.setSsn(ssn);

        LOGGER.debug("### EgovUsersByUsernameMapping mapRow() userVO : {}", userVO);

        return new EgovUserDetails(userid, password, enabled, userVO);
    }

}
