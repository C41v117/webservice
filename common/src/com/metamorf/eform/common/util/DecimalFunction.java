package com.metamorf.eform.common.util;

import java.math.BigDecimal;

public class DecimalFunction {

	public static BigDecimal normalRound(BigDecimal number) {
		BigDecimal result;
		result = number.setScale(2, BigDecimal.ROUND_HALF_UP);
		return result;
	}
}
