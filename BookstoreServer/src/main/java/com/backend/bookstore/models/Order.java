
package com.backend.bookstore.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;



import lombok.Data;

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "orders")
public class Order implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_fk")
    private List<Book> books;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "status")
    private String status;
}
