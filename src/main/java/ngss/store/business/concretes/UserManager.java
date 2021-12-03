package ngss.store.business.concretes;

import ngss.store.business.abstracts.UserService;

import ngss.store.dataAccess.abstracts.UserDao;
import ngss.store.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserManager implements UserService {

    private UserDao userDao;
    private final ProductManager productManager;
    private final InvoiceManager invoiceManager;

    @Autowired
    public UserManager(UserDao userDao,ProductManager productManager,InvoiceManager invoiceManager) {
        super();
        this.userDao = userDao;
        this.productManager = productManager;
        this.invoiceManager = invoiceManager;
    }

    @Override
    public User add(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByUid(String uid) {

        return userDao.findByUid(uid);
    }


    public InvoiceResponse shopping(String uid, List<String> pid){
        List<Product> products = productManager.getProductsByProductsId(pid);
        ProductResponse productResponse = productManager.getCostumerAmount(pid);
        User user = findByUid(uid);

        LocalDateTime userCreatedAccount = user.getInsertDate();
        LocalDateTime userInvoiceTime = LocalDateTime.now();

        long daysBetween = Duration.between(userCreatedAccount,userInvoiceTime).toDays();
        double amountPhones = productResponse.getPhoneAmount();
        double amountAllProducts = productResponse.getAmount();
        double ifPhoneExists = amountAllProducts - amountPhones;

        double totalAmount  = products.stream().mapToDouble(Product::getProductAmount).sum();
        double lastAmount = 0;
        double discount = 0;
        int minus5Calculator = 0;
        int minus5Result = 0;


        if(user.getCardType().equals("GOLD")){
            if(ifPhoneExists == 0.0){
                lastAmount = amountPhones;
                if(lastAmount > 200){
                    minus5Calculator = (int) (lastAmount / 200);
                    minus5Result = minus5Calculator * 5;
                    lastAmount = lastAmount - minus5Result;
                }
            discount =  minus5Result;
            }
            else if(ifPhoneExists > 0){
                double withoutPhonePercent =  ifPhoneExists * 0.30;
                ifPhoneExists = ifPhoneExists - withoutPhonePercent;
                lastAmount = ifPhoneExists + amountPhones;

                if(lastAmount > 200){
                    minus5Calculator = (int) (lastAmount / 200);
                    minus5Result = minus5Calculator * 5;
                    lastAmount = lastAmount - minus5Result;
                }
            discount = withoutPhonePercent + minus5Result;

            }
        }
        else if(user.getCardType().equals("SILVER")){
            if(ifPhoneExists == 0.0){
                lastAmount = amountPhones;
                if(lastAmount > 200){
                    minus5Calculator = (int) (lastAmount / 200);
                    minus5Result = minus5Calculator * 5;
                    lastAmount = lastAmount - minus5Result;
                }
            discount = minus5Result;
            }
            else if(ifPhoneExists > 0){
                double withoutPhonePercent =  ifPhoneExists * 0.20;
                ifPhoneExists = ifPhoneExists - withoutPhonePercent;
                lastAmount = ifPhoneExists + amountPhones;

                if(lastAmount > 200){
                    minus5Calculator = (int) (lastAmount / 200);
                    minus5Result = minus5Calculator * 5;
                    lastAmount = lastAmount - minus5Result;
                }
                discount = withoutPhonePercent + minus5Result;
            }
        }
        else if(user.isAffiliate() && user.getCardType().equals("")){
            if(ifPhoneExists == 0.0){
                lastAmount = amountPhones;
                if(lastAmount > 200){
                    minus5Calculator = (int) (lastAmount / 200);
                    minus5Result = minus5Calculator * 5;
                    lastAmount = lastAmount - minus5Result;
                }
                discount =  minus5Result;
            }
            else if(ifPhoneExists > 0){
                double withoutPhonePercent =  ifPhoneExists * 0.10;
                ifPhoneExists = ifPhoneExists - withoutPhonePercent;
                lastAmount = ifPhoneExists + amountPhones;

                if(lastAmount > 200){
                    minus5Calculator = (int) (lastAmount / 200);
                    minus5Result = minus5Calculator * 5;
                    lastAmount = lastAmount - minus5Result;
                }
                discount = withoutPhonePercent + minus5Result;

            }
        }
        else if(!user.isAffiliate() && user.getCardType().equals("") && (daysBetween > 730)){
            if(ifPhoneExists == 0.0){
                lastAmount = amountPhones;
                if(lastAmount > 200){
                    minus5Calculator = (int) (lastAmount / 200);
                    minus5Result = minus5Calculator * 5;
                    lastAmount = lastAmount - minus5Result;
                }
                discount =  minus5Result;
            }
            else if(ifPhoneExists > 0){
                double withoutPhonePercent =  ifPhoneExists * 0.05;
                ifPhoneExists = ifPhoneExists - withoutPhonePercent;
                lastAmount = ifPhoneExists + amountPhones;

                if(lastAmount > 200){
                    minus5Calculator = (int) (lastAmount / 200);
                    minus5Result = minus5Calculator * 5;
                    lastAmount = lastAmount - minus5Result;
                }
                discount = withoutPhonePercent + minus5Result;
            }
        }
        else{
            lastAmount = totalAmount;
            if(lastAmount > 200){
                minus5Calculator = (int) (lastAmount / 200);
                minus5Result = minus5Calculator * 5;
                lastAmount = lastAmount - minus5Result;
            }
            discount = minus5Result;
            lastAmount = totalAmount - minus5Result;
        }


        double lastAmountWithSetScale = BigDecimal.valueOf(lastAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
        double discountWithSetScale = BigDecimal.valueOf(discount).setScale(2, RoundingMode.FLOOR).doubleValue();
        double AmountWithSetScale = BigDecimal.valueOf(productResponse.getAmount()).setScale(2, RoundingMode.HALF_UP).doubleValue();


        Invoice invoice = Invoice.builder().discount(discountWithSetScale).lastAmount(lastAmountWithSetScale).createdDate(LocalDateTime.now()).user(user).amount(AmountWithSetScale).build();

        invoiceManager.add(invoice);

        return InvoiceResponse.builder().products(products).invoice(invoice).build();


    }




}
