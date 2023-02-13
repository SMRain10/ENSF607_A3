// package com.postgresqltutorial;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {

    /**
     * @author postgresqltutorial.com
     */

    private final String url = "jdbc:postgresql://localhost/ENSF607_A2";
    private final String user = "postgres";
    private final String password = "test";

    private Connection conn = null;

    private static final String createCourseTableSQL = "CREATE TABLE Course " +
            "(Course_Id VARCHAR(10) PRIMARY KEY, " +
            " Course_Name VARCHAR(50), " +
            " Course_Title VARCHAR(50)) ";

    private static final String createStudentTableSQL = "CREATE TABLE Student " +
            "(Student_Id VARCHAR(10) PRIMARY KEY, " +
            " FirstName VARCHAR(50), " +
            " LastName VARCHAR(50), " +
            " Location VARCHAR(100)) ";

    private static final String createRegistrationTableSQL = "CREATE TABLE Registration " +
            "(Registration_Id VARCHAR(10) PRIMARY KEY, " +
            " Course_Id VARCHAR(10), " +
            " Student_Id VARCHAR(10), " +
            " CONSTRAINT fk_course_id FOREIGN KEY (Course_Id) REFERENCES Course(Course_Id), " +
            " CONSTRAINT fk_student_id FOREIGN KEY (Student_Id) REFERENCES Student(Student_Id))";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public void connect() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTable(String str1) throws SQLException{
        // Try to create a table
        try {
            Statement statement = conn.createStatement();
            statement.execute(str1);
            System.out.println("Created table successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertCourse(Course c1){
        String SQL = "INSERT INTO course(course_id,course_name,course_title) "
                + "VALUES(?,?,?)";

        try {
            PreparedStatement statement = conn.prepareStatement(SQL);


            statement.setString(1, c1.getCourseId());
            statement.setString(2, c1.getCourseName());
            statement.setString(3, c1.getCourseTitle());
            statement.executeUpdate();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void insertStudent(Student s1){
        String SQL = "INSERT INTO Student(Student_Id,FirstName,LastName,Location) "
                + "VALUES(?,?,?,?)";

        try {
            PreparedStatement statement = conn.prepareStatement(SQL);


            statement.setString(1, s1.getStudentId());
            statement.setString(2, s1.getFirstName());
            statement.setString(3, s1.getLastName());
            statement.setString(4, s1.getLocation());
            statement.executeUpdate();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void insertRegistration(Registration r1){
        String SQL = "INSERT INTO Registration(Registration_Id,Course_Id,Student_Id) "
                + "VALUES(?,?,?)";

        try {
            PreparedStatement statement = conn.prepareStatement(SQL);


            statement.setString(1, r1.getRegistrationId());
            statement.setString(2, r1.getCourseId());
            statement.setString(3, r1.getStudentId());
            statement.executeUpdate();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();

        String SQL = "SELECT * FROM course";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                String courseId = resultSet.getString("course_id");
                String courseName = resultSet.getString("course_name");
                String courseTitle = resultSet.getString("course_title");

                Course course = new Course(courseId, courseName, courseTitle);
                courses.add(course);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return courses;
    }
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        String SQL = "SELECT * FROM student";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                String studentId = resultSet.getString("Student_Id");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String location = resultSet.getString("Location");

                Student student = new Student(studentId, firstName, lastName, location);
                students.add(student);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return students;
    }
    public List<Registration> getAllRegistrations() {
        List<Registration> registrations = new ArrayList<>();

        String SQL = "SELECT * FROM registration";

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                String registrationId = resultSet.getString("Registration_Id");
                String courseId = resultSet.getString("Course_Id");
                String studentId = resultSet.getString("Student_Id");

                Registration registration = new Registration(registrationId, courseId, studentId);
                registrations.add(registration);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return registrations;
    }

    public static void main(String[] args) throws SQLException {
        App app = new App();
        app.connect();

        //Create Course and Student tables in PostGreSQL
        app.createTable(createCourseTableSQL);
        app.createTable(createStudentTableSQL);
        app.createTable(createRegistrationTableSQL);

        // Add 3 courses using Course object to the Course PostGreSQL table created above
        app.insertCourse(new Course("6001","ENSF607","Software Architecture"));
        app.insertCourse(new Course("6002","ENSF608","Databases"));
        app.insertCourse(new Course("6003","ENSF612","Big Data"));

        // Add 3 students to the PostGreSQL Student Table created above
        app.insertStudent(new Student("30084292","Sam", "Rainbow", "Calgary, Alberta"));
        app.insertStudent(new Student("30179887","Samuel", "Sofela", "Calgary, Alberta"));
        app.insertStudent(new Student("10014545","Jane", "Doe", "Edmonton, Alberta"));

        // Add 3 registrations to the PostGreSQL Registration Table created above
        app.insertRegistration(new Registration("0000001","6001","30084292"));
        app.insertRegistration(new Registration("0000002","6002","30179887"));
        app.insertRegistration(new Registration("0000003","6003","10014545"));

        // Print out data from PostGreSQL
        System.out.println(app.getAllCourses().toString());
        System.out.println(app.getAllStudents().toString());
        System.out.println(app.getAllRegistrations().toString());


    }
}