package br.com.rhscdeveloper.persistence;

import static java.util.Objects.isNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.rhscdeveloper.util.ConnectionUtil;

public class ContactParamDAO {

	public void insert(final Contact e) {
		try (
				var Connection = ConnectionUtil.getConnecion();
		) {
			PreparedStatement statement = Connection.prepareStatement("INSERT INTO contact (description, type, id_employee) values (?, ?, ?)");
			statement.setString(1, e.getDescription());
			statement.setString(2, e.getType());
			statement.setLong(3, e.getEmployee().getId());
			statement.executeUpdate();
			
			System.out.println(String.format("Foram afetados %s registros", statement.getUpdateCount()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void update(final Contact e) {
		
		try (
				var Connection = ConnectionUtil.getConnecion();
		) {
			
			Contact contact = findById(e.getId());
			
			if (isNull(contact)) {
				System.out.println("Contact not found on database");
				return;
			}
			
			PreparedStatement statement = Connection.prepareStatement("UPDATE contact SET description = ?, type = ? WHERE id = ?");
			
			statement.setString(1, e.getDescription());
			statement.setString(2, e.getType());
			statement.executeUpdate();

			System.out.println("Contact updated");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public List<Contact> findAll(){
		
		List<Contact> contacts = new ArrayList<>();
		
		try (
				var Connection = ConnectionUtil.getConnecion();
				var statement = Connection.createStatement();
		) {
			
			ResultSet rs = statement.executeQuery("SELECT * FROM contact");
			
			while(rs.next()) {
				contacts.add(new Contact(rs.getLong("id"), rs.getString("description"), rs.getString("type")));
			}
			
			System.out.println(String.format("A consulta encontrou %s registros", contacts.size()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return contacts;
	}
	
	public Contact findById(final long id){

		Contact contact = null;
		
		try (
				var Connection = ConnectionUtil.getConnecion();
		) {
			
			PreparedStatement statement = Connection.prepareStatement("SELECT * FROM Contact WHERE id = ?");
			
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				contact = new Contact(rs.getLong("id"), rs.getString("description"), rs.getString("type"));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		if(isNull(contact)) {
			System.out.println("Contact not found on database");
			return null;
		}
		
		System.out.println("A consulta encontrou o registro: "+ contact);
		
		return contact;
	}
}
