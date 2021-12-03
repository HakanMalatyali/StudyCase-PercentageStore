package ngss.store.dataAccess.abstracts;



import ngss.store.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceDao extends JpaRepository<Invoice,String> {

    @Query(value = "Select created_date From invoices Where uid IN (:uid)",nativeQuery = true)
    List<Invoice> getCreatedDatesByUserId(String uid);

   @Query(value = "SELECT * FROM invoices WHERE uid IN (:uid)",nativeQuery = true)
    List<Invoice> getProductsByUserId(String uid);





}
