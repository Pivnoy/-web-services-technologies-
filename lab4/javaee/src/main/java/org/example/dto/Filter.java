package org.example.dto;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

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

    public String formFilterStr()  {
        if (value instanceof String) {
            return this.fieldName + EQUALS + "'" + value + "'";
        }

        return this.fieldName + EQUALS + value;
    }
}
