import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:mariadb://localhost:3306/product_manager_db";
    private static final String USER = "yokub"; // Укажите свой логин
    private static final String PASSWORD = "True7"; // Укажите свой пароль

    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Метод для добавления нового товара
    public void saveProduct(String productName, int quantity, double price, String supplier, String category) {
        String query = "INSERT INTO products (product_name, quantity, price, supplier, category) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, productName);
            preparedStatement.setInt(2, quantity);
            preparedStatement.setDouble(3, price);
            preparedStatement.setString(4, supplier);
            preparedStatement.setString(5, category);
            preparedStatement.executeUpdate();

            System.out.println("Product saved successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для загрузки всех товаров
    public void loadProducts() {
        String query = "SELECT * FROM products";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                System.out.println("Product ID: " + resultSet.getInt("id"));
                System.out.println("Product Name: " + resultSet.getString("product_name"));
                System.out.println("Quantity: " + resultSet.getInt("quantity"));
                System.out.println("Price: " + resultSet.getDouble("price"));
                System.out.println("Supplier: " + resultSet.getString("supplier"));
                System.out.println("Category: " + resultSet.getString("category"));
                System.out.println("------------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
