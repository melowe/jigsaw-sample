package org.acme.gateway.sample;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleGatewayTest {

    @Test
    void statusAndType() {
        SampleGateway gateway = new SampleGateway();
        assertThat(gateway.status()).isEqualTo("Sample Status");
        assertThat(gateway.type()).isEqualTo("Sample");
    }


}
