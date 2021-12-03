package ngss.store.business.concretes;

import ngss.store.business.abstracts.ProductService;
import ngss.store.dataAccess.abstracts.ProductDao;

import ngss.store.entities.Product;
import ngss.store.entities.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class ProductManager implements ProductService {

    private ProductDao productDao;



    @Autowired
    public ProductManager(ProductDao productDao ){
        super();
        this.productDao = productDao;


    }

    @Override
    public Product add(Product product) {
        return productDao.save(product);
    }

    @Override
    public Product findByPid(String pid) {
        return productDao.findByPid(pid);
    }

    @Override
    public List<Product> findByProductType(String productType) {
        return productDao.findByProductType(productType);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public List<Product> getProductsByProductsId(List<String> pid) {

        return productDao.getProductsByProductsId(pid);

    }

    public ProductResponse getCostumerAmount(List<String> pid){
        List<Product> products = getProductsByProductsId(pid);
        double balance  = products.stream().mapToDouble(Product::getProductAmount).sum();
        double phoneBalance = products.stream().filter(t -> t.getProductType().equals("PHONE")).mapToDouble(Product :: getProductAmount).sum();

        return ProductResponse.builder().phoneAmount(phoneBalance).amount(balance).build();
    }


}
