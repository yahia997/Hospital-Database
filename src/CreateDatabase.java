import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateDatabase {

    public CreateDatabase() {
        String url = "jdbc:sqlserver://localhost:1433;encrypt=false;integratedSecurity=true";

        try (Connection connection = DriverManager.getConnection(url)) {
            Statement statement = connection.createStatement();

            String checkDbSQL = "SELECT name FROM sys.databases WHERE name = 'Hospital'";
            ResultSet resultSet = statement.executeQuery(checkDbSQL);

            if (!resultSet.next()) {
                String fileContent = new String(Files.readAllBytes(Paths.get("Query.sql")));
                String[] sqlStatements = fileContent.split("(?i)\\bGO\\b");

                for (String sql : sqlStatements) {
                    sql = sql.trim();
                    if (!sql.isEmpty()) {
                        statement.execute(sql);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}