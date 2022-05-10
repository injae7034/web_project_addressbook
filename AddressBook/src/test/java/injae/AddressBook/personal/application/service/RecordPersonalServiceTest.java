package injae.AddressBook.personal.application.service;

import injae.AddressBook.personal.application.port.in.record.RecordPersonalCommand;
import injae.AddressBook.personal.application.port.out.PersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class RecordPersonalServiceTest {

    @Autowired
    RecordPersonalService service;
    @Autowired
    PersonalRepository personalRepository;

    @Test
    void recordPersonalTest() {
        //given
        RecordPersonalCommand command = new RecordPersonalCommand(
                "홍길동",
                "서울시 중구",
                "01033450093",
                "hong@naver.com"
        );

        //when
        Long saveId = service.recordPersonal(command);

        //then
        assertThat(command.getName())
                .isEqualTo(personalRepository.findOne(saveId).getName());
        assertThat(command.getAddress())
                .isEqualTo(personalRepository.findOne(saveId).getAddress());
        assertThat(command.getTelephoneNumber())
                .isEqualTo(personalRepository.findOne(saveId).getTelephoneNumber());
        assertThat(command.getEmailAddress())
                .isEqualTo(personalRepository.findOne(saveId).getEmailAddress());

    }
}
