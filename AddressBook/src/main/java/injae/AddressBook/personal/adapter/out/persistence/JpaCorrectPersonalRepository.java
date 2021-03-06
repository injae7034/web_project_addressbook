package injae.AddressBook.personal.adapter.out.persistence;

import injae.AddressBook.personal.application.port.out.CorrectPersonalRepository;
import injae.AddressBook.personal.domain.Personal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class JpaCorrectPersonalRepository implements CorrectPersonalRepository {

    private final EntityManager em;

    @Override
    public void update(Long id, String address,
                       String telephoneNumber, String emailAddress) {
        Personal findPersonal = em.find(Personal.class, id);

        findPersonal.changePersonalInfo(address, telephoneNumber, emailAddress);

    }
}
