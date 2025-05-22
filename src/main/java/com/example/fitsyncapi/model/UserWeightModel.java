package com.example.fitsyncapi.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "user_weights")
@IdClass(UserWeightModel.UserWeightId.class)
public class UserWeightModel {

    @Id
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Id
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "weight", nullable = false)
    private double weight;

    public UserWeightModel() {}

    public UserWeightModel(int userId, LocalDate date, double weight) {
        this.userId = userId;
        this.date = date;
        this.weight = weight;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Composite key class
    public static class UserWeightId implements Serializable {
        private int userId;
        private LocalDate date;

        public UserWeightId() {}

        public UserWeightId(int userId, LocalDate date) {
            this.userId = userId;
            this.date = date;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof UserWeightId)) return false;
            UserWeightId that = (UserWeightId) o;
            return userId == that.userId && Objects.equals(date, that.date);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, date);
        }
    }
}
