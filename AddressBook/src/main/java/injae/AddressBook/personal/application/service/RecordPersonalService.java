package injae.AddressBook.personal.application.service;

import injae.AddressBook.personal.application.port.in.record.RecordPersonalCommand;
import injae.AddressBook.personal.application.port.in.record.RecordPersonalUseCase;
import injae.AddressBook.personal.application.port.out.PersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class RecordPersonalService implements RecordPersonalUseCase {

    private final PersonalRepository personalRepository;

    @Override
    public Long recordPersonal(RecordPersonalCommand command) {
        Personal personal = new Personal(command.getName(),
                command.getAddress(), command.getTelephoneNumber(),
                command.getEmailAddress());

        personalRepository.save(personal);

        return personal.getId();
    }
}
