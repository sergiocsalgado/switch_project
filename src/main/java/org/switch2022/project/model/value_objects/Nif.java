package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.EntityID;
import org.switch2022.project.utils.StringValidation;

import java.util.Objects;

public final class Nif implements EntityID {
    private final String nifValue;

    public Nif(String nif) {
        StringValidation.checkNull("NIF", nif);

        StringValidation.checkBlank("NIF", nif);

        if (!verifyNif(nif)) {
            throw new IllegalArgumentException("Nif is not valid!");
        }

        this.nifValue = nif;
    }

    /**
     * Nif authentication, used to verify the validity and authenticity of a numeric
     * value, preventing fraud, or transmission or typing errors.
     */
    private static boolean verifyNif(String nif){
        final int max=9;
        //check if is numeric and has 9 numbers
        if (!nif.matches("[0-9]+") || nif.length()!=max){
            return false;
        }
        int checkSum=0;
        //calculate checkSum
        for (int i=0; i<max-1; i++){
            checkSum+=(nif.charAt(i)-'0')*(max-i);
        }
        int checkDigit=11-(checkSum % 11);
        //if checkDigit is higher than 9 set it to zero
        if (checkDigit>9){
            checkDigit=0;
        }
        //compare checkDigit with the last number of NIF
        return checkDigit==nif.charAt(max-1)-'0';
    }

    public String getNif() {
        return nifValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Nif nif = (Nif) o;
        return sameAs(nif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nifValue);
    }

    @Override
    public boolean sameAs(Object other) {
        if (other instanceof Nif) {
            Nif nif = (Nif) other;

            return this.nifValue.equals(nif.nifValue);
        }
        return false;
    }
}
