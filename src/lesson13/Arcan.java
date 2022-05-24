package lesson13;


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.Stroke;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Arcan extends JComponent {
	private static final long 		serialVersionUID = 4066983457124156696L;	// <1> 
	private static final double		WINDOW_WIDTH = 80;
	private static final double		WINDOW_HEIGHT = 60;
	private static final double		BRICK_WIDTH = 2;
	private static final double		BRICK_HEIGHT = 1;
	private static final double		BALL_SIZE = 1;
	private static final double		BALL_SPEED = 0.2;
	private static final float		LINE_WIDTH = 0.1f;
	private static final double		BARRIER_WIDTH = 1;
	private static final double		BARRIER_HEIGHT = 5;
	private static final double		BARRIER_SPEED = 0.2;
	private static final long		ACTION_LOOP = 30;

	public enum Terminal {
		PREPARE,
		ARROW_UP_PRESSED,
		ARROW_UP_RELEASED,
		ARROW_DOWN_PRESSED,
		ARROW_DOWN_RELEASED,
		ARROW_ESCAPE,
		ARROW_ENTER,
		CRUSH;
	}

	public enum State {
		INITIAL,
		STARTED,
		PAUSED,
		ENDED;
	}
	
	private final JLabel		message = new JLabel();
	private final JTextField	score = new JTextField();
	private final Timer			timer = new Timer(true); 
	
	private State				state = State.INITIAL;
	private TimerTask			tt;
	private int					crushes = 0;
	
	private double				ballX, ballY;
	private double				ballDX, ballDY;
	private double				barrierY;
	private double				barrierDY;
	
	public Arcan() {
		setLayout(null);
		setFocusable(true);
		requestFocusInWindow();
		
		addKeyListener(new KeyListener() {
			@Override public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_UP : automat(Terminal.ARROW_UP_RELEASED); break;
					case KeyEvent.VK_DOWN : automat(Terminal.ARROW_DOWN_RELEASED); break;
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_ENTER : automat(Terminal.ARROW_ENTER); break;
					case KeyEvent.VK_ESCAPE : automat(Terminal.ARROW_ESCAPE); break;
					case KeyEvent.VK_UP : automat(Terminal.ARROW_UP_PRESSED); break;
					case KeyEvent.VK_DOWN : automat(Terminal.ARROW_DOWN_PRESSED); break;
				}
			}
		});
		addComponentListener(new ComponentListener() {
			@Override public void componentShown(ComponentEvent e) {}
			@Override public void componentMoved(ComponentEvent e) {}
			@Override public void componentHidden(ComponentEvent e) {}
			
			@Override
			public void componentResized(ComponentEvent e) {
				message.setBounds(0, getHeight()/2, getWidth(), 20);
				score.setBounds(getWidth()-50, 20, 40, 30);
			}
		});
		
		message.setForeground(Color.MAGENTA);
		
		score.setEditable(false);
		score.setForeground(Color.YELLOW);
		score.setBackground(Color.BLUE);
		score.setHorizontalAlignment(JTextField.RIGHT);
		score.setFont(new Font("Monospace",Font.BOLD,24));
		
		add(message);
		add(score);
		automat(Terminal.PREPARE);
	}
	
	private void automat(final Terminal terminal) {
		switch (state) {
			case INITIAL	:
				message.setText("Press Ener to start...");
				score.setText("0");
				switch (terminal) {
					case ARROW_ENTER :
						prepareInitials();
						state = State.STARTED;
						message.setText("");
						startAction();
						break;
				}
				break;
			case STARTED	:
				switch (terminal) {
					case ARROW_ESCAPE :
						pauseAction();
						state = State.PAUSED;
						message.setText("Escape pressed. Press Enter to continue...");
						break;
					case CRUSH :
						stopAction();
						crushes++;
						state = State.ENDED;
						message.setText("Crush!!! Press enter to start again or Escape to exit...");
						score.setText(""+crushes);
						break;
					case ARROW_UP_PRESSED :
						barrierDY = BARRIER_SPEED;
						break;
					case ARROW_UP_RELEASED :
						barrierDY = 0;
						break;
					case ARROW_DOWN_PRESSED :
						barrierDY = -BARRIER_SPEED;
						break;
					case ARROW_DOWN_RELEASED :
						barrierDY = 0;
						break;
				}
				break;
			case PAUSED		:
				switch (terminal) {
					case ARROW_ENTER :
						resumeAction();
						state = State.STARTED;
						message.setText("");
						break;
				}
				break;
			case ENDED		:
				switch (terminal) {
					case ARROW_ENTER :
						prepareInitials();
						state = State.STARTED;
						message.setText("");
						startAction();
						break;
					case ARROW_ESCAPE :
						System.exit(0);
						break;
				}
				break;
			default:
				break;
		}
	}

	private void prepareInitials() {
		ballX = BRICK_WIDTH;
		ballY = WINDOW_HEIGHT/2;
		ballDX = BALL_SPEED;
		ballDY = BALL_SPEED;
		barrierY = WINDOW_HEIGHT/2;
		barrierDY = 0;
	}

	private void startAction() {
		tt = new TimerTask() {
			@Override
			public void run() {
				tick();
			}
		};
		timer.schedule(tt, ACTION_LOOP, ACTION_LOOP);
	}

	private void pauseAction() {
		tt.cancel();
		timer.purge();
	}

	private void resumeAction() {
		startAction();
	}

	private void stopAction() {
		pauseAction();
	}

	private void tick() {
		if (ballY + ballDY <= BRICK_HEIGHT || ballY + ballDY >= WINDOW_HEIGHT - BRICK_HEIGHT) {
			ballDY = -ballDY;
		}
		if (ballX + ballDX >= WINDOW_WIDTH - BRICK_WIDTH) {
			if (ballY < barrierY - BARRIER_HEIGHT/2 || ballY > barrierY + BARRIER_HEIGHT/2) {
				automat(Terminal.CRUSH);
			}
			else {
				ballDX = -ballDX;
			}
		}
		else if (ballX + ballDX <= BRICK_WIDTH) {
			ballDX = -ballDX;
		}
		ballX += ballDX;
		ballY += ballDY;
		barrierY += barrierDY;
		repaint();
	}
	
	@Override
	public void paintComponent(final Graphics g) {	// <2>
		final Graphics2D		g2d = (Graphics2D)g;
		final AffineTransform	oldAt = g2d.getTransform();
		final AffineTransform	at = pickCoordinates(g2d);

		g2d.setTransform(at);
		fillBackground(g2d);
		
		paintWall(g2d);
		
		paintBall(g2d, ballX, ballY);
		paintBarrier(g2d, WINDOW_WIDTH - BRICK_WIDTH, barrierY);
		
		g2d.setTransform(oldAt);
	}

	private AffineTransform pickCoordinates(final Graphics2D g2d) {	// <3>
		final Dimension			screenSize = this.getSize();
		final AffineTransform	result = new AffineTransform();

		result.scale(screenSize.getWidth()/WINDOW_WIDTH, -screenSize.getHeight()/WINDOW_HEIGHT);
		result.translate(0, -WINDOW_HEIGHT);
		return result;
	}
	
	
	private void fillBackground(final Graphics2D g2d) {		// <4>
		final Rectangle2D.Double	r2d = new Rectangle2D.Double(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
		final Color					oldColor = g2d.getColor();

		g2d.setColor(Color.BLACK);
		g2d.fill(r2d);
		g2d.setColor(oldColor);
	}

	private void paintBrick(final Graphics2D g2d, final double x, final double y) {
		final Rectangle2D.Double	r2d = new Rectangle2D.Double(0,0,BRICK_WIDTH,BRICK_HEIGHT);
		final Color					oldColor = g2d.getColor();
		final Stroke				oldStroke = g2d.getStroke();
		final AffineTransform		oldAt = g2d.getTransform();
		final AffineTransform		newAt = new AffineTransform(oldAt);
		
		newAt.translate(x, y);
		g2d.setTransform(newAt);
		g2d.setColor(Color.RED);
		g2d.fill(r2d);
		g2d.setColor(Color.GRAY);
		g2d.setStroke(new BasicStroke(LINE_WIDTH));
		g2d.draw(r2d);
		g2d.setStroke(oldStroke);
		g2d.setColor(oldColor);
		g2d.setTransform(oldAt);
	}
	
	private void paintHorizontalWall(final Graphics2D g2d, double x, double y) {
		for (double deltaX = 0; deltaX < 40; deltaX++) {
			paintBrick(g2d,x+deltaX*BRICK_WIDTH,y);
		}
	}

	private void paintVerticalWall(final Graphics2D g2d, double x, double y) {
		for (double deltaY = 0; deltaY < 60; deltaY++) {
			paintBrick(g2d,x,y+deltaY*BRICK_HEIGHT);
		}
	}

	private void paintBall(final Graphics2D g2d, double x, double y) {
		final RadialGradientPaint	rgp = new RadialGradientPaint(0.0f, 0.0f, (float)(0.75f*BALL_SIZE), new float[]{0.0f, (float)BALL_SIZE}, new Color[]{Color.WHITE, Color.GRAY});
		final Color					oldColor = g2d.getColor();
		final Stroke				oldStroke = g2d.getStroke();
		final Ellipse2D.Double		ell = new Ellipse2D.Double(-BALL_SIZE/2,-BALL_SIZE/2,BALL_SIZE,BALL_SIZE);
		final Paint					oldPaint = g2d.getPaint();
		final AffineTransform		oldAt = g2d.getTransform();
		final AffineTransform		newAt = new AffineTransform(oldAt);
		
		g2d.setPaint(rgp);
		newAt.translate(x, y);
		g2d.setTransform(newAt);
		g2d.fill(ell);
		g2d.setColor(Color.GREEN);
		g2d.setStroke(new BasicStroke(LINE_WIDTH));
		g2d.draw(ell);
		g2d.setPaint(oldPaint);
		g2d.setStroke(oldStroke);
		g2d.setColor(oldColor);
		g2d.setTransform(oldAt);
	}
	
	private void paintWall(final Graphics2D g2d) {
		paintVerticalWall(g2d,0,0);
		paintHorizontalWall(g2d,2,0);
		paintHorizontalWall(g2d,2,59);
	}

	private void paintBarrier(final Graphics2D g2d, final double x, final double y) {
		final Rectangle2D.Double	r2d = new Rectangle2D.Double(-BARRIER_WIDTH/2,-BARRIER_HEIGHT/2,BARRIER_WIDTH,BARRIER_HEIGHT);
		final Color					oldColor = g2d.getColor();
		final Stroke				oldStroke = g2d.getStroke();
		final AffineTransform		oldAt = g2d.getTransform();
		final AffineTransform		newAt = new AffineTransform(oldAt);
		
		newAt.translate(x, y);
		g2d.setTransform(newAt);
		g2d.setColor(Color.CYAN);
		g2d.fill(r2d);
		g2d.setStroke(new BasicStroke(LINE_WIDTH));
		g2d.setColor(Color.WHITE);
		g2d.draw(r2d);
		g2d.setStroke(oldStroke);
		g2d.setColor(oldColor);
		g2d.setTransform(oldAt);
	}
	
	public static void main(String[] args) {
		final JFrame	frame = new JFrame("Arcan ");
		
		frame.setPreferredSize(new Dimension(800,600));
		frame.getContentPane().add(new Arcan(),BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}