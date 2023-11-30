package com.acme.messaging;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.resteasy.reactive.RestStreamElementType;

import java.util.List;

@Path("/prices")
public class PriceResource {

    // Inject our Book channel
    @Inject
    @Channel("prices")
    Multi<Integer> prices;

    @GET
    public Uni<List<Price>> getAllPrices() {
        return Price.listAll();
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestStreamElementType(MediaType.APPLICATION_JSON)
    @Path("stream")
    public Multi<Integer> streamPrices() {
        Log.infof("Called on %s", Thread.currentThread());
        return prices;
    }
}