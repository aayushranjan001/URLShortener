package com.urlshortener.mvc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	public static String getShortURL(String longURL) {
		long startTime = System.currentTimeMillis();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet  = null;
		String result = null;
		String dbURL = "jdbc:mysql://localhost:3306/url_shortener";
		String user = "USERNAME";
		String password = "PASSWORD";
		if (!URLValidator.isValidURL(longURL)) {
			result = "Invalid URL";
			return result;
		}
		try {
			DBUtility databaseUtility = new DBUtility(dbURL, user, password);
			connection = databaseUtility.establishConnection();
			System.out.println("Database Connection Successful");
			preparedStatement = connection.prepareStatement("SELECT * FROM urls WHERE longURL = ?;");
			preparedStatement.setString(1, longURL);
			resultSet = preparedStatement.executeQuery();
			int rowsAffected;
			if (resultSet.next()) {
				result = "http://localhost:8080/URLShortener/shortenURL/" + resultSet.getString("shortURL");
			} else {
				int id = 0;
				preparedStatement = connection.prepareStatement("INSERT INTO urls(longURL) values(?);");
				preparedStatement.setString(1, longURL);
				rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Insert Successful");
					preparedStatement = connection.prepareStatement("SELECT * FROM urls WHERE longURL = ?;");
					preparedStatement.setString(1, longURL);
					resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						id = resultSet.getInt("ID");
					}
					URLShortener urlShortener = new URLShortener();
					String shortURL = urlShortener.idToString(id);
					preparedStatement = connection.prepareStatement("UPDATE urls set shortURL = ? WHERE ID = ? ;");
					preparedStatement.setString(1, shortURL);
					preparedStatement.setInt(2, id);
					rowsAffected = preparedStatement.executeUpdate();
					if (rowsAffected > 0) {
						result = "http://localhost:8080/URLShortener/shortenURL/" + shortURL;
					} else {
						System.out.println("INSERT Failed");
					}
				} else {
					System.out.println("Insert Failed");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
					System.out.println("ResultSet closed Successfully");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
					System.out.println("prepared Statement closed Successfully");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
					System.out.println("Database connection closed Successfully");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			long endTime = System.currentTimeMillis();
			long totalExecutionTime = endTime - startTime;
			if (totalExecutionTime < 1000)
				System.out.println("Program executed in " + totalExecutionTime + "ms");
			else {
				long seconds = (totalExecutionTime) / 1000;
				long milliseconds = (totalExecutionTime) % 1000;
				if (milliseconds == 0)
					System.out.println("Program executed in " + seconds + "s");
				else
					System.out.println("Program executed in " + seconds + "s " + milliseconds + "ms");
			}
		}
		return result;
	}

	public static String getLongURL(String shortURL) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet  = null;
		String result = null;
		String dbURL = "jdbc:mysql://localhost:3306/url_shortener";
		String user = "student";
		String password = "student";
		try {
			DBUtility databaseUtility = new DBUtility(dbURL, user, password);
			connection = databaseUtility.establishConnection();
			System.out.println("Database Connection Successful");
			preparedStatement = connection.prepareStatement("SELECT * FROM urls WHERE shortURL = ?;");
			preparedStatement.setString(1, shortURL);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				result = resultSet.getString("longURL");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
					System.out.println("ResultSet closed Successfully");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
					System.out.println("prepared Statement closed Successfully");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
					System.out.println("Database connection closed Successfully");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
