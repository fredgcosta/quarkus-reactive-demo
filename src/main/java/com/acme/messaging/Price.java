package com.acme.messaging;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Price extends PanacheEntity {

    public int value;

}