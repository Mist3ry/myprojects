
import java.util.ArrayList;
import java.util.List;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library from internet
import parsing.ParseFeed;
public class EarthquakeCityMap extends PApplet {

	// It's to keep eclipse from generating a warning.
	//private static final long serialVersionUID = 1L;


	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	
	public void setup() {
		size(950, 600, OPENGL);
		//create a map with representation from OpenStreetMap, possible to use Google or another one
		map = new UnfoldingMap(this, 200, 20, 750, 500, new  OpenStreetMap.OpenStreetMapProvider());
		//set zoom level
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
	    List<Marker> markers = new ArrayList<Marker>();

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);

	    //it creates a new SimplePointMarker for each PointFeature
	    for (PointFeature eq: earthquakes) {
	    	SimplePointMarker a = new SimplePointMarker();
	    	a = createMarker(eq);
	    	markers.add(a);
	    }
	    
	    // Add the markers to the map so that they are displayed
	    map.addMarkers(markers);

	    

	    
	}
	
	private SimplePointMarker createMarker(PointFeature feature)
	{  

		//System.out.println(feature.getProperties());
		
		// Create a new SimplePointMarker at the location given by the PointFeature
		SimplePointMarker marker = new SimplePointMarker(feature.getLocation());
		
		//get the property "magnitude" from feature and parse it to float value
		Object magObj = feature.getProperty("magnitude");
		float mag = Float.parseFloat(magObj.toString());
		  
	    int yellow = color(255, 255, 0);
	    int blue = color(0,191,255);
	    int red = color(255,0,0);
	    //change the color of marker depends on the magnitude level
	    	if ( mag < 4 ) {
	    		marker.setColor(blue);
	    	}
	    	else if( mag < 4.9  ) {
	    		marker.setColor(yellow);
	    	}
	    	else {
	    		marker.setColor(red);
	    	}
	    return marker;
	}
	
	public void draw() {
	    background(10);
	    //draw a map in PApplet
	    map.draw();
	    //fill it with white color
	    fill(255,255,255);
	    //draw rectangle
	    rect(30, 20, 140, 300);
	    
	    addKey();
	}


	private void addKey() 
	{	
		//fill a rectangle with explanation of the lvl of magnitude
		fill(50);
		text("Earthquake key", 40, 60);
		fill(255,0,0);
		ellipse(40, 100, 15, 15);
		text("5.0+ Magnitude", 60, 105);
		fill(255,255,0);
		ellipse(40, 140, 10, 10);
		text("4.0+ Magnitude", 60, 145);
		fill(0,191,255);
		ellipse(40, 180, 5, 5);
		text("Below 4.0", 60, 185);
		
		
	}
}
