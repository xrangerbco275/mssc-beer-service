package guru.springframework.msscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

@ActiveProfiles("kebab")
@JsonTest
class BeerDtoKebabTest extends BaseTest
{
    @Test
    void testDeserialization() throws IOException
    {
        String json = "{\"version\":null,\"created-date\":\"2019-11-08T23:38:24-0600\",\"last-modified-date\":\"2019-11-08T23:38:24-0600\",\"beer-name\":\"BeerName\",\"beer-style\":\"PALE_ALE\",\"upc\":123456789,\"price\":12.9900000000000002131628207280300557613372802734375,\"min-on-hand\":null,\"quantity-on-hand\":null,\"my-date\":\"20191108\",\"beerId\":\"c63fb54c-42d9-4105-9cda-8b5b86762521\"}\n";

        BeerDto beerDto = objectMapper.readValue(json, BeerDto.class);

        System.out.println(beerDto);
    }

    @Test
    void testSerializeDto() throws JsonProcessingException
    {
        BeerDto beerDto = getDto();

        String jsonString = objectMapper.writeValueAsString(beerDto);

        System.out.println(jsonString);
    }


}