package com.yeahwell.epu.web.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeahwell.epu.web.log.LogType;
import com.yeahwell.epu.web.sms.impl.SmsClientSend;

public class SmsSender {

	private static Logger logger = LoggerFactory
			.getLogger(LogType.EPU_COMMON.val);

	public static void send(String sms) {
		logger.info("发送短信--" + sms);
	}

	public static void sendReal(String sms, String mobile) {
		logger.info("发送短信--" + sms);
		SmsClientSend.sendSms("http://122.4.79.43:2852/sms.aspx", "1671",
				"yuanshouyan1823", "910816ysy", mobile, sms);
	}

	public static void send(Sms sms) {

	}

	public static void sendSingle(SingleSms singleSms) {

	}

}