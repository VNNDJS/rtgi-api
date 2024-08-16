package mg.vnnd.rtgiapi.rest.endpoint.repository.model;

import static jakarta.persistence.EnumType.STRING;
import static org.hibernate.type.SqlTypes.NAMED_ENUM;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mg.vnnd.rtgiapi.endpoint.rest.model.EventType;
import org.hibernate.annotations.JdbcTypeCode;

@Table(name = "\"event\"")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventModel {
  @Id private String id;

  @Enumerated(STRING)
  @JdbcTypeCode(NAMED_ENUM)
  private EventType type;

  private String name;
  private double longitude;
  private double latitude;
  private String locationName;
  private String address;
  private Instant creationDatetime;
  private Instant beginDatetime;
  private Instant endDatetime;
  private String description;
  private String greenSpaceId;
  private BigDecimal meetingFee;
}
