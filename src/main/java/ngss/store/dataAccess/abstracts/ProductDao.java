package ngss.store.dataAccess.abstracts;


import ngss.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product,String> {

    Product findByPid(String pid);
    List<Product> findByProductType(String productType);

    @Query(value = "SELECT * FROM products WHERE pid IN (:pid)", nativeQuery = true)
    List<Product> getProductsByProductsId(List<String> pid);






}
