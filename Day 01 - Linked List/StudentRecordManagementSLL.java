package Day5.PracticeProblems;
import java.util.Scanner;
class Student{
    int rollNumber;
    String name;
    int age;
    String grade;
    Student next;

    Student(int rollNumber, String name, int age, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}
class StudentList{
    Student head;
    void addAtBeginning(int rollNumber, String name, int age, String grade) {
        Student newStudent = new Student(rollNumber, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    void addAtEnd(int rollNumber, String name, int age, String grade) {
        Student newStudent = new Student(rollNumber, name, age, grade);
        if (head == null) {
            head = newStudent;
            return;
        }
        Student temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newStudent;
    }

    void addAtPosition(int position, int rollNumber, String name, int age, String grade) {
        if (position <= 1) {
            addAtBeginning(rollNumber, name, age, grade);
            return;
        }
        Student newStudent = new Student(rollNumber, name, age, grade);
        Student temp = head;
        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            addAtEnd(rollNumber, name, age, grade);
            return;
        }
        newStudent.next = temp.next;
        temp.next = newStudent;
    }

    void deleteByRollNumber(int rollNumber) {
        if (head == null) return;
        if (head.rollNumber == rollNumber) {
            head = head.next;
            return;
        }
        Student temp = head;
        while (temp.next != null && temp.next.rollNumber != rollNumber) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    Student searchByRollNumber(int rollNumber) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == rollNumber) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    void updateGradeByRollNumber(int rollNumber, String newGrade) {
        Student student = searchByRollNumber(rollNumber);
        if (student != null) {
            student.grade = newGrade;
        }
    }

    void displayAll() {
        Student temp = head;
        while (temp != null) {
            System.out.println("Roll Number: " + temp.rollNumber + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }
}
public class StudentRecordManagementSLL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentList list = new StudentList();
        while (true) {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Delete by Roll Number\n5. Search by Roll Number\n6. Update Grade\n7. Display All\n8. Exit\nEnter choice:");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Roll Number, Name, Age, Grade:");
                    list.addAtBeginning(sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                    break;
                case 2:
                    System.out.println("Enter Roll Number, Name, Age, Grade:");
                    list.addAtEnd(sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                    break;
                case 3:
                    System.out.println("Enter Position, Roll Number, Name, Age, Grade:");
                    list.addAtPosition(sc.nextInt(), sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                    break;
                case 4:
                    System.out.println("Enter Roll Number to delete:");
                    list.deleteByRollNumber(sc.nextInt());
                    break;
                case 5:
                    System.out.println("Enter Roll Number to search:");
                    Student s = list.searchByRollNumber(sc.nextInt());
                    if (s != null) {
                        System.out.println("Found: Roll Number: " + s.rollNumber + ", Name: " + s.name + ", Age: " + s.age + ", Grade: " + s.grade);
                    } else {
                        System.out.println("Student not found");
                    }
                    break;
                case 6:
                    System.out.println("Enter Roll Number and new Grade:");
                    list.updateGradeByRollNumber(sc.nextInt(), sc.next());
                    break;
                case 7:
                    list.displayAll();
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
