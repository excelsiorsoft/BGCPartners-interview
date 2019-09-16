package com.excelsiorsoft.bgc_partners;

import static com.excelsiorsoft.bgc_partners.Spacebus3000.ARROW;
import static com.excelsiorsoft.bgc_partners.Spacebus3000.COLUMN;
import static com.excelsiorsoft.bgc_partners.Spacebus3000.MSG_TOO_FEW_ARGUMENTS;
import static com.excelsiorsoft.bgc_partners.Spacebus3000.MSG_TOO_MANY_ARGUMENTS;
import static com.excelsiorsoft.bgc_partners.Spacebus3000.USAGE;
import static com.excelsiorsoft.bgc_partners.Spacebus3000.YES;
import static com.excelsiorsoft.bgc_partners.Spacebus3000.NO;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Spacebus3000Test {

	private static final String EOL = System.getProperty("line.separator");
	private PrintStream console;
	private ByteArrayOutputStream bytes;

	@Before
	public void setUp() {
		bytes = new ByteArrayOutputStream();
		console = System.out;
		System.setOut(new PrintStream(bytes));
	}

	@After
	public void tearDown() {
		System.setOut(console);
	}

	@Test
	public void testAbortWhenInsufficientArgumentsSupplied() throws Exception {
		Spacebus3000.main();
		assertEquals(MSG_TOO_FEW_ARGUMENTS + EOL + USAGE + EOL, bytes.toString());
	}
	
	@Test
	public void testAbortWhenTooManyArgumentsSupplied() throws Exception {
		Spacebus3000.main("a", "b", "c", "d", "e");
		assertEquals(MSG_TOO_MANY_ARGUMENTS + EOL + USAGE + EOL, bytes.toString());
		
	}
	
	@Test
	public void testARI_GNS() throws Exception {
		String src = "Asteroid Research Institute";
		String dest = "Grand Nebula Spaceport";
		Spacebus3000.main("spacebusroutes.txt",src,dest);
		//assertEquals(src+ARROW+dest+COLUMN+YES+EOL,bytes.toString());
		assertEquals(YES+EOL,bytes.toString());
		
		//Asteroid Research Institute -> Grand Nebula Spaceport:yes
	}
	
	@Test
	public void testARI_GNS_ext() throws Exception {
		String src = "Asteroid Research Institute";
		String dest = "Grand Nebula Spaceport";
		Spacebus3000.main("spacebusroutes.txt",src,dest, "true");
		assertEquals(src+ARROW+dest+COLUMN+YES+EOL,bytes.toString());
		//assertEquals(YES+EOL,bytes.toString());
		
		//Asteroid Research Institute -> Grand Nebula Spaceport:yes
	}
	
	@Test
	public void testARI_HES() throws Exception {
		String src = "Asteroid Research Institute";
		String dest = "Heavy Element Spacemine";
		Spacebus3000.main("spacebusroutes.txt",src,dest);
		//assertEquals(src+ARROW+dest+COLUMN+NO+EOL,bytes.toString());
		assertEquals(NO+EOL,bytes.toString());
		
		//Asteroid Research Institute -> Heavy Element Spacemine:no
	}
	
	@Test
	public void testARI_HES_ext() throws Exception {
		String src = "Asteroid Research Institute";
		String dest = "Heavy Element Spacemine";
		Spacebus3000.main("spacebusroutes.txt",src,dest, "true");
		assertEquals(src+ARROW+dest+COLUMN+NO+EOL,bytes.toString());
		//assertEquals(NO+EOL,bytes.toString());
		
		//Asteroid Research Institute -> Heavy Element Spacemine:no
	}
	
	@Test
	public void testOCOF_ARI() throws Exception {
		String src = "Oort Cloud Observation Facility";
		String dest = "Asteroid Research Institute";
		Spacebus3000.main("spacebusroutes.txt",src,dest);
		//assertEquals(src+ARROW+dest+COLUMN+YES+EOL,bytes.toString());
		assertEquals(YES+EOL,bytes.toString());
		
		//Oort Cloud Observation Facility -> Asteroid Research Institute:yes
	}
	
	@Test
	public void testOCOF_ARI_ext() throws Exception {
		String src = "Oort Cloud Observation Facility";
		String dest = "Asteroid Research Institute";
		Spacebus3000.main("spacebusroutes.txt",src,dest, "true");
		assertEquals(src+ARROW+dest+COLUMN+YES+EOL,bytes.toString());
		//assertEquals(NO+EOL,bytes.toString());
		
		//Oort Cloud Observation Facility -> Asteroid Research Institute:yes
	}

	
	
	@Test
	public void testARI_Pluto() throws Exception {
		String src = "Asteroid Research Institute";
		String dest = "Pluto";
		Spacebus3000.main("spacebusroutes.txt",src,dest);
		//assertEquals(src+ARROW+dest+COLUMN+NO+EOL,bytes.toString());
		assertEquals(NO+EOL,bytes.toString());
		
		//Asteroid Research Institute -> Pluto:no
	}
	
	@Test
	public void testARI_Pluto_ext() throws Exception {
		String src = "Asteroid Research Institute";
		String dest = "Pluto";
		Spacebus3000.main("spacebusroutes.txt",src,dest, "true");
		assertEquals(src+ARROW+dest+COLUMN+NO+EOL,bytes.toString());
		//assertEquals(NO+EOL,bytes.toString());
		
		//Asteroid Research Institute -> Pluto:no
	}
	
	@Test
	public void testGNS_DRSH() throws Exception {
		String src = "Grand Nebula Spaceport";
		String dest = "Double Ring Space Habitat";
		Spacebus3000.main("spacebusroutes.txt",src,dest);
		//assertEquals(src+ARROW+dest+COLUMN+YES+EOL,bytes.toString());
		assertEquals(YES+EOL,bytes.toString());
		
		//Grand Nebula Spaceport -> Double Ring Space Habitat:yes
	}
	
	@Test
	public void testGNS_DRSH_ext() throws Exception {
		String src = "Grand Nebula Spaceport";
		String dest = "Double Ring Space Habitat";
		Spacebus3000.main("spacebusroutes.txt",src,dest, "true");
		assertEquals(src+ARROW+dest+COLUMN+YES+EOL,bytes.toString());
		//assertEquals(YES+EOL,bytes.toString());
		
		//Grand Nebula Spaceport -> Double Ring Space Habitat:yes
	}
	
	@Test
	public void testDRSH_GNS() throws Exception {
		String src = "Double Ring Space Habitat";
		String dest = "Grand Nebula Spaceport";
		Spacebus3000.main("spacebusroutes.txt",src,dest);
		//assertEquals(src+ARROW+dest+COLUMN+YES+EOL,bytes.toString());
		assertEquals(YES+EOL,bytes.toString());
		
		//Grand Nebula Spaceport -> Double Ring Space Habitat:yes
	}
	
	@Test
	public void testDRSH_GNS_ext() throws Exception {
		String src = "Double Ring Space Habitat";
		String dest = "Grand Nebula Spaceport";
		Spacebus3000.main("spacebusroutes.txt",src,dest, "true");
		assertEquals(src+ARROW+dest+COLUMN+YES+EOL,bytes.toString());
		//assertEquals(YES+EOL,bytes.toString());
		
		//Grand Nebula Spaceport -> Double Ring Space Habitat:yes
	}
	
	@Test
	public void testARI_BNSM() throws Exception {
		String src = "Asteroid Research Institute";
		String dest = "Blue Nova Space Market";
		Spacebus3000.main("spacebusroutes.txt",src,dest);
		//assertEquals(src+ARROW+dest+COLUMN+NO+EOL,bytes.toString());
		assertEquals(NO+EOL,bytes.toString());
		
		//Asteroid Research Institute -> Blue Nova Space Market:no
	}
	
	@Test
	public void testARI_BNSM_ext() throws Exception {
		String src = "Asteroid Research Institute";
		String dest = "Blue Nova Space Market";
		Spacebus3000.main("spacebusroutes.txt",src,dest, "true");
		assertEquals(src+ARROW+dest+COLUMN+NO+EOL,bytes.toString());
		//assertEquals(NO+EOL,bytes.toString());
		
		//Asteroid Research Institute -> Blue Nova Space Market:no
	}
	
	@Test
	public void testBNSM_Pluto() throws Exception {
		String src = "Blue Nova Space Market";
		String dest = "Pluto";
		Spacebus3000.main("spacebusroutes.txt",src,dest);
		//assertEquals(src+ARROW+dest+COLUMN+NO+EOL,bytes.toString());
		assertEquals(NO+EOL,bytes.toString());
		
		//Blue Nova Space Market -> Pluto:no
	}
	
	@Test
	public void testBNSM_Pluto_ext() throws Exception {
		String src = "Blue Nova Space Market";
		String dest = "Pluto";
		Spacebus3000.main("spacebusroutes.txt",src,dest, "true");
		assertEquals(src+ARROW+dest+COLUMN+NO+EOL,bytes.toString());
		//assertEquals(NO+EOL,bytes.toString());
		
		//Blue Nova Space Market -> Pluto:no
	}

}
