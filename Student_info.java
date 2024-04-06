import java.util.*;

class Student {
    private String name;
    private int id;
    private Map<String, Integer> courses = new HashMap<>(); // CourseName -> Grade

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void addCourseGrade(String courseName, int grade) {
        courses.put(courseName, grade);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Map<String, Integer> getCourses() {
        return courses;
    }
}

class StudentInformationSystem {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void registerCourse(Student student, String courseName, int grade) {
        student.addCourseGrade(courseName, grade);
    }

    public void generateTranscript(Student student) {
        System.out.println("Transcript for " + student.getName() + " (ID: " + student.getId() + ")");
        Map<String, Integer> courses = student.getCourses();
        for (Map.Entry<String, Integer> entry : courses.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        StudentInformationSystem sis = new StudentInformationSystem();

        // Adding students
        Student student1 = new Student("Mrudula", 1001);
        Student student2 = new Student("Vemaraju", 1002);
        sis.addStudent(student1);
        sis.addStudent(student2);

        // Course registration
        sis.registerCourse(student1, "Math", 90);
        sis.registerCourse(student1, "English", 85);
        sis.registerCourse(student2, "Math", 88);
        sis.registerCourse(student2, "English", 92);

        // Generate transcripts
        sis.generateTranscript(student1);
        sis.generateTranscript(student2);
    }
}
