package br.org.funcate.glue.tool;

import br.org.funcate.glue.model.ScaleService;
import br.org.funcate.glue.model.canvas.GeneralTileSchema;

public class TileThreadTimer extends Thread {

	public void run() {
		
		long time = System.currentTimeMillis();
		System.out.println(time + " " + TileThreadTimeAuxiliar.wheelCounter);
		TileThreadTimeAuxiliar.zoomTimeStamp = time;
		TileThreadTimeAuxiliar.timeIsOn = true;

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (time == TileThreadTimeAuxiliar.zoomTimeStamp) {
			System.out.println("Running: " + time + " "
					+ TileThreadTimeAuxiliar.wheelCounter);
			System.out.println("1s");
		}
		TileThreadTimeAuxiliar.timeIsOn = false;	
		ScaleService.updateScaleSettings();
		GeneralTileSchema.generateTilesLists(false);

	}
}