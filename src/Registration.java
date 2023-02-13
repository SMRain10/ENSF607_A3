public class Registration {
    private String registrationId,courseId, studentId;

    public Registration(String registrationId, String courseId, String studentId) {
        this.registrationId = registrationId;
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "registrationId='" + registrationId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}
