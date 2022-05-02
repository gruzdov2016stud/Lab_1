package ru.mpei.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.mpei.SingelLineDiagram;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonMapper {

    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @SneakyThrows /**Try cetch */
    public SingelLineDiagram mapJasonToSld (String filePath) {
        return objectMapper.readValue( new File(filePath), SingelLineDiagram.class);
    }
    @SneakyThrows
    public SingleLineDiagram mapJsonToSld(String filePath) {
        return objectMapper.readValue(
                new File(filePath), SingleLineDiagram.class
        );
    }

    @SneakyThrows
    public Map<String, String> mapDeviceJsonToSld(String filePath) {
        Map<String, String> device = new HashMap<>();
        Device[] deviceType = objectMapper.readValue(new File(filePath), Device[].class);
        for (Device line : deviceType) {
            device.put(line.getId(), line.getName().getEn());
        }
        return device;
    }

    @SneakyThrows
    public Map<String, String> mapVoltageLevelJsonToSld(String filePath) {
        Map<String, String> voltage = new HashMap<>();
        Voltage[] voltageType = objectMapper.readValue(new File(filePath), Voltage[].class);
        for (Voltage line : voltageType) {
            voltage.put(line.getId(), line.getValue().getEn());
        }
        return voltage;
    }
}
