package ngss.store.business.concretes;


import ngss.store.business.abstracts.InvoiceService;
import ngss.store.dataAccess.abstracts.InvoiceDao;
import ngss.store.entities.Invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceManager implements InvoiceService {

    private InvoiceDao invoiceDao;


    @Autowired
    public InvoiceManager(InvoiceDao invoiceDao ) {
        super();
        this.invoiceDao = invoiceDao;

    }

    @Override
    public Invoice add(Invoice invoice){
        return invoiceDao.save(invoice);
    }


    @Override
    public List<Invoice> findAll() {
        return invoiceDao.findAll();
    }

    @Override
    public List<Invoice> getProductsByUserId(String uid) {
        return invoiceDao.getProductsByUserId(uid);
    }

    @Override
    public List<Invoice> getCreatedDatesByUserId(String uid) {
        return invoiceDao.getCreatedDatesByUserId(uid);
    }
}
