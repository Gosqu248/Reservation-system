package pl.urban.notification.response;

public record UserResponse(
        Long id,
        String firstname,
        String lastname,
        String email
) {
}
