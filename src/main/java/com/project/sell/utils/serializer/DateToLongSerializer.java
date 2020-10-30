package com.project.sell.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @Author: HB
 * @Description: Date 转换器
 * @CreateDate: 23:08 2020/10/30
 */

public class DateToLongSerializer extends JsonSerializer<Date> {

    /**
     * @Author: HB
     * @Description: Date转换为Long
     * @Date: 23:09 2020/10/30
     * @Params: null
     * @Returns:
    */
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeNumber(date.getTime() / 1000);
    }
}
