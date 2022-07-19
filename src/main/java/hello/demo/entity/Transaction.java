package hello.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Transaction")
@NoArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "walletId")
    private Wallet walletId;

    @OneToOne
    @JoinColumn(name = "productId")
    private Product productId;

    private TransactionType transactionType;

    @Temporal(TemporalType.TIMESTAMP)
    Date purchaseDateTime;
}
