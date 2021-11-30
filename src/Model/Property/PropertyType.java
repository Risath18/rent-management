package Model.Property;

import Exceptions.IllegalPropertyTypeException;

/**
 * Types enum includes Property Type
 */
public enum PropertyType{
    APARTMENT,
    ATTACHED,
    TOWNHOUSE,
    OTHER;

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
     * @throws IllegalPropertyTypeException
     */
    public static PropertyType fromString(String input) throws IllegalPropertyTypeException {
        for (PropertyType pt : PropertyType.values()) {
            if (pt.toString().toLowerCase().contains(input.toLowerCase())) {
                return pt;
            }
        }
        throw new IllegalPropertyTypeException();
    }
}