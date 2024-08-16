package mg.vnnd.rtgiapi.rest.endpoint.service;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import mg.vnnd.rtgiapi.model.BoundedPageSize;
import mg.vnnd.rtgiapi.model.Page;
import mg.vnnd.rtgiapi.model.PageFromOne;
import mg.vnnd.rtgiapi.model.exception.NotFoundException;
import mg.vnnd.rtgiapi.rest.endpoint.repository.GreenSpaceRepository;
import mg.vnnd.rtgiapi.rest.endpoint.repository.model.GreenSpace;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GreenSpaceService {
  private final GreenSpaceRepository repository;

  public Page<GreenSpace> findAllBy(String userId, PageFromOne page, BoundedPageSize pageSize) {
    List<GreenSpace> list;
    Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue());
    if (userId == null) {
      list = repository.findAll(pageable).getContent();
    } else {
      list = repository.findAllByUserId(userId, pageable);
    }
    return new Page<>(page, pageSize, list);
  }

  public Optional<GreenSpace> findById(String id) {
    return repository.findById(id);
  }

  public GreenSpace getById(String id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("GreenSpace with Id " + id + " not found"));
  }

  public GreenSpace save(GreenSpace greenSpace) {
    return repository.save(greenSpace);
  }
}
