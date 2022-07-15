package hello.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "person")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthday;

    @OneToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    public String getName()
    {
        return name;
    }

    public void setName(String username)
    {
        this.name = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}