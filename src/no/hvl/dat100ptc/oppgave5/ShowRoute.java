package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 600;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute () {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch (args);
	}

	public void run() {

		makeWindow ("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap (MARGIN + MAPYSIZE);
		
		showStatistics ();
	}

	// antall x-pixels per lengdegrad
	public double xstep () {

		double maxlon = GPSUtils.findMax (GPSUtils.getLongitudes (gpspoints));
		double minlon = GPSUtils.findMin (GPSUtils.getLongitudes (gpspoints));

		double xstep = MAPXSIZE / (Math.abs (maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep () {
	
		double maxlat = GPSUtils.findMax (GPSUtils.getLatitudes (gpspoints));
		double minlat = GPSUtils.findMin (GPSUtils.getLatitudes (gpspoints));
		
		double ystep = MAPYSIZE / (Math.abs (maxlat - minlat));
		
		return ystep;
	}

	public void showRouteMap (int ybase) {
		
		double xstep = xstep ();
		double ystep = ystep ();
		
		double xMin = GPSUtils.findMin (GPSUtils.getLongitudes (gpspoints));
		double yMin = GPSUtils.findMin (GPSUtils.getLatitudes (gpspoints));
		
		for (int i = 1; i < gpspoints.length; i++) {
			
			int x1 = (int) ((gpspoints [i - 1].getLongitude () - xMin) * xstep);
			int y1 = (int) ((gpspoints [i - 1].getLatitude () - yMin) * ystep);
			
			int x2 = (int) ((gpspoints [i].getLongitude () - xMin) * xstep);
			int y2 = (int) ((gpspoints [i].getLatitude () - yMin) * ystep);
			
			setColor (0, 200, 0);
			drawLine (x1 + MARGIN, ybase - y1, x2 + MARGIN, ybase - y2);
			setColor (0, 200, 0);
			fillCircle (x1 + MARGIN, ybase - y1, 3);
			
			if (i == gpspoints.length - 1) {
				
				setColor (0, 0, 200);
				fillCircle (x2 + MARGIN, ybase - y2, 6);
			}
		}
	}

	public void showStatistics() {

		setColor (0, 0, 0);
		setFont ("Courier", 12);
		
		drawString (String.format ("Total time      : %12s", GPSUtils.formatTime (gpscomputer.totalTime ())), MARGIN, 20);
		drawString (String.format ("Total distance  : %12.2f km", (gpscomputer.totalDistance () / 1000)), MARGIN, 40);
		drawString (String.format ("Total elevation : %12.2f m", gpscomputer.totalElevation ()), MARGIN, 60);
		drawString (String.format ("Max speed       : %12.2f km/t", gpscomputer.maxSpeed ()), MARGIN, 80);
		drawString (String.format ("Average speed   : %12.2f km/t", gpscomputer.averageSpeed ()), MARGIN, 100);
		drawString (String.format ("Energy          : %12.2f kcal", gpscomputer.totalKcal (80)), MARGIN, 120);
	}
}
