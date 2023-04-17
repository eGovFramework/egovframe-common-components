package egovframework.code.security.copyobj;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCopyDate {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestCopyDate.class);
	
	public static void main(String[] args) {
		
		Date d = new Date();
		Date copyDate = (Date)d.clone();
		d.setYear(2021-1900);
		LOGGER.debug("Org Date.year = "+(d.getYear()+1900));
		LOGGER.debug("Org Date = "+d);
		LOGGER.debug("Org Date = "+d.hashCode());
		LOGGER.debug("Destination Date = "+copyDate);
		LOGGER.debug("Destination Date = "+copyDate.hashCode());

	}

}
