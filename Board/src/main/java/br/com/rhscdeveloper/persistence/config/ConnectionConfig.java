package br.com.rhscdeveloper.persistence.config;

import static lombok.AccessLevel.PRIVATE;

import java.sql.Connection;
import java.sql.DriverManager;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class ConnectionConfig {
	
	public static Connection getConnection() throws Exception {
		
		String user = PropertitesApplication.loadProperties().getProperty("db.data.user");
		String password = PropertitesApplication.loadProperties().getProperty("db.data.password");
		String url = PropertitesApplication.loadProperties().getProperty("db.data.url");
		
		Connection connection = DriverManager.getConnection(url, user, password);
		connection.setAutoCommit(false);

		return connection;
	}
}
