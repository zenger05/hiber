package jm.task.core.jdbc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bmw")
public class Bmw extends Car {
    @Column(name = "kalian")
    String kalian;

    public Bmw() {};

    public Bmw(String kalian) {
        this.kalian = kalian;
    }

    public Bmw(String marka, String kalian) {
        super(marka);
        this.kalian = kalian;
    }

    public String getKalian() {
        return kalian;
    }

    public void setKalian(String kalian) {
        this.kalian = kalian;
    }
}
