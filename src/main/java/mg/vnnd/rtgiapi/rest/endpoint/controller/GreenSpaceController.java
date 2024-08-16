package mg.vnnd.rtgiapi.rest.endpoint.controller;

import lombok.AllArgsConstructor;
import mg.vnnd.rtgiapi.endpoint.rest.model.GetGreenSpaces200Response;
import mg.vnnd.rtgiapi.endpoint.rest.model.GreenSpace;
import mg.vnnd.rtgiapi.model.BoundedPageSize;
import mg.vnnd.rtgiapi.model.PageFromOne;
import mg.vnnd.rtgiapi.rest.endpoint.controller.mapper.GreenSpaceMapper;
import mg.vnnd.rtgiapi.rest.endpoint.service.GreenSpaceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GreenSpaceController {
  private final GreenSpaceService service;
  private final GreenSpaceMapper mapper;

  @GetMapping("/green-spaces")
  public GetGreenSpaces200Response getGreenSpaces(
      @RequestParam(value = "user_id", required = false) String userId,
      @RequestParam(required = false) PageFromOne page,
      @RequestParam(value = "page_size", required = false) BoundedPageSize pageSize) {
    var data = service.findAllBy(userId, page, pageSize);
    return new GetGreenSpaces200Response()
        .data(data.data().stream().map(mapper::toRest).toList())
        .count(data.count())
        .hasPrevious(data.hasPrevious())
        .pageSize(data.queryPageSize().getValue())
        .pageNumber(data.queryPage().getValue());
  }

  @GetMapping("/green-spaces/{gsp_id}")
  public GreenSpace getGreenSpace(@PathVariable("gsp_id") String gspId) {
    return mapper.toRest(service.getById(gspId));
  }

  @PutMapping("/green-spaces/{gsp_id}")
  public GreenSpace crupdateGreenSpace(
      @PathVariable("gsp_id") String gspId, @RequestParam GreenSpace greenSpace) {
    return mapper.toRest(service.save(mapper.toDomain(greenSpace)));
  }
}
