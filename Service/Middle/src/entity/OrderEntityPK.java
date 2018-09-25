package entity;

import adapters.IntAdapter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Objects;

public class OrderEntityPK implements Serializable {
    private int idclient;
    private int idtour;

    @Column(name = "idclient", nullable = false)
    @Id
    @XmlAttribute
    @XmlID
    @XmlJavaTypeAdapter(type = int.class, value = IntAdapter.class)

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    @Column(name = "idtour", nullable = false)
    @Id
    @XmlAttribute
    @XmlID
    @XmlJavaTypeAdapter(type = int.class, value = IntAdapter.class)

    public int getIdtour() {
        return idtour;
    }

    public void setIdtour(int idtour) {
        this.idtour = idtour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntityPK that = (OrderEntityPK) o;
        return idclient == that.idclient &&
                idtour == that.idtour;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idclient, idtour);
    }
}
