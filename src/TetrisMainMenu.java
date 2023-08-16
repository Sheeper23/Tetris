



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The main menu for Tetris. Can set the game's parameters here.
 * 
 * @author Jai Misquith
 * @version (2022-07-25)
 */
public class TetrisMainMenu extends JFrame {
	private BufferedImage logoImage;
	private JLabel logo;
	private JPanel logoPanel;
	private JPanel paramPanel;
	private JComboBox<String> presets;
	private JTextField rows;
	private JTextField cols;
	private JTextField slowness;
	private JButton submit;
	private GameWindow window;
	
	public TetrisMainMenu() {
		super("Tetris: Main Menu");
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setLocation(650, 200);
		setSize(600, 400);
		
		try {
			logoImage = ImageIO.read(new URL("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/756ac3cf-5cef-4bfe-a74e-43e4d713903a/d8h1sf0-7c3dd7bd-a7de-474f-b577-37ef8ec354f6.png/v1/fill/w_1024,h_341,strp/tetris_logo_by_jmk_prime_d8h1sf0-fullview.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9MzQxIiwicGF0aCI6IlwvZlwvNzU2YWMzY2YtNWNlZi00YmZlLWE3NGUtNDNlNGQ3MTM5MDNhXC9kOGgxc2YwLTdjM2RkN2JkLWE3ZGUtNDc0Zi1iNTc3LTM3ZWY4ZWMzNTRmNi5wbmciLCJ3aWR0aCI6Ijw9MTAyNCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl19.sBBPAfM7oruGBabGdqrlC0O2KTPnec8BrS6jtq1Jhq8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		logo = new JLabel(new ImageIcon(new ImageIcon(logoImage).getImage().getScaledInstance(450, 150, Image.SCALE_DEFAULT)), JLabel.CENTER);
		logoPanel = new JPanel();
		
		logoPanel.setBackground(Color.DARK_GRAY);
		logoPanel.setMaximumSize(new Dimension(600, 200));
		
		paramPanel = new JPanel();
		paramPanel.setBackground(Color.DARK_GRAY);
		paramPanel.setLayout(new BoxLayout(paramPanel, BoxLayout.Y_AXIS));
		
		String[] options = {"Custom", "Dev Intended", "Mini Tetris", "Mega Tetris"};
		presets = new JComboBox<String>(options);
		presets.setMaximumSize(new Dimension(600, 50));
		
		rows = new JTextField("Number of Rows");
		cols = new JTextField("Number of Columns");
		slowness = new JTextField("Autodrop Wait Time (seconds)");
		
		rows.setMaximumSize(new Dimension(150, 50));
		cols.setMaximumSize(new Dimension(150, 50));
		slowness.setMaximumSize(new Dimension(200, 50));
		
		rows.setEditable(true);
		cols.setEditable(true);
		slowness.setEditable(true);
		
		JPanel textBoxes = new JPanel();
		textBoxes.setBackground(Color.DARK_GRAY);
		textBoxes.setLayout(new BoxLayout(textBoxes, BoxLayout.X_AXIS));
		textBoxes.add(rows);
		textBoxes.add(cols);
		textBoxes.add(slowness);
		
		submit = new JButton("Play");
		
		
		rows.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (rows.getText().equals("Number of Rows")) rows.setText("");
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (rows.getText().equals("")) rows.setText("Number of Rows");
				
			}
			
		});
		
		cols.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (cols.getText().equals("Number of Columns")) cols.setText("");
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (cols.getText().equals("")) cols.setText("Number of Columns");
				
			}
			
		});
		
		slowness.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (slowness.getText().equals("Autodrop Wait Time (seconds)")) slowness.setText("");
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (slowness.getText().equals("")) slowness.setText("Autodrop Wait Time (seconds)");
				
			}
			
		});
		
		presets.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				String choice = (String)presets.getSelectedItem();
				
				if (choice.equals("Custom")) {
					rows.setEditable(true);
					cols.setEditable(true);
					slowness.setEditable(true);
					
					
					rows.setText("Number of Rows");
					cols.setText("Number of Columns");
					slowness.setText("Autodrop Wait Time (seconds)");
				}
				else if (choice.equals("Dev Intended")) {
					rows.setEditable(false);
					cols.setEditable(false);
					slowness.setEditable(false);
					
					rows.setText("20");
					cols.setText("10");
					slowness.setText("1");
				}
				else if (choice.equals("Mini Tetris")) {
					rows.setEditable(false);
					cols.setEditable(false);
					slowness.setEditable(false);
					
					rows.setText("10");
					cols.setText("5");
					slowness.setText("1.5");
				}
				else if (choice.equals("Mega Tetris")) {
					rows.setEditable(false);
					cols.setEditable(false);
					slowness.setEditable(false);
					
					rows.setText("40");
					cols.setText("20");
					slowness.setText("0.5");
				}
			}
			
		});
		
		submit.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				try {  
				    Integer.parseInt(rows.getText());
				    Integer.parseInt(cols.getText());
				    Double.parseDouble(slowness.getText());
				  } catch(NumberFormatException f){
				    return;  
				  }  
				
				dispose();
				
				window = new GameWindow(Integer.parseInt(rows.getText()), Integer.parseInt(cols.getText()));
				
				int slownessVal = (int)(Double.parseDouble(slowness.getText()) * 1000);
				
				window.startGame();
				
				Thread t = new Thread(() -> {
			        while (true) {
			            try{
			                Thread.sleep(slownessVal);
			                window.autoMoveDown();
			            }
			            catch(InterruptedException f){
			                System.out.println("eeee!");
			            }
			        }
			    });
			    t.start();
				
				
				
				
			}
		});
		
		
		logoPanel.add(logo);
		
		paramPanel.add(presets);
		paramPanel.add(textBoxes);
		paramPanel.add(submit);
		
		
		add(logoPanel);
		add(paramPanel);
		
		
		
		
		
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.DARK_GRAY);
		
	}
	
}
