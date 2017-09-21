package com.metamorf.eform.web.util;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
//	private static final String[] MONTH_YEAR = {"MMM-yyyy"};
	private static final SimpleDateFormat MONTH_YEAR_FORMAT = new SimpleDateFormat("MMM yyyy");
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd");
	private static final SimpleDateFormat HOUR_MINUTE = new SimpleDateFormat("hh:mm");
//	public static SimpleDateFormat DATE_MONTH_YEAR = new SimpleDateFormat(SystemParameterConstant.DATE_FORMAT_EXT);
	public static final SimpleDateFormat YEAR = new SimpleDateFormat("yyyy");
	public static final SimpleDateFormat DAY_MONTH_YEAR = new SimpleDateFormat("ddMMMyyyy");
	private static final SimpleDateFormat YEAR_MONTH = new SimpleDateFormat("yyyy-MM");
	
	/* Mysql date format */
	public static final String DATE_MONTH = "%m-%d";
	
	public final static long SECOND_MILLIS = 1000;
	public final static long MINUTE_MILLIS = SECOND_MILLIS*60;
	public final static long HOUR_MILLIS = MINUTE_MILLIS*60;
	public final static long DAY_MILLIS = HOUR_MILLIS*24;
	public final static long YEAR_MILLIS = DAY_MILLIS*365;

	public static DateFormat OUT_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	public static DateFormat OUT_TIME_FORMAT = new SimpleDateFormat("H:mm:ss");
	public static DateFormat OUT_DATETIME_FORMAT = new SimpleDateFormat("d/M/yyyy H:mm:ss");
	public static DateFormat OUT_TIMESTAMP_FORMAT = new SimpleDateFormat("d/M/yy H:mm:ss.SSS");
	    
	public static DateFormat IN_DATE_FORMAT = new SimpleDateFormat("d/M/yy");
	public static DateFormat IN_TIME_FORMAT = new SimpleDateFormat("H:mm:ss");
	public static DateFormat IN_DATETIME_FORMAT = new SimpleDateFormat("d/M/yy H:mm:ss");
	public static DateFormat IN_TIMESTAMP_FORMAT = new SimpleDateFormat("d/M/yy H:mm:ss.SSS");
	
	public static final String[] YYYY_MM_DD_HH_MM = {"yyyy-MM-dd H:mm"};
	    
	public static DateFormat DATE_TIME_FORMAT= new SimpleDateFormat( "yyyyMMddkkmmss" );  
	
	public static final String[] DD_MM_YY = {"dd-MM-yyyy"};
	public static final String YEAR_MONTH_DAY = "yyyy/MM/dd";
	
	public static final SimpleDateFormat DD_MM_YYYY = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");
	public static final SimpleDateFormat MM_DD = new SimpleDateFormat("MM-dd");
	public static final SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final SimpleDateFormat sdfWithTimeIntegrated = new SimpleDateFormat("yyMMddHHmmss");
	public static final SimpleDateFormat sdfWithTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static final SimpleDateFormat monthYear = new SimpleDateFormat("MMM-yyyy");
	
	public static final SimpleDateFormat HOUR_MINUTE_SECOND = new SimpleDateFormat("HH:mm:ss");

	public static Calendar calendar = new GregorianCalendar();
	
	public static String getNextMonth(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, 1);
		return YEAR_MONTH.format(cal.getTime());
	}
	
	public static String getNextMonth(String month){
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(YEAR_MONTH.parse(month));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.add(Calendar.MONTH, 1);
		return YEAR_MONTH.format(cal.getTime());
	}
	
	public static String getPrevMonth(String month){
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(YEAR_MONTH.parse(month));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cal.add(Calendar.MONTH, -1);
		return YEAR_MONTH.format(cal.getTime());
	}
	
	public static String getMonth(){
		return YEAR_MONTH.format(calendar.getTime());
	}

	public static String formatFilePassword(Date date){
		return DAY_MONTH_YEAR.format(date);
	}
	
	public static String formatMonthYear(Date date){
		return MONTH_YEAR_FORMAT.format(date);
	}
	
	public static String formatToDate(Date date){
		return DATE_FORMAT.format(date);
	}
	
	public static Date parseDefaultDate(String dateString) throws ParseException
	{
		return DD_MM_YYYY.parse(dateString);
	}
	
	public static String getThisYear(){
		return YEAR.format(new Date());
	}

	public static Date parse2HourMinute(String time) throws ParseException{
		return HOUR_MINUTE.parse(time);
	}
	
	 static
	    {
	        IN_DATE_FORMAT.setLenient(false);
	        IN_TIME_FORMAT.setLenient(false);
	        IN_DATETIME_FORMAT.setLenient(false);
	    }

	    /**
	     * Create a new DateTime. To the last second. This will not create any 
	     * extra-millis-seconds, which may cause bugs when writing to stores such as
	     * databases that round milli-seconds up and down. 
	     */
	    public static java.util.Date newDateTime()
	    {
	        return new java.util.Date( (System.currentTimeMillis()/SECOND_MILLIS)*SECOND_MILLIS);
	    }

	    /**
	     * Create a new Date. To the last day.
	     */
	    public static java.sql.Date newDate()
	    {
	        return new java.sql.Date( (System.currentTimeMillis()/DAY_MILLIS)*DAY_MILLIS);
	    }
	    
	    /**
	     * Create a new Time, with no date component. 
	     */
	    public static java.sql.Time newTime()
	    {
	        return new java.sql.Time( System.currentTimeMillis()%DAY_MILLIS);
	    }
	    
	    /**
	     * Create a new Timestamp. 
	     */
	    public static java.sql.Timestamp newTimestamp()
	    {
	        return new java.sql.Timestamp( System.currentTimeMillis() );
	    }
	    
	   /**
	    * Roll the java.util.Time forward or backward.
	    * @param startDate - The start date
	    * @period Calendar.YEAR etc
	    * @param amount - Negative to rollbackwards.
	    */
	    public static java.sql.Time rollTime( java.util.Date startDate, int period, int amount )
	    {
	        GregorianCalendar gc = new GregorianCalendar();
	        gc.setTime(startDate);
	        gc.add(period, amount);
	        return new java.sql.Time(gc.getTime().getTime());
	    }
	    
	   /**
	    * Roll the java.util.Date forward or backward.
	    * @param startDate - The start date
	    * @period Calendar.YEAR etc
	    * @param amount - Negative to rollbackwards.
	    */
	    public static java.util.Date rollDateTime( java.util.Date startDate, int period, int amount )
	    {
	        GregorianCalendar gc = new GregorianCalendar();
	        gc.setTime(startDate);
	        gc.add(period, amount);
	        return new java.util.Date(gc.getTime().getTime());
	    }
	    
	   /**
	    * Roll the java.sql.Date forward or backward.
	    * @param startDate - The start date
	    * @period Calendar.YEAR etc
	    * @param amount - Negative to rollbackwards.
	    */
	    public static java.sql.Date rollDate( java.util.Date startDate, int period, int amount )
	    {
	        GregorianCalendar gc = new GregorianCalendar();
	        gc.setTime(startDate);
	        gc.add(period, amount);
	        return new java.sql.Date(gc.getTime().getTime());
	    }
	    
	   /**
	    * Roll the years forward or backward.
	    * @param startDate - The start date
	    * @param years - Negative to rollbackwards.
	    */
	    public static java.sql.Date rollYears( java.util.Date startDate, int years )
	    {
	        return rollDate( startDate, Calendar.YEAR, years );
	    }

	   /**
	    * Roll the days forward or backward.
	    * @param startDate - The start date
	    * @param months - Negative to rollbackwards.
	    */
	    public static java.sql.Date rollMonths( java.util.Date startDate, int months )
	    {
	        return rollDate( startDate, Calendar.MONTH, months );
	    }

	   /**
	    * Roll the days forward or backward.
	    * @param startDate - The start date
	    * @param days - Negative to rollbackwards.
	    */
	    public static java.sql.Date rollDays( java.util.Date startDate, int days )
	    {
	        return rollDate( startDate, Calendar.DATE, days );
	    }
	    
	     /**
	      * Checks the day, month and year are equal.
	      */
	     public static boolean dateEquals( java.util.Date d1, java.util.Date d2 )
	     {
	        if( d1 == null || d2 == null ) return false;
	        Calendar calendar = Calendar.getInstance();
	        Calendar calendar2 = Calendar.getInstance();
	        calendar.setTime(d1);
	        calendar2.setTime(d2);
	        return calendar.compareTo(calendar2) == 0;
//	        return d1.getDate() == d2.getDate() &&
//	               d1.getMonth() == d2.getMonth() &&
//	               d1.getYear() == d2.getYear();
	     }
	     
//	     /**
//	      * Checks the hour, minute and second are equal.
//	      */
//	     public static boolean timeEquals( java.util.Date d1, java.util.Date d2 )
//	     {
//	        if( d1 == null || d2 == null ) return false;
//	        
//	        return d1.getHours() == d2.getHours() &&
//	               d1.getMinutes() == d2.getMinutes() &&
//	               d1.getSeconds() == d2.getSeconds();
//	     }
//	
//	
//	    /**
//	      * Checks the second, hour, month, day, month and year are equal.
//	      */
//	     public static boolean dateTimeEquals( java.util.Date d1, java.util.Date d2 )
//	     {
//	        if( d1 == null || d2 == null ) return false;               
//	        
//	        return d1.getDate() == d2.getDate() &&
//	               d1.getMonth() == d2.getMonth() &&
//	               d1.getYear() == d2.getYear() &&
//	               d1.getHours() == d2.getHours() &&
//	               d1.getMinutes() == d2.getMinutes() &&
//	               d1.getSeconds() == d2.getSeconds();
//	     }
	     
	     /**
	     * Convert an Object of type Classs to an Object.
	     */
	    public static Object toObject( Class<?> clazz, Object value ) throws ParseException
	    {
	        if( value == null ) return null;
	        if( clazz == null ) return value;
	        
	        if( java.sql.Date.class.isAssignableFrom( clazz ) ) return toDate( value );
	        if( java.sql.Time.class.isAssignableFrom( clazz ) ) return toTime( value );
	        if( java.sql.Timestamp.class.isAssignableFrom( clazz ) ) return toTimestamp( value );
	        if( java.util.Date.class.isAssignableFrom( clazz ) ) return toDateTime( value );
	        
	        return value;
	    }
	    
	    /**
	     * Convert an Object to a DateTime, without an Exception
	     */
	    public static java.util.Date getDateTime( Object value )
	    {
	        try
	        {
	            return toDateTime( value );
	        }
	        catch( ParseException pe )
	        {
	            pe.printStackTrace();
	            return null;
	        }
	    }
	    
	    /**
	     * Convert an Object to a DateTime.
	     */
	    public static java.util.Date toDateTime( Object value ) throws ParseException
	    {
	        if( value == null ) return null;        
	        if( value instanceof java.util.Date ) return (java.util.Date)value;
	        if( value instanceof String )
	        {
	            if( "".equals( (String)value ) ) return null;
	            return IN_DATETIME_FORMAT.parse( (String)value );
	        }
	                
	        return IN_DATETIME_FORMAT.parse( value.toString() );
	    }
	    
	    /**
	     * Convert an Object to a Date, without an Exception
	     */
	    public static java.sql.Date getDate( Object value )
	    {
	        try
	        {
	            return toDate( value );
	        }
	        catch( ParseException pe )
	        {
	            pe.printStackTrace();
	            return null;
	        }
	    }

	    /**
	     * Convert an Object to a Date.
	     */
	    public static java.sql.Date toDate( Object value ) throws ParseException
	    {
	        if( value == null ) return null;        
	        if( value instanceof java.sql.Date ) return (java.sql.Date)value;
	        if( value instanceof String )
	        {
	            if( "".equals( (String)value ) ) return null;
	            return new java.sql.Date( IN_DATE_FORMAT.parse( (String)value ).getTime() );
	        }
	                
	        return new java.sql.Date( IN_DATE_FORMAT.parse( value.toString() ).getTime() );
	    }
	    
	    /**
	     * Convert an Object to a Time, without an Exception
	     */
	    public static java.sql.Time getTime( Object value )
	    {
	        try
	        {
	            return toTime( value );
	        }
	        catch( ParseException pe )
	        {
	            pe.printStackTrace();
	            return null;
	        }
	    }
	    
	    /**
	     * Convert an Object to a Time.
	     */
	    public static java.sql.Time toTime( Object value ) throws ParseException
	    {
	        if( value == null ) return null;        
	        if( value instanceof java.sql.Time ) return (java.sql.Time)value;
	        if( value instanceof String )
	        {
	            if( "".equals( (String)value ) ) return null;
	            return new java.sql.Time( IN_TIME_FORMAT.parse( (String)value ).getTime() );
	        }
	                
	        return new java.sql.Time( IN_TIME_FORMAT.parse( value.toString() ).getTime() );
	    }
	   
	    /**
	     * Convert an Object to a Timestamp, without an Exception
	     */
	    public static java.sql.Timestamp getTimestamp( Object value )
	    {
	        try
	        {
	            return toTimestamp( value );
	        }
	        catch( ParseException pe )
	        {
	            pe.printStackTrace();
	            return null;
	        }
	    }

	    /**
	     * Convert an Object to a Timestamp.
	     */
	    public static java.sql.Timestamp toTimestamp( Object value ) throws ParseException
	    {
	        if( value == null ) return null;        
	        if( value instanceof java.sql.Timestamp ) return (java.sql.Timestamp)value;
	        if( value instanceof String )
	        {
	            if( "".equals( (String)value ) ) return null;
	            return new java.sql.Timestamp( IN_TIMESTAMP_FORMAT.parse( (String)value ).getTime() );
	        }
	                
	        return new java.sql.Timestamp( IN_TIMESTAMP_FORMAT.parse( value.toString() ).getTime() );
	    }
	    
	    /**
	     * Tells you if the date part of a datetime is in a certain time range.
	     */
	    @SuppressWarnings("deprecation")
		public static boolean isTimeInRange( java.sql.Time start, java.sql.Time end, java.util.Date d )
	    {
	        d=new java.sql.Time(d.getHours(),d.getMinutes(),d.getSeconds());
	        
	        if (start==null || end==null)
	        {
	            return false;
	        }
	        
	        if (start.before(end)&&(!(d.after(start)&&d.before(end))))
	        {
	            return false;
	        }
	        
	        if (end.before(start)&&(!(d.after(end)||d.before(start))))
	        {
	            return false;
	        }   
	        return true;
	    }

	    public static  int getYear( Date date )
	    {
	        calendar.setTime( date );
	        return calendar.get( Calendar.YEAR );
	    }

	    public static int getMonth( Date date )
	    {
	        calendar.setTime( date );
	        return calendar.get( Calendar.MONTH );
	    }

	    public static int getDate( Date date )
	    {
	        calendar.setTime( date );
	        return calendar.get( Calendar.DATE );
	    }

	    public static int getHour( Date date )
	    {
	        calendar.setTime( date );
	        return calendar.get( Calendar.HOUR );
	    }

	    public static int getMinute( Date date )
	    {
	        calendar.setTime( date );
	        return calendar.get( Calendar.MINUTE );
	    }
	     
	    public static int getSeconds( Date date )
	    {
	        calendar.setTime( date );
	        return calendar.get( Calendar.SECOND );
	    }
	    
	    public static Time toTime(Date date){
	    	Time time = new Time(date.getTime());
	    	return time;
	    }


	    public static int getMillisecond( Date date )
	    {
	        calendar.setTime( date );
	        return calendar.get( Calendar.MILLISECOND );
	    }
	    
	    /**
	     * Convert an Object to a String using Dates
	     */
	    public static String toString( Object date )
	    {
	        if( date == null ) return null;
	        
	        if( java.sql.Timestamp.class.isAssignableFrom( date.getClass() ) )
	        {
	            return OUT_TIMESTAMP_FORMAT.format( date );
	        }
	        if( java.sql.Time.class.isAssignableFrom( date.getClass() ) )
	        {
	            return OUT_TIME_FORMAT.format( date );
	        }
	        if( java.sql.Date.class.isAssignableFrom( date.getClass() ) )
	        {
	            return OUT_DATE_FORMAT.format( date );
	        }
	        if( java.util.Date.class.isAssignableFrom( date.getClass() ) )
	        {
	            return OUT_DATETIME_FORMAT.format( date );
	        }
	        
	        throw new IllegalArgumentException( "Unsupported type " + date.getClass() );
	    }
	    
	    public static double getHoursDiff(Time arg0, Time arg1){
	    	return new Double(arg0.getTime() - arg1.getTime()) / (60 * 60 * 1000);
	    }
	    
	    public static double getHourDiff(Date end, Date start){
	    	return new Double((end.getTime() - start.getTime())/ (60 * 60 * 1000));
	    }
	    
	    public static Date formatToTemporal(Object object) throws ParseException{
	    	return OUT_DATE_FORMAT.parse(object.toString());
	    }
	    
	    public static Date formatToTemporal(Date date) throws ParseException{
	    	return OUT_DATE_FORMAT.parse(OUT_DATE_FORMAT.format(date).toString());
	    }
	    
	    public static String getDate(long time){
	    	Calendar cal = Calendar.getInstance();
	    	cal.setTimeInMillis(time);
	    	return sdfWithTime.format(cal.getTime());
	    }
	
}
