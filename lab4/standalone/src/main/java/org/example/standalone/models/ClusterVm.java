package org.example.standalone.models;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class ClusterVm {
    private UUID id;
    private Long vmid;
    private String name;
    private String image;
    private Integer cpu;
    private Integer memory;
}
