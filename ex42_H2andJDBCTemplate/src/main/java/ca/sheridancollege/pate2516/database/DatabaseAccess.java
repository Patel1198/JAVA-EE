package ca.sheridancollege.pate2516.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.pate2516.beans.Student;

@Repository
public class DatabaseAccess {
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	public void insertStudent() {
		MapSqlParameterSource namedParameters= new MapSqlParameterSource();
		String query="INSERT INTO student(name) VALUES ('Frank')";
		int rowsAffected= jdbc.update(query, namedParameters);
		if (rowsAffected> 0) 
			System.out.println("Inserted student into database.");
		}
	public void insertStudent(String name) {
		MapSqlParameterSource namedParameters= new MapSqlParameterSource();
		namedParameters.addValue("name", name);
		String query="INSERT INTO student(name) VALUES (:name)";
		int rowsAffected= jdbc.update(query, namedParameters);
		if (rowsAffected> 0) 
			System.out.println("Inserted student into database.");
		}
	public List<Student> getStudents() {
		MapSqlParameterSource namedParameters= new  MapSqlParameterSource();
		String query = "SELECT * FROM student";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Student>(Student.class));
		}

}
