package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DatabaseConnection {

        public static String status = "Não conectou!";

        // Connection method
        public static java.sql.Connection getConnectionMySQL(){
            Connection connection = null;

            try {
                // Loading standard JDBC driver
                String driverName = "com.mysql.cj.jdbc.Driver";
                Class.forName(driverName);

                // Configuring DB connection
                String serverName = "localhost:3306";
                String database = "avaliacao_02";
                String url = "jdbc:mysql://" + serverName + "/" + database;
//                jdbc:mysql://localhost:3306/avaliacao_02
                String username = "root";
                String password = "mulinhas";
                connection = DriverManager.getConnection(url, username, password);

                // Testing connection:
                if(connection != null) {
                    status = ("Conectado com sucesso.");
                }else {
                    status = ("Deu merda.");
                }
                return connection;

            }catch(ClassNotFoundException e) {
                System.out.println("O driver especificado não foi encontrado.");
                return null;
            }catch(SQLException e) {
                System.out.println("Não foi possível conectar ao banco de dados.");
                return null;
            }
        }

        public static String getStatusConnection() {
            return status;
        }
        public static void closeConnection() throws SQLException {
            try {
                Objects.requireNonNull(DatabaseConnection.getConnectionMySQL()).close();
            }
            catch(SQLException e) {
                throw new SQLException(e);
            }
        }
}
