package ngss.store.business.abstracts;

import ngss.store.entities.Invoice;



import java.util.List;

public interface InvoiceService {

    Invoice add(Invoice invoice);

    List<Invoice> findAll();

    List<Invoice> getProductsByUserId(String uid);

    List<Invoice> getCreatedDatesByUserId(String uid);




}
