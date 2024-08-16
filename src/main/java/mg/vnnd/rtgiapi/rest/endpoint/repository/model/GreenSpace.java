package mg.vnnd.rtgiapi.rest.endpoint.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "\"green_space\"")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GreenSpace {
  @Id private String id;
  private String userId;
  private double area;
  private String description;
  private double ticketPrice;
  private double longitude;
  private double latitude;
  private String name;
  private String address;
}
