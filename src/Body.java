
public class Body {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	public Body (double xpos, double ypos, double xvel, double yvel, double mass, String name)
	{
		myXPos = xpos;
		myYPos = ypos;
		myXVel = xvel;
		myYVel = yvel;
		myMass = mass;
		myFileName = name;
	}
	
	public Body (Body b)
	{
		myXPos = b.getX();
		myYPos = b.getY();
		myXVel = b.getXVel();
		myYVel = b.getYVel();
		myMass = b.getMass();
		myFileName = b.getName();
	}
	
	public double getX()
	{
		return myXPos;
	}
	
	public double getY()
	{
		return myYPos;
	}
	
	public double getXVel()
	{
		return myXVel;
	}
	
	public double getYVel()
	{
		return myYVel;
	}
	
	public double getMass()
	{
		return myMass;
	}
	
	public String getName()
	{
		return myFileName;
	}
	
	public double calcDistance(Body b) {
		return Math.sqrt(Math.pow(b.getX() - myXPos, 2) + Math.pow(b.getY() - myYPos, 2));
	}
	
	public double calcForceExertedBy(Body p)
	{
		return 6.67 * Math.pow(10, (-11)) * myMass * p.getMass() / Math.pow(calcDistance(p), 2);
	}
	
	public double calcForceExertedByX(Body p)
	{
		return calcForceExertedBy(p) * (p.getX() - myXPos) / calcDistance(p);
	}
	
	public double calcForceExertedByY(Body p)
	{
		return calcForceExertedBy(p) * (p.getY() - myYPos) / calcDistance(p);
	}
	
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
	
	public void draw()
	{
		StdDraw.picture(myXPos, myYPos, "images/" + myFileName);
	}
}
