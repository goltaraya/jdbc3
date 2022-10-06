package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {
	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement statement = null;

		try {
			conn = DB.getConnection();
//			statement = conn.prepareStatement("INSERT INTO seller "
//					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) " + " VALUES (?, ?, ?, ?, ?)",
//					Statement.RETURN_GENERATED_KEYS);
//
//			statement.setString(1, "Yago Alexandre");
//			statement.setString(2, "yago@gmail.com");
//			statement.setDate(3, new java.sql.Date(sdf.parse("19/10/2000").getTime()));
//			statement.setDouble(4, 9000000.0);
//			statement.setInt(5, 2);

			statement = conn.prepareStatement("INSERT INTO department (Name) VALUES ('Marketing'), ('Security')",
					Statement.RETURN_GENERATED_KEYS);

			int rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = statement.getGeneratedKeys();
				while (rs.next()) {

					int id = rs.getInt(1);
					System.out.println("Done! ID: " + id);
				}
			} else {
				System.out.println("No rows affected!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(statement);
			DB.closeConnection();
		}
	}
}
