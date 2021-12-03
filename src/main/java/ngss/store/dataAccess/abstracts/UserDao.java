package ngss.store.dataAccess.abstracts;

import ngss.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User,String> {

    User findByUid(String uid);

}
