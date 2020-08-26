package cl.com.nisum.challenge.service;

import cl.com.nisum.challenge.components.BusinessLogicExceptionComponent;
import cl.com.nisum.challenge.model.dto.PhoneDTO;
import cl.com.nisum.challenge.model.entity.Phone;
import cl.com.nisum.challenge.model.mapper.CycleAvoidingMappingContext;
import cl.com.nisum.challenge.model.mapper.PhoneMapper;
import cl.com.nisum.challenge.model.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PhoneService {
    @Service("PhonesServices")
    public class PhonesService {

        @Autowired
        private BusinessLogicExceptionComponent logicExceptionComponent;

        @Autowired
        private PhoneRepository phonesRepository;

        private PhoneMapper phonesMapper = PhoneMapper.MAPPER;

        @Autowired
        private CycleAvoidingMappingContext context;

        public PhoneDTO save(PhoneDTO dto) {
            Phone phoneToSave = phonesMapper.toEntity(dto, context);
            Phone phoneSaved = phonesRepository.save(phoneToSave);
            PhoneDTO phoneDTOSaved = phonesMapper.toDto(phoneSaved, context);
            return phoneDTOSaved;
        }

        public List<PhoneDTO> findAll() {
            List<Phone> phones = phonesRepository.findAll();
            List<PhoneDTO> phonesDTOList = phonesMapper.toDto(phones, context);
            return phonesDTOList;
        }

        public void delete(UUID id) {
            Optional<Phone> byIdOptional = phonesRepository.findById(id);
            if (byIdOptional.isPresent()) {
                Phone phonesToDelete = byIdOptional.get();
                phonesRepository.delete(phonesToDelete);
            } else {
                logicExceptionComponent.throwExceptionEntityNotFound("Phones", id);
            }
        }
    }
}
