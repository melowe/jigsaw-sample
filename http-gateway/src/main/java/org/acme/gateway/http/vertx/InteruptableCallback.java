package org.acme.gateway.http.vertx;

@FunctionalInterface
public interface InteruptableCallback<T> {

    T execute() throws InterruptedException;

    static <T> T execute(InteruptableCallback<T> interuptableCallback) {
        try {
            return interuptableCallback.execute();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
