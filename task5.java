import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    List<Student> enrolledStudents;

    public Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
    }
}

class Student {
    String studentID;
    String name;
    List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}

public class task5 {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Course");
            System.out.println("2. Register Student");
            System.out.println("3. List Available Courses");
            System.out.println("4. Register Student for a Course");
            System.out.println("5. Drop Course for a Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.next();
                    System.out.print("Enter Course Title: ");
                    String courseTitle = scanner.next();
                    System.out.print("Enter Course Description: ");
                    String courseDescription = scanner.next();
                    System.out.print("Enter Course Capacity: ");
                    int courseCapacity = scanner.nextInt();

                    Course newCourse = new Course(courseCode, courseTitle, courseDescription, courseCapacity);
                    courses.add(newCourse);
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.next();
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.next();

                    Student newStudent = new Student(studentID, studentName);
                    students.add(newStudent);
                    break;

                case 3:
                    System.out.println("Available Courses:");
                    for (Course course : courses) {
                        int availableSlots = course.capacity - course.enrolledStudents.size();
                        System.out.println(course.code + " - " + course.title + " (" + availableSlots + " slots available)");
                    }
                    break;

                case 4:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.next();
                    System.out.print("Enter Course Code to Register: ");
                    courseCode = scanner.next();

                    Student studentToRegister = null;
                    for (Student student : students) {
                        if (student.studentID.equals(studentID)) {
                            studentToRegister = student;
                            break;
                        }
                    }

                    Course courseToRegister = null;
                    for (Course course : courses) {
                        if (course.code.equals(courseCode)) {
                            courseToRegister = course;
                            break;
                        }
                    }

                    if (studentToRegister != null && courseToRegister != null) {
                        if (courseToRegister.enrolledStudents.size() < courseToRegister.capacity) {
                            studentToRegister.registeredCourses.add(courseToRegister);
                            courseToRegister.enrolledStudents.add(studentToRegister);
                            System.out.println("Registration successful!");
                        } else {
                            System.out.println("Course is full. Registration failed.");
                        }
                    } else {
                        System.out.println("Student or Course not found. Registration failed.");
                    }
                    break;

                case 5:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.next();
                    System.out.print("Enter Course Code to Drop: ");
                    courseCode = scanner.next();

                    Student studentToDrop = null;
                    for (Student student : students) {
                        if (student.studentID.equals(studentID)) {
                            studentToDrop = student;
                            break;
                        }
                    }

                    Course courseToDrop = null;
                    for (Course course : courses) {
                        if (course.code.equals(courseCode)) {
                            courseToDrop = course;
                            break;
                        }
                    }

                    if (studentToDrop != null && courseToDrop != null) {
                        studentToDrop.registeredCourses.remove(courseToDrop);
                        courseToDrop.enrolledStudents.remove(studentToDrop);
                        System.out.println("Course dropped successfully!");
                    } else {
                        System.out.println("Student or Course not found. Course drop failed.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
