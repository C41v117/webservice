package com.metamorf.eform.common.config;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;

public class BeanUtilsConfigurer {
	static IntegerConverter integerConverter = new IntegerConverter(null);
	static DoubleConverter doubleConverter = new DoubleConverter(null);
	static LongConverter longConverter = new LongConverter(null);
	static FloatConverter floatConverter = new FloatConverter(null);
	static DateConverter dateConverter = new DateConverter(null);
	static BigDecimalConverter bigDecimalConverter = new BigDecimalConverter(null);
	
	public static void configure() {
		ConvertUtils.register(integerConverter, Integer.class);
		ConvertUtils.register(longConverter, Long.class);
		ConvertUtils.register(doubleConverter, Double.class);
		ConvertUtils.register(floatConverter, Float.class);
		ConvertUtils.register(dateConverter, Date.class);
		ConvertUtils.register(bigDecimalConverter, BigDecimal.class);
	}
}
