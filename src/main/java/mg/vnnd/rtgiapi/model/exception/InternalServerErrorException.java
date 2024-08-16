package mg.vnnd.rtgiapi.model.exception;

import static mg.vnnd.rtgiapi.model.exception.ApiException.ExceptionType.SERVER_EXCEPTION;

public class InternalServerErrorException extends ApiException {
  public InternalServerErrorException(Exception source) {
    super(SERVER_EXCEPTION, source);
  }
}
