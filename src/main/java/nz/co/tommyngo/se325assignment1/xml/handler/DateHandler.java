package nz.co.tommyngo.se325assignment1.xml.handler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.format.datetime.DateFormatter;

public class DateHandler extends GeneralizedFieldHandler{
	private static String dateFormatPattern;

	public void setConfiguration(Properties config) throws ValidityException {
		dateFormatPattern = config.getProperty("date-format");
	}
	public Object convertUponGet(Object value) {

		Date date = (Date) value;
		
		return format(date);
	}
	
	public Object convertUponSet(Object value) {
		
		String dateTimeString = (String) value;
		
		return parse(dateTimeString);
	}

	@Override
	public Class<Date> getFieldType() {
		// TODO Auto-generated method stub
		return Date.class;
	}
	
	protected static String format(final Date date) {

		String dateString = "";
		
		if (date != null) {
			DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
			dateString = dateFormat.format(date);
		}
		
		return dateString;
		
	}
	
	protected static Date parse(final String dateString) {
		
		Date date = new Date();
		
		if (dateString != null) {
			DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
			try {
				date = dateFormat.parse(dateString);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return date;
		
	}

}
