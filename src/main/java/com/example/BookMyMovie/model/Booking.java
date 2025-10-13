package com.example.BookMyMovie.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "booking", schema = "public")
@EntityListeners(AuditingEntityListener.class)
public class Booking {

    public enum Status
    {
        CONFIRMED,
        CANCELLED,
        PENDING
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;


    @ManyToOne
    @JoinColumn(name = "user_profile_user_id")
    private UserProfile userProfile;

    @ManyToOne
    @JoinColumn(name = "show_show_id")
    private Show show;

    @Column(columnDefinition = "jsonb")
    private List<Integer> seats;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private Status status;
    private boolean isPaid;


    public Booking(){}

    public Booking(UserProfile userProfile, Integer bookingId, List<Integer> seats, Double amount, Status status, Boolean isPaid) {
        this.userProfile = userProfile;
        this.bookingId = bookingId;
        this.seats = seats;
        this.amount = amount;
        this.status = status;
        this.isPaid = isPaid;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public List<Integer> getSeats() {return seats;}

    public void setSeats(List<Integer> seats) {this.seats = seats;}
}
