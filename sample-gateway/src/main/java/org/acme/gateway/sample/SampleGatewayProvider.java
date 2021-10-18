package org.acme.gateway.sample;

import org.acme.gateway.Gateway;

public class SampleGatewayProvider {

    public static Gateway provider() {
        return new SampleGateway();
    }
}
