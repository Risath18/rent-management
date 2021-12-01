package Model.Property;

import java.lang.instrument.IllegalClassFormatException;

/**
 * Quadrant Enum
 */
public enum CityQuadrant{
    NE,
    NW,
    SE,
    SW;

    /**
     * @return string of name
     */
    @Override
    public String toString(){
        return this.name();
    }

    public CityQuadrant fromString(String quadrant) throws IllegalClassFormatException{
        switch (quadrant){
            case "NW":
                return NW;
            case "NE":
                return NE;
            case "SW":
                return SW;
            case "SE":
                return SE;
            throw new IllegalClassFormatException();
        }

        
    }
}