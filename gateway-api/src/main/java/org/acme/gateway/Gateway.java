package org.acme.gateway;

import java.util.ServiceLoader;

public interface Gateway {

    String status();

    String type();

    static Gateway create(String type) {

        return ServiceLoader.load(Gateway.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .filter(g -> g.type().equals(type))
                .reduce((l,r) -> {
                    throw new IllegalStateException("Found multiple Gateways of type "+ type);
                })
                .get();
    }


}
