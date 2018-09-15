	

/**
 * @author Mylie Walker
 * 
 * Simulation program for the NBody assignment
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {
	
	/**
	 * Read the specified file and return the radius
	 * @param fname is name of file that can be open
	 * @return the radius stored in the file
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static double readRadius(String fname) throws FileNotFoundException  {
		Scanner s = new Scanner(new File(fname));
		String num = s.next();
		double radius = Double.parseDouble(s.next());
		s.close();
		return radius;	
	}
	
	/**
	 * Read all data in file, return array of Celestial Bodies
	 * read by creating an array of Body objects from data read.
	 * @param fname is name of file that can be open
	 * @return array of Body objects read
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static Body[] readBodies(String fname) throws FileNotFoundException {
			Scanner s = new Scanner(new File(fname));
			int nb = s.nextInt();
			double rad = s.nextDouble();
			Body[] bodies = new Body[nb];
			for (int i = 0; i < nb; i++)
			{
				// bodies[i] = new Body(s.nextDouble(), s.nextDouble(), s.nextDouble(), s.nextDouble(), s.nextDouble(), s.next());
				bodies[i] = new Body(s.getX(), s.getY(), s.getXVel(), s.getYVel(), s.getMass(), s.getName());
			}
			return bodies;
	}
	public static void main(String[] args) throws FileNotFoundException{
		double totalTime = 157788000.0;
		double dt = 25000.0;
		
		String fname= "./data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			fname = args[2];
		}	
		
		Body[] bodies = readBodies(fname);
		double radius = readRadius(fname);
		
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");
	
		for(double t = 0.0; t < totalTime; t += dt) {
			
			// TODO: create double arrays xforces and yforces
			// to hold forces on each body
			
			double[] xforces = new double[bodies.length];
			double[] yforces = new double[bodies.length];
			
			// TODO: loop over all bodies, calculate
			// net forces and store in xforces and yforces
			
			for (int i = 0; i < xforces.length; i++)
			{
				xforces[i] = bodies[i].calcNetForceExertedByX(bodies);
				//Switched from Body.calcNetForceExertedByX(bodies)
				yforces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}
			
			
			// TODO: loop over all bodies and call update
			// with dt and corresponding xforces, yforces values
			for (int i = 0; i < bodies.length; i++)
			{
				bodies[i].update(dt, bodies[i].calcForceExertedByX(bodies[i]), bodies[i].calcForceExertedByY(bodies[i]));
				//Switched from bodies[i].update(dt, bodies[i].calcForceExertedByX(bodies[i]), bodies[i].calcForceExertedByY(bodies[i]));
			}
			
			StdDraw.picture(0,0,"images/starfield.jpg");
			
			// TODO: loop over all bodies and call draw on each one
			for (int i = 0; i < bodies.length; i++)
			{
				bodies[i].draw();
				//Switched from Body.draw();
			}
			
			StdDraw.show(10);
		}
		
		// prints final values after simulation
		
		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              bodies[i].getX(), bodies[i].getY(), 
		                      bodies[i].getXVel(), bodies[i].getYVel(), 
		                      bodies[i].getMass(), bodies[i].getName());	
		}
	}
}
