package org.switch2022.project.ddd;

/**
 * An entity.
 *
 */
public interface Entity<ID extends  EntityID>{

    ID identity();

    /**
     * Entities compare by identity, not by attributes.
     *
     * @param object The other entity.
     * @return true if the identities are the same, regardless of other attributes.
     */
    boolean sameIDAs(Object object);
}
