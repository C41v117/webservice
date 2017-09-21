/*
 * Created on Jun 9, 2005
 *
 */
package com.metamorf.eform.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.core.SystemConstant.RegexPattern;
import com.metamorf.eform.common.core.SystemParameter;

/**
 * @author eRyan
 *
 */
public class StringFunction {
    private static final String APOSTROPHE_CODE="'";
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public static String trim(String s) {
    	if (s != null) {
    		return s.trim();
    	}
    	return null;
    }
    
    public static boolean isEmpty (String s) {
        boolean empty = false;
        if ((s == null) || s.trim().equals("")) {
            empty = true;
        }
        return empty;
    }

    public static String[] split(String str, String delim) {

      StringTokenizer strtok = new StringTokenizer(str, delim);
      String[] result = new String[strtok.countTokens()];

      for (int i = 0; i < result.length; i++) {
        result[i] = strtok.nextToken();
      }

      return result;
    }

    public static String replace2(String str, String what, String onwhat) {

        int beginIndex = 0;
        int endIndex = 0;
        String Result = "";

        endIndex = str.indexOf(what, beginIndex);

        while (endIndex != -1) {
            Result = Result + str.substring(beginIndex, endIndex) + onwhat;
            beginIndex = endIndex + what.length();
            endIndex = str.indexOf(what, beginIndex);
        }

        Result = Result + str.substring(beginIndex, str.length());

        return Result;
    }

    public static String htmlEscape(String str) {
        str = replace(str, "&", "&amp;");
        str = replace(str, "<", "&lt;");
        str = replace(str, ">", "&gt;");
        str = replace(str, "\"", "&quot;");

        return str;
    }

    public static String preformat(String str) {
        str = replace(str, "\n", "<br/>");
        return str;
    }

    /**
     * Concat two arrays of Strings,
     * part2 is appended to part1
     */
    public static String[] concat(String[] part1, String[] part2) {
        String[] full = new String[part1.length + part2.length];
        System.arraycopy(part1, 0, full, 0, part1.length);
        System.arraycopy(part2, 0, full, part1.length, part2.length);
        return full;
    }

    public static String replace3(String inputString, String oldString, String newString) {
        StringBuffer desc = new StringBuffer();
        int index = inputString.indexOf(safeTrim(oldString));
        int last = 0;
        while (index != -1) {
            desc.append(inputString.substring(last, index));
            desc.append(newString);
            last = index + oldString.length();
            index = inputString.indexOf(oldString, last);
        }
        desc.append(inputString.substring(last));
        return desc.toString();
    }
    
    public static String replaceIfEmptyWith(String inputString, String replacingString) {
    	if (isEmpty(inputString)) 
    		return replacingString;
    	else
    		return inputString;
    }

    /**
     *  Replace all instances of a String in a String.
     *   @param  s  String to alter.
     *   @param  f  String to look for.
     *   @param  r  String to replace it with, or null to just remove it.
     */
    public static String replace(String s, String f, String r) {
        if (s == null)
            return s;
        if (f == null)
            return s;
        if (r == null)
            r = "";

        int index01 = s.indexOf(f);
        while (index01 != -1) {
            s = s.substring(0, index01) + r + s.substring(index01 + f.length());
            index01 += r.length();
            index01 = s.indexOf(f, index01);
        }
        return s;
    }


    public static long[] splitIntoLong(String str, String delimiter) {
        StringTokenizer st = new StringTokenizer(str, delimiter);
        long[] value = new long[st.countTokens()];
        int size = st.countTokens();
        for (int i=0; i<size; i++) {
            value[i] = Long.parseLong(st.nextToken());
        }
        return value;
    }

    public static int[] splitIntoInt(String str, String delimiter) {
        StringTokenizer st = new StringTokenizer(str, delimiter);
        int[] value = new int[st.countTokens()];
        int size = st.countTokens();
        for (int i=0; i<size; i++) {
            value[i] = Integer.parseInt(st.nextToken());
        }
        return value;
    }

    public static String null2String(Object obj) {
        if ( obj == null ) {
            return "";
        } else {
            return obj.toString();
        }
    }

    public static double[] splitIntoDouble(String str, String delimiter) {
        StringTokenizer st = new StringTokenizer(str, delimiter);
        double[] value = new double[st.countTokens()];
        double size = st.countTokens();
        for (int i=0; i<size; i++) {
            value[i] = Double.parseDouble(st.nextToken());
        }
        return value;
    }

    /**
     * This method is used to evaluate data String
     * If parameter is null or the length of trim of parametes is 0 the method will return null value
     * else value of parameter will returned
     * @param stInput
     * @return
     */
    public static String checkString(String stInput) {
        if (StringUtils.isEmpty(stInput)) {
            return null;
        }

        if (StringUtils.isBlank(stInput)) {
            return null;
        }

        return stInput;
    }

    /**
     * Compare value of two String object
     * @param s1
     * @param s2
     * @return boolean
     */
    public static boolean compareString(String s1, String s2) {
        boolean bResult;
        if (s1 == null) {
            if (s2 == null) {
                bResult = true;
            } else {
                bResult = false;
            }
        } else {
            bResult = s1.equals(s2);
        }
        return bResult;
    }



    /**
     * to secure and save input string into sql statement
     * @param s
     * @return
     */
    public static String toSQLString(String s) {
        return replace(s,"'", "''");
    }
    private static String buildStringWithApostrophe(Object[] obj,String del){
        if ( obj.length > 0) {
            if ( obj.length == 1) {
                return APOSTROPHE_CODE+obj[0].toString()+APOSTROPHE_CODE;
            } else {
                StringBuffer buff = new StringBuffer("'"+obj[0].toString()+APOSTROPHE_CODE);
                for (int i=1; i<obj.length; i++) {
                    buff.append(del);
                    buff.append(APOSTROPHE_CODE);
                    buff.append(obj[i].toString());
                    buff.append(APOSTROPHE_CODE);
                }
                return buff.toString();

            }

        } else {
            return null;
        }

    }

    public static String buildStringList(Object[] obj, String del,boolean isApostrophe) {
        if (del == null) del = ",";
        if(!isApostrophe) return buildStringListFromArray(obj,del);
        else return buildStringWithApostrophe(obj,del);

    }


    /**
     * build "1,2,3,4" from array of String {"1", "2", "3", "4"} with delimiter ','
     * @param obj
     * @param del Delimiter. default value is comma
     * @return
     */
    public static String buildStringListFromArray(Object[] obj, String del) {
        if (del == null) del = ",";
        if ( obj.length > 0) {
            if ( obj.length == 1) {
                return obj[0].toString();
            } else {
                StringBuffer buff = new StringBuffer(obj[0].toString());
                for (int i=1; i<obj.length; i++) {
                    buff.append(del);
                    buff.append(obj[i].toString());
                }
                return buff.toString();

            }

        } else {
            return null;
        }
    }

    public static String hexaToAscii(String strHex){
        String result = null;
        if((strHex != null) && (strHex.length() > 2)){
            StringBuffer sb = new StringBuffer();
            String str = strHex.substring(2);
            boolean isComplete = false;
            int idx = 0;
            while(!isComplete){
                String s    = str.substring(idx, idx+2);
                int i       = Integer.parseInt(s, 16);
                char c      =(char)i;
                sb.append(c);
                idx = idx + 2;
                if(idx > (str.length()-1)){
                    isComplete = true;
                }
            }
            result = sb.toString();
        }

        return result;
    }

    public static boolean isNumber(String s){
        try {
            Long.parseLong(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    private static final Pattern areaPattern = Pattern.compile("[a-zA-Z0-9]{2}");
    private static final Pattern areaRegionPattern = Pattern.compile("[a-zA-Z0-9]{3}-[a-zA-Z0-9]{2}");
    private static final Pattern noAreaPattern = Pattern.compile("[a-zA-Z0-9]{3}--");
    //unused password pattern because of october immediate deployment
    /*private static String AAPL_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?!.*[ ;='\\-\\(\\)]).{" 
    	+ SystemParameter.MIN_AAPL_LENGTH + "," + SystemParameter.MAX_PASSWORD_LENGTH + "}$";*/
    //used password pattern using (SAT Execution Log - English August 2013 version.doc) 
    private static String AAPL_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{" 
        	+ SystemParameter.MIN_PASSWORD_LENGTH + "," + SystemParameter.MAX_PASSWORD_LENGTH + "}$";
    private static Pattern pwdPattern = Pattern.compile(AAPL_PATTERN);
    private static final Pattern moneyPattern = Pattern.compile("^[+-]?[0-9]{1,3}(?:[0-9]*(?:[.,][0-9]{1,2})?|(?:,[0-9]{3})*(?:\\.[0-9]{1,2})?|(?:\\.[0-9]{1,2})*(?:,[0-9]{1,2})?)$");
    private static final Pattern percentagePattern = Pattern.compile("100\\.00|\\d{1,3}\\.\\d{1,2}");
    
    public static boolean isValidCompanyCodeFormat(String value){
        Matcher matcher = RegexPattern.companyCodePattern.matcher(value);
        return matcher.matches();
    }
    
    public static boolean isValidPhoneFormat(String value){
        Matcher matcher = RegexPattern.phonePattern.matcher(value);
        return matcher.matches();
    }
    
    public static boolean isValidAreaFormat(String value){
        Matcher matcher = areaPattern.matcher(value);
        return matcher.matches();
    }

    public static boolean isValidAreaRegionFormat(String value) {
        Matcher matcher1 = areaRegionPattern.matcher(value);
        Matcher matcher2 = noAreaPattern.matcher(value);
        if(matcher1.matches())
        	return true;
        else
        	return matcher2.matches();
    }	
    
    public static boolean isValidMoneyFormat(String value){
        Matcher matcher = moneyPattern.matcher(value);
        return matcher.matches();
    }
    
    public static boolean isValidPercentageFormat(String value){
        Matcher matcher = percentagePattern.matcher(value);
        return matcher.matches();
    }
    
    public static Long[] splitIntoLongObject(String str, String delimiter) {
        StringTokenizer st = new StringTokenizer(str, delimiter);
        Long[] value = new Long[st.countTokens()];
        int size = st.countTokens();
        for (int i=0; i<size; i++) {
            value[i] = Long.parseLong(st.nextToken());
        }
        return value;
    }
    
    public static List<String> convertIntoListOfString(List<Object> objs){
    	List<String> strList = new ArrayList<String>();
    	for (Object object : objs) {
			strList.add(object.toString());
		}
    	
		return strList;
    	
    }

	public static String formatNumber(long number, String pattern) {
		NumberFormat fmt = new DecimalFormat(pattern);
		return fmt.format(number);
	}
	
	public static String safeString(String value) {
		if (value == null) {
			return "";
		}
		return value;
	}
	
	public static String safeStringInteger(String value) {
		if ("".equals(safeString(value))) {
			return "0";
		}
		return value;
	}
	
	public static String safeStringDouble(String value) {
		if ("".equals(safeString(value))) {
			return "0.0";
		}
		return value;
	}
	
    public static String safeTrim(String value) {
    	if (value == null) {
    		return null;
    	}
    	return value.trim();
    }
    
    public static String convertNewlineToBr(String text) {
		return replace(text, "\n", "<br />");
	}
    
    public static String convertNewline(String text, String replacement) {
		return replace(text, "\n", replacement);
	}

    /**
     * Coalesce whitespace into single space. 
     * Main purpose is to coalesce tabs and spaces into a single space for displaying in HTML.
     * 
     * @param text string to convert
     * @return string where all the whitespaces are coalesced
     */
	public static String coalesceWhitespace(String text) {
		if (text == null || text.length() == 0) {
			return text;
		}
		return text.replaceAll("[ \\t\\x0B\\f\\r]+", " ");
	}

	/**
	 * Trim and coalesce all whitespaces except newline
	 * @param text String to convert
	 * @return
	 */
	public static String safeTrimAndCoalesceWhitespace(String text) {
		String result = safeTrim(text);
		if (result == null || result.length() == 0) {
			return result;
		}
		
		return result.replaceAll("[ \\t\\x0B\\f\\r]+", " ");
	}

	public static boolean isValidPassword(String value) {
		String _AAPL_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{" 
	        	+ SystemParameter.MIN_PASSWORD_LENGTH + "," + SystemParameter.MAX_PASSWORD_LENGTH + "}$";
		Pattern localPwdPattern = Pattern.compile(_AAPL_PATTERN);
    	Matcher matcher = localPwdPattern.matcher(value);
        return matcher.matches();
    }
	
	//add by yk (20130522)
	private static final char FLOAT_SEPARATOR = '.';
	private static final Pattern numericRE = Pattern.compile("\\" + FLOAT_SEPARATOR + "?\\d*");
	public static boolean isValidNumeric(String string) {
		return numericRE.matcher(string).matches();
	}
	
	public static boolean isValidDateFormat(String string) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(SystemConstant.SYSTEM_DATE_MASK);
		try {
			return (dateFormat.parse(string) == null) ? false : true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isValidBooleanFormat(String string) {
		boolean rtn=false;
		try {
			rtn=Boolean.parseBoolean(string);
			if(!rtn && string.toUpperCase().equals("FALSE")){
				rtn=true;
			}
		} catch (Exception e) {
			return false;
		}
		return rtn;
	}
	
	//end

	
	public static int countOccurrences(String haystack, char needle)
	{
	    int count = 0;
	    for (int i=0; i < haystack.length(); i++)
	    {
	        if (haystack.charAt(i) == needle)
	        {
	             count++;
	        }
	    }
	    return count;
	}
	
	public static String getStackTrace(Throwable aThrowable) {
	    final Writer result = new StringWriter();
	    final PrintWriter printWriter = new PrintWriter(result);
	    aThrowable.printStackTrace(printWriter);
	    return result.toString();
   }


	public static String pad(String toPad, Boolean isLeft, String padChar, int size){
		if(isLeft){
			return StringUtils.leftPad(toPad, size, padChar);
		}else{
			return StringUtils.rightPad(toPad, size, padChar);
		}
	}
	
	/**
	 * Add a Trailing Slash to a path if not exist, else return original path
	 * 
	 * @param path - path need to be added with a trailing slash
	 * @return		 path with trailing slash
	 */
	public static String addTrailingSlash(String path) {
		char lastChar = path.charAt(path.length()-1);
		if (lastChar != '/' && lastChar != '\\'){
		    path += "/";
		}
		return path;
	}
	
	// add by ggn
	public static boolean isInteger(String s){
        try {
        	Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
	
	public static boolean isLong(String s){
        try {
        	Long.parseLong(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
	
	public static boolean isDouble(String s){
        try {
        	Double.parseDouble(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
	
	public static boolean isString(String s){
        try {
        	@SuppressWarnings("unused")
			String input = new String(s);
        	input = null;
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
	
	public static boolean isValidDateFormatSlash(String string) {
		try {
			final SimpleDateFormat dateFormatSlash = new SimpleDateFormat(SystemConstant.SYSTEM_DATE_MASK_SLASH);
			return (dateFormatSlash.parse(string) == null) ? false : true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static boolean isTime(String s){
        try {
        	Time.parse(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
	// enf of add by ggn

	public static String formatMoney(BigDecimal amount) {
		String result = "";
		if(amount!=null){
			String moneyFomat = SystemParameter.MONEY_FORMAT;
			if(moneyFomat==null){
				moneyFomat = SystemConstant.DEFAULT_MONEY_FORMAT;
			}
			
			String paramSymbol = moneyFomat.replace("#", "");
			char separator = paramSymbol.charAt(0);
			char decimal = paramSymbol.charAt(paramSymbol.length()-1);		
			
			String format = "#"+separator+"##0"+decimal+"00";
			result = new DecimalFormat(format).format(amount);
		}
		
		return result;
	}
	
	public static Boolean isValidInvoiceDateFormat(String dateStr){
		Boolean isValid = true;
		
		String[] anDate = dateStr.split("-");
		
		if(anDate.length==3){
			String yearStr = anDate[0];
			int year = Integer.parseInt(yearStr);
			int month = Integer.parseInt(anDate[1]);
			int date = Integer.parseInt(anDate[2]);
			
			if(yearStr.length()!=4)
				isValid = false;
			else if(!(month > 0 && month < 13))
				isValid = false;
			else{
				Calendar mycal = new GregorianCalendar(year, month-1, 1);
				int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
				
				if(!(date > 0 && date <= daysInMonth))
					isValid = false;
			}
		}else{
			isValid=false;
		}
		
		return isValid;
	}
	
	public static String arraysToString(Object[] paramArrayOfObject) {
	    if (paramArrayOfObject == null) {
	      return "null";
	    }
	    int i = paramArrayOfObject.length - 1;
	    if (i == -1) {
	      return "null";
	    }
	    StringBuilder localStringBuilder = new StringBuilder();
	    for (int j = 0; ; ++j) {
	      localStringBuilder.append(String.valueOf(paramArrayOfObject[j]));
	      if (j==i) {
	    	  return localStringBuilder.toString();
	      }
	      localStringBuilder.append(SystemConstant.COMMA_DELIMITER);
	    }
	}
	
	private static synchronized long getSystemId () {
		return System.currentTimeMillis();
	}
 	
	/**
	 * @param server
	 * @param referenceNo
	 * @return param referenceNo if not null
	 */
	public static String populateReferenceNo (String server, String referenceNo) {
		if (org.apache.commons.lang.StringUtils.trimToNull(referenceNo) == null) {
			StringBuffer sb = new StringBuffer(server).append(SystemConstant.DELIMITER_DASH).append(getSystemId());
			return sb.toString();
		}
		return referenceNo;
	}
	
	public static String generateOutsystemsDummyId () {
		return String.valueOf(getSystemId());
	}
	
	/**
	 * 
	 * @param textString
	 * @param length
	 * @return fixlength String
	 */
	public static String fixedLengthStringRightPadded(String textString, int length) {
	    return String.format("%1$-"+length+ "s", StringFunction.safeString(textString));
	}
	
	/**
	 * @param listSeparatedTitle is list title, user SystemParameter like CA or etc
	 * @param titleToCheck is title to be checked, return true if contain, false if not contain
	 */
	public static boolean checkTitle(String listSeparatedTitle, String titleToCheck) {
		String[] arrSeparatedTitle = listSeparatedTitle.split(SystemConstant.COMMA_DELIMITER);
		for (int i=0 ; i<arrSeparatedTitle.length ; i++) {
			if (titleToCheck.equalsIgnoreCase(StringUtils.trimToEmpty(arrSeparatedTitle[i]))) {
				return true;
			}
		}
		return false;
	}
	
	public static String substringMitraId (String mitraId) {
		if (StringUtils.trimToEmpty(mitraId).length() > 2) {
			return  mitraId.substring(4, 6);
		}
		else {
			return mitraId;
		}
	}
	
	public static String ifNotEmptyCondition(String message,String elseMessageCondition){
		return ifNotEmptyCondition(message, message, elseMessageCondition);
	}
	
	public static String ifNotEmptyCondition(String message, String ifMessageCondition, String elseMessageCondition){
		if(!isEmpty(safeString(message))){
			return ifMessageCondition;
		}else{
			return elseMessageCondition;
		}
	}
}