package com.github.andreytondo.chess.persistence;

import java.sql.Connection;

public interface Database {

    String getUrl();

    String getUser();

    String getPassword();

    Connection getConnection();
}
