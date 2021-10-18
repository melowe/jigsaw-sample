package org.acme.app;

import org.acme.core.SomeBusinessObject;

import java.util.ServiceLoader;

public class Main {

    public static void main(String... args) {
        SomeBusinessObject someBusinessService = ServiceLoader.load(SomeBusinessObject.class).findFirst().get();

        System.out.printf("SomeBusinessObject returned %s",someBusinessService.getGatewayStatus());
        System.out.println();
    }

}
