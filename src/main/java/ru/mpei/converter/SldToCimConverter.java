package ru.mpei.converter;

import lombok.Getter;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import ru.mpei.Element;
import ru.mpei.SingelLineDiagram;
import ru.mpei.writer.RdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SldToCimConverter {

    private final String cimNamespace = "http://iec.ch/TC57/2013/CIM-schema-cim16#";
    @Getter
    private ModelBuilder modelBuilder = new ModelBuilder();

    /**Конветация каждого элемента первичного оборудования в сущность RDF */
    public void convert(SingelLineDiagram sld){
        sld.getElements().forEach(e->converterElementsToRdfResource(e));
    }
    /**
     * Субъект
     * Предикат
     * Объект
     *
     * */

    public SldToCimConverter(){
        modelBuilder.
                setNamespace("cim",cimNamespace).
                setNamespace(RDF.PREFIX,RDF.NAMESPACE);
    }
    /**Передача ресурс "subject" и добавляем ему mRiD и name */
    private void converterElementsToRdfResource(Element element){
        modelBuilder.subject("cim:"+element.getId());
        modelBuilder.add("cim:IdentifiedObject.mRID", element.getId());
        if(element.getProjectName()!=null)
            modelBuilder.add("cim:IdentifiedObject.name", element.getProjectName());
    }
    /**Метод для сохранения в XML файл*/
    public String getResult(RDFFormat rdfFormat) {
        Model model = modelBuilder.build();
        if (rdfFormat.equals(RDFFormat.RDFXML)) {
            RdfWriter rdfWriter = new RdfWriter();
            return rdfWriter.writeXml(model);
        } else {
            OutputStream out = null;
            String cim;
            try {
                File tempFile = File.createTempFile("file", ".xml");
                out = new FileOutputStream(tempFile);
                Rio.write(model, out, cimNamespace, rdfFormat);
                //;
                cim = Files.readAllLines(Path.of(tempFile.getPath()));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return cim;
        }
    }



}
