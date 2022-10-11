package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import static java.lang.Integer.*;
import static java.lang.Double.*;

public class GPSDataConverter {

	// konverter tidsinformasjon i gps data punkt til antall sekunder fra midnatt
	// dvs. ignorer information om dato og omregn tidspunkt til sekunder
	// Eksempel - tidsinformasjon (som String): 2017-08-13T08:52:26.000Z
    // skal omregnes til sekunder (som int): 8 * 60 * 60 + 52 * 60 + 26 
	
	private static int TIME_STARTINDEX = 11; // posisjon for start av tidspunkt i timestr

	public static int toSeconds(String timestr) {
		
		int secs;
		int hr, min, sec;
		
		hr = parseInt (timestr.substring (TIME_STARTINDEX, 13)); 
		min = parseInt (timestr.substring (14, 16));
		sec = parseInt (timestr.substring (17, 19));
		
		secs = (hr * 60 * 60) + (min * 60) + sec;
		
		return secs;
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;
		
		int time;
		int hr, min, sec;
		
		hr = parseInt (timeStr.substring (TIME_STARTINDEX, 13)); 
		min = parseInt (timeStr.substring (14, 16));
		sec = parseInt (timeStr.substring (17, 19));
		time = (hr * 60 * 60) + (min * 60) + sec;
		
		double latitude = parseDouble (latitudeStr.substring (0, 9));
		double longitude = parseDouble (longitudeStr.substring (0, 7));
		double elevation = parseDouble (elevationStr.substring (0, 4)); 
	    
		gpspoint = new GPSPoint (time, latitude, longitude, elevation);
		
		return gpspoint;
	}
	
}
