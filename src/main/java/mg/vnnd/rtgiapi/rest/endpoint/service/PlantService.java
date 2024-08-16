package mg.vnnd.rtgiapi.rest.endpoint.service;

import java.util.List;
import lombok.AllArgsConstructor;
import mg.vnnd.rtgiapi.model.BoundedPageSize;
import mg.vnnd.rtgiapi.model.Page;
import mg.vnnd.rtgiapi.model.PageFromOne;
import mg.vnnd.rtgiapi.rest.endpoint.repository.PlantRepository;
import mg.vnnd.rtgiapi.rest.endpoint.repository.model.Plant;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlantService {
  private final PlantRepository repository;

  public Page<Plant> findAllBy(
      String gspId, Boolean isEndemic, PageFromOne page, BoundedPageSize pageSize) {
    List<Plant> data;
    Pageable pageable = PageRequest.of(page.getValue(), pageSize.getValue());
    if (isEndemic == null) {
      data = repository.findAllByGreenSpaceId(gspId, pageable);
    } else {
      data = repository.findAllByGreenSpaceIdAndEndemic(gspId, isEndemic, pageable);
    }
    return new Page<>(page, pageSize, data);
  }

  public List<Plant> saveAll(List<Plant> animals) {
    return repository.saveAll(animals);
  }
}
