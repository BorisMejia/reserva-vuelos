package api.reserva.common;

public enum Messages {
    RESERVA_CREATE_SUCCESS("Reserva creada correctamente"),
    RESERVA_CREATE_ERROR("Error al crear la reserva");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
