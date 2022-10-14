package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowSpeed extends EasyGraphics {
			
	private static final int MARGIN = 50;
	private static final int BARHEIGHT = 100; // assume no speed above 200 km/t

	private GPSComputer gpscomputer;
	private GPSPoint[] gpspoints;
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();
		
	}
	
	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch (args);
	}

	public void run() {

		int N = gpspoints.length-1; // number of data points
		
		makeWindow("Speed profile", 2*MARGIN + 2 * N, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT,N);
	}
	
	//5 b) Hastighetskurve gjennom ruten, med grønn makering av average
	public void showSpeedProfile(int ybase, int N) {

		double distanse = 0;
		int time = 0;
		int k = 0;
		int x = MARGIN;
		double gjHastighet = 0;
		
		// get segments speeds from the GPS computer object		
		double[] speeds = gpscomputer.speeds ();
		
		for (int i = 0; i < speeds.length; i++) {
            		
			double hastighet = speeds[i];
	
			int fart = (int) hastighet;
			
			setColor (200, 0, 255);
			drawLine (x + i * 2, ybase, x + i * 2, ybase - fart);
		}    
		
		for (int j = 0; j < this.gpspoints.length - 1; j++, k++) {
			
			distanse += GPSUtils.distance (gpspoints[j], gpspoints[j + 1]);
			
			time += gpspoints[k + 1].getTime () - gpspoints[k].getTime ();
		}
		
		gjHastighet = (distanse / time) * 3.6;
				
		int average = (int) gjHastighet;
			
		setColor (50, 250, 150);
		drawLine (x, ybase - average, x + 390, ybase - average);
	}
}

