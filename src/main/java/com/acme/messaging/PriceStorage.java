package com.acme.messaging;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class PriceStorage {

    @Incoming("prices")
    @ActivateRequestContext
    Uni<Void> store(int priceInUsd) {
        Price price = new Price();
        price.value = priceInUsd;
        return Panache.withTransaction(price::persist)
                .replaceWithVoid();

    }

}