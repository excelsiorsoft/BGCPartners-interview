package com.excelsiorsoft.bgc_partners;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Spacebus3000 {

	static final String NO = "no";
	static final String YES = "yes";
	static final String COLUMN = ":";
	static final String ARROW = " -> ";
	static final String USAGE = "Usage:\n\tjava Spacebus3000 src dest";
	static final String MSG_TOO_FEW_ARGUMENTS = "too few arguments";
	static final String MSG_TOO_MANY_ARGUMENTS = "too many arguments";
	private static final String COMMA_DELIMITER = ",";
	

	private final Map<String, HashSet<String>> routes = new ConcurrentHashMap<>();
	private final Set<String> visited = new HashSet<>();
	
	private static Spacebus3000 BUS = new Spacebus3000();

	private Spacebus3000() {super();};
	
	private void addEdge(final String srcNode, final String destNode) {
		HashSet<String> adjacentNodes = routes.get(srcNode);
		if (adjacentNodes == null) {
			adjacentNodes = new HashSet<>();
			routes.put(srcNode, adjacentNodes);
		}
		adjacentNodes.add(destNode);
	}

	public void addRoute(final String srcNode, final String destNode) {
		addEdge(srcNode, destNode);
		addEdge(destNode, srcNode);
	}

	public String runsBetween(final String srcNode, final String destNode) {
		visited.clear();
		return this.isConnected(srcNode, destNode) ? YES : NO;
	}

	private boolean isConnected(final String srcNode, final String destNode) {

		boolean result = false;

		if (srcNode == null || destNode == null || srcNode.isEmpty() || destNode.isEmpty())
			return false;

		visited.add(srcNode);

		Set<String> adjacents = routes.get(srcNode);
		if (adjacents == null) {
			result = false;
		} else if (adjacents.contains(destNode)) {
			return true;
		} else {
			for (String adjacent : adjacents) {
				if (visited.contains(adjacent))
					continue;
				return isConnected(adjacent, destNode);
			}
		}
		return result;
	}

	private static boolean validate(final String... args) {
		boolean result = true;
		if (args.length < 3) {
			System.out.println(MSG_TOO_FEW_ARGUMENTS);
			System.out.println(USAGE);
			result = false;
		} else if (args.length > 4) {
			System.out.println(MSG_TOO_MANY_ARGUMENTS);
			System.out.println(USAGE);
			result = false;
		}
		else if (args.length == 3 || args.length == 4) {
		try {
			Spacebus3000.class.getClassLoader().getResource(args[0]).getPath();
		} catch (Exception e) {
			System.out.println("Make sure first argument points to a valid file: " + args[0]);
			result = false;
		}
	}
		return result;
	}

	public static void main(final String... args) throws Exception {
		// System.out.println("Hello Gallaxy!");

		
/*		  Spacebus3000 bus = new Spacebus3000(); 
		  bus.addRoute("Infrared Spacehub","Grand Nebula Spaceport"); 
		  bus.addRoute("Blue Nova Space Market","Heavy Element Spacemine"); 
		  bus.addRoute("Asteroid Research Institute","Oort Cloud Observation Facility");
		  bus.addRoute("Infrared Spacehub", "Oort Cloud Observation Facility");
		  bus.addRoute("Oort Cloud Observation Facility", "Double Ring Space Habitat");
		  
		  System.out.println("ARI -> GNS: "+bus.runsBetween("Asteroid Research Institute",
		  "Grand Nebula Spaceport"));
		  System.out.println("ARI -> HES: "+bus.runsBetween("Asteroid Research Institute",
		  "Heavy Element Spacemine"));
		  System.out.println("OCOF -> ARI: "+bus.runsBetween("Oort Cloud Observation Facility"
		  ,"Asteroid Research Institute"));
		  System.out.println("ARI -> Pluto:"+bus.runsBetween("Asteroid Research Institute","Pluto"));
		  System.out.println("GNS -> DRSH: "+bus.runsBetween("Grand Nebula Spaceport"
		  ,"Double Ring Space Habitat"));
		  System.out.println("DRSH -> GNS: "+bus.runsBetween("Double Ring Space Habitat",
		  "Grand Nebula Spaceport"));*/
		 
		if (validate(args)) {

			if(BUS.routes.isEmpty()) {
				BUS = primeRoutesRegistry(parseRoutesFile(Spacebus3000.class.getClassLoader().getResource(args[0]).getPath()), BUS);
			}

			final String src = args[1];
			final String dest = args[2];
			final boolean expandedOutput = args.length == 4 ? Boolean.parseBoolean(args[3]) : false;

			if (expandedOutput) {
				System.out.println(src + ARROW + dest + COLUMN + BUS.runsBetween(src, dest));
			} else {
				System.out.println(BUS.runsBetween(src, dest));
			}
		}	
	}
	
	private static Spacebus3000 primeRoutesRegistry(final List<List<String>> records, final Spacebus3000 bus) {
		records.stream().forEach(el -> bus.addRoute(el.get(0), el.get(1)));
		return bus;
	}

	private static List<List<String>> parseRoutesFile(final String path) throws Exception {

		final List<List<String>> records = new ArrayList<List<String>>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.trim().split(COMMA_DELIMITER);
		        List<String> trimmed = new ArrayList<String>();
		        for(String value: values) {
		        	trimmed.add(value.trim());
		        }
		        records.add(trimmed);
		    }
		}
		return records;
	}
	

}
