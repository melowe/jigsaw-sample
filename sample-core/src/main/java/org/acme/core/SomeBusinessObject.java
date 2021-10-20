package org.acme.core;

import java.util.ServiceLoader;

public interface SomeBusinessObject {

    String getGatewayStatus();

    static SomeBusinessObject create() {
        return ServiceLoader.load(SomeBusinessObject.class)
                .stream().reduce((l,r) -> {
                    throw new IllegalStateException("Found multiple SomeBusinessObject");
                }).get().get();
    }

}
