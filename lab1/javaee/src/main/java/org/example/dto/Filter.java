package org.example.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
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
