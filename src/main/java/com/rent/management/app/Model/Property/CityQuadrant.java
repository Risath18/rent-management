package com.rent.management.app.Model.Property;

import java.lang.instrument.IllegalClassFormatException;

/**
 * Quadrant Enum
 */
public enum CityQuadrant{
    NE,
    NW,
    SE,
    SW,
    AL;

    /**
     * @return string of name
     */
    @Override
    public String toString(){
        return this.name();
    }

    public static CityQuadrant fromString(String quadrant){
        if(quadrant=="Al"){
            return AL;
        }
        switch (quadrant) {
            case "NW":
                return NW;
            case "NE":
                return NE;
            case "SW":
                return SW;
            case "SE":
                return SE;
        }
        return AL;
    }
}