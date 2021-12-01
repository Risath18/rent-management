package Model.Property;

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
}