package org.acme.core.internal;

import org.acme.core.SomeBusinessObject;
import org.acme.gateway.Gateway;

import java.util.ServiceLoader;

public class SomeBusinessObjectProvider {

    public static SomeBusinessObject provider() {
        Gateway gateway = ServiceLoader.load(Gateway.class).findFirst().get();
        return new SomeBusinessObjectImpl(gateway);
    }
}
