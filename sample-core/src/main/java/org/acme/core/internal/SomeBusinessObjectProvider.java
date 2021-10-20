package org.acme.core.internal;

import org.acme.core.SomeBusinessObject;
import org.acme.gateway.Gateway;

public class SomeBusinessObjectProvider {

    public static SomeBusinessObject provider() {
        Gateway gateway = Gateway.create("HTTP");
        return new SomeBusinessObjectImpl(gateway);
    }
}
