package Luyentap2;

import Luyentap2.Appointment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AppointmentBusiness {

    private List<Appointment> appointments = new ArrayList<>();

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // =========================
    // 1. THÊM LỊCH HẸN
    // =========================

    public void addAppointment(Scanner scanner) {

        Appointment appointment = new Appointment();

        while (true) {
            appointment.inputData(scanner);

            // Kiểm tra mã lịch hẹn trùng
            boolean exists = appointments.stream()
                    .anyMatch(a -> a.getAppointmentId()
                            .equalsIgnoreCase(appointment.getAppointmentId()));

            if (exists) {
                System.out.println("Mã lịch hẹn đã tồn tại!");
                System.out.println("Vui lòng nhập lại.");
            } else {
                break;
            }
        }

        appointments.add(appointment);

        System.out.println("Thêm lịch hẹn thành công!");
    }

    // =========================
    // 2. HIỂN THỊ DANH SÁCH
    // =========================

    public void displayAppointments() {

        if (appointments.isEmpty()) {
            System.out.println("Danh sách lịch hẹn đang trống!");
            return;
        }

        System.out.println("\n========== DANH SÁCH LỊCH HẸN ==========");

        appointments.stream()
                .sorted(Comparator.comparing(Appointment::getAppointmentDate))
                .forEach(System.out::println);
    }

    // =========================
    // 3. TÌM KIẾM THEO TÊN
    // =========================

    public void searchByPatientName(Scanner scanner) {

        System.out.print("Nhập tên bệnh nhân cần tìm: ");
        String keyword = scanner.nextLine().trim().toLowerCase();

        List<Appointment> result = appointments.stream()
                .filter(a -> a.getPatientName()
                        .toLowerCase()
                        .contains(keyword))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("Không tìm thấy lịch hẹn!");
        } else {
            System.out.println("\n========== KẾT QUẢ TÌM KIẾM ==========");

            result.forEach(System.out::println);
        }
    }

    // =========================
    // 4. CẬP NHẬT
    // =========================

    public void updateAppointment(Scanner scanner) {

        System.out.print("Nhập mã lịch hẹn cần cập nhật: ");
        String id = scanner.nextLine().trim();

        Optional<Appointment> optionalAppointment = appointments.stream()
                .filter(a -> a.getAppointmentId()
                        .equalsIgnoreCase(id))
                .findFirst();

        optionalAppointment.ifPresentOrElse(
                appointment -> updateAppointmentInfo(scanner, appointment),
                () -> System.out.println("Không tìm thấy mã lịch hẹn!")
        );
    }

    private void updateAppointmentInfo(
            Scanner scanner,
            Appointment appointment) {

        System.out.println("\nLịch hẹn hiện tại:");
        System.out.println(appointment);

        // Tên bệnh nhân
        while (true) {
            System.out.print("Nhập tên bệnh nhân mới (10-50 ký tự): ");
            String name = scanner.nextLine().trim();

            if (name.length() >= 10 && name.length() <= 50) {
                appointment.setPatientName(name);
                break;
            }

            System.out.println("Tên phải từ 10 đến 50 ký tự!");
        }

        // Số điện thoại
        while (true) {
            System.out.print("Nhập số điện thoại mới: ");
            String phone = scanner.nextLine().trim();

            if (phone.matches("^(03|05|07|08|09)[0-9]{8}$")) {
                appointment.setPhoneNumber(phone);
                break;
            }

            System.out.println("Số điện thoại không hợp lệ!");
        }

        // Ngày hẹn
        while (true) {
            System.out.print("Nhập ngày hẹn mới (dd/MM/yyyy): ");
            String date = scanner.nextLine().trim();

            try {
                LocalDate appointmentDate =
                        LocalDate.parse(date, FORMATTER);

                appointment.setAppointmentDate(appointmentDate);
                break;

            } catch (DateTimeParseException e) {
                System.out.println("Ngày không hợp lệ!");
            }
        }

        // Bác sĩ
        while (true) {
            System.out.print("Nhập bác sĩ mới: ");
            String doctor = scanner.nextLine().trim();

            if (!doctor.isEmpty() && doctor.length() <= 200) {
                appointment.setDoctor(doctor);
                break;
            }

            System.out.println("Tên bác sĩ không hợp lệ!");
        }

        System.out.println("Cập nhật thành công!");
    }

    // =========================
    // 5. XÓA LỊCH HẸN
    // =========================

    public void deleteAppointment(Scanner scanner) {

        System.out.print("Nhập mã lịch hẹn cần xóa: ");
        String id = scanner.nextLine().trim();

        Optional<Appointment> optionalAppointment = appointments.stream()
                .filter(a -> a.getAppointmentId()
                        .equalsIgnoreCase(id))
                .findFirst();

        if (optionalAppointment.isPresent()) {

            Appointment appointment = optionalAppointment.get();

            System.out.println("Thông tin lịch hẹn:");
            System.out.println(appointment);

            System.out.print("Bạn có chắc chắn muốn xóa? (Y/N): ");
            String confirm = scanner.nextLine().trim();

            if (confirm.equalsIgnoreCase("Y")) {

                appointments.remove(appointment);

                System.out.println("Xóa lịch hẹn thành công!");

            } else {
                System.out.println("Đã hủy thao tác xóa!");
            }

        } else {
            System.out.println("Không tìm thấy mã lịch hẹn!");
        }
    }

    // =========================
    // 6. THỐNG KÊ
    // =========================

    public void statistics() {

        System.out.println("\n========== THỐNG KÊ ==========");

        // Tổng số lịch hẹn
        System.out.println(
                "Tổng số lịch hẹn: " + appointments.size()
        );

        // Số lịch hẹn theo bác sĩ
        System.out.println("\nSố lịch hẹn theo từng bác sĩ:");

        appointments.stream()
                .collect(Collectors.groupingBy(
                        Appointment::getDoctor,
                        Collectors.counting()
                ))
                .forEach((doctor, count) ->
                        System.out.println(
                                "Bác sĩ: " + doctor +
                                        " - " + count + " lịch hẹn"
                        )
                );
    }
}