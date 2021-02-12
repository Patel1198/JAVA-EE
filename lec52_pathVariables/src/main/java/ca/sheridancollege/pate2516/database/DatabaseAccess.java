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

	public void updateStudent(Student student) {
		

		MapSqlParameterSource namedParameters= new MapSqlParameterSource();
		namedParameters.addValue("sid", student.getId() );
		namedParameters.addValue("sname", student.getName() );
		String query="UPDATE student (name) SET name=:sname WHERE id=:sid";
		int rowsAffected= jdbc.update(query, namedParameters);
		if (rowsAffected> 0) 
			System.out.println("student updated on the database.");
		}
	
	public void deleteStudentById(Long id) {
		MapSqlParameterSource namedParameters= new MapSqlParameterSource();
	String query = "DELETE FROM student WHERE id = :id";
	namedParameters.addValue("id", id);
	int rowsAffected= jdbc.update(query, namedParameters);
	if (rowsAffected> 0)
		System.out.println("Deleted student " + id + " from database");
	}
	
	public List<Student> getStudentListById(Long id) {
		MapSqlParameterSource namedParameters= new MapSqlParameterSource();
		String query = "SELECT * FROM student WHERE id = :id";
		namedParameters.addValue("id", id);
	return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Student>(Student.class));
	}
}