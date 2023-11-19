package pet;

import dto.PetDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.PetApiActions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NegativeCreationPetTests {

    private PetApiActions petApiActions = new PetApiActions();

    @Test
    @DisplayName("Проверка вызова метода добавления Pet с пустым телом")
    public void checkCreationPetWithEmptyFields() {
        PetDto pet = PetDto.builder().build();
        PetDto petResult = petApiActions.createPet(pet).extract().body().as(PetDto.class);
        assertThat(petResult.getId()).isNotNull();
    }
}
