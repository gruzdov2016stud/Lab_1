package ru.mpei;

import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Port extends Idenrifiend {
    private String name;
    private List<String> lins;
    private ArrayNode fields;


}
