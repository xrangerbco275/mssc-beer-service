package guru.springframework.msscbeerservice.web.model;

import guru.springframework.msscbeerservice.web.enums.BeerStyleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @Null
    private Integer version;

    @Null
    private OffsetDateTime createdDate;

    @Null
    private OffsetDateTime lastModifiedDate;

    @NotBlank
    private String beerName;

    @NotBlank
    private BeerStyleEnum beerStyle;

    @Positive
    @NotNull
    private String upc;

    @Positive
    @NotNull
    private BigDecimal price;

    private Integer minOnHand;
    private Integer quantityOnHand;

}
