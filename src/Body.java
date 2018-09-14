
public class Body {

	private static double myXPos;
	private static double myYPos;
	private static double myXVel;
	private static double myYVel;
	private static double myMass;
	private static String myFileName;
	private Body bod;
	
	public Body (double xpos, double ypos, double xvel, double yvel, double mass,String name)
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
		bod = new Body(b);
	}
	//create a body object
	
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
	
	public static double calcDistance(Body b) {
		return Math.sqrt(Math.pow(b.getX() - myXPos, 2) + Math.pow(b.getY() - myYPos, 2));
	}
	
	public static double calcForceExertedBy(Body p)
	{
		return 6.67 * Math.pow(10, (-11)) * myMass * p.getMass() / calcDistance(p);
	}
	
	public static double calcForceExertedByX(Body p)
	{
		return calcForceExertedBy(p) * (p.getX() - myXPos) / calcDistance(p);
	}
	
	public static double calcForceExertedByY(Body p)
	{
		return calcForceExertedBy(p) * (p.getY() - myYPos) / calcDistance(p);
	}
	
	public static double calcNetForceExertedByX(Body[] bodies)
	{
		double x = 0;
		for (int i = 0; i < bodies.length; i++)
		{
			if (b != this)
			{
				x += calcForceExertedByX(b);
			}
		}
		return x;
	}
	//FIX: can't use if (b != this)
	
	public static double calcNetForceExertedByY(Body[] bodies)
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
	
	public static void update(double deltaT, double xforce, double yforce)
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
	
	public static void draw()
	{
		StdDraw.picture(myXPos, myYPos, "images/" + myFileName);
	}
}
