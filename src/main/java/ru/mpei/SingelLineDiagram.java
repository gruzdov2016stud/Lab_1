package ru.mpei;

import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SingelLineDiagram {
    private List<Link> links;
    private List<Element> elements;
}
