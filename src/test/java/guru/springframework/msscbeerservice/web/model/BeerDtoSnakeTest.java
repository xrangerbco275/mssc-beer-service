package guru.springframework.msscbeerservice.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

@ActiveProfiles("snake")
@JsonTest
class BeerDtoSnakeTest extends BaseTest
{

    @Test
    void testDeserialization() throws IOException
    {
        String json = "{\"version\":null,\"created_date\":\"2019-11-08T23:46:49-0600\",\"last_modified_date\":\"2019-11-08T23:46:49-0600\",\"beer_name\":\"BeerName\",\"beer_style\":\"PALE_ALE\",\"upc\":123456789,\"price\":12.9900000000000002131628207280300557613372802734375,\"min_on_hand\":null,\"quantity_on_hand\":null,\"my_date\":\"20191108\",\"beerId\":\"767e0b57-0e75-47d1-82af-531bf95be4d4\"}\n";

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