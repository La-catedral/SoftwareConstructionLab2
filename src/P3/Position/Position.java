package P3.Position;

public class Position {

	private int x;
	private int y;
	// Abstraction function:
    // 	AF(x,y) = the x and y coordinate of the position
    // Representation invariant:
    //	true
    // Safety from rep exposure:
    //  all fields are private
	// there are no mutator
	
    // constructor
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * get the x coordinate of this position
	 * @return,x coordinate of this position
	 */
	public int getX()
	{
		return this.x;
	}

	/**
	 * get the y coordinate of this position
	 * @return,y coordinate of this position
	 */
	public int getY()
	{
		return this.y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
	

	
	
}
