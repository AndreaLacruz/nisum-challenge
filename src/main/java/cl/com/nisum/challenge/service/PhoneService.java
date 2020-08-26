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

@Service("PhoneService")
public class PhoneService {

    @Autowired
        private BusinessLogicExceptionComponent logicExceptionComponent;

        @Autowired
        private PhoneRepository phoneRepository;

        private PhoneMapper phoneMapper = PhoneMapper.MAPPER;

        @Autowired
        private CycleAvoidingMappingContext context;

        public PhoneDTO save(PhoneDTO dto) {
            Phone phoneToSave = phoneMapper.toEntity(dto, context);
            Phone phoneSaved = phoneRepository.save(phoneToSave);
            PhoneDTO phoneDTOSaved = phoneMapper.toDto(phoneSaved, context);
            return phoneDTOSaved;
        }

        public List<PhoneDTO> findAll() {
            List<Phone> phones = phoneRepository.findAll();
            List<PhoneDTO> phoneDTOList = phoneMapper.toDto(phones, context);
            return phoneDTOList;
        }

        public void delete(UUID id) {
            Optional<Phone> byIdOptional = phoneRepository.findById(id);
            if (byIdOptional.isPresent()) {
                Phone phonesToDelete = byIdOptional.get();
                phoneRepository.delete(phonesToDelete);
            } else {
                logicExceptionComponent.throwExceptionEntityNotFound("Phone", id);
            }
        }
}