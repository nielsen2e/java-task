package assignment;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Assignment {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Course> courses = new ArrayList<>();
        Assignment st = new Assignment();

        try {
            for (int j = 1; j <= 5; j++) {
                System.out.println("Enter Course " + j + ": ");
                String courseName = scanner.nextLine();

                int unit = st.getInputAsInt(scanner, "Enter " + courseName + " unit: ", 0, Integer.MAX_VALUE);
                double score = st.getInputAsDouble(scanner, "Enter " + courseName + " score: ", 0, 100);

                String grade = st.getGrade(score);
                int points = st.getPoint(grade);
                courses.add(new Course(courseName, unit, score, points));
            }
        } catch (Exception e) {
            System.out.println("Invalid input encountered. Please restart and try again.");
        } finally {
            scanner.close();
        }

        displayResults(courses);
    }

    private int getInputAsInt(Scanner scanner, String prompt, int min, int max) {
        int input;
        while (true) {
            System.out.println(prompt);
            try {
                input = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.println("Please enter a value between " + min + " and " + max);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear buffer
            }
        }
    }

    private double getInputAsDouble(Scanner scanner, String prompt, double min, double max) {
        double input;
        while (true) {
            System.out.println(prompt);
            try {
                input = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.println("Please enter a value between " + min + " and " + max);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear buffer
            }
        }
    }

    public String getGrade(double score) {
        if (score >= 70 && score <= 100) {
            return "A";
        } else if (score >= 60 && score <= 69) {
            return "B";
        } else if (score >= 50 && score <= 59) {
            return "C";
        } else if (score >= 45 && score <= 49) {
            return "D";
        } else if (score >= 40 && score <= 44) {
            return "E";
        } else {
            return "F";
        }
    }

    public int getPoint(String grade) {
        switch (grade) {
            case "A":
                return 5;
            case "B":
                return 4;
            case "C":
                return 3;
            case "D":
                return 2;
            case "E":
                return 1;
            case "F":
                return 0;
            default:
                return 0;
        }
    }

    private static void displayResults(List<Course> courses) {
        System.out.println("|----------------|-------------|-------|-------");
        System.out.println("|COURSE & CODE   | COURSE UNIT | SCORE | GRADE ");
        System.out.println("|----------------|-------------|-------|-------");

        for (Course course : courses) {
            displayCourseInfo(course);
        }

        calculateGPA(courses);
    }

    private static void calculateGPA(List<Course> courses) {
        double totalQualityPoints = 0;
        int totalUnits = 0;

        for (Course course : courses) {
            totalQualityPoints += course.getPoints() * course.getUnit();
            totalUnits += course.getUnit();
        }

        double gpa = totalUnits > 0 ? totalQualityPoints / totalUnits : 0;
        System.out.println("Total Units: " + totalUnits);
        System.out.println("Total Quality Points: " + totalQualityPoints);
        System.out.printf("Your GPA is: %.2f\n", gpa);
    }

    private static void displayCourseInfo(Course course) {
        System.out.printf("|%-16s|%-13d|%-7.2f|%-7s%n", course.getName(), course.getUnit(), course.getScore(), course.getGrade());
    }

    public static class Course {
        private final String name;
        private final int unit;
        private final double score;
        private final int points;

        public Course(String name, int unit, double score, int points) {
            this.name = name;
            this.unit = unit;
            this.score = score;
            this.points = points;
        }

        public String getName() {
            return name;
        }

        public int getUnit() {
            return unit;
        }

        public double getScore() {
            return score;
        }

        public int getPoints() {
            return points;
        }

        public String getGrade() {
            if (score >= 70 && score <= 100) {
                return "A";
            } else if (score >= 60 && score <= 69) {
                return "B";
            } else if (score >= 50 && score <= 59) {
                return "C";
            } else if (score >= 45 && score <= 49) {
                return "D";
            } else if (score >= 40 && score <= 44) {
                return "E";
            } else {
                return "F";
            }
        }
    }
}

