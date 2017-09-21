package com.metamorf.eform.common.util;

import com.metamorf.eform.common.core.SystemConstant;
import com.metamorf.eform.common.encryptor.MD5Encryptor;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentRefGenerator {

	public static String getPaymentRef(String paymentCode, Date paymentDate, String sequenceNo) {
		MD5Encryptor md5 = new MD5Encryptor();

		StringBuffer sb = new StringBuffer(paymentCode).
				append(new SimpleDateFormat(SystemConstant.SYSTEM_REPORT_DATE).format(paymentDate)).
				append(sequenceNo);
		
		String generateMd5 = md5.encrypt(sb.toString()).substring(sb.toString().length());
		String paymentRef = sb.toString().concat(generateMd5.substring(generateMd5.length()-3).toUpperCase());
		return paymentRef;
    }
}
