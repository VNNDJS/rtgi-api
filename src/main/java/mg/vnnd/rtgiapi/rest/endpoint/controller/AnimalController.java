package mg.vnnd.rtgiapi.rest.endpoint.controller;

import lombok.AllArgsConstructor;
import mg.vnnd.rtgiapi.endpoint.rest.model.CrupdateGspAnimalsStatsRequest;
import mg.vnnd.rtgiapi.endpoint.rest.model.GetGspAnimalsStats200Response;
import mg.vnnd.rtgiapi.model.BoundedPageSize;
import mg.vnnd.rtgiapi.model.PageFromOne;
import mg.vnnd.rtgiapi.rest.endpoint.controller.mapper.AnimalMapper;
import mg.vnnd.rtgiapi.rest.endpoint.service.AnimalService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AnimalController {
  private final AnimalService service;
  private final AnimalMapper mapper;

  @GetMapping("/green-spaces/{gsp_id}/animal-statistics")
  public GetGspAnimalsStats200Response getGspAnimals(
      @PathVariable("gsp_id") String gspId,
      @RequestParam(required = false, name = "is_endemic") Boolean isEndemic,
      @RequestParam(value = "page", required = false) PageFromOne page,
      @RequestParam(value = "page_size", required = false) BoundedPageSize pageSize) {
    var data = service.findAllBy(gspId, isEndemic, page, pageSize);
    return new GetGspAnimalsStats200Response()
        .hasPrevious(data.hasPrevious())
        .pageNumber(page.getValue())
        .pageSize(pageSize.getValue())
        .count(data.count())
        .data(data.data().stream().map(mapper::toRest).toList());
  }

  @PutMapping("/green-spaces/{gsp_id}/animal-statistics")
  public CrupdateGspAnimalsStatsRequest crupdateGspAnimalsStats(
      @PathVariable("gsp_id") String gspId, @RequestBody CrupdateGspAnimalsStatsRequest body) {
    var data = service.saveAll(body.getData().stream().map(mapper::toDomain).toList());
    return new CrupdateGspAnimalsStatsRequest().data(data.stream().map(mapper::toRest).toList());
  }
}
