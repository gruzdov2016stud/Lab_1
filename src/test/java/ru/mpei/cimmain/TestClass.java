package ru.mpei.cimmain;


import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.junit.jupiter.api.Test;
import ru.mpei.SingelLineDiagram;
import ru.mpei.converter.SldToCimConverter;
import ru.mpei.mapper.JsonMapper;

import java.io.IOException;

public class TestClass {
    String filePath = "src/test/java/resources/Viezdnoe.json";
    @Test
    public void test(){
        JsonMapper jsonMapper = new JsonMapper();
        SingelLineDiagram sld = jsonMapper.mapJasonToSld(filePath);
        SldToCimConverter converter = new SldToCimConverter();
        converter.convert(sld);
        ModelBuilder modelBuilder = converter.getModelBuilder();
        String simModel = converter.getResult();
        System.out.println();


    }

}
