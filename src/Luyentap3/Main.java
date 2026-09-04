package Luyentap3;

import Luyentap3.OrderBusiness;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        OrderBusiness orderBusiness = new OrderBusiness();

        while (true) {

            System.out.println("\n");
            System.out.println("*************** QUẢN LÝ ĐƠN HÀNG ***************");
            System.out.println("1. Thêm đơn hàng");
            System.out.println("2. Hiển thị danh sách đơn hàng");
            System.out.println("3. Cập nhật trạng thái đơn hàng theo mã đơn hàng");
            System.out.println("4. Xóa đơn hàng theo mã đơn hàng");
            System.out.println("5. Tìm kiếm đơn hàng theo tên khách hàng");
            System.out.println("6. Thống kê tổng số đơn hàng");
            System.out.println("7. Thống kê tổng doanh thu các đơn hàng Delivered");
            System.out.println("8. Thống kê số lượng đơn hàng theo từng trạng thái");
            System.out.println("9. Tìm kiếm đơn hàng có giá trị lớn nhất");
            System.out.println("10. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            try {

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {

                    case 1:
                        orderBusiness.addOrder(scanner);
                        break;

                    case 2:
                        orderBusiness.displayOrders();
                        break;

                    case 3:
                        orderBusiness.updateStatus(scanner);
                        break;

                    case 4:
                        orderBusiness.deleteOrder(scanner);
                        break;

                    case 5:
                        orderBusiness.searchByCustomerName(scanner);
                        break;

                    case 6:
                        orderBusiness.countOrders();
                        break;

                    case 7:
                        orderBusiness.totalRevenue();
                        break;

                    case 8:
                        orderBusiness.statisticsByStatus();
                        break;

                    case 9:
                        orderBusiness.findMaxOrder();
                        break;

                    case 10:
                        System.out.println("Đã thoát chương trình!");
                        scanner.close();
                        return;

                    default:
                        System.out.println(
                                "Vui lòng chọn từ 1 đến 10!"
                        );
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Lựa chọn phải là số nguyên!"
                );
            }
        }
    }
}
