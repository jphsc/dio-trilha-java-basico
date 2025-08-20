package br.com.rhscdeveloper.persistence.entity;
import lombok.Data;

@Data
public class Cards {

	private long id;
	private String title;
	private String description;
	private BoardColumn boardColumnId;
	
	public Cards() {
		
	}

	public Cards(String title, String description, BoardColumn boardColumnId) {
		this.title = title;
		this.description = description;
		this.boardColumnId = boardColumnId;
	}

	public Cards(long id, String title, String description, BoardColumn boardColumnId) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.boardColumnId = boardColumnId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BoardColumn getBoardColumnId() {
		return boardColumnId;
	}

	public void setBoardColumnId(BoardColumn boardColumnId) {
		this.boardColumnId = boardColumnId;
	}
	
}
