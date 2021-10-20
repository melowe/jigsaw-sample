package org.acme.gateway.sample;

import org.acme.gateway.Gateway;

public class SampleGateway implements Gateway {
    @Override
    public String status() {
        return "Sample Status";
    }

    @Override
    public String type() {
        return "Sample";
    }

}
