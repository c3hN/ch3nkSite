package com.ch3nk.ch3nkSite.common.processor;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * 自定义转换器
 * Json中Date类型数据在转换后页面显示为时间戳
 * 使用该转换器将时间戳转换为 yyyy-MM-dd 格式
 *
 * POJO的对应属性getter加上@JsonSerialize(using = JsonDateSerializer.class)注解
 */
public class JsonDate2StringProcessor extends JsonSerializer {
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(o);
        jsonGenerator.writeString(date);
    }
}
