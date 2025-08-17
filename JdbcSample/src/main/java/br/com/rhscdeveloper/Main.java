package br.com.rhscdeveloper;

import static java.time.ZoneOffset.UTC;
import static java.util.Objects.isNull;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.exception.FlywayValidateException;

import br.com.rhscdeveloper.persistence.Contact;
import br.com.rhscdeveloper.persistence.ContactParamDAO;
import br.com.rhscdeveloper.persistence.Employee;
import br.com.rhscdeveloper.persistence.EmployeeParamDAO;
import net.datafaker.Faker;

public class Main {

	private static final EmployeeParamDAO employeePDAO = new EmployeeParamDAO();
	private static final ContactParamDAO contactPDAO = new ContactParamDAO();
	private static final Faker faker = new Faker(Locale.of("pt", "BR"));

	public static void main(String[] args) {
		
		// configuração necessária para rodar o flyway
		applyMigrations();
		
		/* os arquivos que o flyway busca, sempre estaram no caminho .../resources/db/migration */
		/*
		 * o nome do arquivo tem que ser: VXXXX__YYYY.sql, onde:
		 * XXXX é o identificador da versão, pode ser timestamp = 20210123, ordinal V1, V4, etc
		 * __ separador obrigatorio após a versão e YYYY a descricão que desejar da migração
		 */
		
		/* Testando a inclusão de registros */
//		Employee emp1 = new Employee("Fulano da Silva", new BigDecimal(1500), OffsetDateTime.now().minusYears(24));
//		new EmployeeDAO().insert(emp1);
		
		/* Testando consulta de todos os registros */
//		new EmployeeDAO().findAll().forEach(System.out::println);

		/* Testando consulta de registro por id */
//		new EmployeeDAO().findById(5);

		/* Testando atualizar registro */
//		Employee emp2Base = new EmployeeDAO().findById(2);
//		emp2Base.setName("Ferroada");
//		emp2Base.setSalary(new BigDecimal(11298.12));
//		new EmployeeDAO().update(emp2Base);

		/* Testando exclusão de registro por id*/
//		new EmployeeDAO().delete(5);
		
		
		/* Testando a inclusão de registros*/
//		Employee emp1 = new Employee("Fernando Silveira", new BigDecimal(1500), OffsetDateTime.now().minusYears(24));
//		employeePDAO.insert(emp1);
		
		/* Testando consulta de todos os registros*/
//		employeePDAO.findAll().forEach(System.out::println);

		/* Testando consulta de registro por id*/
//		employeePDAO.findById(7);

		/* Testando atualizar registro*/
//		Employee emp2Base = employeePDAO.findById(7);
//		emp2Base.setName("Veronica");
//		emp2Base.setSalary(new BigDecimal(5762.09));
//		employeePDAO.update(emp2Base);

		/* Testando exclusão de registro por id*/
//		employeePDAO.delete(3);
		
		/*inserindo vários registros fakes utilizando o faker */
		List<Employee> emps = Stream.generate(() -> {
			Employee emp = new Employee();
			emp.setName(faker.name().fullName());
			emp.setSalary(new BigDecimal(faker.number().digits(4)));
			emp.setBirthday(OffsetDateTime.of(faker.date().birthdayLocalDate(1, 50), LocalTime.MIN, UTC));
			return emp;
		})
		.limit(4) // gera 4 mil registros iguais
		.toList();   // converte o resultado em uma lista do tipo Employee
		employeePDAO.insertBatch(emps);
		
		/* Criar contato fake para cada empregado e salvar todos */
		List<Contact> contacts = new ArrayList<>();
		employeePDAO.findAll().forEach(e -> {
			
			if (isNull(e.getContact())) {
				Contact contact = new Contact();
				contact.setDescription(faker.address().streetName());
				contact.setType(faker.phoneNumber().cellPhone());
				contact.setEmployee(e);
				contacts.add(contact);
			}
		});
		contacts.forEach(c -> contactPDAO.insert(c));
		

//		Employee emp1 = new Employee("Fulano da Silva", new BigDecimal(1500), OffsetDateTime.now().minusYears(24));
//		long idEmp = employeePDAO.insert(emp1);
//		emp1 = employeePDAO.findById(idEmp);
//
//		Contact contactEmp1 = new Contact("Personnal", "phone");
//		contactEmp1.setEmployee(emp1);
//		contactPDAO.insert(contactEmp1);
//		
//		contactPDAO.findAll().forEach(System.out::println);
		
	}
	
	public static void applyMigrations() {
		
		Flyway flyway = null;
		
		// configuração necessária para rodar o flyway
		try {
			flyway = Flyway
					.configure()
					.dataSource("jdbc:mysql://localhost:3306/projetoteste2", "root", "")
					.load();
			flyway.migrate();
		} catch (FlywayValidateException e) {
			flyway.repair();
			e.printStackTrace();
			applyMigrations();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
