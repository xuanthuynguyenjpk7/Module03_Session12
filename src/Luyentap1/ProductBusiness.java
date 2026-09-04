package Luyentap1;

import Luyentap1.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ProductBusiness {

    private final List<Product> products = new ArrayList<>();

    // Thêm sản phẩm
    public void addProduct(Scanner scanner) {

        Product product = new Product();

        product.inputData(scanner);

        // Kiểm tra tên không trùng
        for (Product p : products) {
            if (p.getProductName().equalsIgnoreCase(product.getProductName())) {
                System.out.println("Tên sản phẩm đã tồn tại!");
                return;
            }
        }

        products.add(product);

        System.out.println("Thêm sản phẩm thành công!");
    }

    // Hiển thị danh sách
    public void displayProducts() {

        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm đang trống!");
            return;
        }

        System.out.println("\n========== DANH SÁCH SẢN PHẨM ==========");

        for (Product product : products) {
            System.out.println(product);
        }
    }

    // Tìm sản phẩm theo ID
    private Product findById(int id) {

        for (Product product : products) {
            if (product.getProductId() == id) {
                return product;
            }
        }

        return null;
    }

    // Cập nhật sản phẩm
    public void updateProduct(Scanner scanner) {

        try {
            System.out.print("Nhập mã sản phẩm cần cập nhật: ");
            int id = Integer.parseInt(scanner.nextLine());

            Product product = findById(id);

            if (product == null) {
                System.out.println("Không tìm thấy sản phẩm có mã " + id);
                return;
            }

            System.out.println("Thông tin hiện tại:");
            System.out.println(product);

            // Cập nhật tên
            while (true) {
                System.out.print("Nhập tên mới (10-50 ký tự): ");
                String name = scanner.nextLine().trim();

                if (name.length() < 10 || name.length() > 50) {
                    System.out.println("Tên phải từ 10 đến 50 ký tự!");
                    continue;
                }

                boolean duplicate = false;

                for (Product p : products) {
                    if (p != product &&
                            p.getProductName().equalsIgnoreCase(name)) {
                        duplicate = true;
                        break;
                    }
                }

                if (duplicate) {
                    System.out.println("Tên sản phẩm đã tồn tại!");
                } else {
                    product.setProductName(name);
                    break;
                }
            }

            // Cập nhật giá
            while (true) {
                try {
                    System.out.print("Nhập giá mới: ");
                    float price = Float.parseFloat(scanner.nextLine());

                    if (price > 0) {
                        product.setPrice(price);
                        break;
                    }

                    System.out.println("Giá phải lớn hơn 0!");

                } catch (NumberFormatException e) {
                    System.out.println("Giá phải là số!");
                }
            }

            // Cập nhật category
            while (true) {
                System.out.print("Nhập danh mục mới: ");
                String category = scanner.nextLine().trim();

                if (category.length() <= 200) {
                    product.setCategory(category);
                    break;
                }

                System.out.println("Danh mục tối đa 200 ký tự!");
            }

            // Cập nhật quantity
            while (true) {
                try {
                    System.out.print("Nhập số lượng mới: ");
                    int quantity = Integer.parseInt(scanner.nextLine());

                    if (quantity >= 0) {
                        product.setQuantity(quantity);
                        break;
                    }

                    System.out.println("Số lượng phải >= 0!");

                } catch (NumberFormatException e) {
                    System.out.println("Số lượng phải là số nguyên!");
                }
            }

            System.out.println("Cập nhật sản phẩm thành công!");

        } catch (NumberFormatException e) {
            System.out.println("Mã sản phẩm phải là số nguyên!");
        }
    }

    // Xóa sản phẩm
    public void deleteProduct(Scanner scanner) {

        try {
            System.out.print("Nhập mã sản phẩm cần xóa: ");
            int id = Integer.parseInt(scanner.nextLine());

            Product product = findById(id);

            if (product == null) {
                System.out.println("Không tìm thấy sản phẩm có mã " + id);
                return;
            }

            products.remove(product);

            System.out.println("Xóa sản phẩm thành công!");

        } catch (NumberFormatException e) {
            System.out.println("Mã sản phẩm phải là số nguyên!");
        }
    }

    // Tìm kiếm theo tên
    public void searchByName(Scanner scanner) {

        System.out.print("Nhập từ khóa cần tìm: ");
        String keyword = scanner.nextLine().toLowerCase();

        boolean found = false;

        for (Product product : products) {

            if (product.getProductName()
                    .toLowerCase()
                    .contains(keyword)) {

                System.out.println(product);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sản phẩm phù hợp!");
        }
    }

    // Sắp xếp giá tăng dần
    public void sortByPriceAsc() {

        products.sort(Comparator.comparing(Product::getPrice));

        System.out.println("Đã sắp xếp theo giá tăng dần!");

        displayProducts();
    }

    // Sắp xếp số lượng giảm dần
    public void sortByQuantityDesc() {

        products.sort(
                Comparator.comparing(Product::getQuantity).reversed()
        );

        System.out.println("Đã sắp xếp theo số lượng giảm dần!");

        displayProducts();
    }
}