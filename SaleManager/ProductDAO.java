// Interfaces định nghĩa các phương thức để tao tác với sản phẩm
import java.util.List;
public interface ProductDAO {
    List<Product> getAllProducts();
    Product getProductById(int id);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int id);
}
