package mg.vnnd.rtgiapi.model.exception;

import static mg.vnnd.rtgiapi.model.exception.ApiException.ExceptionType.CLIENT_EXCEPTION;

public class BadRequestException extends ApiException {
  public BadRequestException(String message) {
    super(CLIENT_EXCEPTION, message);
  }
}
