package ksc.ts.exception;

public class InvalidCredentialsException extends AuthenticationException {
    public InvalidCredentialsException() {
        super("유효하지 않은 권한입니다.");
    }
}
