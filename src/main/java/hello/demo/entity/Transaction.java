package hello.demo.entity;

import javax.persistence.*;
import java.util.Date;

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Wallet wallet;

    private Product product;

    private TransactionType transactionType;

    @Temporal(TemporalType.TIMESTAMP)
    Date purchaseDateTime;
}
