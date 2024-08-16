package mg.vnnd.rtgiapi.model;

import lombok.Getter;
import mg.vnnd.rtgiapi.model.exception.BadRequestException;

public class PageFromOne {

  @Getter private final int value;

  public PageFromOne(int value) {
    if (value < 1) {
      throw new BadRequestException("page value must be >= 1");
    }
    this.value = value;
  }
}
