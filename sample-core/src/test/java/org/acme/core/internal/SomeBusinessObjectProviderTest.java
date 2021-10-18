package org.acme.core.internal;

import org.acme.core.SomeBusinessObject;
import org.acme.gateway.Gateway;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.ServiceLoader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class SomeBusinessObjectProviderTest {

    @Test
    void provider() {
        Gateway mockGateway = mock(Gateway.class);
        ServiceLoader<Gateway> mockServiceLoader = mock(ServiceLoader.class);
        when(mockServiceLoader.findFirst()).thenReturn(Optional.of(mockGateway));

        final SomeBusinessObject someBusinessObject;
        try (var mockedStaticServiceLoader = mockStatic(ServiceLoader.class)) {
            mockedStaticServiceLoader.when(() -> ServiceLoader.load(Gateway.class))
                    .thenReturn(mockServiceLoader);

            someBusinessObject =
                    SomeBusinessObjectProvider.provider();

            mockedStaticServiceLoader.verify(() -> ServiceLoader.load(Gateway.class));
            verify(mockServiceLoader).findFirst();

        }

        assertThat(someBusinessObject)
                .isExactlyInstanceOf(SomeBusinessObjectImpl.class);

        verifyNoMoreInteractions(mockServiceLoader);
        verifyNoInteractions(mockGateway);
    }

    @Test
    void coverageOnlyDefaultConstructor() {
        assertThat(new SomeBusinessObjectProvider()).isNotNull();
    }

}
