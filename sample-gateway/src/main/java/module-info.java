module acme.gateway.sample {

    requires acme.gateway.api;

    provides org.acme.gateway.Gateway
            with org.acme.gateway.sample.SampleGatewayProvider;
}
