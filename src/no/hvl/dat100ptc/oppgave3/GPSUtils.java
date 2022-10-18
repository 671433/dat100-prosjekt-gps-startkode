package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import java.time.Duration;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;

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

		// TODO - START
		min = da [0];
		
		for ( double d : da) {
			
			if (d < min) {
				min =d;
			}
			
		}

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUT
		return min;

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double [] latitudes = new double [gpspoints.length];
		
		for (int i = 0 ;  i < gpspoints.length; i++) {
			latitudes[i]= gpspoints[i].getLatitude();
		}
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
		return latitudes;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		// TODO - START
		double [] longitudes = new double [gpspoints.length] ;
		
		for ( int i = 0 ; i < gpspoints.length; i++) {
			longitudes [i]= gpspoints[i].getLongitude();
		}

		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
		return longitudes;

	}
	


	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;
		
		latitude1 =gpspoint1.getLatitude();
		latitude2 =gpspoint2.getLatitude();
		longitude1 = gpspoint1.getLongitude();
		longitude2 = gpspoint2.getLongitude();
		// TODO - START
		double latitudeO1 = toRad(latitude1);
		double latitudeO2 = toRad(latitude2);
		double latitudeD = toRad(latitude2 - latitude1);
		double longitudeD = toRad(longitude2-longitude1);
		
		double a = Math.sin(latitudeD/2)*Math.sin(latitudeD/2)+
				  Math.cos(latitudeO1) * Math.cos(latitudeO2) *
				  Math.sin(longitudeD/2) * Math.sin(longitudeD/2);
		
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		       d = R * c;
		

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		       return d;

	}
	public static Double toRad(Double value) {
		 return value * Math.PI / 180;
		 }

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs = (gpspoint2.getTime() - gpspoint1.getTime());
		double speed = distance(gpspoint1, gpspoint2)/secs;
				
		// TODO - START
		
		//double distanceHere = GPSUtils.distance(gpspoint1, gpspoint2);
		
		speed = (speed*60*60)/1000 ;
		
		//throw new UnsupportedOperationException(TODO.method());
		// TODO - SLUTT
		return speed ;

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START
		double hours = secs /3600;
		double minutes = (secs %3600)/60;
		double secounds = secs % 60;
		
		//timestr = String.format("|%1.2d|"+TIMESEP+"%02d"+TIMESEP+"%02d",hours, minutes,secounds);

		timestr = String.format("%02d:%02d:%02d", hours,minutes,secounds);
		

		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
		return timestr;

	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;		
	
	
	//str = String.format("%5.2f", d );
		 
		    str = String.format("%.2f",d);
		 
		        //return str= String.format("%s",d);}
		
		

		// TODO - START

		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		return str;
	}
}
