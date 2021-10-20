package org.acme.core.internal;

import org.acme.core.SomeBusinessObject;
import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SomeBusinessObjectTest {

    @Test
    void create() {

        SomeBusinessObject someBusinessObject = mock(SomeBusinessObject.class);
        ServiceLoader<SomeBusinessObject> serviceLoader = mock(ServiceLoader.class);
        ServiceLoader.Provider<SomeBusinessObject> provider = mock(ServiceLoader.Provider.class);
        when(provider.get()).thenReturn(someBusinessObject);
        when(serviceLoader.stream()).thenReturn(Stream.of(provider));

        final SomeBusinessObject result;
        try(var mockStaticServiceLoader = mockStatic(ServiceLoader.class)) {
            mockStaticServiceLoader.when(() -> ServiceLoader.load(SomeBusinessObject.class))
                    .thenReturn(serviceLoader);

            result = SomeBusinessObject.create();
            mockStaticServiceLoader.verify(() -> ServiceLoader.load(SomeBusinessObject.class));
        }
        assertThat(result).isSameAs(someBusinessObject);
        verify(serviceLoader).stream();
        verify(provider).get();
        verifyNoMoreInteractions(serviceLoader,provider);
    }
    @Test
    void createWithMulipleThrowsException() {

        SomeBusinessObject someBusinessObject = mock(SomeBusinessObject.class);
        ServiceLoader<SomeBusinessObject> serviceLoader = mock(ServiceLoader.class);

        ServiceLoader.Provider<SomeBusinessObject> provider = mock(ServiceLoader.Provider.class);
        when(provider.get()).thenReturn(someBusinessObject);
        when(serviceLoader.stream()).thenReturn(Stream.of(provider,provider));

        try(var mockStaticServiceLoader = mockStatic(ServiceLoader.class)) {
            mockStaticServiceLoader.when(() -> ServiceLoader.load(SomeBusinessObject.class))
                    .thenReturn(serviceLoader);

            SomeBusinessObject.create();
            failBecauseExceptionWasNotThrown(IllegalStateException.class);
        } catch (IllegalStateException illegalStateException) {
            assertThat(illegalStateException).hasMessage("Found multiple SomeBusinessObject");
            verify(serviceLoader).stream();
            verifyNoMoreInteractions(serviceLoader,provider);
        }

    }
}
