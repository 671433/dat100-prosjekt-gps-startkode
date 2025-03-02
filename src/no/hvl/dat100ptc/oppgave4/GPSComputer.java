package no.hvl.dat100ptc.oppgave4;

import javax.swing.plaf.ToolTipUI;

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
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO - START
		for(int i = 0 ; i < gpspoints.length -1;i++) {
	
		distance += GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
		
		}

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		return distance;

	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO - START
		for ( int i = 0 ;  i < gpspoints.length -1; i++) {
			
			//double distance = GPSUtils.distance(gpspoints[i], gpspoints[i+1]);
			
			if (gpspoints[i].getElevation() < gpspoints[i+1].getElevation()) {
			elevation+=	gpspoints[i+1].getElevation() - gpspoints[i].getElevation();
			}
		}

			
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		return elevation;

	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		
		int totalTime = 0;
		
		for ( int i = 0 ;  i < gpspoints.length -1;i++) {
			
			totalTime += gpspoints[i+1].getTime() - gpspoints[i].getTime();
		}

		//throw new UnsupportedOperationException(TODO.method());

		return totalTime;
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene

	public double[] speeds() {
		
		// TODO - START		// OPPGAVE - START
		double [] speeds = new double [gpspoints.length -1 ];
		
		for ( int i = 0; i <gpspoints.length -1 ;i++) {
			
			
			speeds[i]=  GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
			
		}
		
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		return speeds;

	}
	
	public double maxSpeed() {
		
		double maxspeed = 0;
		int i = 0;
		double x;
		// TODO - START
		while (i < gpspoints.length) {
			x = GPSUtils.findMax(speeds());
			if (x >maxspeed ) {
			maxspeed = x;
			}
			i++;
		//throw new UnsupportedOperationException(TODO.method());
		}
		// TODO - SLUTT
		return maxspeed;
		
	}

	public double averageSpeed() {

		double average = 0;
			
		average = totalDistance() / totalTime();
		
		average = average* 3.6;
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	
		return average;
		
	}

	/*
	 * bicycling, <10 mph, leisure, to work or for pleasure 4.0 bicycling,
	 * general 8.0 bicycling, 10-11.9 mph, leisure, slow, light effort 6.0
	 * bicycling, 12-13.9 mph, leisure, moderate effort 8.0 bicycling, 14-15.9
	 * mph, racing or leisure, fast, vigorous effort 10.0 bicycling, 16-19 mph,
	 * racing/not drafting or >19 mph drafting, very fast, racing general 12.0
	 * bicycling, >20 mph, racing, not drafting 16.0
	 */

	// conversion factor m/s to miles per hour
	public static double MS = 2.236936;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal;
		
		//Energy Expended (kcal) = MET x Body Weight (kg) x Time (h)

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;
		

		// TODO - START
		if ( speedmph <10) {
			
			met = 4.0;
			
		}else if (10 <= speedmph && speedmph<=12) {
			
			met = 6.0;
		}else if (12 < speedmph && speedmph<=14) {
						
			met =8.0;
		
		}else if (14 < speedmph && speedmph<=16) {
			
			met =10.0;
		}else if (16 < speedmph && speedmph<=20) {
			
			met =12.0;
		}else if (speedmph > 20) {
			
			met =16.0;
		}
		
		//met = kcal * (1/weight) * (1/(secs/3600));
		kcal = met * weight * (secs/3600);
		
		
		
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		return kcal;
		
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO - START
		
		double [] avergS = new double [gpspoints.length-1];
		double [] kcalTab = new double [gpspoints.length-1];
		
		for (int i =0 ; i < gpspoints.length-1;i++) {
			
			avergS[i] = GPSUtils.speed(gpspoints[i], gpspoints[i+1]);
			kcalTab[i]= kcal(weight,gpspoints[i+1].getTime()-gpspoints[i].getTime(),avergS[i]);
			
			totalkcal = totalkcal + kcalTab[i];
		}
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		return totalkcal;
		
	}
	
	private static double WEIGHT = 80.0;
	
	public void displayStatistics() {

		System.out.println("==============================================");

		// TODO - START
		System.out.println("Total Time     :" +"      " +totalTime());
		System.out.println("Total distance :" +"      "+ GPSUtils.formatDouble(totalDistance()/1000) +" km"  );
		System.out.println("Total elevation:" +"      "+GPSUtils.formatDouble(totalElevation())+ " m");
		System.out.println("Max speed      :" +"      "+GPSUtils.formatDouble(maxSpeed())+ " km/t");
		System.out.println("Average speed  :" +"      "+GPSUtils.formatDouble(averageSpeed()) + " km/t");
		System.out.println("Energy         :" +"      "+totalKcal(WEIGHT) + " kcal");

		System.out.println("==============================================");
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
		
	}

}
