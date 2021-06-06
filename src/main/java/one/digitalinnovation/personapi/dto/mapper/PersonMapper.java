package one.digitalinnovation.personapi.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entities.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {
	
    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO);
    
    PersonDTO toDTO(Person person);
}
