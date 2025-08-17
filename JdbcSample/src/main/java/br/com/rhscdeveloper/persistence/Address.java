package br.com.rhscdeveloper.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String street;
	private int number;
	private String cep;
	private String city;
	private String uf;
	private Employee employee;
	
	public Address() {
		
	}

	public Address(String street, int number, String cep, String city, String uf) {
		this.street = street;
		this.number = number;
		this.cep = cep;
		this.city = city;
		this.uf = uf;
	}

	public Address(long id, String street, int number, String cep, String city, String uf) {
		this.id = id;
		this.street = street;
		this.number = number;
		this.cep = cep;
		this.city = city;
		this.uf = uf;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", number=" + number + ", cep=" + cep + ", city=" + city
				+ ", uf=" + uf + "]";
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
