package session5.kiemtradaugio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductManager {
    private List<Product> products = new ArrayList<>();

    // CREATE
    public void addProduct(Product product) throws InvalidProductException {
        boolean exists = products.stream()
                .anyMatch(p -> p.getId() == product.getId());

        if (exists) {
            throw new InvalidProductException("ID đã tồn tại!");
        }
        products.add(product);
    }

    // READ
    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }

        System.out.printf("%-5s %-15s %-10s %-10s %-15s\n",
                "ID", "Name", "Price", "Quantity", "Category");

        products.forEach(p -> System.out.printf(
                "%-5d %-15s %-10.2f %-10d %-15s\n",
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getQuantity(),
                p.getCategory()
        ));
    }

    // UPDATE
    public void updateQuantity(int id, int newQuantity) throws InvalidProductException {
        Optional<Product> optionalProduct =
                products.stream()
                        .filter(p -> p.getId() == id)
                        .findFirst();

        if (!optionalProduct.isPresent()) {
            throw new InvalidProductException("Không tìm thấy sản phẩm!");
        }
        optionalProduct.get().setQuantity(newQuantity);
    }

    // DELETE
    public void removeOutOfStock() {
        products.removeIf(p -> p.getQuantity() == 0);
    }
}