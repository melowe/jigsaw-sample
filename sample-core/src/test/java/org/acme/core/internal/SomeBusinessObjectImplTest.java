package org.acme.core.internal;

import org.acme.gateway.Gateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Mockito.*;

class SomeBusinessObjectImplTest {

    private SomeBusinessObjectImpl someBusinessObject;

    private Gateway mockGateway;

    @BeforeEach
    void beforeTest() {
        mockGateway = mock(Gateway.class);
        someBusinessObject = new SomeBusinessObjectImpl(mockGateway);
    }

    @AfterEach
    void afterTest() {
        verifyNoMoreInteractions(mockGateway);
    }

    @Test
    void testStatus() {
        String status = "I LOve Sparrows!!";
        when(mockGateway.status()).thenReturn(status);

        String result = someBusinessObject.getGatewayStatus();

        assertThat(result).isEqualTo(status);

        verify(mockGateway).status();
    }

    @Test
    void testHandleGatewayException() {
        Exception exception = new SomeUnexpectedGatwayException();
        when(mockGateway.status()).thenThrow(exception);
        try {
            someBusinessObject.getGatewayStatus();
            failBecauseExceptionWasNotThrown(SomeUnexpectedGatwayException.class);
        } catch (SomeUnexpectedGatwayException e) {
            verify(mockGateway).status();
        }
    }

    class SomeUnexpectedGatwayException extends RuntimeException {}

}
