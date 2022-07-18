package hello.demo.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "Wallet")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double balance;

    @Transient
    private long personId;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }
}
