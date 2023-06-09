package com.zjxz.gateway.config;

import java.text.*;
import java.util.Date;

/**
 * @author hzzzzzy
 * @create 2023/4/1
 * @description 自定义DateFormat
 */
public class MyDateFormat extends DateFormat{

    private DateFormat dateFormat;
    private SimpleDateFormat format1 = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
    public MyDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return dateFormat.format(date, toAppendTo, fieldPosition);
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        Date date = null;
        try {
            date = format1.parse(source, pos);
        } catch (Exception e) {
            date = dateFormat.parse(source, pos);
        }
        return date;
    }

    @Override
    public Date parse(String source) throws ParseException {
        Date date = null;
        try {
            date = format1.parse(source);
        } catch (Exception e) {
            // 按原先的规则吧
            date = dateFormat.parse(source);
        }

        return date;
    }

    @Override
    public Object clone() {
        Object format = dateFormat.clone();
        return new MyDateFormat((DateFormat) format);
    }
}
