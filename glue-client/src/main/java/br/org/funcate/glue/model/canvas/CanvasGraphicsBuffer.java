package br.org.funcate.glue.model.canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import br.org.funcate.glue.main.AppSingleton;
import br.org.funcate.glue.utilities.patterns.CanvasObserver;
import br.org.funcate.glue.utilities.patterns.CanvasSubject;

public class CanvasGraphicsBuffer implements GraphicsPainter, CanvasSubject {

	private ScaleRulerGraphics scaleRuler;

	private Graphics tilesBufferGraph;
	private BufferedImage tilesBuffer;

	private Graphics canvasBufferGraph;
	private BufferedImage canvasBuffer;

	private BufferedImage googleCopyrightText;

	private static ToolGraphics toolGraphics;

	private ArrayList<CanvasObserver> observer;

	private boolean isDragMode;

	private Map<BufferEnum, BufferedImage> bufferMap;

	private int xImagePosition = 0, yImagePosition = 0;

	@SuppressWarnings("unused")
	private BufferedImage geopxIcon;

	@SuppressWarnings("unused")
	private static BufferedImage marker;

	@SuppressWarnings("unused")
	private static BufferedImage imgMark;

	CanvasGraphicsBuffer(int canvasWidth, int canvasHeight) {
		scaleRuler = new ScaleRulerGraphics();
		observer = new ArrayList<CanvasObserver>();
		bufferMap = new HashMap<BufferEnum, BufferedImage>();
		redefineBuffers(canvasWidth, canvasHeight);
	}

	public void redefineBuffers(int canvasWidth, int canvasHeight) {
		tilesBuffer = new BufferedImage(canvasWidth - 1, canvasHeight, 1);
		tilesBufferGraph = tilesBuffer.getGraphics();
		canvasBuffer = new BufferedImage(canvasWidth - 1, canvasHeight - 1, 1);
		canvasBufferGraph = canvasBuffer.getGraphics();
		canvasBufferGraph.setColor(Color.white);
		canvasBufferGraph.fillRect(-canvasWidth, -canvasHeight,
				canvasWidth * 3, canvasHeight * 3);
		tilesBufferGraph.setColor(Color.white);
		tilesBufferGraph.fillRect(-canvasWidth, -canvasHeight, canvasWidth * 3,
				canvasHeight * 3);
		this.createBuffer(canvasWidth, canvasHeight, BufferEnum.EDITION);
		this.createBuffer(canvasWidth, canvasHeight, BufferEnum.FEEDBACK);
		this.createBuffer(canvasWidth, canvasHeight, BufferEnum.TEXT);
	}

	public void createBuffer(int width, int height, BufferEnum bufferId) {
		BufferedImage buffer = new BufferedImage(width, height,
				BufferedImage.BITMASK);
		buffer.setAccelerationPriority(1.0f);
		bufferMap.put(bufferId, buffer);
	}

	/**
	 * This method return the BufferedImage represented by {@link BufferEnum}
	 * 
	 * @param bufferId
	 * @return BufferedImage
	 */
	public BufferedImage getBuffer(BufferEnum bufferId) {
		return bufferMap.get(bufferId);
	}

	public void paint(Graphics g) {
		
		if (isDragMode) {
			GeneralTileSchema.generateTilesLists(false);
		}

		if (!CanvasService.isEmptyBox()) {
			scaleRuler.paint(canvasBufferGraph);
		}

		canvasBufferGraph.drawImage(this.getBuffer(BufferEnum.EDITION),
				xImagePosition, yImagePosition, null);
		canvasBufferGraph.drawImage(this.getBuffer(BufferEnum.FEEDBACK),
				xImagePosition, yImagePosition, null);
		canvasBufferGraph.drawImage(this.getBuffer(BufferEnum.TEXT),
				xImagePosition, yImagePosition, null);
		
		CanvasState state = AppSingleton.getInstance().getCanvasState();
		
		if (CanvasService.isGoogleActive() && !CanvasService.isEmptyBox()) {
			canvasBufferGraph.drawImage(googleCopyrightText,state.getCanvasWidth() - 490,
					state.getCanvasHeight() - 17, null);
		}

		if (CanvasService.hasBDImageSource() && !CanvasService.isEmptyBox()) {
			@SuppressWarnings("unused")
			int yTextPosition = 22;

			if (CanvasService.isGoogleActive()) {
				yTextPosition = 39;
			}
		}
	
		g.drawImage(canvasBuffer, 0, 0, null);

		if (toolGraphics != null) {
			toolGraphics.paint(g);
		}
		canvasBuffer.flush();
		isDragMode = false;
	}

	/**
	 * Creates the Google Copyright text that will be showed on the canvas lower
	 * right corner
	 */
	public void createImgGoogleCopyright(Color googleCopyrightTextColor) {
//		googleCopyrightText = new BufferedImage(490, 15, BufferedImage.BITMASK);
//		Graphics2D g = (Graphics2D) googleCopyrightText.getGraphics();
//		g.setPaint(new GradientPaint(10, 10, new Color(255, 255, 255, 0), 33,
//				30, new Color(255, 255, 255, 200), false));
//		g.fillRect(0, 166, 492, 20);
//		g.setColor(googleCopyrightTextColor);
//		g.setFont(new Font("Default", 0, 12));
//		g.drawString("Imagem de fundo com Copyright de terceiros.",52, 12);
//		if (googleCopyrightTextColor == Color.white)
//			g.setColor(Color.white);
//		else
//			g.setColor(Color.blue);
//			g.drawString("leia a licen�a.", 402, 12);
//			g.drawLine(402, 13, 492, 13);
	}
	public void createMarker(){
		try{
			marker = new BufferedImage(32, 32, BufferedImage.TYPE_INT_BGR);
			imgMark = ImageIO.read(new File("../glue-client/src/main/java/br/org/funcate/glue/image/Flag-2.png"));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteMarker(){
		try{
			marker = null;
			imgMark = null;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void drawTileImage(BufferedImage bufferedImage, int dx1, int dy1,
			int dx2, int dy2, int sx1, int sy1, int sx2, int sy2) {	
		tilesBufferGraph.drawImage(bufferedImage, dx1, dy1, dx2, dy2, sx1, sy1,
				sx2, sy2, null);		
	}

	public void repaintTilesBufferOnCanvas() {
		canvasBufferGraph.drawImage(tilesBuffer, 0, 0, null);
	}
	
	public void resetEditionBuffers() {
		CanvasState state = AppSingleton.getInstance().getCanvasState();
		this.createBuffer(state.getCanvasWidth(), state.getCanvasHeight(), BufferEnum.EDITION);
		this.createBuffer(state.getCanvasWidth(), state.getCanvasHeight(), BufferEnum.FEEDBACK);
		this.createBuffer(state.getCanvasWidth(), state.getCanvasHeight(), BufferEnum.TEXT);
	}

	public Graphics getTilesBufferGraph() {
		return tilesBufferGraph;
	}

	public BufferedImage getTilesBuffer() {
		return tilesBuffer;
	}

	public void drawWhiteTile(int x, int y, int width, int height) {
		tilesBufferGraph.setColor(Color.white);
		tilesBufferGraph.fillRect(x, y, width, height);
	}

	public void drawImageNullCanvas(Image nullImage, int canvasWidth,
			int canvasHeight) {
		canvasBufferGraph.setColor(Color.white);
		canvasBufferGraph.fillRect(0, 0, canvasWidth, canvasHeight);

		int imgWidth = nullImage.getWidth(null);
		int imgHeight = nullImage.getHeight(null);

		canvasBufferGraph.drawImage(nullImage, (canvasWidth / 2)
				- (imgWidth / 2), (canvasHeight / 2) - (imgHeight / 2), null);

		notifyObservers();
	}

	public ToolGraphics getToolGraphics() {
		return toolGraphics;
	}

	public void setToolGraphics(ToolGraphics toolGraphics) {
		CanvasGraphicsBuffer.toolGraphics = toolGraphics;
	}

	public BufferedImage getCanvasBuffer() {
		return canvasBuffer;
	}

	@Override
	public void addObserver(CanvasObserver canvasObserver) {
		observer.add(canvasObserver);
	}

	@Override
	public void notifyObservers() {
		for (CanvasObserver o : observer) {
			o.update(this);
		}
	}

	void redefineBuffers() {
		CanvasState state = AppSingleton.getInstance().getCanvasState();
		int canvasWidth = state.getCanvasWidth();
		int canvasHeight = state.getCanvasHeight();
		this.redefineBuffers(canvasWidth, canvasHeight);
	}

	public void setDragMode(boolean b) {
		isDragMode = b;
	}

	public boolean isDragMode() {
		return isDragMode;
	}

	public int getEditionY() {
		return yImagePosition;
	}

	public void setEditionY(int editionY) {
		this.yImagePosition = editionY;
	}

	public int getEditionX() {
		return xImagePosition;
	}

	public void setEditionX(int editionX) {
		this.xImagePosition = editionX;
	}

	public void incrementEditionX(int increment) {
		this.xImagePosition += increment;
	}

	public void incrementEditionY(int increment) {
		this.yImagePosition += increment;
	}
}
