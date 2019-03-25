




import autoPlay.AutoPlay;
import io.vavr.Tuple2;
import pseudoraster.PSRaster;
import rasterdata.*;
import org.jetbrains.annotations.NotNull;
import snake.BodBarva;
import snakeTelo.GameOver;
import snakeTelo.Had;
import snakeTelo.Jidlo;
import snakeTelo.TeloHada;
import textMaker.TextMaker;
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
	private Had had;
	private TeloHada teloHada;
	private AutoPlay autoPlay;
	private Point2D pohyb;
	private GameOver gameOver;
	private TextMaker textMaker;




	public Canvas(final int width, final int height) {

		autoPlay = new AutoPlay();
		psRaster = new PSRaster(50,width,height);
		textMaker = new TextMaker();
		frame = new JFrame();

		frame.setLayout(new BorderLayout());
		frame.setTitle("Snake : " + this.getClass().getName());
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
		raster = psRaster.orezRastr(raster);
		raster = textMaker.textToText(raster,new Point2D(10,10),"Score:", Color.white,5);
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


		had = new Had(psRaster.getxPSRasteru(),psRaster.getyPSRasteru());


		teloHada = new TeloHada(psRaster);

		raster = psRaster.toRaster(raster,teloHada.getJidlo());
		raster = psRaster.toRaster(raster,had.getHadSeznam());


		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent o) {
				raster=pomraster;
				if (o.getKeyCode() == KeyEvent.VK_UP) {
					pohyb = new Point2D(0,-1);




				}

				if (o.getKeyCode() == KeyEvent.VK_DOWN) {
					pohyb = new Point2D(0,1);



				}

				if (o.getKeyCode() == KeyEvent.VK_LEFT) {
					pohyb = new Point2D(-1,0);



				}

				if (o.getKeyCode() == KeyEvent.VK_RIGHT) {
					pohyb = new Point2D(1,0);


				}

				if (o.getKeyCode() == KeyEvent.VK_L) {



				}

				if (o.getKeyCode() == KeyEvent.VK_SPACE) {




					pohyb=autoPlay.predikce(had,teloHada.getJidlo());

					System.out.println("AutoPlay");
				}

				had.setHadSeznam(teloHada.zvetseni(had,pohyb));
				raster = psRaster.toRaster(raster,teloHada.getJidlo());
				raster = psRaster.toRaster(raster,had.getHadSeznam());
				raster = textMaker.textToText(raster,new Point2D(130,10), Integer.toString(had.getHadSeznam().size()-5) , Color.white,5);

				if(teloHada.kousnuti(had)){
					System.err.println("Game over");
					gameOver = new GameOver(width,height);
					raster=gameOver.prohralJsi(raster,gameOver);
				}

				panel.repaint();

			}
		});

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