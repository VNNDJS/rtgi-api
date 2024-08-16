package mg.vnnd.rtgiapi.rest.converter;

import mg.vnnd.rtgiapi.model.PageFromOne;
import org.springframework.core.convert.converter.Converter;

public class PageConverter implements Converter<String, PageFromOne> {

  @Override
  public PageFromOne convert(String source) {
    return new PageFromOne(Integer.parseInt(source));
  }
}
