package jm.task.core.jdbc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "audi")
public class Audi extends Car{
    @Column(name = "dvaigatel")
    String dvigatel;

    public Audi() {
    }

    public Audi(String marka, String dvigatel) {
        super(marka);
        this.dvigatel = dvigatel;
    }

    public Audi(String marka) {
        super(marka);
    }

    public String getDvigatel() {
        return dvigatel;
    }

    public void setDvigatel(String dvigatel) {
        this.dvigatel = dvigatel;
    }
}
