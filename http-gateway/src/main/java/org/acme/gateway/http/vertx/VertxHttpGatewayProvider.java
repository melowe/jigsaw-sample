package org.acme.gateway.http.vertx;

import org.acme.gateway.Gateway;
import io.vertx.core.Vertx;

public class VertxHttpGatewayProvider {

    public static Gateway provider() {
        Vertx vertx = Vertx.vertx();
        return new VertxHttpGateway(vertx);
    }
}
