package mg.vnnd.rtgiapi.rest.endpoint.service;

import java.util.List;
import lombok.AllArgsConstructor;
import mg.vnnd.rtgiapi.model.BoundedPageSize;
import mg.vnnd.rtgiapi.model.Page;
import mg.vnnd.rtgiapi.model.PageFromOne;
import mg.vnnd.rtgiapi.rest.endpoint.repository.AnimalRepository;
import mg.vnnd.rtgiapi.rest.endpoint.repository.model.Animal;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnimalService {
  private final AnimalRepository repository;

  public Page<Animal> findAllBy(
      String gspId, Boolean isEndemic, PageFromOne page, BoundedPageSize pageSize) {
    List<Animal> data;
    Pageable pageable = PageRequest.of(page.getValue(), pageSize.getValue());
    if (isEndemic == null) {
      data = repository.findAllByGreenSpaceId(gspId, pageable);
    } else {
      data = repository.findAllByGreenSpaceIdAndEndemic(gspId, isEndemic, pageable);
    }
    return new Page<>(page, pageSize, data);
  }

  public List<Animal> saveAll(List<Animal> animals) {
    return repository.saveAll(animals);
  }
}
