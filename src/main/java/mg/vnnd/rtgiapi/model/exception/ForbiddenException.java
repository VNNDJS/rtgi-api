package mg.vnnd.rtgiapi.model.exception;

import static mg.vnnd.rtgiapi.model.exception.ApiException.ExceptionType.CLIENT_EXCEPTION;

public class ForbiddenException extends ApiException {
  public ForbiddenException(String message) {
    super(CLIENT_EXCEPTION, message);
  }
}
