package egovframework.code.security.copyobj;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCopyDate {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestCopyDate.class);
	
	public static void main(String[] args) {
		
		Date d = new Date();
		Date copyDate = (Date)d.clone();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2021);
		d = calendar.getTime();
		LOGGER.debug("Org Date.year = "+(calendar.get(Calendar.YEAR)));
		LOGGER.debug("Org Date = "+d);
		LOGGER.debug("Org Date = "+d.hashCode());
		LOGGER.debug("Destination Date = "+copyDate);
		LOGGER.debug("Destination Date = "+copyDate.hashCode());

	}

}
