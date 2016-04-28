import java.util.*;

public class BasketImplement implements Basket {

    HashMap<String, Integer> shoppingBasket = new HashMap<>();

    // clear a basket
    public void clear() {
        shoppingBasket.clear();
    }

    // add a product into a basket
    public void addProduct(String product, int quantity) {
        if (shoppingBasket.containsKey(product) != true) {
            shoppingBasket.put(product, quantity);
        } else {
            shoppingBasket.put(product, shoppingBasket.get(product) + quantity);
        }
    }

    // get rid of  a product into a basket
    public int  removeProduct(String product) {
        Collection<String> collection = shoppingBasket.keySet();

        for (String key : collection) {
            if (key != null) {
                if (product.equals(key) == true) {
                 //  нашли наше значение равное ключу
                    shoppingBasket.remove(product);
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }


    // update an quantity of products
    public int updateProductQuantity(String product, int quantity) {
        Collection<String> collection = shoppingBasket.keySet();

        for (String key : collection) {
            if (key != null) {
                if (product.equals(key) == true) {
                    //  нашли наше значение равное ключу
                    // кладутся новые значения
                    shoppingBasket.put(product, quantity);
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }

    // get products
    public List<String> getProducts() {
        ArrayList<String> keysOfPoducts = new ArrayList<String>(shoppingBasket.keySet());
        return keysOfPoducts ;
    }

    // get an quantity of products
    public int getProductQuantity(String product) {
        //
        try {
            return shoppingBasket.get(product);
        } catch (NullPointerException exception) {
            throw exception;
        }
    }
}