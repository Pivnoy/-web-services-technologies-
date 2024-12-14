package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ServiceInfo {
    private String id;
    private String name;
    private String description;
    private String accessPoint;
}
