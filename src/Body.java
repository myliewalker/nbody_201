
public class Body {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	/**
     *  Constructs an object of type Body given its x and y positions, velocities, mass, and name.
     */
	public Body (double xpos, double ypos, double xvel, double yvel, double mass, String name)
	{
		myXPos = xpos;
		myYPos = ypos;
		myXVel = xvel;
		myYVel = yvel;
		myMass = mass;
		myFileName = name;
	}

	/**
     *  Constructs an object of type Body based on another body.
     */
	public Body (Body b)
	{
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.getName();
	}
	
	/**
     *  Returns the body's x position.
     */
	public double getX()
	{
		return myXPos;
	}
	
	/**
     *  Returns the body's y position.
     */
	public double getY()
	{
		return myYPos;
	}
	
	/**
     *  Returns the body's x velocity.
     */
	public double getXVel()
	{
		return myXVel;
	}
	
	/**
     *  Returns the body's y velocity.
     */
	public double getYVel()
	{
		return myYVel;
	}
	
	/**
     *  Returns the body's mass.
     */
	public double getMass()
	{
		return myMass;
	}
	
	/**
     *  Returns the body's name.
     */
	public String getName()
	{
		return myFileName;
	}
	
	/**
     *  Returns the distance between two bodies.
     */
	public double calcDistance(Body b) {
		return Math.sqrt(Math.pow(b.getX() - myXPos, 2) + Math.pow(b.getY() - myYPos, 2));
	}
	
	/**
     *  Returns the force exerted by a body.
     */
	public double calcForceExertedBy(Body p)
	{
		return 6.67 * Math.pow(10, (-11)) * myMass * p.getMass() / Math.pow(calcDistance(p), 2);
	}
	
	/**
     *  Returns the x component of the force exerted by a body.
     */
	public double calcForceExertedByX(Body p)
	{
		return calcForceExertedBy(p) * (p.getX() - myXPos) / calcDistance(p);
	}
	
	/**
     *  Returns the y component of the force exerted by a body.
     */
	public double calcForceExertedByY(Body p)
	{
		return calcForceExertedBy(p) * (p.getY() - myYPos) / calcDistance(p);
	}
	
	/**
     *  Returns the net x force exerted by a body.
     */
	public double calcNetForceExertedByX(Body[] bodies)
	{
		double netForce = 0;
		for (Body b : bodies)
		{
			if (b != this)
			{
				netForce += calcForceExertedByX(b);
			}
		}
		return netForce;
	}
	
	/**
     *  Returns the net y force exerted by a body.
     */
	public double calcNetForceExertedByY(Body[] bodies)
	{
		double y = 0;
		for (Body b : bodies)
		{
			if (b != this)
			{
				y += calcForceExertedByY(b);
			}
		}
		return y;
	}
	
	/**
     *  Updates a body's position and velocity based on the force exerted on it over a given period of time.
     */
	public void update(double deltaT, double xforce, double yforce)
	{
		double ax = xforce / myMass;
		double ay = yforce / myMass;
		double nvx = myXVel + deltaT * ax;
		double nvy = myYVel + deltaT * ay;
		double nx = myXPos + deltaT * nvx;
		double ny = myYPos + deltaT * nvy;
		myXVel = nvx;
		myYVel = nvy;
		myXPos = nx;
		myYPos = ny;
	}
	
	/**
     *  Draws the body.
     */
	public void draw()
	{
		StdDraw.picture(myXPos, myYPos, "images/" + myFileName);
	}
}
