package com.example.DAO;

import com.example.model.Person;
import com.example.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDao {
    private StudentsDao studentsDao = new StudentsDaoImpl();
    private TeachersDao teachersDao = new TeachersDaoImpl();

    @Override
    public void insert(Person person) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Person (PersonID, Name) VALUES (?, ?)");
            ps.setInt(1, person.getPersonId());
            ps.setString(2, person.getName());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to delete a person from the database by its ID
    @Override
    public void deleteById(int personId) {
        try {
            Connection conn = DatabaseConnection.getConnection();

            // Delete all records related to the person from the Students and Teachers tables
            studentsDao.deleteByPersonId(personId);
            teachersDao.deleteByPersonId(personId);

            PreparedStatement ps = conn.prepareStatement("DELETE FROM Person WHERE PersonID = ?");
            ps.setInt(1, personId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Person findById(int personId) {
        Person person = null;
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Person WHERE PersonID = ?");
            ps.setInt(1, personId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                person = new Person();
                person.setPersonId(rs.getInt("PersonID"));
                person.setName(rs.getString("Name"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Person");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Person person = new Person();
                person.setPersonId(rs.getInt("PersonID"));
                person.setName(rs.getString("Name"));
                persons.add(person);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }

    @Override
    public void update(Person person) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Person SET Name = ? WHERE PersonID = ?");
            ps.setString(1, person.getName());
            ps.setInt(2, person.getPersonId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}