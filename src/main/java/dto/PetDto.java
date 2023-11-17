package dto;

import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetDto {
    private List<String> photoUrls;
    private String name;
    private Long id;
    private CategoryDto category;
    private List<TagDto> tags;
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetDto)) return false;
        PetDto petDto = (PetDto) o;
        return Objects.equals(id, petDto.id) && Objects.equals(photoUrls, petDto.photoUrls) && Objects.equals(name, petDto.name) && Objects.equals(category, petDto.category) && Objects.equals(tags, petDto.tags) && Objects.equals(status, petDto.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photoUrls, name, id, category, tags, status);
    }
}