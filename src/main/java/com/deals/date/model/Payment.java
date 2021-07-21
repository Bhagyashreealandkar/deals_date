package com.deals.date.model;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



//Creating entity class and table payment
@Entity
@Table
public class Payment {



// Creating column paymentId and setting it as the primary key



@SequenceGenerator(name = "paymentIdGenerator", initialValue = 3001, allocationSize = 1)
@GeneratedValue(strategy = GenerationType.AUTO, generator = "paymentIdGenerator")
@Id
private int paymentId;



// Creating column totalAmount
private float totalAmount;



// Creating column email
private String email;




private LocalDate payDate;
// Generating getters and setters



public LocalDate getPayDate() {
return payDate;
}



public void setPayDate(LocalDate d) {
this.payDate = d;
}



public String getEmail() {
return email;
}



public void setEmail(String email) {
this.email = email;
}




public int getPaymentId() {
return paymentId;
}



public void setPaymentId(int paymentId) {
this.paymentId = paymentId;
}



public float getTotalAmount() {
return totalAmount;
}



public void setTotalAmount(float totalAmount) {
this.totalAmount = totalAmount;
}




// Default constructor
public Payment() {
}



// Parameterized constructor
public Payment(int paymentId, float totalAmount, String email) {
super();
this.paymentId = paymentId;
this.totalAmount = totalAmount;
this.email = email;
}



}
