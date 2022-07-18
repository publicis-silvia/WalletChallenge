package hello.demo.entity;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "Product")
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private double price;

    @Transient
    private Availability available;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Availability getAvailable() {
        return available;
    }

    public void setAvailable(Availability available) {
        this.available = available;
    }
}

