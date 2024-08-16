package mg.vnnd.rtgiapi.rest.endpoint.repository.model;

import static jakarta.persistence.EnumType.STRING;
import static org.hibernate.type.SqlTypes.NAMED_ENUM;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mg.vnnd.rtgiapi.endpoint.rest.model.UserTypeEnum;
import org.hibernate.annotations.JdbcTypeCode;

@Table(name = "\"user\"")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
  @Id private String id;
  private String name;
  private String email;
  private LocalDate birthdate;
  private String encodedPassword;

  @JdbcTypeCode(NAMED_ENUM)
  @Enumerated(STRING)
  private UserTypeEnum type;
}
