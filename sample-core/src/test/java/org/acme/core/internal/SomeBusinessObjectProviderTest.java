package org.acme.core.internal;

import org.acme.core.SomeBusinessObject;
import org.acme.gateway.Gateway;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class SomeBusinessObjectProviderTest {

    @Test
    void provider() {

        String gatewayType = "HTTP";

        Gateway mockGateway = mock(Gateway.class);
        when(mockGateway.type()).thenReturn(gatewayType);

        final SomeBusinessObject someBusinessObject;

        try (var mockedStaticGateway = mockStatic(Gateway.class)) {
            mockedStaticGateway.when(() -> Gateway.create(gatewayType)).thenReturn(mockGateway);
            someBusinessObject =
                    SomeBusinessObjectProvider.provider();
            mockedStaticGateway.verify(() -> Gateway.create(gatewayType));
        }

        assertThat(someBusinessObject)
                .isExactlyInstanceOf(SomeBusinessObjectImpl.class);

        verifyNoInteractions(mockGateway);
    }

    @Test
    void coverageOnlyDefaultConstructor() {
        assertThat(new SomeBusinessObjectProvider()).isNotNull();
    }

}
