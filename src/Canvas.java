




import pseudoraster.PSRaster;
import rasterdata.*;
import org.jetbrains.annotations.NotNull;
import snake.BodBarva;
import transforms.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * trida pro kresleni na platno: zobrazeni pixelu
 *
 * @author PGRF FIM UHK
 * @version 2017
 */




public class Canvas implements  MouseListener,
		MouseMotionListener, KeyListener{

	private final JFrame frame;
	private final JPanel panel;
	private final BufferedImage img;
	private @NotNull Raster<Color> raster;
	private @NotNull Raster<Color> pomraster;
	private final @NotNull Presenter<Graphics, Color> presenter;
	private Color cervena = new Color(255,0,0);
	private Color bila = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	private PSRaster psRaster;
	private List<BodBarva> bod = new ArrayList<>();




	public Canvas(final int width, final int height) {

		psRaster = new PSRaster(25,width,height);

		frame = new JFrame();

		frame.setLayout(new BorderLayout());
		frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);




/*
		raster = new FastRaster<>(img,
				color -> color.getRGB(), 	      //Color::getRGB,
				intColor -> new Color(intColor)); //Color::new);
		lineMaker = new HeWhoDrawsLinesNaively<>();
		presenter = new FastPresenter<>();
		lineMader = new HeWhoDrawsLinesRightly<>();
		/*/

		raster = new VavrRaster<>(width,height,black);
		presenter = new SlowPresenter<>(Color::getRGB);
		pomraster=raster;

//*/
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				present(g);
			}
		};


		panel.setPreferredSize(new Dimension(width, height));

		frame.add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		panel.requestFocus();
		panel.requestFocusInWindow();
		panel.addKeyListener(keyPressed());




		bod.add(new BodBarva(new Point2D(2,2),bila));
		bod.add(new BodBarva(new Point2D(0,0),Color.red));
		bod.add(new BodBarva(new Point2D(1,2),Color.blue));

		raster = psRaster.toRaster(raster,bod);
		draw();

		raster = raster.withValue(11,10,bila);
		panel.repaint();
	}

	private KeyListener keyPressed() {
		return null;
	}

	public void clear() {
		Graphics gr = img.getGraphics();
		gr.setColor(black);
		gr.fillRect(0, 0, img.getWidth(), img.getHeight());
	}

	public void present(final Graphics graphics) {

		presenter.present(graphics,raster);
	}

	public void draw() {
		//clear();
		raster = raster.withValue(10,10,bila);
		raster = psRaster.orezRastr(raster);
		panel.repaint();
	}

	public void start() {
		draw();
		panel.repaint();

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Canvas(920,920)::start);

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent o) {
		if (o.getKeyCode() == KeyEvent.VK_UP) {


			System.out.println("w");

		}

		if (o.getKeyCode() == KeyEvent.VK_DOWN) {


			System.out.println("s");

		}

		if (o.getKeyCode() == KeyEvent.VK_LEFT) {

			System.out.println("a");

		}

		if (o.getKeyCode() == KeyEvent.VK_RIGHT) {

			System.out.println("d");
		}

		if (o.getKeyCode() == KeyEvent.VK_L) {

			System.out.println("d");

		}


	}


	@Override
	public void keyReleased(KeyEvent e) {

	}


	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}//*/