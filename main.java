import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Queues for students and sections
        Queue<String> student = new LinkedList<>();
        Queue<String> section = new LinkedList<>();

        // Input students
        System.out.println("Enter student names (type 'done' to finish):");
        while (true) {
            System.out.println("Enter student name:");
            String input = scan.nextLine();
            
            if (input.equalsIgnoreCase("done")) {
                System.out.println("Student input completed.");
                break;
            }

            // Validate input to ensure it contains only letters and is not empty
            if (!input.trim().isEmpty() && input.matches("[a-zA-Z]+")) {
                student.offer(input);
            } else {
                System.out.println("Invalid input. Please enter a non-empty name with letters only.");
            }
        }

        // Assign students to sections
        System.out.println("Assign students to sections (A or B):");
        while (!student.isEmpty()) {
            System.out.println("Enter section for " + student.peek() + " (A or B):");
            String sectionInput = scan.nextLine();

            if (sectionInput.equalsIgnoreCase("A")) {
                section.offer(student.poll() + " - Section A");
            } else if (sectionInput.equalsIgnoreCase("B")) {
                section.offer(student.poll() + " - Section B");
            } else {
                System.out.println("Invalid section. Please enter A or B.");
            }
        }

        // Display assigned sections
        System.out.println("Final section assignments:");
        for (String entry : section) {
            System.out.println(entry);
        }

        // Schedule subclasses
        Schedule sectionASchedule = new SectionASchedule();
        Schedule sectionBSchedule = new SectionBSchedule();

        // Endless search functionality
        while (true) {
            System.out.println("Search for a student by name (type 'exit' to quit):");
            String searchName = scan.nextLine();

            if (searchName.equalsIgnoreCase("exit")) {
                System.out.println("Exiting search...");
                break;
            }

            boolean found = false;
            for (String entry : section) {
                String studentName = entry.split(" - ")[0];
                if (studentName.equalsIgnoreCase(searchName)) {
                    System.out.println("Found: " + entry);
                    if (entry.contains("Section A")) {
                        System.out.println("Schedule: " + sectionASchedule.getSchedule());
                    } else if (entry.contains("Section B")) {
                        System.out.println("Schedule: " + sectionBSchedule.getSchedule());
                    }
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Student not found in any section.");
            }
        }

        scan.close();
    }
}

// Abstract class for schedules
abstract class Schedule {
    public abstract String getSchedule();
}

// Schedule for Section A
class SectionASchedule extends Schedule {
    @Override
    public String getSchedule() {
        return "Monday to Friday: 8 AM - 12 PM";
    }
}

// Schedule for Section B
class SectionBSchedule extends Schedule {
    @Override
    public String getSchedule() {
        return "Monday to Friday: 1 PM - 5 PM";
    }
}
