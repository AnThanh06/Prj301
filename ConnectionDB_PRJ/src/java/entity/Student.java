
package entity;

// đã có entity để lưu dữ liệu vào database(entity là dùng để lưu dữ liệu)
// dữ liệu từ database  --> JDBC  -->>truy vấn ---> class DAO  nằm trong packages Utils để truy vấn
public class Student {
    private int id;
    private int age;
    private String name;

    public Student(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", age=" + age + ", name=" + name + '}';
    }
    
}
