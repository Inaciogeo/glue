package br.org.funcate.glue.model.canvas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.model.CalculatorService;

public class ScaleRulerGraphics {

	public void paint(Graphics g) {
		int meterConversor = CalculatorService.getMeterConversor(AppSingleton.getInstance().getCanvasState().getProjection());
		double metersPerPixel;
		int scale = 10000000;
		int pixels;
		CanvasState state = AppSingleton.getInstance().getCanvasState();
		metersPerPixel = AppSingleton.getInstance().getCanvasState().getResolution() * meterConversor;

		int canvasHeight = state.getCanvasHeight();

		g.setFont(new Font("Verdana", Font.BOLD, 13));

		while (true) {
			pixels = (int) Math.round(scale / metersPerPixel);
			if (pixels >= 10 && pixels <= 300) {
				g.drawString(String.valueOf(scale) + " metros", pixels, canvasHeight - 26);
				break;
			}
			scale = scale / 10;
			if (scale <= 0)
				return;
		}

		g.setColor(Color.black);

		g.drawString("0", 7, canvasHeight - 26);

		g.fillRect(10, canvasHeight - 21, 2, 12);

		g.fillRect(12, canvasHeight - 18, pixels, 6);

		g.setColor(Color.white);
		g.fillRect(12, canvasHeight - 16, pixels, 2);

		g.setColor(Color.black);
		g.fillRect(pixels + 10, canvasHeight - 21, 2, 12);
	}
}
