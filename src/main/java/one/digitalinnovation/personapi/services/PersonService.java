package one.digitalinnovation.personapi.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.dto.mapper.PersonMapper;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entities.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.repositories.PersonRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {
	
	private PersonRepository personRepository;
	
	private final PersonMapper personMapper;
	
	
	public MessageResponseDTO createPerson(PersonDTO personDTO) {
		
		verifyCpfExists(personDTO.getCpf());
		
		Person personToSave = personMapper.toModel(personDTO);
		Person savedPerson = personRepository.save(personToSave);
		
		return createMessageResponse(savedPerson.getId(), "Created person with ID ");
		
	}
	
	
	public List<PersonDTO> listAll() {
		List<Person> people = personRepository.findAll();
		return people.stream()
				.map(personMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	
	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = verifyIfExists(id);
		return personMapper.toDTO(person);
	}
	
	public PersonDTO findByCpf(String string) {
		
		String cpf = cpfReplace(string);
		
		Person person = personRepository.findByCpf(cpf)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CPF not found"));
		return personMapper.toDTO(person);
	}
	
	
	public MessageResponseDTO updateById(Long id, @Valid PersonDTO personDTO) throws PersonNotFoundException {
		
		verifyIfExists(id);
		Person personToUpdate = personMapper.toModel(personDTO);
		Person updatePerson = personRepository.save(personToUpdate);
		
		return createMessageResponse(updatePerson.getId(), "Update person with ID ");
	}
	
	
	public void delete(Long id) throws PersonNotFoundException {
		verifyIfExists(id);
		personRepository.deleteById(id);
		
	}
	
	
	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		return personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));
	}
	
	private void  verifyCpfExists(String cpf) {
		if (personRepository.findByCpf(cpf).isPresent())
				throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"CPF already exits");
		
	}
	
	private String cpfReplace(String string) {
		if (string.matches("^\\d{3}.\\d{3}.\\d{3}-\\d{2}$|\\d{11}")) {
			return (string.length() == 11) ? 
					string.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})","$1.$2.$3-$4") : 
						string;

		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid CPF format");

	}
	
	private MessageResponseDTO createMessageResponse(Long id, String message) {
		return MessageResponseDTO
				.builder()
				.message(message + id)
				.build();
	}
	
}
