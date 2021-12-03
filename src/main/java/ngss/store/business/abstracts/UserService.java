package ngss.store.business.abstracts;

import ngss.store.entities.Invoice;
import ngss.store.entities.InvoiceResponse;
import ngss.store.entities.User;

import java.util.List;

public interface UserService {


    List<User> findAll();

    User add(User user);

    User findByUid(String uid);

    InvoiceResponse shopping(String uid, List<String> pid);

}
