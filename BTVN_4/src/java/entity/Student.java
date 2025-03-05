
package entity;


public class Student {
    private int StudentID; //1 nam 2 nữ
    private String FirstName, LastName, dob, Gender;

    public Student() {
    }

    public Student(int StudentID, String FirstName, String LastName, String dob, String Gender) {
        this.StudentID = StudentID;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.dob = dob;
        this.Gender = Gender;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int StudentID) {
        this.StudentID = StudentID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    @Override
    public String toString() {
        return "Student{" + "StudentID=" + StudentID + ", FirstName=" + FirstName + ", LastName=" + LastName + ", dob=" + dob + ", Gender=" + Gender + '}';
    }

   
}