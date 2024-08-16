package mg.vnnd.rtgiapi.model.exception;

import static mg.vnnd.rtgiapi.model.exception.ApiException.ExceptionType.SERVER_EXCEPTION;

public class NotImplementedException extends ApiException {
  public NotImplementedException(String message) {
    super(SERVER_EXCEPTION, message);
  }
}
