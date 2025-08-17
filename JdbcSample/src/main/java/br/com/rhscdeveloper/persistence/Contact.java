package br.com.rhscdeveloper.persistence;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Contact {

	@Id
	private long id;
	private String description;
	private String type;
	
	@OneToOne(mappedBy = "contact")
	private Employee employee;
	
	public Contact() {
		
	}
	
	public Contact(String description, String type) {
		this.description = description;
		this.type = type;
	}
	
	public Contact(long id, String description, String type) {
		this.id = id;
		this.description = description;
		this.type = type;
	}
	
	public Contact(long id, String description, String type, Employee e) {
		this.id = id;
		this.description = description;
		this.type = type;
		this.employee = e;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee idEmployee) {
		this.employee = idEmployee;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, employee, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(description, other.description) && id == other.id && employee == other.employee
				&& Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", description=" + description + ", type=" + type + ", employee=" + employee
				+ "]";
	}
}
