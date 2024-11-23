package org.example.standalone.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
@XmlRootElement
public class Filter<T>{

    private static final String EQUALS = " = ";
    private String fieldName;
    private T value;

    public String toString()  {
        if (value instanceof String || value instanceof UUID) {
            return this.fieldName + EQUALS + "'" + value + "'";
        }

        return this.fieldName + EQUALS + value;
    }
}
