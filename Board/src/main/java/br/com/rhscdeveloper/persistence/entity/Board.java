package br.com.rhscdeveloper.persistence.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Board {

	private long id;
	private String name;
	
	public Board() {
		
	}

	public Board(String name) {
		this.name = name;
	}

	public Board(long id, String name) {
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "Board [id=" + id + ", name=" + name + "]";
	}
	
}
