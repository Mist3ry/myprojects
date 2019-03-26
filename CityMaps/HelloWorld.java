package module1;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;

public class HelloWorld extends PApplet
{

	UnfoldingMap currentMap;
	UnfoldingMap map1;
	UnfoldingMap map2;

	public void setup() {
		//set window of Applet
		size(1000, 800, P2D);  
		this.background(100, 200, 200);
		
		// Select a map provider
		//AbstractMapProvider provider = new Google.GoogleTerrainProvider();
		AbstractMapProvider provider = new  OpenStreetMap.OpenStreetMapProvider();
		// Set a zoom level
		int zoomLevel = 10;		
		
		//create a map and zoom it to the Odessa city (my hometown) and La Jolla, CA
		map1 = new UnfoldingMap(this, 50, 50, 425, 700, provider);
		map2 = new UnfoldingMap(this, 550, 50, 425, 700, provider);
		map2.zoomAndPanTo(zoomLevel, new Location(46.469391f, 30.740883f));
	    map1.zoomAndPanTo(zoomLevel, new Location(32.9f, -117.2f));
		
		// This line makes the map interactive
		MapUtils.createDefaultEventDispatcher(this, map1);
		MapUtils.createDefaultEventDispatcher(this, map2);
	}


	public void draw() {
		map1.draw();
		map2.draw();
	}

	
}
