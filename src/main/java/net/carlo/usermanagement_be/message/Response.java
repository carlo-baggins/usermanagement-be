package net.carlo.usermanagement_be.message;

public class Response<T> {
    private T payload;
    private String message;
    private boolean esito;
    private int status;

    public Response(T payload, String message, boolean esito, int status) {
        this.payload = payload;
        this.message = message;
        this.esito = esito;
        this.status = status;
    }

    public T getPayload() {
        return payload;
    }

    public String getMessage() {
        return message;
    }

    public boolean isEsito() {
        return esito;
    }

    public int getStatus() {
        return status;
    }
}
