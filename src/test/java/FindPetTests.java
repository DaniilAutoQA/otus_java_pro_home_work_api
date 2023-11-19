import dto.CategoryDto;
import dto.PetDto;
import dto.TagDto;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import services.PetApiActions;

import java.util.List;

@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FindPetTests {

    private PetApiActions petApiActions = new PetApiActions();
    private static PetDto pet = PetDto.builder().id(1L)
            .category(CategoryDto.builder().id(1).name("Home").build())
            .name("KOT")
            .photoUrls(List.of("https://mykaleidoscope.ru/x/uploads/posts/2022-10/1666328852_41-mykaleidoscope-ru-p-dovolnaya-morda-kota-krasivo-45.jpg"))
            .tags(List.of(TagDto.builder().id(1).name("JAVA").build()))
            .status("available").build();

    @BeforeAll
    public void createCondition() {
        petApiActions.createPet(pet);
    }

    @AfterAll
    public void quitTests(){
        petApiActions.deletePet(1L);
    }

    @Test
    @DisplayName("Поиск Pet по статусу")
    public void findsPet() {
        //Ответ не должен быть пустым, чтобы тест не падал в матчере стоит значение true
        petApiActions.findPetByStatus("available").statusCode(200).assertThat().body("isEmpty()", Matchers.is(true));
    }

    @Test
    @DisplayName("Поиск Pet по невалидному значению статуса")
    public void findsPetWithNotValidStatus() {
        petApiActions.findPetByStatus("all").assertThat().body("$", Matchers.hasSize(0));
    }
}
