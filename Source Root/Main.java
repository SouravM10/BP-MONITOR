import java.sql.*;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== BP Monitor System =====");
            System.out.println("1. Register Patient");
            System.out.println("2. Record Reading");
            System.out.println("3. View Last 5 Readings");
            System.out.println("4. Count High Readings");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    recordReading();
                    break;
                case 3:
                    viewLastReadings();
                    break;
                case 4:
                    countHighReadings();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // ✅ 1. Register Patient
    public static void registerPatient() {
        try (Connection conn = DBConnection.getConnection()) {

            System.out.print("Name: ");
            sc.nextLine(); // consume leftover
            String name = sc.nextLine();

            System.out.print("Age: ");
            int age = sc.nextInt();

            sc.nextLine();
            System.out.print("Address: ");
            String address = sc.nextLine();

            System.out.print("Contact: ");
            String contact = sc.nextLine();

            String query = "INSERT INTO patients (name, age, address, contact) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, address);
            ps.setString(4, contact);

            ps.executeUpdate();

            System.out.println("Patient registered successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ 2. Record Reading
    public static void recordReading() {
        try (Connection conn = DBConnection.getConnection()) {

            System.out.print("Patient ID: ");
            int pat_id = sc.nextInt();

            System.out.print("Systolic: ");
            int sys = sc.nextInt();

            System.out.print("Diastolic: ");
            int dia = sc.nextInt();

            boolean isHigh = (sys > 140 || dia > 90);

            String query = "INSERT INTO readings (pat_id, systolic, diastolic, is_high) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pat_id);
            ps.setInt(2, sys);
            ps.setInt(3, dia);
            ps.setBoolean(4, isHigh);

            ps.executeUpdate();

            if (isHigh) {
                System.out.println("Reading recorded. Status: HIGH BP [ALERT]");
            } else {
                System.out.println("Reading recorded. Status: NORMAL");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ 3. View Last 5 Readings
    public static void viewLastReadings() {
        try (Connection conn = DBConnection.getConnection()) {

            System.out.print("Patient ID: ");
            int pat_id = sc.nextInt();

            String query = "SELECT * FROM readings WHERE pat_id=? ORDER BY read_time DESC LIMIT 5";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pat_id);

            ResultSet rs = ps.executeQuery();

            System.out.println("\nLast 5 Readings:");

            while (rs.next()) {
                System.out.println(
                        "Sys: " + rs.getInt("systolic") +
                                " Dia: " + rs.getInt("diastolic") +
                                " High: " + rs.getBoolean("is_high") +
                                " Time: " + rs.getTimestamp("read_time")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ 4. Count High Readings
    public static void countHighReadings() {
        try (Connection conn = DBConnection.getConnection()) {

            System.out.print("Patient ID: ");
            int pat_id = sc.nextInt();

            String query = "SELECT COUNT(*) FROM readings WHERE pat_id=? AND is_high=TRUE";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pat_id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("High BP Count: " + rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}