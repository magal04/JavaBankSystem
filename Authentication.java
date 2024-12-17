
//: Handles user login by verifying the account number and PIN against the database. Returns a User object upon successful authentication.

package FB;
package JavaBankSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication {
    private Database database;

    public Authentication(Database database) {
        this.database = database;
    }

    // Authenticate the user by matching account number and PIN
    public User login(String accountNumber, String pin) {
        try (Connection connection = database.getConnection()) {
            String query = "SELECT * FROM users WHERE account_number = ? AND pin = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, accountNumber);
                statement.setString(2, pin);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new User(
                                resultSet.getString("account_number"),
                                resultSet.getString("holder_name"),
                                resultSet.getString("pin"),
                                resultSet.getDouble("checking_balance"),
                                resultSet.getDouble("saving_balance")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
