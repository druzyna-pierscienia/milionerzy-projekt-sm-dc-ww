package api;

import connection.Connect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.Random;

@SpringBootApplication
@RestController
public class MyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApiApplication.class, args);
    }

    @GetMapping("/question")
    public String getQuestion(@RequestParam(name = "roundNumber") String roundNumber) {
        Connect connect = new Connect();
        Connection connection = connect.getConnection();
        String pytanie = "blad";
        if (connection != null) {
            try {
                // Tworzenie zapytania SQL
                String query = "SELECT * FROM milionerzy.pytania WHERE numer_rundy = "+roundNumber;
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

                pytanie = resultSet.getString("tresc");
                pytanie += "/"+resultSet.getString("odpowiedz_a");
                pytanie += "/"+resultSet.getString("odpowiedz_b");
                pytanie += "/"+resultSet.getString("odpowiedz_c");
                pytanie += "/"+resultSet.getString("odpowiedz_d");
                pytanie += "/"+resultSet.getString("prawidlowa");

                // Zamknięcie obiektów ResultSet i Statement
                resultSet.close();
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connect.close(); // Zamknięcie połączenia
            }

        }
        return pytanie;
    }

    @GetMapping("/ranking")
    public String getRanking(){
        String ranking = "404";
        Connect connect = new Connect();
        Connection connection = connect.getConnection();
        if (connection != null) {
            try {
                // Tworzenie zapytania SQL
                String query = "SELECT tabilca_wynikow.wynik, uzytkownicy.login FROM milionerzy.tabilca_wynikow INNER JOIN milionerzy.uzytkownicy ON tabilca_wynikow.uzytkownik=uzytkownicy.id_uzytkownika ORDER BY wynik LIMIT 10";

                // Wykonanie zapytania
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                // Przetwarzanie wyników zapytania
                ranking = "";
                while(resultSet.next()){
                    // Pobieranie wartości z kolumn w wyniku zapytania
                    ranking += resultSet.getString("login");
                    ranking += "/"+resultSet.getString("wynik")+";";
                }
                // Zamknięcie obiektów ResultSet i Statement
                resultSet.close();
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connect.close(); // Zamknięcie połączenia
            }

        }
        return ranking;
    }

    @GetMapping("/login")
    public boolean checkLogin(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password){
        boolean logSucces = false;
        int count = 0;
        Connect connect = new Connect();
        Connection connection = connect.getConnection();
        if (connection != null) {
            try {
                // Tworzenie zapytania SQL
                String query = "SELECT id_uzytkownika FROM milionerzy.uzytkownicy WHERE login = ? AND haslo = ?";

                PreparedStatement statement = connection.prepareStatement(query);

                // Ustawienie wartości parametrów
                statement.setString(1, login);
                statement.setString(2, password);

                // Wykonanie zapytania
                ResultSet resultSet = statement.executeQuery();


                // Przetwarzanie wyników zapytania
                while(resultSet.next()){
                    count++;
                }
                if(count>0){
                    logSucces=true;
                }
                // Zamknięcie obiektów ResultSet i Statement
                resultSet.close();
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connect.close(); // Zamknięcie połączenia
            }

        }
        return logSucces;
    }

}