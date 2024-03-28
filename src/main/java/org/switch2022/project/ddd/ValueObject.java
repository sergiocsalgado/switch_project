package org.switch2022.project.ddd;

/**
 * A value object.
 *
 */
public interface ValueObject<T> {
    /**
     * Value objects compare by the values of their attributes, they don't have an identity.
     *
     * @param other The other value object.
     * @return true if the given value object's and this value object's attributes are the same.
     */
    boolean sameAs(T other);
}
