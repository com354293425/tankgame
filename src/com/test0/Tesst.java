package com.test0;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tesst extends Applet{
	String dbUrl = "jdbc:odbc:people";
	String name = "";
	String password = "";
	Statement s;
	TextField serchFor = new TextField(10);
	Label completion = new Label("              ");
	TextArea results = new TextArea(40, 20);
	public void init() {
		serchFor.addTextListener(new SearchForl());
		Panel p = new Panel();
		p.add(new Label("Last name to search for:"));
		p.add(serchFor);
		p.add(completion);
		setLayout(new BorderLayout());
		add(p, BorderLayout.NORTH);
		add(results, BorderLayout.CENTER);
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection c = DriverManager.getConnection(dbUrl, name, password);
			s = c.createStatement();
		} catch (ClassNotFoundException e) {
			results.setText(e.getMessage());
		} catch (SQLException e) {
			results.setText(e.getMessage());
		}
	}
	class SearchForl implements TextListener {
		public void textValueChanged(TextEvent e) {
			ResultSet r;
			if(serchFor.getText().length() == 0) {
				completion.setText("");
				results.setText("");
				return;
			}
			try {
				r = s.executeQuery("SELECT last FROM people.csv people " +
									"where  (last like '" + serchFor.getText() + 
									"%') order by last");
				if(r.next())
					completion.setText(r.getString("last"));
				r = s.executeQuery("SELECT FIRST, LAST, EMAIL " +
									"FROM people.csv people " +
									"WHERE (LAST='" +
									completion.getText() +
									"') AND (EMAIL Is Not Null) " +
									"ORDER BY FIRST");
			} catch (Exception e2) {
				results.setText(serchFor.getText() + "\n");
				results.append(e2.getMessage());
				return;
			}
			results.setText(" ");
			try {
				while(r.next()) {
					results.append(
							r.getString("Last") + ", "
							+ r.getString("fIRST") +
							": " + r.getString("EMAIL") + "\n");
				}
			} catch(Exception e3) {
				results.setText(e3.getMessage());
			}
		}
	}
	public static void main(String[] args) {
		Tesst tesst = new Tesst();
		Frame aFrame = new Frame("Email lookup");
		aFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		aFrame.add(tesst, BorderLayout.CENTER);
		aFrame.setSize(500, 200);
		tesst.init();
		tesst.start();
		aFrame.setVisible(true);
	}
}
