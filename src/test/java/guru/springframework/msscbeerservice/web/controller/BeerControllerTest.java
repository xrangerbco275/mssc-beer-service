package guru.springframework.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.bootstrap.BeerLoader;
import guru.springframework.msscbeerservice.services.BeerService;
import guru.springframework.msscbeerservice.web.enums.BeerStyleEnum;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "dev.springframework.guru", uriPort = 80)
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "guru.springframework.msscbeerservice.web.mappers")
class BeerControllerTest
{

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Test
    void getBeerById() throws Exception
    {

        given(beerService.getBeerById(any())).willReturn(getValidBeerDto());

        ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

        mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
                .param("isCold", "yes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document("v1/beer-get",
                        RequestDocumentation.pathParameters(
                                RequestDocumentation.parameterWithName("beerId").description("UUID of desired beer to get.")
                        ),
                        RequestDocumentation.requestParameters(
                                RequestDocumentation.parameterWithName("isCold").description("Is Beer COld Query param")
                        ),
                        PayloadDocumentation.responseFields(
                                fields.withPath("id").description("Id of beer"),
                                fields.withPath("version").description("Version number"),
                                fields.withPath("createdDate").description("Date Created"),
                                fields.withPath("lastModifiedDate").description("Date Updated"),
                                fields.withPath("beerName").description("Name of beer"),
                                fields.withPath("beerStyle").description("Type of beer"),
                                fields.withPath("upc").description("UPC of beer"),
                                fields.withPath("price").description("Price"),
                                fields.withPath("quantityOnHand").description("Quantity on hand"),
                                fields.withPath("minOnHand").description("Minimum on hand"),
                                fields.withPath("myDate").ignored()
                        )));

    }

    @Test
    void saveNewBeer() throws Exception
    {

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any())).willReturn(getValidBeerDto());

        ConstrainedFields fields = new ConstrainedFields(BeerDto.class);

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated())
                .andDo(MockMvcRestDocumentation.document("v1/beer-new",
                        PayloadDocumentation.requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdDate").ignored(),
                                fields.withPath("lastModifiedDate").ignored(),
                                fields.withPath("beerName").description("Name of beer"),
                                fields.withPath("beerStyle").description("Type of beer"),
                                fields.withPath("upc").description("UPC of beer").attributes(),
                                fields.withPath("price").description("Beer Price"),
                                fields.withPath("quantityOnHand").ignored(),
                                fields.withPath("minOnHand").ignored(),
                                fields.withPath("myDate").ignored()
                        )));
    }

    @Test
    void updateBeerById() throws Exception
    {
        given(beerService.updateBeer(any(), any())).willReturn(getValidBeerDto());

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    BeerDto getValidBeerDto()
    {
        return BeerDto.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal("2.99"))
                .upc(BeerLoader.BEER_1_UPC)
                .build();
    }

    private static class ConstrainedFields
    {
        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input)
        {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path)
        {
            return PayloadDocumentation.fieldWithPath(path)
                    .attributes(Attributes.key("constraints")
                            .value(StringUtils.collectionToDelimitedString(this.constraintDescriptions.descriptionsForProperty(path), ". ")));
        }
    }
}