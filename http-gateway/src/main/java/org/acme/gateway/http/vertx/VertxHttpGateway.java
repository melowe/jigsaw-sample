package org.acme.gateway.http.vertx;

import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import org.acme.gateway.http.HttpGateway;

import java.util.Objects;
import java.util.concurrent.SynchronousQueue;

public class VertxHttpGateway implements HttpGateway {

    private final Vertx vertx;

    public VertxHttpGateway(Vertx vertx) {
        this.vertx = Objects.requireNonNull(vertx);
    }

    @Override
    public String status() {
        WebClient client = WebClient.create(vertx);

        SynchronousQueue<String> responseQueue = new SynchronousQueue();
        client
                .get(80, "www.google.com", "/")
                .send()
                .onSuccess(response -> {
                    responseQueue.offer("Good");
                })
                .onFailure(err -> {
                    responseQueue.offer("Bad");
                 }
                );

        return InteruptableCallback.execute(() -> responseQueue.take());
    }

}
