package guru.springframework.msscjacksonexamples.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.byLessThan;
import static org.junit.jupiter.api.Assertions.assertAll;

@JsonTest
class BeerDtoTest extends BaseTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializeDto() throws JsonProcessingException {
        BeerDto beerDto = getDto();
        String jsonString = objectMapper.writeValueAsString(beerDto);

        System.out.println(jsonString);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonString).isNotBlank();
        softAssertions.assertThat(jsonString).contains("\"beerName\":\"Beer Name\",\"beerStyle\":\"Ale\"");
        softAssertions.assertAll();
    }

    @Test
    //@DefaultTimeZone("UTC")
    void testDeserialize() throws IOException {
        String jsonString = "{\"id\":\"19e838da-b58c-467c-8765-6d06c2ae0549\",\"beerName\":\"Beer Name\",\"beerStyle\":\"Ale\",\"upc\":123456789,\"price\":12.99,\"createdDate\":\"2020-07-12T11:58:03.955485+00:00\",\"lastUpdatedDate\":\"2020-07-12T11:58:03.956414+02:00\",\"myLocalDate\":\"20200712\"}";

        BeerDto beerDto = objectMapper.readValue(jsonString, BeerDto.class);

        System.out.println(beerDto);

        assertAll(
                () -> assertThat(beerDto.getId().toString()).isEqualTo("19e838da-b58c-467c-8765-6d06c2ae0549"),
                () -> assertThat(beerDto.getBeerName()).isEqualTo("Beer Name"),
                () -> assertThat(beerDto.getBeerStyle()).isEqualTo("Ale"),
                () -> assertThat(beerDto.getUpc()).isEqualTo(Long.valueOf(123456789L)),
                () -> assertThat(beerDto.getPrice()).isEqualTo(BigDecimal.valueOf(12.99)),
                () -> assertThat(beerDto.getCreatedDate()).isCloseTo(
                        OffsetDateTime.of(2020, 07, 12, 11, 58, 03, 0, ZoneOffset.UTC),
                        byLessThan(1, ChronoUnit.SECONDS)),
                () -> assertThat(beerDto.getMyLocalDate()).isNotNull()
        );

    }

}