package ngss.store.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String iid;


    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @Column(name = "amount")
    private double amount;

    @Column(name = "discount")
    private double discount;

    @Column(name = "lastAmount")
    private double lastAmount;

    @OneToOne
    @JoinColumn(name = "uid")
    private User user;




}
