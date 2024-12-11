import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        // Step 1: Collect student names
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

        // Step 2: Assign sections to students
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
            students.set(students.indexOf(student), assignedStudent); // Update the student with the assigned section
            assignedStudent.displayDetails();
        }

        System.out.println("\nAll students have been assigned to sections.");

        // Step 3: Each student checks their schedule
        for (Student student : students) {
            System.out.println("\nNow checking the schedule for: " + student.getName());
            while (true) {
                System.out.print("Enter a day of the week to see the schedule (or type 'next' to proceed to the next student): ");
                String day = scanner.nextLine();
                if (day.equalsIgnoreCase("next")) {
                    break;
                }
                student.displaySchedule(day);
            }
        }

        // Exit message
        System.out.println("\nAll students have checked their schedules. Goodbye!");
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

    public void displaySchedule(String day) {
        System.out.println("Schedule for " + day + " not available.");
    }
}

class IT211A extends Student {
    public IT211A(String name) {
        super(name, "IT211A");
    }

    @Override
    public void displaySchedule(String day) {
        switch (day.toLowerCase()) {
            case "monday" -> {
                System.out.println("Schedule for Monday:");
                System.out.println("8:00 AM - 10:00 AM (Programming 1)");
            }
            case "tuesday" -> System.out.println("No Schedule");
            case "wednesday" -> {
                System.out.println("Schedule for Wednesday:");
                System.out.println("1:00 PM - 3:00 PM (Data Structures)");
            }
            case "thursday" -> System.out.println("No Schedule");
            case "friday" -> {
                System.out.println("Schedule for Friday:");
                System.out.println("10:00 AM - 12:00 PM (Database Systems)");
            }
            default -> System.out.println("Wrong Input, please enter a valid day of the week");
        }
    }
}

class IT211B extends Student {
    public IT211B(String name) {
        super(name, "IT211B");
    }

    @Override
    public void displaySchedule(String day) {
        switch (day.toLowerCase()) {
            case "monday" -> System.out.println("No Schedule");
            case "tuesday" -> {
                System.out.println("Schedule for Tuesday:");
                System.out.println("9:00 AM - 11:00 AM (Web Development)");
            }
            case "wednesday" -> System.out.println("No Schedule");
            case "thursday" -> {
                System.out.println("Schedule for Thursday:");
                System.out.println("2:00 PM - 4:00 PM (Operating Systems)");
            }
            case "friday" -> System.out.println("No Schedule");
            case "saturday" -> {
                System.out.println("Schedule for Saturday:");
                System.out.println("8:00 AM - 10:00 AM (Networking Basics)");
            }
            default -> System.out.println("Wrong Input, please enter a valid day of the week");
        }
    }
}

class IT211C extends Student {
    public IT211C(String name) {
        super(name, "IT211C");
    }

    @Override
    public void displaySchedule(String day) {
        switch (day.toLowerCase()) {
            case "monday" -> {
                System.out.println("Schedule for Monday:");
                System.out.println("10:00 AM - 12:00 PM (Software Engineering)");
            }
            case "tuesday" -> System.out.println("No Schedule");
            case "wednesday" -> {
                System.out.println("Schedule for Wednesday:");
                System.out.println("3:00 PM - 5:00 PM (Artificial Intelligence)");
            }
            case "thursday" -> System.out.println("No Schedule");
            case "friday" -> {
                System.out.println("Schedule for Friday:");
                System.out.println("1:00 PM - 3:00 PM (Mobile App Development)");
            }
            default -> System.out.println("Wrong Input, please enter a valid day of the week");
        }
    }
}
