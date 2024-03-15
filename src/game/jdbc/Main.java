package game.jdbc;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;
	import java.util.Scanner;

	public class Main {

	    private static final String URL = "jdbc:mysql://localhost:3306/game";
	    private static final String USER = "root";
	    private static final String PASSWORD = "Nitesh";

	    public static void main(String[] args) {
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
	            PlayerRegistration.createTable(connection);
	            Scanner scanner = new Scanner(System.in);

	            while (true) {
	                System.out.println("\n1. Add Player\n2. View Players\n3. Update Player\n4. Delete Player\n5. Exit");
	                System.out.print("Enter your choice: ");
	                int choice = scanner.nextInt();

	                switch (choice) {
	                    case 1:
	                        PlayerRegistration.addPlayer(connection, scanner);
	                        break;
	                    case 2:
	                        PlayerRegistration.viewPlayers(connection);
	                        break;
	                    case 3:
	                        PlayerRegistration.updatePlayer(connection, scanner);
	                        break;
	                    case 4:
	                        PlayerRegistration.deletePlayer(connection, scanner);
	                        break;
	                    case 5:
	                        System.exit(0);
	                    default:
	                        System.out.println("Invalid choice. Please try again.");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}



