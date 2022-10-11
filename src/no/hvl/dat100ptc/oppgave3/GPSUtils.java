package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;
		
		min = da[0];
		
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}	
		
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double [] latitudes = new double [gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			
			latitudes [i] = gpspoints[i].getLatitude();
		}
		
		return latitudes;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double [] longitudes = new double [gpspoints.length];
		
		for (int i = 0; i < gpspoints.length; i++) {
			
			longitudes [i] = gpspoints[i].getLongitude();
		}
		
		return longitudes;
	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;
		
		latitude1 = gpspoint1.getLatitude ();
		latitude2 = gpspoint2.getLatitude ();
		longitude1 = gpspoint1.getLongitude ();
		longitude2 = gpspoint2.getLongitude ();
	
		double gLat1, gLat2, gLon1, gLon2, deltaG, deltaL, a, c;

		gLat1 = toRadians (latitude1);
		gLat2 = toRadians (latitude2);
		gLon1 = toRadians (longitude1);
		gLon2 = toRadians (longitude2);
		
		deltaG = gLat2 - gLat1;
		deltaL = gLon2 - gLon1;
		
		a = pow (sin (deltaG / 2), 2) + cos (gLat1) * cos (gLat2) * pow (sin (deltaL / 2), 2);
		
		c = 2 * atan2 (sqrt (a), sqrt (1-a));
		
		d = R * c;
		
		return d;
	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed, point1, point2, distance, time;
		
		distance = distance (gpspoint1, gpspoint2);

		point1 = gpspoint1.getTime ();
		point2 = gpspoint2.getTime ();
		time = point2 - point1;
		
		// må finne ut om jeg skal regne om tid og distanse 
		
		speed = distance / time;
		
		return speed;

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";
		String SPACE = " ";

		int hh, mm, ss;
		
		hh = secs % 60;
		mm = (secs / 60) % 60;
		ss = (secs / 60) / 60;
		
		timestr = String.format (hh, mm, ss, 
								SPACE + TIMESEP + hh + SPACE + TIMESEP + mm + SPACE + TIMESEP + ss,
								0);
		
//		timestr = SPACE + Integer.toString (hh) + TIMESEP + SPACE + Integer.toString (mm) + 
//				TIMESEP + SPACE + Integer.toString (ss);
				
		return timestr;
	}
	
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START

		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}
}
