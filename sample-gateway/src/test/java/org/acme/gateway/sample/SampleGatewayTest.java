package org.acme.gateway.sample;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleGatewayTest {

    @Test
    void status() {
        SampleGateway gateway = new SampleGateway();
        assertThat(gateway.status()).isEqualTo("Sample Status");
    }

}
