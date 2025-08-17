package br.com.rhscdeveloper.persistence;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private BigDecimal salary;
	private OffsetDateTime birthday;

	@OneToOne(cascade = CascadeType.ALL)
	private Contact contact;
	
	private List<Address> address = new ArrayList<>();
	
	public Employee() {
		
	}
	
	public Employee(String name, BigDecimal salary, OffsetDateTime birtday) {
		this.name = name;
		this.salary = salary;
		this.birthday = birtday;
	}
	
	public Employee(long id, String name, BigDecimal salary, OffsetDateTime birtday) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.birthday = birtday;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getSalary() {
		return salary;
	}
	
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
	public OffsetDateTime getBirthday() {
		return birthday;
	}
	
	public void setBirthday(OffsetDateTime birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", birthday=" + birthday + "]";
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}
}
