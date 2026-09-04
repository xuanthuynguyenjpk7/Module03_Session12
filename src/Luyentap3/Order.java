package Luyentap3;

import java.util.Scanner;

public class Order {

    private static int nextId = 1;

    private int orderId;
    private String customerName;
    private String phoneNumber;
    private String address;
    private float orderAmount;
    private String status;

    public Order() {
        this.orderId = nextId++;
    }

    public Order(String customerName, String phoneNumber,
                 String address, float orderAmount, String status) {

        this.orderId = nextId++;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderAmount = orderAmount;
        this.status = status;
    }

    // Getter - Setter

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(float orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Nhập dữ liệu
    public void inputData(Scanner scanner) {

        // Customer name
        while (true) {
            System.out.print("Nhập tên khách hàng (6-100 ký tự): ");
            String name = scanner.nextLine().trim();

            if (name.length() >= 6 && name.length() <= 100) {
                this.customerName = name;
                break;
            }

            System.out.println("Tên phải từ 6 đến 100 ký tự!");
        }

        // Phone number
        while (true) {
            System.out.print("Nhập số điện thoại: ");
            String phone = scanner.nextLine().trim();

            // SĐT Việt Nam: 0 + 9 số hoặc +84 + 9 số
            if (phone.matches("^(0[3|5|7|8|9][0-9]{8}|\\+84[3|5|7|8|9][0-9]{8})$")) {
                this.phoneNumber = phone;
                break;
            }

            System.out.println("Số điện thoại không hợp lệ!");
        }

        // Address
        while (true) {
            System.out.print("Nhập địa chỉ giao hàng: ");
            String address = scanner.nextLine().trim();

            if (!address.isEmpty()) {
                this.address = address;
                break;
            }

            System.out.println("Địa chỉ không được để trống!");
        }

        // Order amount
        while (true) {
            try {
                System.out.print("Nhập giá trị đơn hàng: ");
                float amount = Float.parseFloat(scanner.nextLine());

                if (amount > 0) {
                    this.orderAmount = amount;
                    break;
                }

                System.out.println("Giá trị đơn hàng phải > 0!");

            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số hợp lệ!");
            }
        }

        // Mặc định Pending
        this.status = "Pending";
    }

    @Override
    public String toString() {
        return String.format(
                "Order ID: %d | Khách hàng: %s | SĐT: %s | Địa chỉ: %s | Giá trị: %.2f | Trạng thái: %s",
                orderId,
                customerName,
                phoneNumber,
                address,
                orderAmount,
                status
        );
    }
}
