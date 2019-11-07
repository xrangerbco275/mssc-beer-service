package guru.springframework.msscbeerservice.web.model;

import guru.springframework.msscbeerservice.web.enums.BeerStyleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BeerDto
{

    @Null
    private UUID id;
    private Integer version;

    private OffsetDateTime createdDate;
    private OffsetDateTime lastModifiedDate;

    @NotBlank
    private String beerName;
    @NotBlank
    private BeerStyleEnum beerStyle;
    @Positive
    private String upc;

    private BigDecimal price;

    private Integer minOnHand;
    private Integer quantityOnHand;

}
