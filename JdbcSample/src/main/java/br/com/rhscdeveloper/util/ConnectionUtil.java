package br.com.rhscdeveloper.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionUtil {

	public static Connection getConnecion() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/projetoteste2", "root", "");
	}
}
