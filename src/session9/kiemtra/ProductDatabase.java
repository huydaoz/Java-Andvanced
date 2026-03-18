package session9.kiemtra;
import java.util.ArrayList;
import java.util.List;

class ProductDatabase {
    private static ProductDatabase instance;
    private List<Product> productList;

    private ProductDatabase(){
        productList = new ArrayList<>();
    }
    public static ProductDatabase getInstance(){
        if (instance == null){
            instance = new ProductDatabase();
        }
        return instance;
    }
    public void addProduct(Product p){
        productList.add(p);
    }
    public List<Product> getAllProducts(){
        return productList;
    }
    public Product findById(String id){
        for (Product p : productList){
            if (p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }
    public void deleteProduct(String id){
        productList.removeIf(p -> p.getId().equals(id));
    }
}
