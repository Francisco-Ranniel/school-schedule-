import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        
        System.out.println("Enter student names (type 'done' when finished):");
        while (true) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("done")) {
                break;
            }
            if (name.matches("^[a-zA-Z\\s]+$")) {
                students.add(new Student(name, "Unassigned"));
            } else {
                System.out.println("Invalid input. Please enter a valid name (letters only).\n");
            }
        }

        
        System.out.println("\nAssign sections to the students.");
        for (Student student : students) {
            System.out.println("Assigning a section for: " + student.getName());
            int sectionChoice;
            while (true) {
                System.out.println("Select a section:");
                System.out.println("1. IT211A");
                System.out.println("2. IT211B");
                System.out.println("3. IT211C");
                System.out.print("Enter your choice (1/2/3): ");
                String input = scanner.nextLine();

                if (input.matches("\\d+")) {
                    sectionChoice = Integer.parseInt(input);
                    if (sectionChoice >= 1 && sectionChoice <= 3) {
                        break;
                    }
                }
                System.out.println("Invalid input. Please enter a number between 1 and 3.\n");
            }

            Student assignedStudent = switch (sectionChoice) {
                case 1 -> new IT211A(student.getName());
                case 2 -> new IT211B(student.getName());
                case 3 -> new IT211C(student.getName());
                default -> throw new IllegalStateException("Unexpected value: " + sectionChoice);
            };
            students.set(students.indexOf(student), assignedStudent); 
            assignedStudent.displayDetails();
        }

        
        System.out.println("\nAll students have been assigned to sections.");
        System.out.println("");
        System.out.println("\nType 'schedule' to see the full schedule for all students.");

        while (true) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("schedule")) {
                for (Student student : students) {
                    student.displayFullSchedule(); 
                }
                break; 
            } else {
                System.out.println("Invalid command. Please type 'schedule' to see the full schedule.");
            }
        }

        // Exit message
        System.out.println("\nGoodbye!");
        scanner.close();
    }
}

class Student {
    private final String name;
    private final String section;

    public Student(String name, String section) {
        this.name = name;
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public String getSection() {
        return section;
    }

    public void displayDetails() {
        System.out.println("Student Name: " + name);
        System.out.println("Section: " + section);
    }

    public void displayFullSchedule() {
        System.out.println("");
        System.out.println("Full schedule for " + name + ":");
    }
}

class IT211A extends Student {
    public IT211A(String name) {
        super(name, "IT211A");
    }

    @Override
    public void displayFullSchedule() {
        super.displayFullSchedule();
        System.out.println("Monday: 8:00 AM - 10:00 AM (Programming 1)");
        System.out.println("Wednesday: 1:00 PM - 3:00 PM (Data Structures)");
        System.out.println("Friday: 10:00 AM - 12:00 PM (Database Systems)");
    }
}

class IT211B extends Student {
    public IT211B(String name) {
        super(name, "IT211B");
    }

    @Override
    public void displayFullSchedule() {
        super.displayFullSchedule();
        System.out.println("Tuesday: 9:00 AM - 11:00 AM (Web Development)");
        System.out.println("Thursday: 2:00 PM - 4:00 PM (Operating Systems)");
        System.out.println("Saturday: 8:00 AM - 10:00 AM (Networking Basics)");
    }
}

class IT211C extends Student {
    public IT211C(String name) {
        super(name, "IT211C");
    }

    @Override
    public void displayFullSchedule() {
        super.displayFullSchedule();
        System.out.println("Monday: 10:00 AM - 12:00 PM (Software Engineering)");
        System.out.println("Wednesday: 3:00 PM - 5:00 PM (Artificial Intelligence)");
        System.out.println("Friday: 1:00 PM - 3:00 PM (Mobile App Development)");
    }
}
