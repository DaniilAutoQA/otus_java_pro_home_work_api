package pet;

import dto.CategoryDto;
import dto.PetDto;
import dto.TagDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import services.PetApiActions;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreationPetTests {

    private PetApiActions petApiActions = new PetApiActions();
    private PetDto pet = PetDto.builder().id(1L)
            .category(CategoryDto.builder().id(1).name("Home").build())
            .name("KOT")
            .photoUrls(List.of("https://mykaleidoscope.ru/x/uploads/posts/2022-10/1666328852_41-mykaleidoscope-ru-p-dovolnaya-morda-kota-krasivo-45.jpg"))
            .tags(List.of(TagDto.builder().id(1).name("JAVA").build()))
            .status("available").build();

    @Test
    @DisplayName("Проверка создания пользователя")
    public void checkCreationPet() {
        PetDto petResult = petApiActions.createPet(pet).extract().body().as(PetDto.class);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(petResult.getId())
                .as("неверное значение id")
                .isEqualTo(pet.getId());
        softAssertions
                .assertThat(petResult.getName())
                .as("неверное значение name")
                .isEqualTo(pet.getName());
        softAssertions
                .assertThat(petResult.getPhotoUrls())
                .as("неверное значение PhotoUrls")
                .isEqualTo(pet.getPhotoUrls());
        softAssertions
                .assertThat(petResult.getStatus())
                .as("неверное значение status")
                .isEqualTo(pet.getStatus());
        softAssertions
                .assertThat(petResult.getCategory())
                .as("неверное значение Category")
                .isEqualTo(pet.getCategory());
        softAssertions
                .assertThat(petResult.getTags())
                .as("неверное значение Tag")
                .isEqualTo(pet.getTags());
        softAssertions.assertAll();
        petApiActions.getPet(pet.getId()).statusCode(200);
    }

    @AfterAll
    public void quitTests() {
        petApiActions.deletePet(pet.getId());
    }
}
