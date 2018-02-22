package fr.epita.iam.windowApp;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
//import org.eclipse.wb.swt.Menu;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import fr.epita.iam.datamodel.User;
import fr.epita.iam.service.AdminDAO;
import fr.epita.iam.service.IdentityJDBCDAO;
import fr.epita.logger.Logger;


/**
 * Main Login window of the Application to perform the authentication.
 * This window is the starting point for the GUI.
 * @author Sreedevi BEENA
 *
 */
public class Login {

	/**
	 * This is the Login window
	 */
	protected Shell shell;
	/**
	 * Username field 
	 */
	private Text username;
	/**
	 * Password field
	 */
	private Text password;
	/**
	 * Logger object created to record the details happening in this class
	 */
	private static final Logger LOGGER = new Logger(Login.class);
	
	/**
	 * Function to Launch the application. Creates the Login window object and opens the window.
	 */
	public void launch() {
		try {
			Login window = new Login();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window. Sets the layout.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Creates all GUI contents of the Login window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Welcome page of IAM Application");
		
		Label lblNewLabel = new Label(shell, SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Monotype Corsiva", 14, SWT.BOLD));
		lblNewLabel.setBounds(74, 10, 273, 29);
		lblNewLabel.setText("Welcome to IAM Application");
		
		Label lblUserName = new Label(shell, SWT.NONE);
		lblUserName.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblUserName.setBounds(49, 71, 76, 15);
		lblUserName.setText("User Name");
		
		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblPassword.setBounds(49, 124, 76, 15);
		lblPassword.setText("Password");
		
		username = new Text(shell, SWT.BORDER);
		username.setBounds(177, 65, 109, 21);
		
		password = new Text(shell, SWT.BORDER);
		password.setBounds(177, 118, 109, 21);
		password.setEchoChar('*');
		
		//Login button
		Button btnLogin = new Button(shell, SWT.NONE);
		
		//Login button functionality
		btnLogin.addSelectionListener(
		new SelectionAdapter() {			
			/* (non-Javadoc)
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected(SelectionEvent e) {
				try {
					//Setting the user credentials
					LOGGER.info("Login Initiated");
					User userLogin = new User();
					userLogin.setUsername(username.getText());
					userLogin.setPassword(password.getText());
					
					//Creates AdminDAO object and passes to authenticate function for authentication. 
					AdminDAO adminObj = new AdminDAO();
					boolean login = adminObj.authenticate(userLogin);
					
					//IF TRUE closes the Login window and opens the Menu window
					if(login == true) {
						shell.close();
						Menu obj = new Menu();
						obj.menuview();
					}else
						MessageDialog.openError(shell, "Error", "Invalid Username and Password");					 		
				}catch (Exception e1) {
					LOGGER.error("error during Login :" + e1.getMessage());
					return;
				}				
			}
		});
				
		btnLogin.setBounds(190, 187, 75, 25);
		btnLogin.setText("Login");

	}
	
}
