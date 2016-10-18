package Server;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cqu.Shape;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;


public class ServerUi extends JFrame {
	public class Client implements Runnable {
		private Socket socket;
		private ObjectInputStream in;
		private ObjectOutputStream out;

		public Client(Socket s)

		{
			this.socket = s;
		}

		@Override
		public void run() {
			while (true) {
				Shape shape;
				try {
					in = new ObjectInputStream(this.socket.getInputStream());
					shape = (Shape) in.readObject();
					while (shape != null) {
						jta.append("\r\n服务器收到数据");
						for (Client e : clients) {
							if (e != this) {
								out = new ObjectOutputStream(
										e.socket.getOutputStream());
								jta.append("\r\n服务器转发数据");
								out.writeObject(shape);
								out.flush();
							}
						}
						try {
							Thread.sleep(10);
							shape = null;
						} catch (InterruptedException e) {

							// TODO Auto-generated catch block
						}
					}
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					try {
						socket.close();
						clients.remove(this);
						jta.append("\r\n有用户断开了连接\r\n" + "当前连接数"+ clients.size());
						number=clients.size();
						Number.setText(number.toString());
						Thread.currentThread().stop();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}
		}
	}

	private int port = 8100;
	private ServerSocket server;
	private List<Client> clients = new ArrayList<Client>();
	private JTextArea jta = new JTextArea();
	private JLabel Number = new JLabel("0");
	private Integer number;

	public ServerUi() {
		number=0;
		getContentPane().setLayout(new BorderLayout());
		jta.setBackground(Color.LIGHT_GRAY);
		JScrollPane scrollPane = new JScrollPane(jta);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		setTitle("\u7F51\u7EDC\u767D\u677F\uFF08\u670D\u52A1\u5668\uFF09");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); 
		try {
			server = new ServerSocket(port);
			jta.setText("服务已开启，等待用户");
			
			JPanel panel = new JPanel();
			panel.setBackground(SystemColor.activeCaption);
			scrollPane.setColumnHeaderView(panel);
			
			JLabel label = new JLabel("\u8FDE\u63A5\u6570:");
			label.setFont(new Font("汉仪粗黑简", Font.BOLD, 20));
			panel.add(label);
			Number.setFont(new Font("微软雅黑", Font.PLAIN, 20));
			
			panel.add(Number);
		} catch (IOException e) {
			System.out.println("错误：端口正在使用当中！");
			System.exit(0);
		}
		while (true) {
			Socket socket = new Socket();
			try {
				socket = server.accept();
				Client c = new Client(socket);
				clients.add(c);
				jta.append("\r\n有新的连接  \\IP:" + socket.getInetAddress());
				jta.append("\r\n当前连接数" + clients.size());
				number=clients.size();
				Number.setText(number.toString());
				new Thread(c).start();
			} catch (IOException e) {
			}

		}
	}

	public static void main(String[] args) {
		new ServerUi();
	}
}
