package br.com.rhscdeveloper.persistence.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesApplication {

	public static Properties loadProperties() throws Exception {

		try{
			FileInputStream fs = new FileInputStream("src/main/resources/application.properties");
			Properties props = new Properties();
			props.load(fs);
			props.setProperty("TESTE.TESTEADD.PROPERTY", "VALOR");
			return props;
		} catch(IOException e) {
			throw new IOException("Erro ao ler o arquivo de configuração do banco: "+e.getMessage());
		}
	}
}
