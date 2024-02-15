package databaseSpring.databaseSpringBoot.Dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class productdto {

    private int id;

    @NotNull(message = "username shouldn't be null")
    @NotBlank(message = "username must not be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    @NotNull(message = "Quantity shouldn't be null")
    private Integer quantity;
    @NotNull(message = "Price shouldn't be null")
    private Double price;
    @Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered")
    @NotNull(message = "Mobile shouldn't be null")

    private String mobile;

}
