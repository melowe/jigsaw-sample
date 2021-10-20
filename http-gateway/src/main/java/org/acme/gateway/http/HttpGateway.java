package org.acme.gateway.http;

import org.acme.gateway.Gateway;

public interface HttpGateway extends Gateway {
    @Override
    default String type() {
        return "HTTP";
    }
}
