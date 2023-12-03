package AdventureGame;

 
class Person {
    private String name;
    private int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Method
    public void introduce() {
        System.out.println("Hi, I'm " + name + ", and I am " + age + " years old.");
    }
    String getName(){
        return name;
    }
     int getAge(){
        return age;
    }
}

// Student inheriting from Person
class Student extends Person {
    private int studentId;

    // Constructor
    public Student(String name, int age, int studentId) {
        super(name, age);
        this.studentId = studentId;
    }

    // Overriding the introduce method for  
    @Override
    public void introduce() {
        System.out.println("Hi, I'm " + getName() + ", and I am a student with ID " + studentId + "." + getAge());
    }
}

// Main class for testing
public class Main {
    public static void main(String[] args) {
        // Creating an object of the Person class
        Person person = new Person("John", 25);
         person.introduce();

        // Creating an object of the Student class
        Student student = new Student("Alice", 20, 12345);
         student.introduce();
    }
}
