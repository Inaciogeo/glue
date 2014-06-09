package br.org.funcate.glue.model.canvas;

import java.net.HttpURLConnection;
import java.net.URL;

import br.org.funcate.glue.model.cache.TileType;
import br.org.funcate.glue.model.exception.GlueServerException;
import br.org.funcate.glue.service.TerraJavaClient;

/**
 * \class GoogleRegionBoxService This class contains google box definitions for
 * each continent \brief This class contains google box definitions for each
 * continent. \author Henrique and Ricardo \version 1.0 \date 20/08/2010
 * 
 */
abstract class GoogleRegionBoxService {

	private static long IP;
	/** < the respective client IP */

	static {

		try {
			// recebe e transforma o ip da máquina do usuário para um long
			URL url = new URL("http://automation.whatismyip.com/n09230945.asp");
			HttpURLConnection conection = (HttpURLConnection) url
					.openConnection();
			conection.setRequestProperty( "User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11" );
			conection.connect();
			java.io.BufferedReader page = new java.io.BufferedReader(
					new java.io.InputStreamReader(conection.getInputStream()));
			String myIP;
			if ((myIP = page.readLine()) != null) {
				String[] ip = myIP.split("\\.");
				IP = (long) (Integer.parseInt(ip[0]) * Math.pow(256, 3)
						+ Integer.parseInt(ip[1]) * Math.pow(256, 2)
						+ Integer.parseInt(ip[2]) * 256 + Integer
						.parseInt(ip[3]));
			} else {
				IP = 0;
			}
			page.close();
		} catch (Exception e) {
			IP = 0;
		}
	}

	// ! It returns an object ImageSourceDefinition with the specific box
	// according to the user's continent, it will be detected by user's IP
	static ImageSourceDefinition getImageSourceDefinition() {
		String region = "Brazil";
		if (IP != 0) {
			try {
				region = TerraJavaClient.getContinent(IP);
			} catch (GlueServerException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				region = "Brazil";
			}
		}
		if (region.equals("Brazil"))
			return new ImageSourceDefinition(-1.1339586020168554E7,
					-4618019.500879687, -577252.4376099613, 958826.0828097658,
					null, TileType.GOOGLE, null, null);
		if (region.equals("South America"))
			return new ImageSourceDefinition(-16319611.28700703,
					-8120669.8850214835, 3639625.5388289057,
					1702405.4939683601, null, TileType.GOOGLE, null, null);
		if (region.equals("North America"))
			return new ImageSourceDefinition(-20057076.222041015,
					-97839.39620507881, 58703.63772304687, 9881779.016712891,
					null, TileType.GOOGLE, null, null);
		if (region.equals("Europe"))
			return new ImageSourceDefinition(-3571137.9614853514,
					4089686.7613722654, 6408480.451432616, 9001224.450867187,
					null, TileType.GOOGLE, null, null);
		if (region.equals("Asia"))
			return new ImageSourceDefinition(-273950.30937421875,
					58703.63772304687, 19685286.51646172, 9881779.016712891,
					null, TileType.GOOGLE, null, null);
		if (region.equals("Oceania"))
			return new ImageSourceDefinition(97839.39620507811,
					-7357522.594621874, 20057076.222041015, 2465552.7843679693,
					null, TileType.GOOGLE, null, null);
		if (region.equals("Africa"))
			return new ImageSourceDefinition(-7651040.783237109,
					-4794130.414048828, 12308196.042598827, 5028944.9649410155,
					null, TileType.GOOGLE, null, null);
		return null;
	}
}
