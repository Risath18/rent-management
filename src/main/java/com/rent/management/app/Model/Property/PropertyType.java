package com.rent.management.app.Model.Property;

import com.rent.management.app.Exceptions.IllegalPropertyTypeException;

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
     * @param input String to be converted to a PropertyType object
     * @return returns a PropertyType object
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