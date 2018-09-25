package entity;


import adapters.IntAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "voucher", schema = "weblabsuper1", catalog = "")
@NamedQueries({
        @NamedQuery(query = "SELECT o FROM OrderEntity o WHERE o.idclient = :id", name = "interf.OrderEntity.findById")})
@IdClass(OrderEntityPK.class)
public class OrderEntity implements Serializable {
    private int idclient;
    private int idtour;
    private ClientEntity clientByIdclient;
    private TourEntity tourByIdtour;

    @Override
    public String toString() {
        return "interf.OrderEntity{" +
                "idclient=" + idclient +
                ", idtour=" + idtour +
                ", clientByIdclient=" + clientByIdclient +
                ", tourByIdtour=" + tourByIdtour +
                '}';
    }

    @Id
    @XmlAttribute
    @XmlID
    @XmlJavaTypeAdapter(type = int.class, value = IntAdapter.class)

    @Column(name = "idclient", nullable = false)
    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    @Id
    @XmlAttribute
    @XmlID
    @XmlJavaTypeAdapter(type = int.class, value = IntAdapter.class)
    @Column(name = "idtour", nullable = false)
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
        OrderEntity that = (OrderEntity) o;
        return idclient == that.idclient &&
                idtour == that.idtour;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idclient, idtour);
    }

    @ManyToOne
    @XmlIDREF
    @XmlElement
    @JoinColumn(name = "idclient", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    public ClientEntity getClientByIdclient() {
        return clientByIdclient;
    }

    public void setClientByIdclient(ClientEntity clientByIdclient) {
        this.clientByIdclient = clientByIdclient;
    }

    @ManyToOne
    @XmlIDREF
    @XmlElement
    @JoinColumn(name = "idtour", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
    public TourEntity getTourByIdtour() {
        return tourByIdtour;
    }

    public void setTourByIdtour(TourEntity tourByIdtour) {
        this.tourByIdtour = tourByIdtour;
    }
}
