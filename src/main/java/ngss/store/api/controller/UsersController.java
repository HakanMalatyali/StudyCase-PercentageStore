package ngss.store.api.controller;


import ngss.store.business.abstracts.UserService;
import ngss.store.entities.InvoiceResponse;
import ngss.store.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public User add(@RequestBody User user){
        return userService.add(user);
    }

    @GetMapping("/findAll")
    public List<User> findAllUser(){
        return userService.findAll();
    }

    @GetMapping("/findByUid")
    public User findByUid(@RequestParam String uid){
        return userService.findByUid(uid);
    }

    @PostMapping("/shopping")
    public InvoiceResponse shopping(@RequestParam String uid, @RequestParam List<String> pid){
        return userService.shopping(uid,pid);
    }


}
