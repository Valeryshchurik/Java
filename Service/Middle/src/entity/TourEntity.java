package entity;


import adapters.DateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "tour", schema = "weblabsuper1", catalog = "")
@XmlRootElement(name ="Tour")
@XmlAccessorType(XmlAccessType.FIELD)
public class TourEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @XmlAttribute
    private int id;

    @XmlAttribute
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @XmlAttribute
    @Column(name = "country", nullable = false, length = 45)
    private String country;

    @XmlAttribute
    @XmlJavaTypeAdapter(DateAdapter.class)
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @XmlAttribute
    @XmlJavaTypeAdapter(DateAdapter.class)
    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @XmlAttribute
    @Column(name = "type", nullable = false)
    private String type;

    @XmlAttribute
    @Column(name = "price", nullable = false)
    private int price;

    @XmlAttribute
    @Column(name = "burning", nullable = false)
    private byte burning;

    @XmlAttribute
    @Column(name = "discounts", nullable = true, precision = 2)
    private BigDecimal discounts;

    @OneToMany(mappedBy = "tourByIdtour")
    @XmlIDREF
    @XmlElement
    private Collection<OrderEntity> ordersById;
//
//    @Id
//    @Column(name = "id", nullable = false)
//    @XmlAttribute
//    @XmlID
//    @XmlJavaTypeAdapter(type = int.class, value = IntAdapter.class)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


//    @Convert(converter = LocalDateConverter.class)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


//    @Convert(converter = LocalDateConverter.class)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public byte getBurning() {
        return burning;
    }

    public void setBurning(byte burning) {
        this.burning = burning;
    }

    public BigDecimal getDiscounts() {
        return discounts;
    }

    public void setDiscounts(BigDecimal discounts) {
        this.discounts = discounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourEntity that = (TourEntity) o;
        return id == that.id &&
                price == that.price &&
                burning == that.burning &&
                Objects.equals(name, that.name) &&
                Objects.equals(country, that.country) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(type, that.type) &&
                Objects.equals(discounts, that.discounts);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, country, startDate, endDate, type, price, burning, discounts);
    }

    @Override
    public String toString() {
        return "interf.TourEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", burning=" + burning +
                ", discounts=" + discounts +
                '}';
    }


    public Collection<OrderEntity> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<OrderEntity> ordersById) {
        this.ordersById = ordersById;
    }
}
