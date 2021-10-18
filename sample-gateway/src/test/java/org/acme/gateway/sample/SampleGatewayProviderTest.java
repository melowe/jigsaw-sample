package org.acme.gateway.sample;

import org.acme.gateway.Gateway;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SampleGatewayProviderTest {

    @Test
    void provider() {
        Gateway gateway = SampleGatewayProvider.provider();
        assertThat(gateway).isExactlyInstanceOf(SampleGateway.class);
    }

    @Test
    void coverageOnlyEmptyConstructor() {
        assertThat(new SampleGatewayProvider()).isNotNull();
    }

}
