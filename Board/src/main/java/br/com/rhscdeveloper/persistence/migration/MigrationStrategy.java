package br.com.rhscdeveloper.persistence.migration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import br.com.rhscdeveloper.persistence.config.ConnectionConfig;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

public class MigrationStrategy {

	public void executeMigration() throws Exception {
		// configuracao espeficica do liquibase para app de console
		var originalOut = System.out; // salvando as saidas do console nessa variavel
		var originalErr = System.err; // salvando as saidas de erro do console nessa variavel

		try (var fos = new FileOutputStream("liquibase.log")) { // direcionar os logs do loquibase para outro lugar

			System.setOut(new PrintStream(fos));
			System.setErr(new PrintStream(fos));

			try (var conn = ConnectionConfig.getConnection(); var jdbcConnection = new JdbcConnection(conn);) {
				
				// Se for um projeto com interface, so a parte dentro desse try mais interno e necessario
				Liquibase liquibase = new Liquibase(
						"db/changelog/db.changelog-master.yml",
						new ClassLoaderResourceAccessor(), 
						jdbcConnection);
				
				liquibase.update();
			} catch (LiquibaseException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.setOut(originalOut);
			System.setErr(originalErr);
		}

	}
}
