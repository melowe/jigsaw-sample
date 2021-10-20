package org.acme.app;

import org.acme.core.SomeBusinessObject;

public class Main {

    public static void main(String... args) {
        SomeBusinessObject someBusinessService = SomeBusinessObject.create();

        System.out.printf("SomeBusinessObject returned %s",someBusinessService.getGatewayStatus());
        System.out.println();

        System.exit(0);
    }

}
