package Luyentap3;

import Luyentap3.Order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrderBusiness {

    private List<Order> orders = new ArrayList<>();

    // 1. Thêm đơn hàng
    public void addOrder(Scanner scanner) {

        Order order = new Order();

        order.inputData(scanner);

        orders.add(order);

        System.out.println("Thêm đơn hàng thành công!");
    }

    // 2. Hiển thị danh sách giảm dần theo giá trị đơn hàng
    public void displayOrders() {

        if (orders.isEmpty()) {
            System.out.println("Danh sách đơn hàng đang trống!");
            return;
        }

        System.out.println("\n========== DANH SÁCH ĐƠN HÀNG ==========");

        orders.stream()
                .sorted(Comparator.comparing(Order::getOrderAmount).reversed())
                .forEach(System.out::println);
    }

    // 3. Cập nhật trạng thái
    public void updateStatus(Scanner scanner) {

        if (orders.isEmpty()) {
            System.out.println("Danh sách đơn hàng đang trống!");
            return;
        }

        try {
            System.out.print("Nhập mã đơn hàng cần cập nhật: ");
            int id = Integer.parseInt(scanner.nextLine());

            Order order = findById(id);

            if (order == null) {
                System.out.println("Không tìm thấy đơn hàng!");
                return;
            }

            String currentStatus = order.getStatus();

            if (currentStatus.equals("Pending")) {

                order.setStatus("Shipped");
                System.out.println("Cập nhật trạng thái thành Shipped!");

            } else if (currentStatus.equals("Shipped")) {

                order.setStatus("Delivered");
                System.out.println("Cập nhật trạng thái thành Delivered!");

            } else {

                System.out.println(
                        "Đơn hàng đã Delivered, không thể cập nhật thêm!"
                );
            }

        } catch (NumberFormatException e) {
            System.out.println("Mã đơn hàng phải là số nguyên!");
        }
    }

    // 4. Xóa đơn hàng
    public void deleteOrder(Scanner scanner) {

        if (orders.isEmpty()) {
            System.out.println("Danh sách đơn hàng đang trống!");
            return;
        }

        try {
            System.out.print("Nhập mã đơn hàng cần xóa: ");
            int id = Integer.parseInt(scanner.nextLine());

            Order order = findById(id);

            if (order == null) {
                System.out.println("Không tìm thấy đơn hàng!");
                return;
            }

            if (!order.getStatus().equals("Pending")) {
                System.out.println(
                        "Chỉ được xóa đơn hàng có trạng thái Pending!"
                );
                return;
            }

            orders.remove(order);

            System.out.println("Xóa đơn hàng thành công!");

        } catch (NumberFormatException e) {
            System.out.println("Mã đơn hàng phải là số nguyên!");
        }
    }

    // 5. Tìm kiếm theo tên khách hàng
    public void searchByCustomerName(Scanner scanner) {

        if (orders.isEmpty()) {
            System.out.println("Danh sách đơn hàng đang trống!");
            return;
        }

        System.out.print("Nhập tên khách hàng cần tìm: ");
        String keyword = scanner.nextLine().trim();

        List<Order> result = orders.stream()
                .filter(order ->
                        order.getCustomerName()
                                .toLowerCase()
                                .contains(keyword.toLowerCase())
                )
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("Không tìm thấy đơn hàng phù hợp!");
        } else {

            System.out.println("\n========== KẾT QUẢ TÌM KIẾM ==========");

            result.forEach(System.out::println);
        }
    }

    // 6. Tổng số đơn hàng
    public void countOrders() {

        System.out.println("Tổng số đơn hàng: " + orders.size());
    }

    // 7. Tổng doanh thu đơn Delivered
    public void totalRevenue() {

        float total = (float) orders.stream()
                .filter(order ->
                        order.getStatus().equals("Delivered")
                )
                .mapToDouble(Order::getOrderAmount)
                .sum();

        System.out.printf(
                "Tổng doanh thu đơn Delivered: %.2f%n",
                total
        );
    }

    // 8. Thống kê số lượng theo trạng thái
    public void statisticsByStatus() {

        long pending = orders.stream()
                .filter(order -> order.getStatus().equals("Pending"))
                .count();

        long shipped = orders.stream()
                .filter(order -> order.getStatus().equals("Shipped"))
                .count();

        long delivered = orders.stream()
                .filter(order -> order.getStatus().equals("Delivered"))
                .count();

        System.out.println("\n========== THỐNG KÊ ==========");
        System.out.println("Pending: " + pending);
        System.out.println("Shipped: " + shipped);
        System.out.println("Delivered: " + delivered);
    }

    // 9. Tìm đơn hàng có giá trị lớn nhất
    public void findMaxOrder() {

        if (orders.isEmpty()) {
            System.out.println("Danh sách đơn hàng đang trống!");
            return;
        }

        Order maxOrder = orders.stream()
                .max(Comparator.comparing(Order::getOrderAmount))
                .orElse(null);

        System.out.println("\n========== ĐƠN HÀNG GIÁ TRỊ LỚN NHẤT ==========");

        System.out.println(maxOrder);
    }

    // Tìm Order theo ID
    private Order findById(int id) {

        return orders.stream()
                .filter(order -> order.getOrderId() == id)
                .findFirst()
                .orElse(null);
    }
}
