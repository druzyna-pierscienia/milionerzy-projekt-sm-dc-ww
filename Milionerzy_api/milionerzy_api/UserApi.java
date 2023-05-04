import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class UserAPI {
    @GET
    @Path("/{round_number}/question")
    @Produces(MediaType.APPLICATION_JSON)
    public String getQuestion(@PathParam("round_number") String round_number) {
        Connect connect = new Connect();
        Connection connection = connect.getConnection();
        if (connection != null) {
            try {
                // Tworzenie zapytania SQL
                String query = "SELECT * FROM milionerzy.pytania WHERE numer_rundy = "+round_number;
                Random random = new Random();
                int randomNumber = random.nextInt(9);
                // Wykonanie zapytania
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                // Przetwarzanie wyników zapytania
                for(int i = 0; i < randomNumber; i++) {
                    resultSet.next();
                }
                // Pobieranie wartości z kolumn w wyniku zapytania

                String pytanie = resultSet.getString("tresc")
                pytanie += "/"+resultSet.getString("odpowiedz_a")
                pytanie += "/"+resultSet.getString("odpowiedz_b")
                pytanie += "/"+resultSet.getString("odpowiedz_c")
                pytanie += "/"+resultSet.getString("odpowiedz_d")
                pytanie += "/"+resultSet.getString("prawidlowa")

                // Zamknięcie obiektów ResultSet i Statement
                resultSet.close();
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connect.close(); // Zamknięcie połączenia
            }
            pytanie = "Hello, World!";
            return pytanie;
        }
    }
}