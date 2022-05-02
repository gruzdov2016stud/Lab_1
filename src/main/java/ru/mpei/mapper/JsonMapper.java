package ru.mpei.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.mpei.SingelLineDiagram;

import java.io.File;
import java.io.IOException;

public class JsonMapper {

    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @SneakyThrows /**Try cetch */
    public SingelLineDiagram mapJasonToSld (String filePath) {
        return objectMapper.readValue( new File(filePath), SingelLineDiagram.class);
    }
}
