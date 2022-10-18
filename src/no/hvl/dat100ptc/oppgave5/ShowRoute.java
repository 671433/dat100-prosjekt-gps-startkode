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
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
	
		double ystep;
		
		// TODO - START
		double maxLat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minLat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		
		

		 ystep = MAPXSIZE / (Math.abs(maxLat - minLat)); 

		return ystep;
		
		//throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}

	public void showRouteMap(int ybase) {

		// TODO - START
		double xstep = this.xstep();
		double ystep = this.ystep();
		
		double minstLong = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double minstLat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		
		int pointsLength = gpspoints.length;
		double[] longt = new double [pointsLength];
		double[] latt = new double [pointsLength];
		
		for (int i =0; i<pointsLength;i++) {
			longt [i]=gpspoints[i].getLongitude() - minstLong;
		}
		for (int i =0; i<pointsLength;i++) {
			
			latt[i]= gpspoints[i].getLatitude() -minstLat;
			
			int x = (int) (MARGIN + (longt[i] * xstep));
			int y = (int) (ybase-(latt[i] * ystep));
			
			if (i == pointsLength) {
				
				setColor(0, 0, 255);
				fillCircle(x, y, 5);
			}else {
				
				int nestex = (int) (MARGIN + (longt[i] * xstep));
				int nestey = (int) (ybase-(latt[i+1] * ystep));
			
			if (i==0) {
				setColor(255, 0, 0);
				fillCircle(x, y, 5);
				drawLine(x, y, nestex, nestey);
			}else {
				
				fillCircle(x, y, 3);
				drawLine(x, y, nestex, nestey);
			}
			
			}
		}
		
		
		
		
		
		//throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		// TODO - START
		
		throw new UnsupportedOperationException(TODO.method());
		
		// TODO - SLUTT;
	}

}
