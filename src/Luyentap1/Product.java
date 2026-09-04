package Luyentap1;

import java.util.Scanner;

public class Product {

    // ID tự tăng
    private static int nextId = 1;

    private int productId;
    private String productName;
    private float price;
    private String category;
    private int quantity;

    // Constructor không tham số
    public Product() {
        this.productId = nextId++;
    }

    // Constructor đầy đủ tham số
    public Product(int productId, String productName, float price,
                   String category, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    // Getter / Setter
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Nhập dữ liệu
    public void inputData(Scanner scanner) {

        // Nhập tên sản phẩm
        while (true) {
            System.out.print("Nhập tên sản phẩm (10-50 ký tự): ");
            String name = scanner.nextLine().trim();

            if (name.length() >= 10 && name.length() <= 50) {
                this.productName = name;
                break;
            }

            System.out.println("Tên sản phẩm phải từ 10 đến 50 ký tự!");
        }

        // Nhập giá
        while (true) {
            try {
                System.out.print("Nhập giá sản phẩm: ");
                float price = Float.parseFloat(scanner.nextLine());

                if (price > 0) {
                    this.price = price;
                    break;
                }

                System.out.println("Giá phải lớn hơn 0!");

            } catch (NumberFormatException e) {
                System.out.println("Giá phải là số thực!");
            }
        }

        // Nhập category
        while (true) {
            System.out.print("Nhập danh mục sản phẩm: ");
            String category = scanner.nextLine().trim();

            if (category.length() <= 200) {
                this.category = category;
                break;
            }

            System.out.println("Danh mục không được vượt quá 200 ký tự!");
        }

        // Nhập quantity
        while (true) {
            try {
                System.out.print("Nhập số lượng tồn kho: ");
                int quantity = Integer.parseInt(scanner.nextLine());

                if (quantity >= 0) {
                    this.quantity = quantity;
                    break;
                }

                System.out.println("Số lượng phải >= 0!");

            } catch (NumberFormatException e) {
                System.out.println("Số lượng phải là số nguyên!");
            }
        }
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Tên: %s | Giá: %.2f | Danh mục: %s | Số lượng: %d",
                productId,
                productName,
                price,
                category,
                quantity
        );
    }
}
