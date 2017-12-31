package Model;

import java.sql.*;
import java.util.ArrayList;

public class DataCollector {

	private static final String db_host = System.getenv("DB_HOST");
	private static final String db_port = System.getenv("DB_PORT");
	private static final String db_name = System.getenv("DB_NAME");
	private static final String db_user = System.getenv("DB_USER");
	private static final String db_password = System.getenv("DB_PASSWORD");
	private static final String db_url = "jdbc:postgresql://" + db_host + ":" + db_port + "/" + db_name;

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
