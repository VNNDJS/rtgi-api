package mg.vnnd.rtgiapi.model.exception;

import static mg.vnnd.rtgiapi.model.exception.ApiException.ExceptionType.CLIENT_EXCEPTION;

public class NotFoundException extends ApiException {
  public NotFoundException(String message) {
    super(CLIENT_EXCEPTION, message);
  }
}
