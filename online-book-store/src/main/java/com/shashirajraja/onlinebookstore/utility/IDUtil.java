package com.shashirajraja.onlinebookstore.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IDUtil {

	public static String generatePurchaseHistoryId() {
		String id = "T";		
		String pattern = "yyyyMMddhhmmss";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		id += sdf.format(new Date());
		return id;
	}
}
