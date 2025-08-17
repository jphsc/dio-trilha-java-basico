package br.com.rhscdeveloper.persistence;

import static java.time.ZoneOffset.UTC;
import static java.util.Objects.isNull;

import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.rhscdeveloper.util.ConnectionUtil;

public class EmployeeDAO {

	private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public void insert(Employee e) {
		try (
				var Connection = ConnectionUtil.getConnecion();
				var statement = Connection.createStatement();
		) {
			String sql = String.format("INSERT INTO employee (name, salary, birthday) values ('%s', %s, '%s')",
					e.getName(), e.getSalary().toString(), dtf.format(e.getBirthday()));
				
			statement.execute(sql);
			System.out.printf("Foram afetados %s registros", statement.getUpdateCount());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void update(Employee e) {
		
		try (
				var Connection = ConnectionUtil.getConnecion();
				var statement = Connection.createStatement();
		) {
			
			Employee empBase = findById(e.getId());
			
			if (isNull(empBase)) {
				System.out.println("Employee not found on database");
				return;
			}
			
			String sql = String.format("UPDATE employee SET name = '%s', salary = %s, birthday = '%s' WHERE id = %s",
					e.getName(), 
					e.getSalary(), 
					dtf.format(e.getBirthday()),
					e.getId());
			
			statement.execute(sql);
			
			System.out.println("Employee updated");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void delete(final long id) {
		
		try (
				var Connection = ConnectionUtil.getConnecion();
				var statement = Connection.createStatement();
		) {
			
			Employee empBase = findById(id);
			
			if (isNull(empBase)) {
				System.out.println("Employee not found on database");
				return;
			}
			
			String sql = String.format("DELETE FROM employee WHERE id = %s",id);
			
			statement.execute(sql);
			
			System.out.println("Employee deleted");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public List<Employee> findAll(){
		
		List<Employee> employees = new ArrayList<>();
		
		try (
				var Connection = ConnectionUtil.getConnecion();
				var statement = Connection.createStatement();
		) {
			
			ResultSet rs = statement.executeQuery("SELECT * FROM employee");
			
			while(rs.next()) {
				employees.add(
						new Employee(
								rs.getLong("id"),
								rs.getString("name"), 
								rs.getBigDecimal("salary"), 
								rs.getTimestamp("birthday").toInstant().atOffset(UTC)
						)
				);
			}
			
			System.out.println(String.format("A consulta encontrou %s registros", employees.size()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return employees;
	}
	
	public Employee findById(final long id){

		Employee emp = new Employee();
		
		try (
				var Connection = ConnectionUtil.getConnecion();
				var statement = Connection.createStatement();
		) {
			
			ResultSet rs = statement.executeQuery(String.format("SELECT * FROM employee WHERE id = %s", id));
			
			while(rs.next()) {
				emp = new Employee(
								rs.getLong("id"),
								rs.getString("name"), 
								rs.getBigDecimal("salary"), 
								rs.getTimestamp("birthday").toInstant().atOffset(UTC)
						);
			}
			
			System.out.println("A consulta encontrou o registro: "+ emp);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return emp;
	}
}
