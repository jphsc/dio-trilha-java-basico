package br.com.rhscdeveloper.persistence;

import static java.time.ZoneOffset.UTC;
import static java.util.Objects.isNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.rhscdeveloper.util.ConnectionUtil;

public class EmployeeParamDAO {

	public long insert(final Employee e) {
		try ( var Connection = ConnectionUtil.getConnecion(); ) {
			PreparedStatement statement = Connection.prepareStatement("INSERT INTO employee (name, salary, birthday) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, e.getName());
			statement.setBigDecimal(2, e.getSalary());
			statement.setTimestamp(3, Timestamp.valueOf(e.getBirthday().atZoneSimilarLocal(UTC).toLocalDateTime()));
			statement.executeUpdate();
			
			System.out.println(String.format("Foram afetados %s registros", statement.getUpdateCount()));
			
			try (var rs = statement.getGeneratedKeys()) {
				if (rs.next()) {
					long generatedId = rs.getLong(1);
					System.out.println("ID gerado: " + generatedId);
					return generatedId;
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return -1;
	}
	
	public void update(final Employee e) {
		
		try ( var Connection = ConnectionUtil.getConnecion(); ) {
			
			Employee empBase = findById(e.getId());
			
			if (isNull(empBase)) {
				System.out.println("Employee not found on database");
				return;
			}
			
			PreparedStatement statement = Connection.prepareStatement("UPDATE employee SET name = ?, salary = ?, birthday = ? WHERE id = ?");
			
			statement.setString(1, e.getName());
			statement.setBigDecimal(2, e.getSalary());
			statement.setTimestamp(3, Timestamp.valueOf(e.getBirthday().atZoneSimilarLocal(UTC).toLocalDateTime()));
			statement.setLong(4, e.getId());
			statement.executeUpdate();

			System.out.println("Employee updated");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void delete(final long id) {
		
		try ( var Connection = ConnectionUtil.getConnecion(); ) {
			
			Employee empBase = findById(id);
			
			if (isNull(empBase)) {
//				System.out.println("Employee not found on database");
				return;
			}
			
			PreparedStatement statement = Connection.prepareStatement("DELETE FROM employee WHERE id = ?");
			
			statement.setLong(1, id);
			statement.executeUpdate();

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

		Employee emp = null;
		
		try (
				var Connection = ConnectionUtil.getConnecion();
		) {
			
			PreparedStatement statement = Connection.prepareStatement("SELECT * FROM employee WHERE id = ?");
			
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				emp = new Employee(
								rs.getLong("id"),
								rs.getString("name"), 
								rs.getBigDecimal("salary"), 
								rs.getTimestamp("birthday").toInstant().atOffset(UTC)
						);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if(isNull(emp)) {
			System.out.println("Employee not found on database");
			return null;
		}
		
		System.out.println("A consulta encontrou o registro: "+ emp);
		
		return emp;
	}

	
	public void insertBatch(final List<Employee> entities) {
		try (var Connection = ConnectionUtil.getConnecion();){
			String sql = "INSERT INTO employee (name, salary, birthday) values (?, ?, ?)";
			try (PreparedStatement statement = Connection.prepareStatement(sql);) {
				Connection.setAutoCommit(false);
				
				for(Employee emp : entities) {
					statement.setString(1, emp.getName());
					statement.setBigDecimal(2, emp.getSalary());
					statement.setTimestamp(3, Timestamp.from(emp.getBirthday().toInstant()));
					statement.addBatch();
				}
				statement.executeBatch();
			} catch (Exception ex) {
				ex.printStackTrace();
				Connection.rollback();
			}

			Connection.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.printf("%s registro(s) foram incluidos", entities.size());
	}
}
