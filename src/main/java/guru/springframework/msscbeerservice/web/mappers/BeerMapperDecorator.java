package guru.springframework.msscbeerservice.web.mappers;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jt on 2019-06-08.
 */
@Slf4j
public abstract class BeerMapperDecorator implements BeerMapper
{
    private BeerInventoryService beerInventoryService;
    private BeerMapper mapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService)
    {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setMapper(BeerMapper mapper)
    {
        this.mapper = mapper;
    }

    @Override
    public BeerDto beerToBeerDto(Beer beer)
    {
        return mapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer)
    {
        BeerDto dto = mapper.beerToBeerDto(beer);
        log.debug("Value of onHandInventory" + beerInventoryService.getOnhandInventory(beer.getId()));
        dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
        return dto;
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto)
    {
        return mapper.beerDtoToBeer(beerDto);
    }
}
