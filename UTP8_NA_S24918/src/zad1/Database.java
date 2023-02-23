package zad1;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Database {

    String url;
    TravelData travelData;
    Connection connection;

    public Database(String url, TravelData travelData) {
        this.travelData = travelData;
        this.url = url;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            Statement statement = connection.createStatement();
            try {
                //drop table
                statement.executeUpdate("DROP TABLE offers");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //create table
            statement.executeUpdate(
                    "CREATE TABLE offers (locale VARCHAR(5)," +
                            " country VARCHAR(50)," +
                            " departure_date DATE," +
                            " return_date DATE," +
                            " place VARCHAR(50)," +
                            " price VARCHAR(50)," +
                            " currency VARCHAR(3))"
            );

            // instrukcję prekompilowaną podając jako argument odpowiednią instrukcję SQL (z parametrami ?)
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO offers (locale, country, departure_date, return_date, place, price, currency) VALUES (?, ?, ?, ?, ?, ?, ?)"

            );

            //insert data

            for (TravelData.Offer offer : travelData.getOffers()) {
                preparedStatement.setString(1, offer.getLocale().toString());
                preparedStatement.setString(2, offer.getCountry());
                preparedStatement.setDate(3,  offer.getDateFrom());
                preparedStatement.setDate(4, offer.getDateTo());
                preparedStatement.setString(5, offer.getPlace());
                preparedStatement.setDouble(6, offer.getPrice());
                preparedStatement.setString(7, offer.getCurrency());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showGui() {

        JFrame frame = new JFrame("Travel offers");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        String[] columns = {"Locale", "Country", "Departure date", "Return date", "Place", "Price", "Currency"};
        DefaultTableModel dtm = new DefaultTableModel(columns, 0);

        JTable table = new JTable(dtm);
        JButton button = new JButton("Show offers");
        JButton buttonPL = new JButton("pl");
        JButton buttonEng = new JButton("en");

        JPanel panel = new JPanel();
        JPanel south = new JPanel();
        panel.setLayout(new BorderLayout());
        south.setLayout(new FlowLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        button.addActionListener(
                buttonDef -> {
                    try {
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM offers");
                        while (resultSet.next()) {
                            String locale = resultSet.getString("locale");
                            String country = resultSet.getString("country");
                            Date departure_date = resultSet.getDate("departure_date");
                            Date return_date = resultSet.getDate("return_date");
                            String place = resultSet.getString("place");
                            double price = resultSet.getDouble("price");
                            String currency = resultSet.getString("currency");
                            Object[] data = {locale, country, departure_date, return_date, place, price, currency};
                            dtm.addRow(data);
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
        );
        buttonPL.addActionListener(
                buttonPl -> {
                    try {
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM offers WHERE locale = 'pl_PL'");
                        while (resultSet.next()) {
                            String locale = resultSet.getString("locale");
                            String country = resultSet.getString("country");
                            Date departure_date = resultSet.getDate("departure_date");
                            Date return_date = resultSet.getDate("return_date");
                            String place = resultSet.getString("place");
                            double price = resultSet.getDouble("price");
                            String currency = resultSet.getString("currency");
                            Object[] data = {locale, country, departure_date, return_date, place, price, currency};
                            dtm.addRow(data);
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
        );

        buttonEng.addActionListener(
                Eng -> {
                    try {
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery("SELECT * FROM offers WHERE locale = 'en_GB'");
                        while (resultSet.next()) {
                            String locale = resultSet.getString("locale");
                            String country = resultSet.getString("country");
                            Date departure_date = resultSet.getDate("departure_date");
                            Date return_date = resultSet.getDate("return_date");
                            String place = resultSet.getString("place");
                            double price = resultSet.getDouble("price");
                            String currency = resultSet.getString("currency");
                            Object[] data = {locale, country, departure_date, return_date, place, price, currency};
                            dtm.addRow(data);
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
        );


        south.add(button);
        south.add(buttonPL);
        south.add(buttonEng);

        frame.add(panel);
        panel.add(south , BorderLayout.SOUTH);

        frame.setSize(400 , 300);
        frame.setVisible(true);

    }
}



