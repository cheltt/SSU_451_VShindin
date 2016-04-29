import java.util.*;

public interface Basket {
    void addProduct(String product, int quantity);
    int removeProduct(String product);
    int updateProductQuantity(String product, int quantity);
    void clear();
    List<String> getProducts();
    int getProductQuantity(String product);
}