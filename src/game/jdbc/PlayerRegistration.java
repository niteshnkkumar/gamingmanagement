package game.jdbc;
	import java.sql.*;
	import java.util.Scanner;

	public class PlayerRegistration {

	    public static void createTable(Connection connection) throws SQLException {
	        String createTableSQL = "CREATE TABLE IF NOT EXISTS players (" +
	                "id INT AUTO_INCREMENT PRIMARY KEY," +
	                "game_id INT," +
	                "name VARCHAR(255)," +
	                "achievement VARCHAR(255)" +
	                ")";
	        try (Statement statement = connection.createStatement()) {
	            statement.executeUpdate(createTableSQL);
	        }
	    }

	    public static void addPlayer(Connection connection, Scanner scanner) throws SQLException {
	        System.out.print("Enter Game ID: ");

	      
	        while (!scanner.hasNextInt()) {
	            System.out.println("Invalid input. Please enter a valid integer for Game ID:");
	            scanner.next(); 
	        }

	        int gameId = scanner.nextInt();
	        scanner.nextLine(); 
	        System.out.print("Enter Player Name: ");
	        String playerName = scanner.nextLine();
	        System.out.print("Enter Player Achievement: ");
	        String achievement = scanner.nextLine();

	        String insertPlayerSQL = "INSERT INTO players (game_id, name, achievement) VALUES (?, ?, ?)";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(insertPlayerSQL)) {
	            preparedStatement.setInt(1, gameId);
	            preparedStatement.setString(2, playerName);
	            preparedStatement.setString(3, achievement);

	            preparedStatement.executeUpdate();
	            System.out.println("Player added successfully!");
	        }
	    }

	    public static void viewPlayers(Connection connection) throws SQLException {
	        String selectPlayersSQL = "SELECT * FROM players";
	        try (Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(selectPlayersSQL)) {

	            System.out.println("\nPlayers:");
	            System.out.println("'--------------------------------------------' ");
	            System.out.println("|ID\tGame ID\t\tName\tAchievement  |");
	            System.out.println("' -------------------------------------------' ");
	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                int gameId = resultSet.getInt("game_id");
	                String name = resultSet.getString("name");
	                String achievement = resultSet.getString("achievement");
	                System.out.println(id + "\t" + gameId + "\t" + name + "\t" + achievement);
	            }
	        }
	    }

	    public static void updatePlayer(Connection connection, Scanner scanner) throws SQLException {
	        System.out.print("Enter Player ID to update: ");
	        int playerId = scanner.nextInt();
	        scanner.nextLine(); 
	        System.out.print("Enter new Player Name: ");
	        String newName = scanner.nextLine();
	        System.out.print("Enter new Player Achievement: ");
	        String newAchievement = scanner.nextLine();

	        String updatePlayerSQL = "UPDATE players SET name = ?, achievement = ? WHERE id = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(updatePlayerSQL)) {
	            preparedStatement.setString(1, newName);
	            preparedStatement.setString(2, newAchievement);
	            preparedStatement.setInt(3, playerId);

	            int updatedRows = preparedStatement.executeUpdate();
	            if (updatedRows > 0) {
	                System.out.println("Player updated successfully!");
	            } else {
	                System.out.println("Player not found.");
	            }
	        }
	    }

	    public static void deletePlayer(Connection connection, Scanner scanner) throws SQLException {
	        System.out.print("Enter Player ID to delete: ");
	        int playerId = scanner.nextInt();

	        String deletePlayerSQL = "DELETE FROM players WHERE id = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(deletePlayerSQL)) {
	            preparedStatement.setInt(1, playerId);

	            int deletedRows = preparedStatement.executeUpdate();
	            if (deletedRows > 0) {
	                System.out.println("Player deleted successfully!");
	            } else {
	                System.out.println("Player not found.");
	            }
	        }
	    }
	}



