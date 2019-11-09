package guru.springframework.msscbeerservice.web.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.web.enums.BeerStyleEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

public class BaseTest
{
    @Autowired
    ObjectMapper objectMapper;

    BeerDto getDto()
    {
        return BeerDto.builder()
                .beerName("BeerName")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .id(UUID.randomUUID())
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .price(new BigDecimal(12.99))
                .upc(123456789L)
                .myDate(LocalDate.now())
                .build();
    }
}
