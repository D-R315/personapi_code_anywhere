package one.digitalinnovation.personapi.dto.request;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
	
	
	private Long id;
	
	@NotEmpty
	@Size(min = 2, max = 100)
	@ApiModelProperty(example = "Rodrigo")
	private String firstname;
	
	@NotEmpty
	@Size(min = 2, max = 100)
	@ApiModelProperty(example = "Peleias")
	private String lastname;
	
	@NotEmpty
	@CPF
	@ApiModelProperty(example = "257.026.380-00")
	private String cpf;
	
	@NotNull
	@Past
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@ApiModelProperty(example = "23/03/1988")
	private LocalDate birthDate;
	
	@Valid
    @NotEmpty
    private List<PhoneDTO> phones;

}
