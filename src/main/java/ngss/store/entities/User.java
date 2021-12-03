package ngss.store.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uid;

    @Column(name = "userName")
    private String userName;

    @Column(name = "cardType")
    private String cardType;

    @Column(name = "insertDate")
    private LocalDateTime insertDate;

    @Column(name = "isAffiliate")
    private boolean isAffiliate;
}
