package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import static java.lang.Integer.*;

public class GPSDataConverter {

	// konverter tidsinformasjon i gps data punkt til antall sekunder fra midnatt
	// dvs. ignorer information om dato og omregn tidspunkt til sekunder
	// Eksempel - tidsinformasjon (som String): 2017-08-13T08:52:26.000Z
    // skal omregnes til sekunder (som int): 8 * 60 * 60 + 52 * 60 + 26 
	
	private static int TIME_STARTINDEX = 11; // posisjon for start av tidspunkt i timestr

	public static int toSeconds(String timestr) {
		
		int secs;
		int hr, min, sec;
		
		hr = parseInt (timestr.substring (12, 13)); 
		min = parseInt (timestr.substring (15, 16));
		sec = parseInt (timestr.substring (18, 19));
		
		secs = (hr * 60 * 60) + (min * 60) + sec;
		
		return secs;
		
//	 	substring(int beginIndex, int endIndex)
//	 	Returns a new string that is a substring of this string.
//		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint;

		// TODO - START ;
		
		throw new UnsupportedOperationException(TODO.method());

		// OPPGAVE - SLUTT ;
	    
	}
	
}
