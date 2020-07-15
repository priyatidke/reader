package io.litmusblox.aiml.resumeparser.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class KeywordRepositoryImpl implements KeywordRepository {

	@Override
	public Set<String> getKeywords() {
		// TODO Auto-generated method stub
		return new TreeSet<String>(this.connectToDatabase());
	}

	private List<String> connectToDatabase() {
		List<String> result = new ArrayList<String>();

		try {
			// LOAD THE DRIVER
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://jmdb-predictor-dev.cptvjl3vuwqy.ap-south-1.rds.amazonaws.com/role_predictor_dev",
					"jmlabsuser", "Testpass10");
			// here sonoo is database litmusbox_wordcount, root is username and
			// password is not set
			String query = " select key_skills from `Keyword_UAT` where `active`=1";
			// create the java statement
			Statement st = connection.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next()) {

				String keyskill = rs.getString("key_skills");
				result.add(keyskill);

				// print the results
				//System.out.format("%s\n", keyskill);
			}
			st.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		// ===================================
		/*
		 * try { //LOAD THE DRIVER Class.forName("com.mysql.cj.jdbc.Driver");
		 * //CREATE CONNECTION OBJECT Connection con =
		 * DriverManager.getConnection(
		 * "jdbc:mysql://jmdb-predictor-dev.cptvjl3vuwqy.ap-south-1.rds.amazonaws.com:3306/Tech_Type",
		 * "jmlabsuser", "Testpass10");
		 * 
		 * // here sonoo is database litmusbox_wordcount, root is username and
		 * password is not set String query =
		 * " select key_skills from `Keyword_UAT` where `active`=1";
		 * //while(queryResult.next()){ //
		 * keywordmaster.addKeySkillDetail(queryResult.getString(1),
		 * queryResult.getString(2), queryResult.getString(3),
		 * queryResult.getString(4), queryResult.getInt(5),
		 * queryResult.getInt(6), queryResult.getInt(7), queryResult.getInt(8),
		 * queryResult.getString(9), queryResult.getInt(10),
		 * queryResult.getInt(11), queryResult.getInt(12)); // } } catch
		 * (Exception e) { System.out.println(e); }
		 */
		// ===============================
		return result;
	}

}
