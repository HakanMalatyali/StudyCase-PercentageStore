package ngss.store.business.abstracts;

import ngss.store.entities.Product;
import ngss.store.entities.ProductResponse;



import java.util.List;


public interface ProductService {

    Product add(Product product);

    Product findByPid(String pid);

    List<Product> findByProductType(String productType);

    List<Product> findAll();

    List<Product> getProductsByProductsId(List<String> pid);

    ProductResponse getCostumerAmount(List<String> pid);
}
