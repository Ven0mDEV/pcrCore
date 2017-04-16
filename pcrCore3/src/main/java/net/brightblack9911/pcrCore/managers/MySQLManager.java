package net.brightblack9911.pcrCore.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.brightblack9911.pcrCore.basic.Query;

public class MySQLManager
{
	private Connection connection;

	private String host;
	private int port;
	private String database;
	private String user;
	private String pass;

	private static MySQLManager instance;

	public MySQLManager() {
		Settings settings = Settings.getInstance();
		this.host = settings.sql_host;
		this.port = settings.sql_port;
		this.database = settings.sql_base;
		this.user = settings.sql_user;
		this.pass = settings.sql_pass;
		firstConnection();
	}

	public Connection openConnection() {
		try {
			if (isConnection()) {
				return connection;
			}

			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database;
			this.connection = DriverManager.getConnection(url, this.user, this.pass);
			return connection;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public Connection getConnection() throws SQLException {
		if (isConnection()) {
			return this.connection;
		}
		try {
			return openConnection();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void firstConnection() {
		if (!isConnection()) {
			openConnection();
		}
		System.out.println("sdfsdffffffffffff TWORZENIE");
		executeUpdate("CREATE DATABASE IF NOT EXISTS " + database);
		executeUpdate(
				"CREATE TABLE IF NOT EXISTS users (`id` INT(6) NOT NULL AUTO_INCREMENT PRIMARY KEY, `uuid` CHAR(36) NOT NULL, `name` VARCHAR(16) NOT NULL, `coins` INT(10), `home` VARCHAR(100), `turbocoinstime` BIGINT(100), `turbocoinsstart` BIGINT(100))");
		executeUpdate(
				"CREATE TABLE IF NOT EXISTS drop_stat (`id` INT(6) NOT NULL AUTO_INCREMENT PRIMARY KEY, `uuid` CHAR(36) NOT NULL, `name` VARCHAR(16) NOT NULL, `number` INT(10))");
		executeUpdate(
				"CREATE TABLE IF NOT EXISTS cuboids (`id` INT(6) NOT NULL AUTO_INCREMENT PRIMARY KEY, `name` VARCHAR(16) NOT NULL, `owner` CHAR(36) NOT NULL, `center` VARCHAR(100), `members` VARCHAR(50))");
		executeUpdate(
				"CREATE TABLE IF NOT EXISTS achievements (`id` INT(6) NOT NULL AUTO_INCREMENT PRIMARY KEY,`uuid` CHAR(36) NOT NULL, `name` VARCHAR(16) NOT NULL, `progress` INT(10))");
		closeConnection();
	}

	public boolean isConnection() {
		try {
			return this.connection != null && !this.connection.isClosed();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection != null;
	}

	public void closeConnection() {
		try {
			this.connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			this.connection = null;
		}
	}

	public Query querySql(String qsql) throws SQLException, Exception {
		try (Statement pr = getConnection().createStatement()) {
			final ResultSet rset = pr.executeQuery(qsql);
			// closeResources(rset, pr);
			return new Query() {
				@Override
				public boolean existRecord() {
					try {
						if (rset.next()) {
							return true;
						}
					}
					catch (Exception e) {
						e.getStackTrace();
					}
					return false;
				}

				@Override
				public ResultSet getResult() {
					return rset;
				}
			};
		}

	}

	public ResultSet executeQuery(String query) {
		try {
			if (!isConnection()) {
				openConnection();
			}
			if (connection == null) {
				return null;
			}
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int executeUpdate(String query) {
		try {
			if (!isConnection()) {
				openConnection();
			}
			Statement statement = connection.createStatement();
			if (statement == null) {
				return 0;
			}
			return statement.executeUpdate(query);
		}
		catch (SQLException e) {

			e.printStackTrace();

		}
		return 0;
	}

	public static MySQLManager getInstance() {
		if (instance == null) instance = new MySQLManager();
		return instance;
	}
}
