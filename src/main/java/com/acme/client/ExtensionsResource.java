package com.acme.client;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.CompletionStage;

@Path("/extension")
public class ExtensionsResource {

    private static final Logger LOGGER = Logger.getLogger(ExtensionsResource.class.getName());

    @RestClient
    ExtensionsService extensionsService;


    @GET
    @Path("/id/{id}")
    @Blocking
    public Set<Extension> id(String id) {
        LOGGER.infof("Executando em %s", Thread.currentThread());
        return extensionsService.getById(id);
    }

    @GET
    @Path("/id-async/{id}")
    public CompletionStage<Set<Extension>> idAsync(String id) {
        LOGGER.infof("Executando em %s", Thread.currentThread());
        return extensionsService.getByIdAsync(id);
    }

    @GET
    @Path("/id-uni/{id}")
    public Uni<Set<Extension>> idUni(String id) {
        LOGGER.infof("Executando em %s", Thread.currentThread());
        return extensionsService.getByIdAsUni(id)
                .onFailure()
                .retry()
                .withBackOff(Duration.ofSeconds(1), Duration.ofSeconds(10))
                .atMost(10);
    }
}