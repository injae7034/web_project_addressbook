package injae.AddressBook.personal.application.port.in.find;

import injae.AddressBook.common.PersonalCommandValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class FindPersonalCommand
        extends PersonalCommandValidating<FindPersonalCommand> {

    @NotNull
    private final String name;

    public FindPersonalCommand(String name) {
        this.name = name;
        this.validatePersonalCommand();
    }
}

