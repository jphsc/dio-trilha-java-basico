package br.com.rhscdeveloper.persistence.dao;

import static java.util.Objects.isNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rhscdeveloper.persistence.entity.Board;

public class BoardDAO{

	private final Connection conn;
	
	public BoardDAO(Connection connection) {
		this.conn = connection;
	}
	
	public void insertBoard(Board b) {
		
		try {
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO board (name) VALUES (?)");
			ps.setString(1, b.getName());
			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Board findById(long id) {

		Board board = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM board WHERE id = ?");
			ps.setLong(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			
			do {
				board = new Board(
						rs.getLong(0),
						rs.getString(0)
						);
			} while (rs.next());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(isNull(board)) {
			System.out.println(String.format("Board of id %s not found", id));
			return null;
		}
		
		return board;
	}
	
	public List<Board> findAll(){
		
		List<Board> boards = new ArrayList<Board>();
		
		try (var ps = conn.prepareStatement("SELECT * FROM board")){
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Board board = new Board(
						rs.getLong(1),
						rs.getString(2)
						);
				boards.add(board);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(boards.isEmpty()) {
			System.out.print("There isn't boards on the database");
			return null;
		}
		
		return boards;
	}
	
	public void delete(long id) throws SQLException {
		try (var ps = conn.prepareStatement("DELETE FROM board WHERE id = ?")){
			
			if(!isNull(findById(id))) {
				ps.setLong(1, id);
				ps.executeUpdate();
				conn.commit();
			} else {
				System.out.println("Board "+id+" not found");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		}
	}
}
