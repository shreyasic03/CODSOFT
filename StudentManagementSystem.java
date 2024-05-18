import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;
    int enrolledStudents;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }
}

class Student {
    String id;
    String name;
    List<Course> registeredCourses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}

public class StudentManagementSystem {
    static Map<String, Course> courses = new HashMap<>();
    static Map<String, Student> students = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCourses();
        initializeStudents();
        boolean running = true;
        while (running) {
            System.out.println("\nStudent Course Registration System");
            System.out.println("1. View Course Listing");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            switch (choice) {
                case 1:
                    viewCourseListing();
                    break;
                case 2:
                    registerForCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void initializeCourses() {
        courses.put("CS101", new Course("CS101", "Introduction to Computer Science",
                "Basic concepts of computer science", 50, "MWF 9:00-10:00"));
        courses.put("MATH203",
                new Course("MATH203", "Calculus I", "Differential and integral calculus", 40, "TuTh 10:30-12:00"));
        courses.put("ENG112", new Course("ENG112", "English Composition", "Writing and critical thinking skills", 60,
                "MWF 11:00-12:00"));
    }

    static void initializeStudents() {
        students.put("S001", new Student("S001", "John Doe"));
        students.put("S002", new Student("S002", "Jane Smith"));
    }

    static void viewCourseListing() {
        System.out.println("\nCourse Listing:");
        for (Course course : courses.values()) {
            System.out.println(course.code + " - " + course.title + " (" + (course.capacity -
                    course.enrolledStudents) + " slots available)");
            System.out.println("Description: " + course.description);
            System.out.println("Schedule: " + course.schedule);
            System.out.println();
        }
    }

    static void registerForCourse() {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Invalid student ID.");
            return;
        }
        System.out.print("Enter the course code you want to register for: ");
        String courseCode = scanner.nextLine();
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Invalid course code.");
            return;
        }
        if (course.enrolledStudents >= course.capacity) {
            System.out.println("The course is already full.");
            return;
        }
        if (student.registeredCourses.contains(course)) {
            System.out.println("You are already registered for this course.");
            return;
        }
        student.registeredCourses.add(course);
        course.enrolledStudents++;
        System.out.println("You have successfully registered for " + course.title + ".");
    }

    static void dropCourse() {
        System.out.print("Enter your student ID: ");
        String studentId = scanner.nextLine();
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Invalid student ID.");
            return;
        }
        if (student.registeredCourses.isEmpty()) {
            System.out.println("You are not registered for any courses.");
            return;
        }
        System.out.println("\nRegistered Courses:");
        for (int i = 0; i < student.registeredCourses.size(); i++) {
            Course course = student.registeredCourses.get(i);
            System.out.println((i + 1) + ". " + course.code + " - " + course.title);
        }
        System.out.print("Enter the number of the course you want to drop: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        if (choice < 1 || choice > student.registeredCourses.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        Course droppedCourse = student.registeredCourses.get(choice - 1);
        student.registeredCourses.remove(choice - 1);
        droppedCourse.enrolledStudents--;
        System.out.println("You have successfully dropped " + droppedCourse.title + ".");
    }
}