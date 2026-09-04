package Luyentap2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Appointment {

    private String appointmentId;
    private String patientName;
    private String phoneNumber;
    private LocalDate appointmentDate;
    private String doctor;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Appointment() {
    }

    public Appointment(String appointmentId, String patientName,
                       String phoneNumber, LocalDate appointmentDate,
                       String doctor) {
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.phoneNumber = phoneNumber;
        this.appointmentDate = appointmentDate;
        this.doctor = doctor;
    }

    // Getter - Setter

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    // Nhập dữ liệu
    public void inputData(Scanner scanner) {

        // appointmentId
        while (true) {
            System.out.print("Nhập mã lịch hẹn (6 ký tự): ");
            String id = scanner.nextLine().trim();

            if (id.length() == 6) {
                appointmentId = id;
                break;
            }

            System.out.println("Mã lịch hẹn phải gồm đúng 6 ký tự!");
        }

        // patientName
        while (true) {
            System.out.print("Nhập tên bệnh nhân (10-50 ký tự): ");
            String name = scanner.nextLine().trim();

            if (name.length() >= 10 && name.length() <= 50) {
                patientName = name;
                break;
            }

            System.out.println("Tên bệnh nhân phải từ 10 đến 50 ký tự!");
        }

        // phoneNumber
        while (true) {
            System.out.print("Nhập số điện thoại: ");
            String phone = scanner.nextLine().trim();

            // Số điện thoại di động Việt Nam
            // Ví dụ: 0912345678, 0987654321, 0701234567...
            if (phone.matches("^(03|05|07|08|09)[0-9]{8}$")) {
                phoneNumber = phone;
                break;
            }

            System.out.println("Số điện thoại không hợp lệ!");
        }

        // appointmentDate
        while (true) {
            System.out.print("Nhập ngày hẹn (dd/MM/yyyy): ");
            String date = scanner.nextLine().trim();

            try {
                appointmentDate = LocalDate.parse(date, FORMATTER);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ngày không hợp lệ! Hãy nhập theo dạng dd/MM/yyyy.");
            }
        }

        // doctor
        while (true) {
            System.out.print("Nhập tên bác sĩ: ");
            String doc = scanner.nextLine().trim();

            if (!doc.isEmpty() && doc.length() <= 200) {
                doctor = doc;
                break;
            }

            System.out.println("Tên bác sĩ không được để trống và tối đa 200 ký tự!");
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Mã: %s | Bệnh nhân: %s | SĐT: %s | Ngày: %s | Bác sĩ: %s",
                appointmentId,
                patientName,
                phoneNumber,
                appointmentDate.format(FORMATTER),
                doctor
        );
    }
}