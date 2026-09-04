package Luyentap2;
import Luyentap2.AppointmentBusiness;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        AppointmentBusiness business =
                new AppointmentBusiness();

        while (true) {

            System.out.println();
            System.out.println("*************** QUẢN LÝ LỊCH HẸN ***************");
            System.out.println("1. Thêm lịch hẹn");
            System.out.println("2. Hiển thị danh sách lịch hẹn");
            System.out.println("3. Tìm kiếm lịch hẹn theo tên bệnh nhân");
            System.out.println("4. Cập nhật lịch hẹn theo mã lịch hẹn");
            System.out.println("5. Xóa lịch hẹn theo mã lịch hẹn");
            System.out.println("6. Thống kê");
            System.out.println("7. Thoát");
            System.out.println("************************************************");
            System.out.print("Lựa chọn của bạn: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    business.addAppointment(scanner);
                    break;

                case "2":
                    business.displayAppointments();
                    break;

                case "3":
                    business.searchByPatientName(scanner);
                    break;

                case "4":
                    business.updateAppointment(scanner);
                    break;

                case "5":
                    business.deleteAppointment(scanner);
                    break;

                case "6":
                    business.statistics();
                    break;

                case "7":
                    System.out.println("Thoát chương trình!");
                    scanner.close();
                    return;

                default:
                    System.out.println(
                            "Lựa chọn không hợp lệ! Vui lòng chọn 1-7."
                    );
            }
        }
    }
}
