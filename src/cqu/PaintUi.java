package cqu;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JColorChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.SystemColor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class PaintUi extends JFrame {

	public class panel extends JPanel {
		public panel() {
			Thread a1 = new Thread(new Runnable() {

				@Override
				public void run() {
					if (isClient == true) {
						try {
							in = new ObjectInputStream(socket.getInputStream());
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
					}
					int i = 0;
					while (true) {
						if (isRealese == true) {
							if (isClient == true) {
								try {
									in = new ObjectInputStream(socket
											.getInputStream());
									Shape shape;
									try {
										shape = (Shape) in.readObject();
										if (shape != null) {
											// System.out.println("接收数据成功");
											// textArea.append("\r\n接收数据成功");
											// textArea.move(5, 5);
											shapes.add(shape);
											shape = null;
											//repaint();
										} 
									} catch (ClassNotFoundException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
						try {
							Thread.sleep(1000);
							i++;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			a1.start();
		}

		public void paint(Graphics g) {
			super.paint(g);

			/*
			 * 画笔预设
			 */

			for (Shape e : shapes) {
				e.draw(g);
			}

			if (flag == 1 && isDrag == true) {
				Graphics2D g2D;
				g2D = (Graphics2D) g;
				g2D.setStroke(new BasicStroke(px, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_ROUND));
				g.setColor(color);
				for (Point e : points) {
					for (Point f : points1) {
						g.drawLine(e.x, e.y, f.x, f.y);
					}
				}
			}
			/*
			 * 画直线
			 */

			if (flag == 2) {
				Graphics2D g2D;
				g2D = (Graphics2D) g;
				g2D.setStroke(new BasicStroke(px, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_ROUND));
				g.setColor(color);
				int[] pointX = new int[(points2.size())];
				int[] pointY = new int[(points2.size())];
				for (i = 0; i < points2.size(); i++) {
					pointX[i] = points2.get(i).x;
					pointY[i] = points2.get(i).y;
				}
				g.drawPolyline(pointX, pointY, points2.size());
			}
			/*
			 * 画铅笔线条（贝塞尔曲线）
			 */

			if (flag == 3 && isDrag == true) {
				Graphics2D g2D;
				g2D = (Graphics2D) g;
				g2D.setStroke(new BasicStroke(px, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_ROUND));
				g.setColor(color);
				g.drawRect(x, y, w, h);
			}
			/*
			 * 画矩形
			 */

			if (flag == 4 && isDrag == true) {
				Graphics2D g2D;
				g2D = (Graphics2D) g;
				g2D.setStroke(new BasicStroke(px, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_ROUND));
				g.setColor(color);
				g.drawArc(x, y, w, h, 0, 360);
			}
			/*
			 * 画椭圆形
			 */
			if (flag == 5) {
				Graphics2D g2D;
				g2D = (Graphics2D) g;
				g2D.setStroke(new BasicStroke(50, BasicStroke.CAP_ROUND,
						BasicStroke.JOIN_ROUND));
				g.setColor(Color.WHITE);
				int[] pointX = new int[(points2.size())];
				int[] pointY = new int[(points2.size())];
				for (i = 0; i < points2.size(); i++) {
					pointX[i] = points2.get(i).x;
					pointY[i] = points2.get(i).y;
				}
				g.drawPolyline(pointX, pointY, points2.size());
			}
			//repaint();
		}
	}

	private JPanel contentPane;
	// private JPanel paint=new panel();
	private JPanel panel_1 = new JPanel();
	private JPanel panel_2 = new JPanel();
	private JPanel panel_3 = new JPanel();
	private JPanel panel_4 = new JPanel();
	private JButton arc = new JButton("");
	private JButton rect = new JButton("");
	private JButton curve = new JButton("");
	private JButton preColor = new JButton("");
	private JButton rubber = new JButton("");
	private JButton line = new JButton("");
	private JButton fill = new JButton("");
	private JButton lasColor = new JButton("");
	private JSlider slider = new JSlider();
	private JLabel label = new JLabel("");

	private int x;
	private int y;
	private int w;
	private int h;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private static int i;
	private Integer px;
	private int flag;
	private Color color;
	private Color colorb;
	public boolean isFill;
	private boolean isDrag;
	private boolean isRealese;

	private List<Point> points = new ArrayList<Point>();
	private List<Point> points1 = new ArrayList<Point>();
	private List<Point> points2 = new ArrayList<Point>();
	public List<Shape> shapes = new ArrayList<Shape>();
	private JTextField serverAddress;
	private final JButton button = new JButton("连接服务");
	private JTextArea textArea = new JTextArea();

	private Socket socket;
	private int port;
	private String host;
	private boolean isClient;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private final JButton btnNewButton_1 = new JButton(
			"\u4FDD\u5B58\u4E3A\u56FE\u7247");
	private final JLabel lblNewLabel = new JLabel(
			"\u94C5\u7B14\u5176\u5B9E\u4E5F\u53EF\u4EE5\u5F53\u6A61\u76AE\u4F7F\u7528\u54E6  ###\u56FE\u7247\u9ED8\u8BA4\u4FDD\u5B58\u5728D\u76D8\u6839\u76EE\u5F55\u4E0B\u4EB2");
	private final JLabel lblNewLabel_1 = new JLabel(
			"###\u8981\u6CE8\u610F\u6309\u952E\u4E0A\u7684\u6587\u5B57\u63D0\u793A");
	private final JButton btnNewButton_2 = new JButton(
			"\u753B\u5E03\u586B\u5145");
	private final JLabel lblNewLabel_2 = new JLabel(
			"###\u753B\u5E03\u529F\u80FD\u8FD8\u4E0D\u652F\u6301\u8054\u7F51\u54E6\uFF0C\u671F\u5F85\u66F4\u65B0\u3002\u3002\u3002");
	private final JLabel lblNewLabel_3 = new JLabel("");
	private final JLabel lblNewLabel_4 = new JLabel(
			"###\u7EC8\u4E8E\u5360\u6EE1\u4E86\uFF0C*^\u03BF^*");
	private final JLabel lblNewLabel_5 = new JLabel(
			"\u6A61\u76AE\u5927\u5C0F\u6682\u65F6");
	private final JLabel lblNewLabel_6 = new JLabel(
			"\u4E0D\u652F\u6301\u4FEE\u6539");
	private final JLabel label_1 = new JLabel("\u753B\u7B14\u7C97\u7EC6");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaintUi frame = new PaintUi();
					frame.setVisible(true);
					frame.setTitle("网络白板（客户端1.0版）");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PaintUi() {
		label_1.setForeground(new Color(178, 34, 34));
		lblNewLabel_4.setForeground(Color.MAGENTA);
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel.setForeground(Color.RED);

		Init();
		final JPanel paint = new JPanel();
		paint.setForeground(Color.BLACK);
		paint.setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1100, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		paint.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				x2 = e.getX();
				y2 = e.getY();
				Point a = new Point();
				a.x = x2;
				a.y = y2;
				points1.add(a);
				points2.add(a);
				if (points1.size() > 1) {
					points1.remove(0);
				}
				/*
				 * 画直线准备
				 */

				isDrag = true;
				/*
				 * 防止矩形和圆形在不拖动鼠标的情况下绘出
				 */

				if (e.getX() < x1) {
					w = x1 - e.getX();
					x = x1 - w;
				} else {
					w = e.getX() - x;
				}
				if (e.getY() < y1) {
					h = y1 - e.getY();
					y = y1 - h;
				} else {
					h = e.getY() - y;
				}
				/*
				 * 矩形和圆形的长宽为负时也能正确绘图
				 */
				isRealese = false;

			}
		});
		paint.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				x1 = e.getX();
				y1 = e.getY();
				Point a = new Point();
				a.x = x1;
				a.y = y1;
				points.add(a);

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				int[] a1 = new int[points2.size()];
				int[] b1 = new int[points2.size()];
				for (int i = 0; i < points2.size(); i++) {
					a1[i] = points2.get(i).x;
					b1[i] = points2.get(i).y;
				}
				Curve f = new Curve();
				Rubber r = new Rubber();
				/*
				 * 画贝塞尔曲线的准备
				 */

				Point a = new Point();
				a.x = e.getX();
				a.y = e.getY();
				points.add(a);
				Line b = new Line();
				b.a = points.get(0);
				b.b = points.get(1);

				/*
				 * 画直线的准备
				 */

				Rect c = new Rect();
				c.x = x;
				c.y = y;
				c.w = w;
				c.h = h;

				Arc d = new Arc();
				d.x = x;
				d.y = y;
				d.w = w;
				d.h = h;
				/*
				 * 画圆和矩形的准备
				 */
				Shape s;
				if (flag == 1 && isDrag == true) {
					b.px = px;
					b.color = color;
					s = b;
					shapes.add(s);
				}
				if (flag == 2) {
					f.px = px;
					f.x = a1;
					f.y = b1;
					f.color = color;
					f.count = f.x.length;
					s = f;
					shapes.add(s);
				}
				if (flag == 3 && isDrag == true) {
					c.px = px;
					c.colorPre = color;
					c.colorLas = colorb;
					c.isFill = isFill;
					s = c;
					shapes.add(s);
				}
				if (flag == 4 && isDrag == true) {
					d.px = px;
					d.isFill = isFill;
					d.colorPre = color;
					d.colorLas = colorb;
					s = d;
					shapes.add(s);
				}
				if (flag == 5) {
					r.px = 50;
					r.x = a1;
					r.y = b1;
					r.color = Color.WHITE;
					r.count = r.x.length;
					s = r;
					shapes.add(s);
				}
				/*
				 * 判断当前绘制哪种图形
				 */
				points.removeAll(points);
				points1.removeAll(points1);
				points2.removeAll(points2);
				isRealese = true;
				if (isClient == true) {
					try {
						out = new ObjectOutputStream(socket.getOutputStream());
						out.writeObject(shapes.get(shapes.size() - 1));
						out.flush();
						// System.out.println("发送完毕");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						try {
							socket.close();
							isClient = false;
							button.setText("连接服务");
							button.setBackground(Color.WHITE);
							textArea.setText("已断开连接，请重连！");
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

					}
				}
				init();
			}
		});
		btnNewButton_1.setBackground(SystemColor.activeCaption);
		btnNewButton_1.setToolTipText("\u9ED8\u8BA4\u8DEF\u5F84\uFF1AD");

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedImage image = new BufferedImage(paint.getWidth(), paint
						.getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics2D g2 = image.createGraphics();
				paint.paint(g2);
				try {
					ImageIO.write(image, "jpeg", new java.io.File(
							"D:/jpanel.jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		arc.setIcon(new ImageIcon(getClass().getResource("/circle.gif")));

		arc.setToolTipText("\u692D\u5706\u5DE5\u5177");

		arc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 4;
				System.out.println("4--------------->");
				repaint();
				arc.setBackground(Color.BLACK);
				rect.setBackground(Color.WHITE);
				curve.setBackground(Color.WHITE);
				line.setBackground(Color.WHITE);
				rubber.setBackground(Color.WHITE);
			}
		});
		rect.setToolTipText("\u77E9\u5F62\u5DE5\u5177");

		rect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 3;
				rect.setBackground(Color.BLACK);
				arc.setBackground(Color.WHITE);
				curve.setBackground(Color.WHITE);
				line.setBackground(Color.WHITE);
				rubber.setBackground(Color.WHITE);
			}
		});
		curve.setToolTipText("\u94C5\u7B14\u5DE5\u5177");

		curve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 2;
				curve.setBackground(Color.BLACK);
				rect.setBackground(Color.WHITE);
				arc.setBackground(Color.WHITE);
				line.setBackground(Color.WHITE);
				rubber.setBackground(Color.WHITE);
			}
		});
		rubber.setToolTipText("\u6A61\u76AE\u5DE5\u5177");

		rubber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 5;
				rubber.setBackground(Color.BLACK);
				rect.setBackground(Color.WHITE);
				curve.setBackground(Color.WHITE);
				line.setBackground(Color.WHITE);
				arc.setBackground(Color.WHITE);
			}
		});
		line.setToolTipText("\u76F4\u7EBF\u5DE5\u5177");

		line.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 1;
				line.setBackground(Color.BLACK);
				rect.setBackground(Color.WHITE);
				curve.setBackground(Color.WHITE);
				arc.setBackground(Color.WHITE);
				rubber.setBackground(Color.WHITE);
			}
		});
		fill.setToolTipText("\u586B\u5145");
		fill.setHorizontalAlignment(SwingConstants.LEFT);

		fill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isFill == false) {
					fill.setBackground(Color.GRAY);
					isFill = true;
				} else {
					fill.setBackground(Color.WHITE);
					isFill = false;
				}
			}
		});
		preColor.setToolTipText("\u524D\u666F\u8272");

		preColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c;
				c = JColorChooser.showDialog(
						((Component) e.getSource()).getParent(), "选择颜色",
						Color.blue);
				if (c == null)
					;
				else
					color = c;
				preColor.setBackground(color);
				getJTextField_line_R().setText(String.valueOf(c.getRed()));
				getJTextField_line_G().setText(String.valueOf(c.getGreen()));
				getJTextField_line_B().setText(String.valueOf(c.getBlue()));
			}

			private AbstractButton getJTextField_line_B() {
				// TODO Auto-generated method stub
				return null;
			}

			private AbstractButton getJTextField_line_G() {
				// TODO Auto-generated method stub
				return null;
			}

			private AbstractButton getJTextField_line_R() {
				// TODO Auto-generated method stub
				return null;
			}
		});
		lasColor.setToolTipText("\u540E\u666F\u8272");

		lasColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c;
				c = JColorChooser.showDialog(
						((Component) e.getSource()).getParent(), "选择颜色",
						Color.blue);
				if (c == null)
					;
				else
					colorb = c;
				lasColor.setBackground(colorb);
				getJTextField_line_R().setText(String.valueOf(c.getRed()));
				getJTextField_line_G().setText(String.valueOf(c.getGreen()));
				getJTextField_line_B().setText(String.valueOf(c.getBlue()));
			}

			private AbstractButton getJTextField_line_B() {
				// TODO Auto-generated method stub
				return null;
			}

			private AbstractButton getJTextField_line_G() {
				// TODO Auto-generated method stub
				return null;
			}

			private AbstractButton getJTextField_line_R() {
				// TODO Auto-generated method stub
				return null;
			}
		});
		btnNewButton_2.setBackground(SystemColor.activeCaption);

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c;
				c = JColorChooser.showDialog(
						((Component) e.getSource()).getParent(), "选择颜色",
						Color.blue);
				if (c == null)
					System.out.println("错误");
				else
					paint.setBackground(c);
				getJTextField_line_R().setText(String.valueOf(c.getRed()));
				getJTextField_line_G().setText(String.valueOf(c.getGreen()));
				getJTextField_line_B().setText(String.valueOf(c.getBlue()));
			}

			private AbstractButton getJTextField_line_B() {
				// TODO Auto-generated method stub
				return null;
			}

			private AbstractButton getJTextField_line_G() {
				// TODO Auto-generated method stub
				return null;
			}

			private AbstractButton getJTextField_line_R() {
				// TODO Auto-generated method stub
				return null;
			}

		});

		slider.setBackground(SystemColor.activeCaption);

		slider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				px = slider.getValue();
				label.setText(px.toString());
			}
		});

		slider.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				px = slider.getValue();
				label.setText(px.toString());
			}
		});
		button.setBackground(Color.WHITE);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isClient == false) {
					port = 8100;
					host = serverAddress.getText();
					try {
						socket = new Socket(host, port);
						// System.out.println("连接服务器成功");
						textArea.setText("连接服务器成功");
						isClient = true;
						button.setBackground(Color.GRAY);
						button.setText("断开连接");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						// System.out.println("连接服务器失败");
						isClient = false;
						textArea.setText("连接服务器失败");
					}
				} else {
					isClient = false;
					button.setBackground(Color.WHITE);
					button.setText("连接服务");
					try {
						socket.close();
						textArea.setText("成功断开连接");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		serverAddress = new JTextField();
		serverAddress.setColumns(10);
		serverAddress.setText("127.0.0.1");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addGroup(
								gl_contentPane
										.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_1,
												GroupLayout.PREFERRED_SIZE, 98,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(textArea,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(button)
										.addComponent(serverAddress,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_contentPane
										.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_2,
												GroupLayout.DEFAULT_SIZE, 864,
												Short.MAX_VALUE)
										.addComponent(paint,
												GroupLayout.DEFAULT_SIZE, 864,
												Short.MAX_VALUE))));
		gl_contentPane.setVerticalGroup(gl_contentPane
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_contentPane
								.createSequentialGroup()
								.addComponent(panel_2,
										GroupLayout.PREFERRED_SIZE, 85,
										GroupLayout.PREFERRED_SIZE)
								.addGap(11)
								.addComponent(paint, GroupLayout.DEFAULT_SIZE,
										455, Short.MAX_VALUE))
				.addGroup(
						gl_contentPane
								.createSequentialGroup()
								.addComponent(panel_1,
										GroupLayout.PREFERRED_SIZE, 199,
										GroupLayout.PREFERRED_SIZE)
								.addGap(49)
								.addComponent(serverAddress,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(button)
								.addGap(18)
								.addComponent(textArea,
										GroupLayout.PREFERRED_SIZE, 104,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(119, Short.MAX_VALUE)));

		JButton btnNewButton = new JButton("\u6E05\u7A7A\u753B\u677F");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shapes.removeAll(shapes);
			}
		});
		btnNewButton.setBackground(SystemColor.activeCaption);

		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2
				.setHorizontalGroup(gl_panel_2
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_2
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																slider,
																GroupLayout.PREFERRED_SIZE,
																122,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_panel_2
																		.createParallelGroup(
																				Alignment.TRAILING)
																		.addGroup(
																				gl_panel_2
																						.createSequentialGroup()
																						.addComponent(
																								label_1)
																						.addGap(6)
																						.addComponent(
																								lblNewLabel_5))
																		.addGroup(
																				gl_panel_2
																						.createSequentialGroup()
																						.addComponent(
																								label,
																								GroupLayout.PREFERRED_SIZE,
																								44,
																								GroupLayout.PREFERRED_SIZE)
																						.addGap(14)
																						.addComponent(
																								lblNewLabel_6)
																						.addGap(4))))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addGroup(
																gl_panel_2
																		.createSequentialGroup()
																		.addComponent(
																				btnNewButton_2)
																		.addGap(2)
																		.addComponent(
																				lblNewLabel_2)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblNewLabel_1)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				lblNewLabel_3))
														.addGroup(
																gl_panel_2
																		.createSequentialGroup()
																		.addComponent(
																				btnNewButton)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				btnNewButton_1)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				lblNewLabel)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				lblNewLabel_4)))
										.addContainerGap(150, Short.MAX_VALUE)));
		gl_panel_2
				.setVerticalGroup(gl_panel_2
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_2
										.createSequentialGroup()
										.addContainerGap(23, Short.MAX_VALUE)
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																gl_panel_2
																		.createSequentialGroup()
																		.addGroup(
																				gl_panel_2
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								btnNewButton_2)
																						.addComponent(
																								lblNewLabel_2)
																						.addComponent(
																								lblNewLabel_1)
																						.addComponent(
																								lblNewLabel_3))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				gl_panel_2
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								btnNewButton)
																						.addComponent(
																								btnNewButton_1)
																						.addComponent(
																								lblNewLabel)
																						.addComponent(
																								lblNewLabel_4)))
														.addGroup(
																gl_panel_2
																		.createSequentialGroup()
																		.addGroup(
																				gl_panel_2
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								lblNewLabel_6)
																						.addComponent(
																								label))
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				slider,
																				GroupLayout.PREFERRED_SIZE,
																				18,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap())
						.addGroup(
								gl_panel_2
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblNewLabel_5)
														.addComponent(label_1))
										.addContainerGap(60, Short.MAX_VALUE)));
		lblNewLabel_5.setForeground(Color.ORANGE);

		panel_2.setLayout(gl_panel_2);
		contentPane.setLayout(gl_contentPane);

	}

	private void Init() {
		flag = 0;
		px = 1;
		label.setText(px.toString());
		isDrag = false;
		isFill = false;
		isRealese = true;
		isClient = false;
		color = Color.BLACK;
		colorb = Color.WHITE;
		textArea.setLineWrap(true);
		// paint.setForeground(Color.BLACK);
		// paint.setBackground(Color.WHITE);

		panel_1.setForeground(Color.BLACK);
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		panel_1.add(panel_3);
		panel_1.add(panel_4);

		panel_2.setBackground(SystemColor.activeCaption);

		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		panel_3.add(arc);
		panel_3.add(rect);
		panel_3.add(curve);
		panel_3.add(preColor);

		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		panel_4.add(rubber);
		panel_4.add(line);
		panel_4.add(fill);
		panel_4.add(lasColor);
		arc.setBackground(Color.WHITE);

		rect.setIcon(new ImageIcon(getClass().getResource("/rectangle.gif")));
		rect.setBackground(Color.WHITE);

		curve.setIcon(new ImageIcon(getClass().getResource("/pencil.gif")));
		curve.setBackground(Color.WHITE);

		preColor.setIcon(null);
		preColor.setBackground(Color.BLACK);

		rubber.setIcon(new ImageIcon(getClass().getResource("/eraser.gif")));
		rubber.setBackground(Color.WHITE);

		line.setIcon(new ImageIcon(getClass().getResource("/line.gif")));
		line.setBackground(Color.WHITE);
		fill.setBackground(Color.WHITE);

		lasColor.setIcon(null);
		lasColor.setBackground(Color.WHITE);

		slider.setForeground(SystemColor.activeCaption);
		slider.setMinimum(1);
		slider.setValue(1);

		label.setBackground(SystemColor.inactiveCaption);
	}

	public void init() {
		w = 0;
		h = 0;
		isDrag = false;
	}
}
