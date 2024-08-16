package mg.vnnd.rtgiapi.rest.endpoint.repository.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "\"plant\"")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plant {
  @Id private String id;
  private String name;
  private boolean isEndemic;
  private String greenSpaceId;
  private int number;
}
