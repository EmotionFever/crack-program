import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Window.Type;
import javax.swing.JProgressBar;

public class PatchFrame extends JFrame {
	
	private static final String ASSETS_FOLDER = "assets\\";
	private static final String PATH = "\"%appdata%\\com.boonzi.desktop\\app\\"; 
	private static final String FILE = "boonzi-desktop.swf\"";
	private static final String FILE_PATH = PATH + FILE;
	private static final String CLASS_PATH = "com.boonzi.desktop.core.controller.AppController";
	private static final String PATCH_PATH = ASSETS_FOLDER + "patch.pcode";
	private static final String PROGRAM = ASSETS_FOLDER + "ffdec_11.2.0\\ffdec.jar";
	private static final String METHOD_BODY_INDEX = "42942";
	//private static final String CMD_EXEC = String.join(" ", new String[]{"java -jar",
	//		PROGRAM, "-replace", FILE_PATH, FILE_PATH, CLASS_PATH, PATCH_PATH, METHOD_BODY_INDEX});
	private static final String CMD_EXEC = "java -jar " + PROGRAM + " -replace " + FILE_PATH + " " + FILE_PATH + " " + CLASS_PATH + " " + PATCH_PATH + " " + METHOD_BODY_INDEX;
	

	private JFrame frmPatchForBoonzi;
	private JButton btnDoPatch;
	private JLabel lblProgramName;
	private JTextArea tta;
	private JProgressBar pgb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatchFrame window = new PatchFrame();
					window.frmPatchForBoonzi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PatchFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPatchForBoonzi = new JFrame();
		frmPatchForBoonzi.setResizable(false);
		frmPatchForBoonzi.setTitle("Patch for Boonzi");
		frmPatchForBoonzi.setBounds(100, 100, 261, 215);
		frmPatchForBoonzi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPatchForBoonzi.getContentPane().setLayout(new MigLayout("", "[274.00px,grow]", "[45px][23px][][90px,grow]"));
		
		btnDoPatch = new JButton("Do Patch");
		btnDoPatch.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				
				BufferedReader r;
				try {
					ProcessBuilder builder = new ProcessBuilder(
							"cmd.exe", "/c", CMD_EXEC);
					builder.redirectErrorStream(true);
					Process p = builder.start();
					r = new BufferedReader(new InputStreamReader(p.getInputStream()));
					
					for(String line = r.readLine(); line != null; line = r.readLine()) {
						tta.append(line + "\n");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				pgb.setValue(100);
				// create a jframe
			    JFrame frame = new JFrame("JOptionPane showMessageDialog example");

			    JOptionPane.showMessageDialog(frame,
			            "Your Boonzi was Patched!",
			            "Information",
			            JOptionPane.INFORMATION_MESSAGE);
			            
			}
		});
		frmPatchForBoonzi.getContentPane().add(btnDoPatch, "cell 0 1,alignx center,aligny top");
		
		lblProgramName = new JLabel("Patch for Boonzi");
		lblProgramName.setForeground(UIManager.getColor("OptionPane.foreground"));
		lblProgramName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblProgramName.setBackground(UIManager.getColor("MenuItem.selectionBackground"));
		lblProgramName.setHorizontalAlignment(SwingConstants.CENTER);
		frmPatchForBoonzi.getContentPane().add(lblProgramName, "cell 0 0,grow");
		
		pgb = new JProgressBar();
		frmPatchForBoonzi.getContentPane().add(pgb, "cell 0 2,growx");
		
		tta = new JTextArea();
		tta.setEditable(false);
		frmPatchForBoonzi.getContentPane().add(tta, "cell 0 3,grow");
	}
}
