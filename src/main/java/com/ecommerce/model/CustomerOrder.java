package com.ecommerce.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@RequiredArgsConstructor
public class CustomerOrder {
    private @Id
    @GeneratedValue
    @Getter
    @Setter
    Long id;
    @Getter
    @Setter
    private Long customerId;
    @Getter
    @Setter
    private Double amount;

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomerId(), getAmount());
    }

    @Override
    public String toString() {
        return "CustomerOrder{" + "id=" + this.getId() + ", customerId='" + this.getCustomerId() + '\'' + ", amount='" + this.getAmount() + '\'' + '}';
    }

}
