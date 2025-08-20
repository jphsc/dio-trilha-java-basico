package br.com.rhscdeveloper;


import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import br.com.rhscdeveloper.persistence.config.ConnectionConfig;
import br.com.rhscdeveloper.persistence.entity.Board;
import br.com.rhscdeveloper.persistence.migration.MigrationStrategy;
import br.com.rhscdeveloper.service.BoardService;

public class Application {
	
	public static void main(String[] args) {
		
		System.out.println("Application is running");
		
		try (Connection conn = ConnectionConfig.getConnection();) {
			new MigrationStrategy().executeMigration();
			System.out.println("Connected on database");
			
			BoardService boardService = new BoardService(conn);

//			List<Board> boards = Arrays.asList(new Board("time 1"), new Board("time 2"), new Board("time 3"), new Board("time 4"));
//			boards.forEach(boardService::insertBoard);
			
			boardService.findAll().forEach(c -> System.out.println(c));
			
//			boardService.delete(3);
//			boardService.findAll().forEach(c -> System.out.println(c));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error connected");
		}
		

	}

}
