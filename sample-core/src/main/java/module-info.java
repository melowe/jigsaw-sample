module acme.sample {
    requires acme.gateway.api;

    exports org.acme.core;

    uses org.acme.core.SomeBusinessObject;

    provides org.acme.core.SomeBusinessObject with
            org.acme.core.internal.SomeBusinessObjectProvider;
}
