package org.acme.gateway;

import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Mockito.*;

public class GatewayTest {

    @Test
    void create() {

        String gatewayType = "Mock";
        Gateway mockGateway = mock(Gateway.class);
        when(mockGateway.type()).thenReturn(gatewayType);

        ServiceLoader<Gateway> serviceLoader = mock(ServiceLoader.class);
        ServiceLoader.Provider<Gateway> serviceLoaderProvider = mock(ServiceLoader.Provider.class);
        when(serviceLoaderProvider.get()).thenReturn(mockGateway);
        when(serviceLoader.stream()).thenReturn(Stream.of(serviceLoaderProvider));

        final Gateway result;
        try(var serviceLoaderMockedStatic = mockStatic(ServiceLoader.class)) {
            serviceLoaderMockedStatic.when(() -> ServiceLoader.load(Gateway.class)).thenReturn(serviceLoader);
            result = Gateway.create(gatewayType);
        }
        assertThat(result).isSameAs(mockGateway);
    }

    @Test
    void createMultipleGatwaysThrows() {

        String gatewayType = "Mock";
        Gateway mockGateway = mock(Gateway.class);
        when(mockGateway.type()).thenReturn(gatewayType);

        ServiceLoader<Gateway> serviceLoader = mock(ServiceLoader.class);
        ServiceLoader.Provider<Gateway> serviceLoaderProvider = mock(ServiceLoader.Provider.class);
        when(serviceLoaderProvider.get()).thenReturn(mockGateway);
        when(serviceLoader.stream()).thenReturn(Stream.of(serviceLoaderProvider,serviceLoaderProvider));

        try(var serviceLoaderMockedStatic = mockStatic(ServiceLoader.class)) {
            serviceLoaderMockedStatic.when(() -> ServiceLoader.load(Gateway.class)).thenReturn(serviceLoader);
            Gateway.create(gatewayType);
            failBecauseExceptionWasNotThrown(IllegalStateException.class);
        } catch (IllegalStateException illegalStateException) {
            assertThat(illegalStateException).hasMessage("Found multiple Gateways of type Mock");
            verify(serviceLoader).stream();
            verifyNoMoreInteractions(serviceLoader);
            verify(mockGateway,times(2)).type();
            verifyNoMoreInteractions(mockGateway);
        }
    }

}
