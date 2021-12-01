package Model.Property;

import Exceptions.IllegalPropertyStatusException;

/**
 * Types enum includes Property Status
 */
public enum PropertyStatus{
    ACTIVE,
    RENTED,
    CANCELLED,
    SUSPENDED;

    /**
     * @return string of name
     */
    @Override
    public String toString(){
        return this.name();
    }

    /**
     * @param input
     * @return
     * @throws IllegalPropertyStatusException
     */
    public static PropertyStatus fromString(String input) throws IllegalPropertyStatusException {
        for (PropertyStatus ps : PropertyStatus.values()) {
            if (ps.toString().toLowerCase().contains(input.toLowerCase())) {
                return ps;
            }
        }
        throw new IllegalPropertyStatusException();
    }

}
