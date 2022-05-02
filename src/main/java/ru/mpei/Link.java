package ru.mpei;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Link extends Idenrifiend {

    private String sourceId;
    private String sourcePortId;
    private String targetId;
    private String targetPortId;

}
