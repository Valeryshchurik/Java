package entity;


import adapters.ClientIntAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "client", schema = "weblabsuper1", catalog = "")
@NamedQueries({
        @NamedQuery(query = "SELECT c FROM ClientEntity c WHERE c.id = :id", name = "interf.ClientEntity.findById"),
        @NamedQuery(query = "SELECT t FROM TourEntity t JOIN OrderEntity o" +
                " ON t.id = o.idtour JOIN ClientEntity c ON c.id = o.idclient" +
                " WHERE c.name = :name", name = "interf.ClientEntity.findClientOrders")
})
public class ClientEntity implements Serializable {
    private int id;
    private String name;
    private Collection<OrderEntity> ordersById;

    @Id
    @Column(name = "id", nullable = false)
    @XmlAttribute
    @XmlID
    @XmlJavaTypeAdapter(type = int.class, value = ClientIntAdapter.class)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "clientByIdclient")
    @XmlElement
    @XmlIDREF
    public Collection<OrderEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrderEntity> ordersById) {
        this.ordersById = ordersById;
    }

    @Override
    public String toString() {
        return "interf.ClientEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ordersById=" + ordersById +
                '}';
    }
}
