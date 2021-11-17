/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    @Override
    public int hashCode() {
    	final int prime = 17;
    	int result = 1;
    	result = result * prime + xCoord;
    	result = result * prime + yCoord;
    	return result;
    }

    @Override
    public boolean equals(Object obj) {
    	if (this == obj)
    		return true;
    	if (obj == null)
    		return false;
    	if (this.getClass() != obj.getClass())
    		return false;
    	Location another = (Location) obj;
    	if (xCoord != another.xCoord)
    		return false;
    	if (yCoord != another.yCoord)
    		return false;
    	return true;
    }
}