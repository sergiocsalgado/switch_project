package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.EntityID;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Email implements EntityID {
    private final String emailAddress;

    public Email(String emailAddress) {

        StringValidation.checkNull("Email",emailAddress);

        StringValidation.checkBlank("Email", emailAddress);

        if (!isValidEmail(emailAddress)) {
            throw new IllegalArgumentException("Email is not valid");
        }

        this.emailAddress = emailAddress.toLowerCase();
    }

    //Extracted from https://www.geeksforgeeks.org/check-email-address-valid-not-java/
    private static boolean isValidEmail(String emailAddress) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(emailAddress).matches();
    }

    public String getEmail() {
        return this.emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Email email = (Email) o;

        return sameAs(email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }

    @Override
    public boolean sameAs(Object other) {
        return other != null
                && this.emailAddress.equals(((Email) other).getEmail());
    }

}
