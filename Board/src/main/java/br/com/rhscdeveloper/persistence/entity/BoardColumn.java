package br.com.rhscdeveloper.persistence.entity;
import lombok.Data;

@Data
public class BoardColumn {

	private long id;
	private String name;
	private int order;
	private BoardColumnKindEnum kind;
	private Board boardId;
	
	public BoardColumn() {
		
	}

	public BoardColumn(String name, int order, BoardColumnKindEnum kind, Board board) {
		this.name = name;
		this.order = order;
		this.kind = kind;
		this.boardId = board;
	}

	public BoardColumn(long id, String name, int order, BoardColumnKindEnum kind, Board board) {
		this.id = id;
		this.name = name;
		this.order = order;
		this.kind = kind;
		this.boardId = board;
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

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public BoardColumnKindEnum getKind() {
		return kind;
	}

	public void setKind(BoardColumnKindEnum kind) {
		this.kind = kind;
	}

	public Board getboardId() {
		return boardId;
	}

	public void setboardId(Board board) {
		this.boardId = board;
	}
	
}
