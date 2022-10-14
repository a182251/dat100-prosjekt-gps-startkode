package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	
	public GPSComputer(String filename) {
		
		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();
	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	//Oppgave 4: GPS-basert statistikk
	
	//a) Total distanse på ruten
	public double totalDistance() {
		
		double distance = 0;
		
		for (int i = 0; i < this.gpspoints.length - 1; i++) {
			
			distance += GPSUtils.distance (gpspoints [i+1], gpspoints [i]);
			
		} 
		
		return distance;
	}

	//b) Totalt antall høydemeter
	public double totalElevation() {

		double elevation = 0;
		
		for (int i = 0; i < this.gpspoints.length-1; i++) {
			
			if (gpspoints[i+1].getElevation () > gpspoints[i].getElevation ()) {
						
				elevation += gpspoints[i+1].getElevation () - gpspoints[i].getElevation ();
			}
		} 
		
		return elevation;
	}
	
    //c) Total tid det har tatt å sykle ruten
 	public int totalTime() {
		
      int time = 0;
		
		for (int i = 0; i < this.gpspoints.length-1; i++) {
			
			time += gpspoints[i+1].getTime () - gpspoints[i].getTime ();
			
		}
		
		return time;
	}
 	
	//d) Gjennomsnittsfart mellom hvert av gpspunktene
	public double[] speeds() {
		
		double [] hastighet = new double [gpspoints.length - 1];
		int i = 0;
		int j = 0;
		
		for (i = 0; i < this.gpspoints.length - 1; i++, j++) {
			
			hastighet[j] = (GPSUtils.speed (gpspoints[i], gpspoints[i+1]) );	
		}
		
        return hastighet;
	}
	
	//e) Max speed mellom to punkter
	public double maxSpeed() {
		
		 double maxspeed = 0;
	      
		 //Bruker metoden findMax på metoden speed, og finner dermed maks av gjennomsn.hast mellom punktene
	     maxspeed = GPSUtils.findMax (speeds ());
	      
	     return maxspeed;
	}
	
	//f) Gjennomsnittshastighet for hele ruten
	public double averageSpeed() {

		double average = 0;
				 
		average = (totalDistance () / totalTime ()) * 3.6;
		 	 
		return average;
		
	}

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;
	
	// g) Bergner hvor mye energi som er forbrent gitt vekt,tid og hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal = 0;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		// regnet om dette til å bli kcal=(MET*sec*kg)/3600) h=secs/3600sekunder.
		
		double speedmph = speed * MS;
		
		if (speedmph < 10) {
			
			kcal = (4 * secs * weight) / 3600;
		}
		
		if (10 <= speedmph && speedmph < 12) {
			
			kcal = (6 * secs * weight) / 3600;
		}
       
		if (12 <= speedmph && speedmph < 14) {
			
			kcal = (8 * secs * weight) / 3600;
		}
       
		if (14 <= speedmph && speedmph < 16) {
				
			kcal = (10 * secs * weight) / 3600;
		}
       
		if (16 <= speedmph && speedmph < 20) {
			 
			kcal = (12 * secs * weight) / 3600;
		}
       
		if (speedmph >= 20) {
			
			kcal = (16 * secs * weight) / 3600;
       
		}
		
		return kcal;
	}
	
    //h) Beregner den totale energi som er forbrent på hele ruten
	public double totalKcal(double weight) {

		double totalkcal = 0;
		
		totalkcal = kcal (weight, totalTime(), averageSpeed ());
		
		return totalkcal;
		
	}
	
	private static double WEIGHT = 80.0;
	
	//i) Skriver ut statistikk  for ruten
	public void displayStatistics() {

		//String.format ("      %.2f", d);
		System.out.println("==============================================");

		System.out.println ("Total time \t:  " + GPSUtils.formatTime (totalTime ()));
		System.out.println ("Total distance \t:\t" + String.format ("%.2f", totalDistance ()));
		System.out.println ("Total elevation :\t" + String.format ("%.2f", totalElevation ()));
		System.out.println ("Max speed \t:\t" + String.format ("%.2f", maxSpeed ()));
		System.out.println ("Average speed \t:\t" + String.format ("%.2f", averageSpeed ()));
		System.out.println ("Energi \t\t:\t" + String.format ("%.2f", totalKcal (GPSComputer.WEIGHT)));
		
		System.out.println("==============================================");
	}
}
