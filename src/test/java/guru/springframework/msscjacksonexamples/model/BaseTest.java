package guru.springframework.msscjacksonexamples.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public class BaseTest {

    BeerDto getDto() {
        return BeerDto.builder()
                .beerName("Beer Name")
                .beerStyle("Ale")
                .id(UUID.randomUUID())
                .createdDate(OffsetDateTime.now())
                .lastUpdatedDate(OffsetDateTime.now())
                .price(BigDecimal.valueOf(12.99))
                .upc(123456789L)
                .myLocalDate(LocalDate.now())
                .build();
    }
}
