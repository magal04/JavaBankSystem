package JavaBankSystem;

//Manages the SQLite database connection and handles database initialization. It provides methods to fetch and update user data in the users table.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Database {
    private static Database instance;
    private Connection connection;
    private Database(){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db")
            initializationDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Database getInstance(){
        if (instance == null){
            instance == new Database();
        }
        return instance;
    }
    private void initializationDatabase(){
        try{
            string createUsersTable = "Create Table If not Exists users (" +
                    "id Integer PRIMARY KEY AUTOINCREMENT, " +
                    "account_number TEXT UNIQUE, " + 
                    "holder_name TEXT, " +
                    "pin TEXT, " +
                    "checking_balance REAL, " +
                    "saving_balance REAL " +
                    ");";
            connection.createStatement().execute(createUsersTable);
            String createTransactionsTable = "CREATE TABLE IF NOT EXISTS transactions (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_id INTEGER, " +
                    "type TEXT, " +
                    "amount REAL, " +
                    "account_type TEXT, " +
                    "date TEXT, " +
                    "FOREIGN KEY(user_id) REFERENCES users(id)" +
                    ");";
            connection.createStatement().execute(createTransactionsTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    }
    public User getUser(String accountNumber) {
        try {
            String query = "SELECT * FROM users WHERE account_number = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, accountNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getString("account_number"),
                        resultSet.getString("holder_name"),
                        resultSet.getString("pin"),
                        resultSet.getDouble("checking_balance"),
                        resultSet.getDouble("saving_balance")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updateUser(User user) {
        try {
            String query = "UPDATE users SET checking_balance = ?, saving_balance = ? WHERE account_number = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, user.getCheckingBalance());
            statement.setDouble(2, user.getSavingBalance());
            statement.setString(3, user.getAccountNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    }
}
