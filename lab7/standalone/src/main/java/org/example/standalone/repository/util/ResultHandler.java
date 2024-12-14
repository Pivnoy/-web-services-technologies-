package org.example.standalone.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}
