package nl.tomsanders.game.engine.util;

public class Rectangle {
	private final Vector position;
	private final Size size;
	
	public Rectangle(Vector position, Size size) {
		this.position = position;
		this.size = size;
	}
	
	public Vector getTopLeftCorner() {
		return this.position;
	}
	
	public Vector getTopRightCorner() {
		return new Vector(
				this.position.x + this.size.getWidth(), 
				this.position.y);
	}
	
	public Vector getBottomLeftCorner() {
		return new Vector(
				this.position.x,
				this.position.y + this.size.getHeight());
		
	}
	
	public Vector getBottomRightCorner() {
		return new Vector(
				this.position.x + this.size.getWidth(),
				this.position.y + this.size.getHeight());
	}
	
	public double getTop() {
		return this.position.getY();
	}
	
	public double getBottom() {
		return this.position.getY() + this.size.getHeight();
	}
	
	public double getLeft() {
		return this.position.getX();
	}
	
	public double getRight() {
		return this.position.getX() + this.size.getWidth();
	}
	
	public boolean intersects(Rectangle b) {
		// Assumes top < bottom (top-left corner of screen is origin)
		return this.getLeft() <= b.getRight() &&
				b.getLeft() <= this.getRight() &&
				this.getTop() <= b.getBottom() &&
				b.getTop() <= this.getBottom();
	}
}
