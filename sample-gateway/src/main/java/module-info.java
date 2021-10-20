module acme.gateway.sample {

    requires acme.gateway.api;
    requires io.vertx.core;
    requires io.vertx.web.client;

    provides org.acme.gateway.Gateway
            with org.acme.gateway.sample.SampleGatewayProvider;
}
