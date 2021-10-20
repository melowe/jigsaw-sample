package org.acme.gateway.http.vertx;

import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import io.vertx.junit5.VertxExtension;

@ExtendWith(VertxExtension.class)
class VertxHttpGatewayTest {

    private VertxHttpGateway vertxHttpGateway;

    @BeforeEach
    void beforeTest(Vertx vertx) {
        vertxHttpGateway = new VertxHttpGateway(vertx);
    }

    @AfterEach
    void afterTest(Vertx vertx) {
        vertx.close().result();
    }


    @Test
    void status(VertxTestContext testContext) throws Exception {
        testContext.succeedingThenComplete();
        String outcome = vertxHttpGateway.status();
        testContext.completeNow();
        assertThat(outcome).isEqualTo("Good");
    }

    @Disabled
    @Test
    void statusUnhappy(VertxTestContext testContext) throws Exception {
        testContext.failingThenComplete();
        String outcome = vertxHttpGateway.status();
        testContext.failNow(new Exception("OUCH"));
        assertThat(outcome).isEqualTo("Bad");
    }

}
