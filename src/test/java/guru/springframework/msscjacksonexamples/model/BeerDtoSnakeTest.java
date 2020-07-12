package guru.springframework.msscjacksonexamples.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

@JsonTest
@ActiveProfiles("snake")
public class BeerDtoSnakeTest extends BaseTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerializeDto() throws JsonProcessingException {
        BeerDto beerDto = getDto();
        String jsonString = objectMapper.writeValueAsString(beerDto);

        System.out.println(jsonString);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(jsonString).isNotBlank();
        softAssertions.assertThat(jsonString).contains("\"beer_name\":\"Beer Name\",\"beer_style\":\"Ale\"");
        softAssertions.assertAll();

        System.out.println(jsonString);
    }
}
