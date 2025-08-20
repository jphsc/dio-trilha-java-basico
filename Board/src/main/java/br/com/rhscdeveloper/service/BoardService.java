package br.com.rhscdeveloper.service;

import java.sql.Connection;
import java.util.List;

import br.com.rhscdeveloper.persistence.dao.BoardDAO;
import br.com.rhscdeveloper.persistence.entity.Board;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardService {

	private Connection conn;
	private BoardDAO boardDAO;
	
	public BoardService(Connection conn) {
		this.conn = conn;
		this.boardDAO = new BoardDAO(conn);
	}
	
	public void delete(long id) {
		try {
			boardDAO.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void insertBoard(Board b) {
		try {
			boardDAO.insertBoard(b);;
		} catch (Exception e) {
			e.printStackTrace();
		}		
	} 
	
	public Board findById(long id) {
		try {
			return boardDAO.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Board> findAll() {
		try {
			return boardDAO.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
