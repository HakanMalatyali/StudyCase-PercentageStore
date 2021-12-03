package ngss.store.api.controller;

import ngss.store.business.abstracts.InvoiceService;
import ngss.store.entities.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {

    private InvoiceService invoiceService;

    @Autowired
    public InvoicesController(InvoiceService invoiceService) {
        super();
        this.invoiceService = invoiceService;
    }

    @PostMapping("/add")
    public Invoice add(@RequestBody Invoice invoice){
        return invoiceService.add(invoice);
    }


    @GetMapping("/allInvoices")
    public List<Invoice> findAll(){
        return invoiceService.findAll();
    }


    @GetMapping("/getProductsByUserId")
    public List<Invoice> getProductsByUserId(String uid){
        return invoiceService.getProductsByUserId(uid);
    }

    @GetMapping("/getCreatedDatesByUserId")
    public List<Invoice> getCreatedDatesByUserId(String uid){
        return invoiceService.getCreatedDatesByUserId(uid);
    }


}


