package Model;

import java.sql.*;
import java.util.ArrayList;

public class DataCollector {

	private static final String db_url = "jdbc:mysql://localhost:3306/persian_tutor?useSSL=false";
	private static final String db_user = "matthew";
	private static final String db_password = System.getenv("DB_PSWD");

	public void getPhrases(ArrayList<Phrase> phrases) {

		try {

			Connection connection = DriverManager.getConnection(db_url, db_user, db_password);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM phrases");

			while (resultSet.next()) {
				String transliteration = resultSet.getString("transliteration");
				String english = resultSet.getString("english");
				String farsi = resultSet.getString("farsi");

				phrases.add(new Phrase(transliteration, english, farsi));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
