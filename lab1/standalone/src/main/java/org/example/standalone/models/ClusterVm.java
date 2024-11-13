package org.example.standalone.models;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@Builder
public class ClusterVm {
    private UUID id;
    private Long vmid;
    private String name;
    private String image;
    private Integer cpu;
    private Integer memory;
}
