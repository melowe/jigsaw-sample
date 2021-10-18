package org.acme.core.internal;

import org.acme.core.SomeBusinessObject;
import org.acme.gateway.Gateway;

import java.util.Objects;

public class SomeBusinessObjectImpl implements SomeBusinessObject {

    private final Gateway gateway;

    public SomeBusinessObjectImpl(Gateway gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public String getGatewayStatus() {
        return gateway.status();
    }
}
