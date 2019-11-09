package guru.springframework.msscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.io.IOException;

@JsonTest
class BeerDtoTest extends BaseTest
{

    @Test
    void testSerializeDto() throws JsonProcessingException
    {
        BeerDto beerDto = getDto();

        String jsonString = objectMapper.writeValueAsString(beerDto);

        System.out.println(jsonString);
    }

    @Test
    void testDeserialization() throws IOException
    {
        String json = "{\"version\":null,\"createdDate\":\"2019-11-08T23:47:27-0600\",\"lastModifiedDate\":\"2019-11-08T23:47:27-0600\",\"beerName\":\"BeerName\",\"beerStyle\":\"PALE_ALE\",\"upc\":123456789,\"price\":12.9900000000000002131628207280300557613372802734375,\"minOnHand\":null,\"quantityOnHand\":null,\"myDate\":\"20191108\",\"beerId\":\"648e021d-3049-4410-b189-b7f97458142a\"}\n";

        BeerDto beerDto = objectMapper.readValue(json, BeerDto.class);

        System.out.println(beerDto);
    }
}