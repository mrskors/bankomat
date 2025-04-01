package bankomat;

import lombok.*;

@AllArgsConstructor
public class Person {
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private String pesel;
}
