package cn.zch.ssm.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.core.convert.converter.Converter;

//日期转换类     泛型为    要转化的   类型     目标类型     
public class DataConverter implements Converter<String, Date> {

	@Override
	public Date convert(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			 date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
