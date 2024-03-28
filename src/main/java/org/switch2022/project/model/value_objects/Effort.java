package org.switch2022.project.model.value_objects;

import org.switch2022.project.ddd.ValueObject;

import java.util.Objects;

/**
 * Represents an Effort with some story points
 */
public final class Effort implements ValueObject<Effort> {
    public static final int STORY_POINTS_ZERO = 0;
    public static final int STORY_POINTS_ONE = 1;
    public static final int STORY_POINTS_TWO = 2;
    public static final int STORY_POINTS_THREE = 3;
    public static final int STORY_POINTS_FIVE = 5;
    public static final int STORY_POINTS_EIGHT = 8;
    public static final int STORY_POINTS_THIRTEEN = 13;
    public static final int STORY_POINTS_TWENTY = 20;
    public static final int STORY_POINTS_FORTY = 40;

    private final int storyPoints;

    /**
     * Constructs a new Effort object
     *
     * @param storyPoints the story points of this Effort object, must be one of the allowed values.
     * @throws IllegalArgumentException if the story points is not one of the allowed values.
     */
    public Effort(int storyPoints) {
        if (isInvalidStoryPoints(storyPoints)) {
            throw new IllegalArgumentException("This Story Points is not valid!");
        }
        this.storyPoints = storyPoints;
    }

    /**
     * Returns True if Story Poents are valid.
     *
     * @param storyPoints the story points of this Effort object, must be one of the allowed values.
     * @return true when Story Points are valid.
     */
    private static boolean isInvalidStoryPoints(int storyPoints) {
        return storyPoints != STORY_POINTS_ZERO &&
                storyPoints != STORY_POINTS_ONE &&
                storyPoints != STORY_POINTS_TWO &&
                storyPoints != STORY_POINTS_THREE &&
                storyPoints != STORY_POINTS_FIVE &&
                storyPoints != STORY_POINTS_EIGHT &&
                storyPoints != STORY_POINTS_THIRTEEN &&
                storyPoints != STORY_POINTS_TWENTY &&
                storyPoints != STORY_POINTS_FORTY;
    }

    /**
     * Returns the story points of this Effort object.
     *
     * @return the story points of this Effort object.
     */
    public int getStoryPoints() {
        return storyPoints;
    }

    @Override
    public boolean sameAs(Effort o) {
        return this.storyPoints == o.getStoryPoints();
    }

    /**
     * Returns true if this Effort object is equal to the specified object, false otherwise.
     *
     * @param o the object to compare this Effort object to.
     * @return true if this Effort object is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Effort effort = (Effort) o;
        return sameAs(effort);
    }

    /**
     * Returns the Int representation of the object. In this case, the Int
     * returned will include the Effort story points.
     *
     * @return the Int representation of the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(storyPoints);
    }
}