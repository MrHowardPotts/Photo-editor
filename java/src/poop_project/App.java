package poop_project;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.Stroke;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import poop_project.BasicOperation;

import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class App extends Frame {

	Image IMAGE = null;
	Panel LAYERS = new Panel(new GridLayout(7, 3));
	Panel SELECTIONS = new Panel(new GridLayout(7, 3));
	int x, y, xx, yy;
	int lineX, lineY, lineWidth, lineHeight;

	boolean pressed = false;

	// APP()-----------------------------------------------------------
	public App() {
		super("App");
		setSize(new Dimension(1280, 900));
//		setSize(new Dimension(1024, 768));
		addListen();
		addMenu();
//		add(img,BorderLayout.CENTER);
		add(options(), BorderLayout.SOUTH);
		setVisible(true);
	}
	// APP()_END--------------------------------------------------------

	// OPTIONS_PANEL-----------------------------------------------------------
	private Panel options() {
		Panel panel = new Panel(new GridLayout(1, 2));
		Panel pan = new Panel(new GridLayout(1, 2));
		LAYERS.setBackground(Color.CYAN);
		SELECTIONS.setBackground(Color.GREEN);

		pan.add(new Label("layers:"));
		LAYERS.add(pan);
		SELECTIONS.add(new Label("selections:"));

		panel.add(LAYERS);
		panel.add(SELECTIONS);
		return panel;
	}
	// OPTIONS_PANEL_END--------------------------------------------------------

	// POPOUT_WINDOW_DIALOG-----------------------------------------------------------
	class Popout extends Dialog {
		TextField text = new TextField();

		Button button = new Button("done");
		ActionListener exit = e -> {
			dispose();
		};

		public Popout(Frame frame) { // constructor for Frame
			super(frame, "Enter file location", true);
			setSize(new Dimension(210, 180));
			// ---
			setSize(280, 200);

			GridBagConstraints constraints = new GridBagConstraints();
			GridBagLayout layout = new GridBagLayout();

			this.setLayout(layout);

			// text box dimensions
			constraints.ipady = 40;
			constraints.ipadx = 150;
			add(text, constraints);

			// button dimensions
			constraints.ipady = 15;
			constraints.ipadx = 30;
			add(button, constraints);

			addListen();

			// ----
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
		}

		public Popout(Dialog frame) { // constructor for Dialog
			super(frame, "Enter file location", true);
			setSize(new Dimension(210, 180));

			// ---
			setSize(280, 200);

			GridBagConstraints constraints = new GridBagConstraints();
			GridBagLayout layout = new GridBagLayout();

			this.setLayout(layout);

			// text box dimensions
			constraints.ipady = 40;
			constraints.ipadx = 150;
			add(text, constraints);

			// button dimensions
			constraints.ipady = 15;
			constraints.ipadx = 30;
			add(button, constraints);

			addListen();

			addListen();

			// ----

			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
		}

		public void work(ActionListener listener) {

			text.addActionListener(listener);
			button.addActionListener(listener);

			text.addActionListener(exit);
			button.addActionListener(exit);

			setVisible(true);
		}
	}
	// POPOUT_WINDOW_DIALOG_END--------------------------------------------------------

	// Read Values Popup
	// begin----------------------------------------------------------------
	class Popout_for_value extends Dialog {
		TextField text = new TextField();

		Button button = new Button("done");
		ActionListener exit = e -> {
			dispose();
		};

		public Popout_for_value(Frame frame) { // constructor for Frame
			super(frame, "Enter value", true);
			setSize(new Dimension(210, 180));
			// ---
			setSize(280, 200);

			GridBagConstraints constraints = new GridBagConstraints();
			GridBagLayout layout = new GridBagLayout();

			this.setLayout(layout);

			// text box dimensions
			constraints.ipady = 40;
			constraints.ipadx = 150;
			add(text, constraints);

			// button dimensions
			constraints.ipady = 15;
			constraints.ipadx = 30;
			add(button, constraints);

			addListen();

			// ----
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
		}

		public Popout_for_value(Dialog frame) { // constructor for Dialog
			super(frame, "Enter file location", true);
			setSize(new Dimension(210, 180));

			// ---
			setSize(280, 200);

			GridBagConstraints constraints = new GridBagConstraints();
			GridBagLayout layout = new GridBagLayout();

			this.setLayout(layout);

			// text box dimensions
			constraints.ipady = 40;
			constraints.ipadx = 150;
			add(text, constraints);

			// button dimensions
			constraints.ipady = 15;
			constraints.ipadx = 30;
			add(button, constraints);

			addListen();

			addListen();

			// ----

			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
		}

		public void work(ActionListener listener) {

			text.addActionListener(listener);
			button.addActionListener(listener);

			text.addActionListener(exit);
			button.addActionListener(exit);

			setVisible(true);
		}
	}

	// Read Values Popup
	// end----------------------------------------------------------------
	class CompositePopout extends Dialog {
		TextField text = new TextField();
		TextField text2 = new TextField();
		Label labela = new Label();
		Label labela2 = new Label();
		Checkbox checkMore = new Checkbox("More after this one?", true);

		Button button = new Button("done");
		ActionListener exit = e -> {
			dispose();
		};

		public CompositePopout(Frame frame) { // constructor for Frame
			super(frame, "Which Operation you want to add?", true);
			setSize(new Dimension(210, 600));
			// ---
			setSize(1600, 600);
			labela.setText("1. Fill  \n" + "2. Add  \n" + "3. Sub  \n" + "4. SubInvert  \n" + "5. Mul  \n"
					+ "6. Div  \n" + "7. DivInvert  \n" + "8. Pow  \n" + "9. Log  \n" + "10. Abs  \n" + "11. Min  \n"
					+ "12. Max  \n" + "13. Invert  \n" + "14. Gray  \n" + "15. Black & White  \n" + "16. Blur  \n"
					+ "");

			GridBagConstraints constraints = new GridBagConstraints();
			GridBagLayout layout = new GridBagLayout();

			this.setLayout(layout);

			add(labela);

			// text box dimensions
			constraints.ipady = 40;
			constraints.ipadx = 150;
			add(text, constraints);
			add(text2, constraints);

			// button dimensions
			constraints.ipady = 15;
			constraints.ipadx = 30;
			add(button, constraints);

			add(checkMore);

			addListen();

			// ----
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
		}

		public CompositePopout(Dialog frame) { // constructor for Dialog
			super(frame, "Enter file location", true);
			setSize(new Dimension(210, 600));
			labela.setText("1. Fill  \n" + "2. Add  \n" + "3. Sub  \n" + "4. SubInvert  \n" + "5. Mul  \n"
					+ "6. Div  \n" + "7. DivInvert  \n" + "8. Pow  \n" + "9. Log  \n" + "10. Abs  \n" + "11. Min  \n"
					+ "12. Max  \n" + "13. Invert  \n" + "14. Gray  \n" + "15. Black & White  \n" + "16. Blur  \n"
					+ "");

			// ---
			setSize(1600, 600);

			GridBagConstraints constraints = new GridBagConstraints();
			GridBagLayout layout = new GridBagLayout();

			add(labela);

			this.setLayout(layout);

			// text box dimensions
			constraints.ipady = 40;
			constraints.ipadx = 150;
			add(text, constraints);
			add(text2, constraints);
			// button dimensions
			constraints.ipady = 15;
			constraints.ipadx = 30;
			add(button, constraints);

			addListen();

			addListen();

			// ----

			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					dispose();
				}
			});
		}

		public void work(ActionListener listener) {

			text.addActionListener(listener);
			button.addActionListener(listener);

			text.addActionListener(exit);
			button.addActionListener(exit);

			setVisible(true);
		}
	}

	// Read Values Popup
	// READ_BMP-------------------------------------------------------------------------
	private void readBMP(App app) {
		Popout popout = new Popout(this);

		popout.work(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				String location = popout.text.getText();

				try {
					Layer jund = new BMPFormatter().read(location);

					IMAGE = new Image(jund);

					IMAGE.addMouseListener(mouse);
					IMAGE.addMouseMotionListener(mouse);
					add(IMAGE, BorderLayout.CENTER);

					app.LAYERS.add(new Layers(jund, app));
					revalidate();
					repaint();

				} catch (IOException e1) {
				}
			}
		});

	}
	// READ_BMP_END--------------------------------------------------------

	// SAVE_BMP--------------------------------------------------------
	private void saveBMP() {
		Popout popout = new Popout(this);

		popout.work(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				String location = popout.text.getText();

				try {
					if (IMAGE != null)
						new BMPFormatter().write(location, IMAGE);

				} catch (IOException e1) {
				}
			}
		});

	}
	// SAVE_BMP_END--------------------------------------------------------

	// READ_PAM------------------------------------------------------------
	private void readPAM(App app) {
		Popout popout = new Popout(this);

		popout.work(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				String location = popout.text.getText();

				try {
					Layer grixis = new PAMFormatter().read(location);

					IMAGE = new Image(grixis);

					IMAGE.addMouseListener(mouse);
					IMAGE.addMouseMotionListener(mouse);
					add(IMAGE, BorderLayout.CENTER);

					app.LAYERS.add(new Layers(grixis, app));
					revalidate();
					repaint();
				} catch (IOException e1) {
				}
			}
		});
	}
	// READ_PAM_END--------------------------------------------------------

	// SAVE_PAM------------------------------------------------------------
	private void savePAM() {
		Popout popout = new Popout(this);

		popout.work(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				String location = popout.text.getText();

				try {
					if (IMAGE != null)
						new PAMFormatter().write(location, IMAGE);

				} catch (IOException e1) {
				}
			}
		});
	}
	// SAVE_PAM_END------------------------------------------------------------

	// ADD_BMP-----------------------------------------------------------------
	private void addBMP(App app) {
		Popout popout = new Popout(this);

		popout.work(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				String location = popout.text.getText();

				try {
					Layer jund = new BMPFormatter().read(location);
					IMAGE.addLayer(jund);
					app.LAYERS.add(new Layers(jund, app));
					add(IMAGE, BorderLayout.CENTER);
					revalidate();
					repaint();
				} catch (IOException e1) {
				}
			}
		});
	}
	// ADD_BMP_END-------------------------------------------------------------

	// ADD_PAM-----------------------------------------------------------------
	private void addPAM(App app) {
		Popout popout = new Popout(this);

		popout.work(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				String location = popout.text.getText();

				try {
					Layer grixis = new PAMFormatter().read(location);
					IMAGE.addLayer(grixis);
					app.LAYERS.add(new Layers(grixis, app));
					add(IMAGE, BorderLayout.CENTER);
					revalidate();
					repaint();
				} catch (IOException e1) {
				}
			}
		});
	}

	// ADD_PAM_END-------------------------------------------------------------
	double val;

	private double ValueReader() {
		Popout_for_value popout = new Popout_for_value(this);
//		double val;
		popout.work(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				val = Double.parseDouble(popout.text.getText());

			}
		});
		return val;
	}

	Pixel pix_reader;

	private Pixel PixelReader() {
		Popout_for_value popout = new Popout_for_value(this);

		popout.work(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
//				val = Double.parseDouble(popout.text.getText());

				Pattern regex = Pattern.compile("([0-9]*) ([0-9]*) ([0-9]*)");
				Matcher matcher = regex.matcher(popout.text.getText());
				boolean flag = matcher.matches();
				pix_reader = new Pixel(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
						Integer.parseInt(matcher.group(3)), 255);

			}
		});
		return pix_reader;
	}

	private Vector<Operation> CompositeReader() {
		CompositePopout popout = new CompositePopout(this);
		Vector<Operation> ops = new Vector<Operation>();
		Vector<String> save = new Vector<String>();
		
		
		while (popout.checkMore.getState()) {
			popout.work(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					switch (Integer.parseInt(popout.text.getText())) {
					case 1:
						
						Pattern regex = Pattern.compile("([0-9]*) ([0-9]*) ([0-9]*)");
						Matcher matcher = regex.matcher(popout.text2.getText());
						boolean flag = matcher.matches();
						pix_reader = new Pixel(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)),
								Integer.parseInt(matcher.group(3)), 255);
						
						ops.add(new Fill(pix_reader));
						save.add("Fill " + popout.text2.getText());
						break;
					case 2:
						System.out.println("sta se desava");
						ops.add(new Add(Integer.parseInt(popout.text2.getText())));
						save.add("Add " + popout.text2.getText());
						break;
					case 3:
						ops.add(new Sub(Integer.parseInt(popout.text2.getText())));
						save.add("Sub " + popout.text2.getText());
						break;
					case 4:
						ops.add(new SubInvert(Integer.parseInt(popout.text2.getText())));
						save.add("SubInvert " + popout.text2.getText());
						break;
					case 5:
						ops.add(new Mul(Integer.parseInt(popout.text2.getText())));
						save.add("Mul " + popout.text2.getText());
						break;
					case 6:
						ops.add(new Div(Integer.parseInt(popout.text2.getText())));
						save.add("Div " + popout.text2.getText());
						break;
					case 7:
						ops.add(new DivInvert(Integer.parseInt(popout.text2.getText())));
						save.add("DivInvert " + popout.text2.getText());
						break;
					case 8:
						ops.add(new Pow(Integer.parseInt(popout.text2.getText())));
						save.add("Pow " + popout.text2.getText());
						break;
					case 9:
						ops.add(new Log(Integer.parseInt(popout.text2.getText())));
						save.add("Log " + popout.text2.getText());
						break;
					case 10:
						ops.add(new Abs());
						save.add("Abs ");
						break;
					case 11:
						ops.add(new Min(Integer.parseInt(popout.text2.getText())));
						save.add("Min " + popout.text2.getText());
						break;
					case 12:
						ops.add(new Max(Integer.parseInt(popout.text2.getText())));
						save.add("Max " + popout.text2.getText());
						break;
					case 13:
						ops.add(new Invert());
						save.add("Invert ");
						break;
					case 14:
						ops.add(new Gray());
						save.add("Gray ");
						break;
					case 15:
						ops.add(new BlackWhite());
						save.add("BlackWhite ");
						break;
					case 16:
						ops.add(new Blur());
						save.add("Blur ");
						break;
					}
				}
			});
		}
//		System.out.println(save.size());
//		for(int i=0;i<save.size();i++) {
//			System.out.println(save.get(i));
//		}
		
		return ops;
	}

	private void CompositeCreator() {

	}

	// LAYERS_PANEL------------------------------------------------------------
	class Layers extends Panel {

		Layer layer;
		App app;

		Checkbox checkV = new Checkbox("visible", true);
		Checkbox checkA = new Checkbox("active", true);
		Button buttonRemove = new Button("remove");
		TextField alphaBox = new TextField("100");
		Label labela = new Label();
		// constructor

		JScrollPane scroll = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		public Layers(Layer lay, App ap) {
			super();
			app = ap;
			layer = lay;
			int layer_index = IMAGE.layers.size();
			labela.setText("layer " + layer_index + ".");
			alphaBox.setText(lay.alpha + "");
			checkV.setState(lay.visible);
			checkA.setState(lay.active);

			add(labela);
			add(checkV);
			add(checkA);
			add(alphaBox);
			add(buttonRemove);

			// AVAILABLE_CHECKBOX
			checkA.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					layer.active = (arg0.getStateChange() == 1);
				}
			});

			// VISIBLE_CHECKBOX
			checkV.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					layer.visible = (arg0.getStateChange() == 1);
					app.IMAGE.repaint();
					revalidate();
//					repaint();
				}
			});

			// BUTTON_TO_REMOVE_LAYER
			buttonRemove.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {

					app.IMAGE.layers.remove(lay);

					app.LAYERS.remove(Layers.this);
					app.revalidate();
					app.IMAGE.repaint();
//					revalidate();
//					repaint();

				}
			});

			// ALPHA_VALUE_TEXTBOX
			alphaBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						if (Integer.parseInt(alphaBox.getText()) <= 100 && Integer.parseInt(alphaBox.getText()) >= 0) {
							layer.alpha = Integer.parseInt(alphaBox.getText());
						}
					} catch (NumberFormatException e) {
						alphaBox.setText("100");
						layer.alpha = 100;
					}
					app.IMAGE.repaint();
//					revalidate();
//					repaint();
				}
			});
		}// constructor_end
	}
	// LAYERS_PANEL_END------------------------------------------------------------

	// SELECTIONS_PANEL_BEGIN------------------------------------------------------------
	// ----------------------------------------------------------------------------------
	class Selections extends Panel {

		Checkbox checkA = new Checkbox("active", true);
		Button buttonRemove = new Button("remove");
		Label labela = new Label();
		Selection selection;
		App app;
		// constructor

		JScrollPane scroll = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		public Selections(Selection sele, App ap) {
			super();
			app = ap;
			selection = sele;
			int selection_index = IMAGE.selections.size();
			labela.setText("layer " + selection_index + ".");
			checkA.setState(selection.active);

			add(labela);

			add(checkA);

			add(buttonRemove);

			// AVAILABLE_CHECKBOX
			checkA.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent arg0) {
					selection.active = (arg0.getStateChange() == 1);
//					app.revalidate();
//					app.IMAGE.repaint();
					revalidate();
					repaint();
				}
			});

			// BUTTON_TO_REMOVE_SELECTION
			buttonRemove.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {

					app.IMAGE.selections.remove(sele);
					app.SELECTIONS.remove(Selections.this);
//					app.revalidate();
//					app.IMAGE.repaint();
					revalidate();
					repaint();

				}
			});

		}// constructor_end
	}
	// ----------------------------------------------------------------------------------
	// SELECTIONS_PANEL_END--------------------------------------------------------------

	// MOUSE_LISTENERS------------------------------------------------------------
	MouseAdapter mouse = new MouseAdapter() {
		@Override
		public void mousePressed(java.awt.event.MouseEvent click) {
			if (click.getButton() != MouseEvent.BUTTON1)
				return;

			pressed = true;
			x = Math.round(Math.round((float) click.getX()));
			y = Math.round(Math.round((float) click.getY()));

			lineX = Math.round(Math.round((float) click.getX()));
			lineY = Math.round(Math.round((float) click.getY()));

			xx = Math.round(Math.round((float) click.getX()));
			yy = Math.round(Math.round((float) click.getY()));
		}

		@Override
		public void mouseReleased(MouseEvent click) {

			if (click.getButton() != MouseEvent.BUTTON1)
				return;
//			System.out.println("released");

			if (!pressed)
				return;
			pressed = false;

//			int w = Math.abs(Math.round(x/((float)IMAGE.getWidth()/IMAGE.getWWidth()))-Math.round((float)click.getX() / (float)IMAGE.getWidth()/IMAGE.getWWidth()));
//			int h = Math.abs(Math.round(y/((float)IMAGE.getHeight()/IMAGE.getHHeight()))-Math.round((float)click.getY() / (float)IMAGE.getHeight()/IMAGE.getHHeight()));
//			
//			
//			if(w <=0 || h <=0)return;
//			
//			Selection shadow = new Selection(
//											 w,
//											 h,
//											 Math.min(x, Math.round((float)click.getX() / (float)IMAGE.getWidth()/IMAGE.getWWidth())),
//											 Math.min(y, Math.round((float)click.getY() / (float)IMAGE.getHeight()/IMAGE.getHHeight()))
//											 );
			int xn, yn;
			xn = Math.round((float) click.getX() / ((float) IMAGE.getWidth() / IMAGE.width));
			yn = Math.round((float) click.getY() / ((float) IMAGE.getHeight() / IMAGE.height));

			lineWidth = Math.round(Math.round((float) click.getX()));
			lineHeight = Math.round(Math.round((float) click.getY()));

			int tempw = lineWidth;
			int temph = lineHeight;
			int tempX = lineX;
			int tempY = lineY;

			lineX = Math.min(lineX, tempw);
			lineY = Math.min(lineY, temph);

			lineWidth = Math.max(tempw, tempX);
			lineHeight = Math.max(tempY, temph);

//			lineX = Math.round(Math.round((float)click.getX()));
//			lineY = Math.round(Math.round((float)click.getY()));

//			xn = Math.round(Math.round((float)arg0.getX()));
//			yn =  Math.round(Math.round((float)arg0.getY()));

			int x1, y1, width, height;

			x = Math.round(x / ((float) IMAGE.getWidth() / IMAGE.width));
			y = Math.round(y / ((float) IMAGE.getHeight() / IMAGE.height));
			// --

			x1 = Math.min(x, xn);
			y1 = Math.min(y, yn);

			width = Math.abs(x - xn);
			height = Math.abs(y - yn);

			if (width <= 0 || height <= 0)
				return;

			Selection shadow = new Selection(width, height, x1, y1, lineX, lineY, lineWidth, lineHeight);

			SELECTIONS.add(new Selections(shadow, App.this));

			IMAGE.selections.add(shadow);
			revalidate();
			repaint();
//			App.this.fill(width, height, x1, y1);

//			App.this.revalidate();
//			App.this.IMAGE.repaint();
		}

		@Override
		public void mouseDragged(MouseEvent click) {

			int x1 = Math.round(Math.round((float) click.getX()));
			int y1 = Math.round(Math.round((float) click.getY()));

			int h = IMAGE.getHeight() / IMAGE.getHHeight();
			int w = IMAGE.getWidth() / IMAGE.getWWidth();

			if (Math.abs(yy - y1) >= h || Math.abs(xx - x1) >= w) {
				xx = x1;
				yy = y1;
				repaint();
			}
		}

	};

	// MOUSE_LISTENERS_END------------------------------------------------------------
	// PAINT_BEGIN--------------------------------------------------------------------

	@Override
	public void update(Graphics g) {
		paint(g);
	}

	public void rects() {
		if (IMAGE != null) {
			Graphics2D line = (Graphics2D) IMAGE.getGraphics();

			for (Selection sale : IMAGE.selections) {
				if (!sale.active)
					continue;

				float[] dashingPattern2 = { 10f, 4f };
				Stroke stroke2 = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f,
						dashingPattern2, 0.0f);

				line.setColor(Color.BLUE);
				line.setStroke(stroke2);

				line.drawLine(sale.X, sale.Y, sale.W, sale.Y); // up
				line.drawLine(sale.X, sale.Y, sale.X, sale.H); // left
				line.drawLine(sale.W, sale.Y, sale.W, sale.H); // right
				line.drawLine(sale.X, sale.H, sale.W, sale.H); // bottom
			}

		}
	}

	@Override
	public void paint(Graphics g) {

		if (IMAGE != null) {
			IMAGE.paint(IMAGE.getGraphics());
		}

//		if(IMAGE != null) {
//		
//		for (Selection sale : IMAGE.selections) {
//				if(sale.active) {
//					fill(sale.width,sale.height,sale.x,sale.y);
//				}else {
//					fill_Deleted(sale.width,sale.height,sale.x,sale.y);
//				}
//			}
//		}

		if (pressed) {

			Graphics2D line = (Graphics2D) IMAGE.getGraphics();

			float[] dashingPattern3 = { 10f, 10f, 1f, 10f };
			Stroke stroke3 = new BasicStroke(2f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, dashingPattern3,
					0.0f);

			line.setColor(Color.CYAN);
			line.setStroke(stroke3);

			line.drawLine(x, y, xx, y); // up
			line.drawLine(x, y, x, yy); // left
			line.drawLine(xx, y, xx, yy); // right
			line.drawLine(x, yy, xx, yy); // bottom

		}
		rects();

	}
	// PAINT_END----------------------------------------------------------------------
	// ----proba

	public void fill(int a, int b, int c, int d) {

		for (int i = c; i < c + a; i++) {
			for (int j = d; j < d + b; j++) {
				IMAGE.layers.get(0).pixels.get(j).get(i).alpha = 255;
				IMAGE.layers.get(0).pixels.get(j).get(i).red = 0;
				IMAGE.layers.get(0).pixels.get(j).get(i).green = 255;
				IMAGE.layers.get(0).pixels.get(j).get(i).blue = 0;
			}
		}
	}

	/// ---------------
	public void fill_Deleted(int a, int b, int c, int d) {

		for (int i = c; i < c + a; i++) {
			for (int j = d; j < d + b; j++) {
				IMAGE.layers.get(0).pixels.get(j).get(i).alpha = 255;
				IMAGE.layers.get(0).pixels.get(j).get(i).red = 255;
				IMAGE.layers.get(0).pixels.get(j).get(i).green = 0;
				IMAGE.layers.get(0).pixels.get(j).get(i).blue = 0;
			}
		}
	}
	/// ---------------

	// ----proba_end
	// MENU
	private void addMenu() {
		MenuBar menubar = new MenuBar();

		Menu load = new Menu("Load");

		menubar.add(load);

		MenuItem BMP = new MenuItem("BMP");
		MenuItem PAM = new MenuItem("PAM");

		BMP.addActionListener(e -> {
			if (IMAGE == null)
				readBMP(this);
			else
				addBMP(this);
		});

		PAM.addActionListener(e -> {
			if (IMAGE == null)
				readPAM(this);
			else
				addPAM(this);
		});

		load.add(BMP);
		load.add(PAM);

		Menu save = new Menu("Save");
		MenuItem bmpSave = new MenuItem("BMP");
		MenuItem pamSave = new MenuItem("PAM");
		MenuItem project = new MenuItem("Project File");

		Menu edit = new Menu("Edit");
		MenuItem addLayerBMP = new MenuItem("Add BMP Layer");
		MenuItem addLayerPAM = new MenuItem("Add PAM Layer");
		MenuItem Fill = new MenuItem("Fill");
		MenuItem Add = new MenuItem("Add");
		MenuItem Sub = new MenuItem("Sub");
		MenuItem SubInvert = new MenuItem("SubInvert");
		MenuItem Mul = new MenuItem("Mul");
		MenuItem Div = new MenuItem("Div");
		MenuItem DivInvert = new MenuItem("DivInvert");
		MenuItem Pow = new MenuItem("Pow");
		MenuItem Log = new MenuItem("Log");
		MenuItem Abs = new MenuItem("Abs");
		MenuItem Min = new MenuItem("Min");
		MenuItem Max = new MenuItem("Max");
		MenuItem Invert = new MenuItem("Invert");
		MenuItem Gray = new MenuItem("Gray");
		MenuItem BlackWhite = new MenuItem("BlackWhite");
		MenuItem Blur = new MenuItem("Blur");
		MenuItem CompositeOperation = new MenuItem("CompositeOperation");
		edit.add(addLayerBMP);
		edit.add(addLayerPAM);
		edit.add(Fill);
		edit.add(Add);
		edit.add(Sub);
		edit.add(SubInvert);
		edit.add(Mul);
		edit.add(Div);
		edit.add(DivInvert);
		edit.add(Pow);
		edit.add(Log);
		edit.add(Abs);
		edit.add(Min);
		edit.add(Max);
		edit.add(Invert);
		edit.add(Gray);
		edit.add(BlackWhite);
		edit.add(Blur);
		edit.add(CompositeOperation);

		bmpSave.addActionListener(e -> {
			saveBMP();
		});

		pamSave.addActionListener(e -> {
			savePAM();
		});

		addLayerBMP.addActionListener(e -> {
			addBMP(this);
		});

		addLayerPAM.addActionListener(e -> {
			addPAM(this);
		});

		Fill.addActionListener(e -> {

			Fill op = new Fill(PixelReader());
			op.work(IMAGE);

		});

		Add.addActionListener(e -> {
			Add op = new Add(ValueReader());
			op.work(IMAGE);
		});

		Sub.addActionListener(e -> {
			Sub op = new Sub(ValueReader());
			op.work(IMAGE);
		});

		SubInvert.addActionListener(e -> {
			SubInvert op = new SubInvert(ValueReader());
			op.work(IMAGE);
		});

		Mul.addActionListener(e -> {
			Mul op = new Mul(ValueReader());
			op.work(IMAGE);
		});

		Div.addActionListener(e -> {
			Div op = new Div(ValueReader());
			op.work(IMAGE);
		});

		DivInvert.addActionListener(e -> {
			DivInvert op = new DivInvert(ValueReader());
			op.work(IMAGE);
		});

		Pow.addActionListener(e -> {
			Pow op = new Pow(ValueReader());
			op.work(IMAGE);
		});

		Log.addActionListener(e -> {
			Log op = new Log(ValueReader());
			op.work(IMAGE);
		});

		Abs.addActionListener(e -> {
			Abs op = new Abs();
			op.work(IMAGE);
		});

		Min.addActionListener(e -> {
			Min op = new Min(ValueReader());
			op.work(IMAGE);
		});

		Max.addActionListener(e -> {
			Max op = new Max(ValueReader());
			op.work(IMAGE);
		});

		Invert.addActionListener(e -> {
			Invert op = new Invert();
			op.work(IMAGE);
		});

		Gray.addActionListener(e -> {
			Gray op = new Gray();
			op.work(IMAGE);
		});

		BlackWhite.addActionListener(e -> {
			BlackWhite op = new BlackWhite();
			op.work(IMAGE);
		});

		Blur.addActionListener(e -> {
			Blur op = new Blur();
			op.work(IMAGE);
		});

		CompositeOperation.addActionListener(e -> {
			CompositeOperation op = new CompositeOperation(CompositeReader());
			op.work(IMAGE);
			
		});

		save.add(bmpSave);
		save.add(pamSave);
		save.add(project);
		menubar.add(save);
		menubar.add(edit);

		setMenuBar(menubar);

	}

	private void addListen() { // close on X
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// save before closing
				dispose();
			}
		});
	}

	public static void main(String[] args) {

		System.out.println("start_program");
//		Image image;
//		BMPFormatter bmp = new BMPFormatter();
//		PAMFormatter pam = new PAMFormatter();
//
//		try {
//			image =new Image( bmp.read("img/LAND2.BMP"));
//			
//			image.addLayer(bmp.read("img/Shapes.bmp"));
//			pam.write("img2/PAMAMAMA.pam", image);
//			
//			
//			
//			
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}

		new App();

		System.out.println("end_program");
	}

}
