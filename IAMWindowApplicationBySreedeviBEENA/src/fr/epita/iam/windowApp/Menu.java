package fr.epita.iam.windowApp;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import fr.epita.iam.datamodel.Identity;
import fr.epita.iam.exceptions.IdentityCreationException;
import fr.epita.iam.exceptions.IdentityDeletionException;
import fr.epita.iam.exceptions.IdentityUpdateException;
import fr.epita.iam.service.AdminDAO;
import fr.epita.iam.service.IdentityJDBCDAO;
import fr.epita.logger.Logger;

/**
 * This is the main menu GUI window where a user could perform all the JDBC services.
 * A user has to enter the details like UID, Name and email and perform 
 * Create, update search and delete operations on the input values provided in the respective text fields.
 * GUI contains 3 text fields (UID, Name and email-ID), 1 text field to enter UID for performing deleting operation.
 * A tableViewer is on the right side to display the contents of the database as per the actions performed.
 * @author Sreedevi BEENA
 *
 */
public class Menu {
	private static final Logger LOGGER = new Logger(Menu.class);
	protected Shell shellMainMenu;
	private Text textUid;
	private Text textDisplayName;
	private Text textEmail;
	private Label lblEnterTheUid;
	private Label lblEnterTheName;
	private Label lblNewLabel;
	private Label lblNewLabel_1;
	private Text textDeleteUID;
	private Button btnSearch;
	private Label lblNewLabel_2;
	private ScrolledComposite scrolledComposite;
	private Table table;
	private TableViewer tableViewer;
	
	/**
	 * Launch the application.
	 */
	public void menuview() {
		try {
			Menu window = new Menu();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the Menu GUI window. Creates the GUI components and sets the layout.
	 */
	public void open() {
		LOGGER.info("Enter : Main Menu");
		Display display = Display.getDefault();
		createContents();
		//shell.setVisible(true);
		shellMainMenu.open();
		shellMainMenu.layout();
		while (!shellMainMenu.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Creates contents of the Menu window. 
	 * Javadoc does not contain the comments of the functions carried out by each buttons in the layout and 
	 * hence refer the raw code inside to view the specific comments for each SelectionEvent of the button.
	 */
	protected void createContents() {
		//Title
		shellMainMenu = new Shell();
		shellMainMenu.setSize(770, 423);
		shellMainMenu.setText("Welcome to Main menu");
		shellMainMenu.setLayout(new FormLayout());
		
		//Label with text as Admin
		Label lblWelcomeAdmin = new Label(shellMainMenu, SWT.NONE);
		FormData fd_lblWelcomeAdmin = new FormData();
		lblWelcomeAdmin.setLayoutData(fd_lblWelcomeAdmin);
		lblWelcomeAdmin.setFont(SWTResourceManager.getFont("Monotype Corsiva", 13, SWT.BOLD));
		lblWelcomeAdmin.setAlignment(SWT.CENTER);
		lblWelcomeAdmin.setText("Welcome ADMIN");
		
		lblEnterTheUid = new Label(shellMainMenu, SWT.NONE);
		fd_lblWelcomeAdmin.left = new FormAttachment(lblEnterTheUid, 0, SWT.LEFT);
		FormData fd_lblEnterTheUid = new FormData();
		fd_lblEnterTheUid.bottom = new FormAttachment(0, 74);
		fd_lblEnterTheUid.top = new FormAttachment(0, 60);
		fd_lblEnterTheUid.left = new FormAttachment(0, 51);
		lblEnterTheUid.setLayoutData(fd_lblEnterTheUid);
		lblEnterTheUid.setText("Enter the UID");
		
		//Text box for UID
		textUid = new Text(shellMainMenu, SWT.BORDER);
		fd_lblWelcomeAdmin.right = new FormAttachment(textUid, 0, SWT.RIGHT);
		fd_lblWelcomeAdmin.bottom = new FormAttachment(textUid, -10);
		FormData fd_textUid = new FormData();
		fd_textUid.top = new FormAttachment(0, 57);
		fd_textUid.left = new FormAttachment(0, 197);
		textUid.setLayoutData(fd_textUid);
		
		lblEnterTheName = new Label(shellMainMenu, SWT.NONE);
		FormData fd_lblEnterTheName = new FormData();
		fd_lblEnterTheName.top = new FormAttachment(0, 86);
		fd_lblEnterTheName.left = new FormAttachment(0, 51);
		lblEnterTheName.setLayoutData(fd_lblEnterTheName);
		lblEnterTheName.setText("Enter the Name");
		
		//Text box for entering the name of the Identity
		textDisplayName = new Text(shellMainMenu, SWT.BORDER);
		FormData fd_textDisplayName = new FormData();
		fd_textDisplayName.top = new FormAttachment(0, 83);
		fd_textDisplayName.left = new FormAttachment(0, 197);
		textDisplayName.setLayoutData(fd_textDisplayName);
		
		lblNewLabel = new Label(shellMainMenu, SWT.NONE);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(0, 112);
		fd_lblNewLabel.left = new FormAttachment(0, 51);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("Enter the Email");
		
		//Text box for entering the email-id
		textEmail = new Text(shellMainMenu, SWT.BORDER);
		FormData fd_textEmail = new FormData();
		fd_textEmail.top = new FormAttachment(0, 109);
		fd_textEmail.left = new FormAttachment(0, 197);
		textEmail.setLayoutData(fd_textEmail);
		
		//Create button 
		Button btnCreate = new Button(shellMainMenu, SWT.NONE);
		FormData fd_btnCreate = new FormData();
		fd_btnCreate.left = new FormAttachment(0, 51);
		fd_btnCreate.top = new FormAttachment(lblNewLabel, 27);
		btnCreate.setLayoutData(fd_btnCreate);
		btnCreate.addSelectionListener(new SelectionAdapter() {

			/* (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected(SelectionEvent e) {
				try {
					/*
					 * Creates an Identity object and assigns the UID
					 */
					Identity uiid = new Identity();
					uiid.setUid(textUid.getText());
					/*
					 *  If UID is empty message dialogbox pops up informing the user to enter a valid UID.
					 *  else it checks whether the new UID has already been used.
					 */
					if(textUid.getText().trim().isEmpty()) {
						MessageDialog.openInformation(shellMainMenu, "Warning", "Please enter a UID");
					}else {
						//Creates an List<Identity> object to recieve the search results
						IdentityJDBCDAO abc = new IdentityJDBCDAO();
						List<Identity> Localidentities = new ArrayList<>();
						Localidentities = abc.search(uiid);
						int listCount = Localidentities.size();
						/*
						 *  IF listCount TRUE then there is an identity and dialog box informs the error and asks 
						 *  the user to enter another UID.
						 *  ELSE it sets the Identity object with the name as well as the email id
						 */
						if (listCount != 0) {
							MessageDialog.openInformation(shellMainMenu, "Warning", "Identity Already exists. Please enter a new one");	
						}else {
							if(textDisplayName.getText().trim().isEmpty() || textEmail.getText().trim().isEmpty()) {
								MessageDialog.openInformation(shellMainMenu, "Warning", "Please enter a valid Email and Name");
							}else {
								uiid.setDisplayName(textDisplayName.getText());
								uiid.setEmail(textEmail.getText());
								// Passes to the create function to insert the identity into the database
								abc.create(uiid);
								//Clears the text box values of the GUI
								clear();
								MessageDialog.openInformation(shellMainMenu, "Success", "Identity created successfully");
								// Set the UID to null 
								uiid=null;
								//Passes null object to display all the updated rows from the database
								display(uiid);
							}
						}
					}
				}catch (IdentityCreationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCreate.setText("Create");
		
		//Update button
		Button btnUpdate = new Button(shellMainMenu, SWT.NONE);
		fd_btnCreate.right = new FormAttachment(btnUpdate, -15);
		FormData fd_btnUpdate = new FormData();
		fd_btnUpdate.left = new FormAttachment(0, 129);
		fd_btnUpdate.bottom = new FormAttachment(btnCreate, 27);
		fd_btnUpdate.top = new FormAttachment(btnCreate, 0, SWT.TOP);
		btnUpdate.setLayoutData(fd_btnUpdate);
		btnUpdate.addSelectionListener(new SelectionAdapter() {
			/* (non-Javadoc)
			 * Update button functionality
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected(SelectionEvent e) {
				try {
					/*
					 * Sets the UID to the identity object and passes to the search function to check whether the UID exists.
					 * If listCount == 0, means no user with the UID and hence error dialog pops up 
					 * Else it sets the Displayname and email id parameters to Identity object
					 */
					Identity uiid = new Identity();
					uiid.setUid(textUid.getText());
					IdentityJDBCDAO abc = new IdentityJDBCDAO();
					List<Identity> Localidentities = new ArrayList<>();
					Localidentities = abc.search(uiid);
					int listCount = Localidentities.size();
					if (listCount == 0) {
						MessageDialog.openInformation(shellMainMenu, "Invalid", "The entered UID doesn't exist. Please enter a valid UID");	
					}
					else {
						uiid.setDisplayName(textDisplayName.getText());
						uiid.setEmail(textEmail.getText());
						/*
						 * If the entered textbox values are empty string then the existing value for the user 
						 * from the database will be assigned for the Identity object
						 */
						if(textDisplayName.getText().trim().isEmpty()) {
							uiid.setDisplayName(Localidentities.get(0).getDisplayName());
						}
						if(textEmail.getText().trim().isEmpty() || textEmail.getText().trim().isEmpty()) {
							uiid.setEmail(Localidentities.get(0).getEmail());
						}
						/*
						 * Opens a confirmation dialog with new details for the UID selected. 
						 * If user clicks OK then 
						 * Identity object passed to update function.
						 * Clears the text boxes
						 * Sets null and display function called to display the new updated results in the table.
						 */
						boolean confirm = MessageDialog.openConfirm(shellMainMenu, "Update Information", "\nUID\t:  "+uiid.getUid()+" ,\nName\t:  "+uiid.getDisplayName()+"\nEmail\t:  "+uiid.getEmail() );
						if(confirm) {
							abc.update(uiid);
							clear();
							MessageDialog.openInformation(shellMainMenu, "Success", "Identity updated successfully");
							uiid=null;
							display(uiid);
						}
					}
				} catch (IdentityUpdateException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setText("Update");
		
		lblNewLabel_1 = new Label(shellMainMenu, SWT.NONE);
		fd_btnCreate.bottom = new FormAttachment(100, -203);
		FormData fd_lblNewLabel_1 = new FormData();
		fd_lblNewLabel_1.top = new FormAttachment(btnCreate, 23);
		fd_lblNewLabel_1.left = new FormAttachment(lblEnterTheUid, 0, SWT.LEFT);
		lblNewLabel_1.setLayoutData(fd_lblNewLabel_1);
		lblNewLabel_1.setText("Enter the UID to be deleted");
		
		//Text box to enter the UID for the Delete operation
		textDeleteUID = new Text(shellMainMenu, SWT.BORDER);
		FormData fd_textDeleteUID = new FormData();
		fd_textDeleteUID.top = new FormAttachment(lblNewLabel_1, 7);
		fd_textDeleteUID.left = new FormAttachment(lblEnterTheUid, 0, SWT.LEFT);
		textDeleteUID.setLayoutData(fd_textDeleteUID);
		
		Button btnDelete = new Button(shellMainMenu, SWT.NONE);
		FormData fd_btnDelete = new FormData();
		fd_btnDelete.right = new FormAttachment(textDeleteUID, 65, SWT.RIGHT);
		fd_btnDelete.left = new FormAttachment(textDeleteUID, 2);
		fd_btnDelete.top = new FormAttachment(lblNewLabel_1, 4);
		fd_btnDelete.bottom = new FormAttachment(lblNewLabel_1, 31, SWT.BOTTOM);
		btnDelete.setLayoutData(fd_btnDelete);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			
			/* (non-Javadoc)
			 * Delete button functionality
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected(SelectionEvent e) {
				try {
					/*
					 * Identity object created and sets UID from the textbox "textDeleteUID" and serachs for the entry.
					 * If entry doesnt exists an error pops up
					 * Else the entry against the corresponding UID is deleted by
					 * passing the Identity object to delete function
					 * Sets Identity object to null and calls display function to display the updated table.
					 */
					Identity uiid = new Identity();
					uiid.setUid(textDeleteUID.getText());
					IdentityJDBCDAO abc = new IdentityJDBCDAO();
					List<Identity> Localidentities = new ArrayList<>();
					Localidentities = abc.search(uiid);
					int listCount = Localidentities.size();
					if (listCount == 0) {
						MessageDialog.openInformation(shellMainMenu, "Invalid", "The entered UID doesn't exist. Please enter a valid UID");	
					}
					else {
					abc.delete(uiid);
					textDeleteUID.setText("");
					MessageDialog.openInformation(shellMainMenu, "Success", "Identity deleted successfully");
					uiid=null;
					display(uiid);
					}
				} catch (IdentityDeletionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		btnDelete.setText("Delete");
		
		btnSearch = new Button(shellMainMenu, SWT.NONE);
		btnSearch.addSelectionListener(new SelectionAdapter() {
			/* (non-Javadoc)
			 * Search button in the GUI will search for the entries in the database and displays in the table
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
			 */
			public void widgetSelected(SelectionEvent e) {
				try {
					/*
					 * If the text field is non empty we assign the value else
					 * we dont assign and hence the object will hold a null value for other paramaters
					 * and the search function is called with uiid passed as argument 
					 */
					Identity uiid = new Identity();
					if (textUid.getText()!= "")
						uiid.setUid(textUid.getText());
					if (textDisplayName.getText()!= "")
						uiid.setDisplayName(textDisplayName.getText());
					if (textEmail.getText()!= "")
						uiid.setEmail(textEmail.getText());
					/*
					 * LocalIdentities object created to receive the returned values from search function
					 */
					IdentityJDBCDAO abc = new IdentityJDBCDAO();
					List<Identity> Localidentities = new ArrayList<>();
					Localidentities = abc.search(uiid);
					/*
					 * If no entries returned will display an error.
					 * else the object is passed to the display function to update the table with the returned results from the database
					 */
					if (Localidentities.size() == 0) {
						MessageDialog.openInformation(shellMainMenu, "Invalid", "The entered Identity details doesn't exist. Please enter valid details");	
					}
					else {
					display(uiid);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		fd_btnUpdate.right = new FormAttachment(btnSearch, -18);
		FormData fd_btnSearch = new FormData();
		fd_btnSearch.bottom = new FormAttachment(btnCreate, 27);
		fd_btnSearch.right = new FormAttachment(textUid, 0, SWT.RIGHT);
		fd_btnSearch.top = new FormAttachment(btnCreate, 0, SWT.TOP);
		fd_btnSearch.left = new FormAttachment(textUid, -63);
		btnSearch.setLayoutData(fd_btnSearch);
		btnSearch.setText("Search");
		
		lblNewLabel_2 = new Label(shellMainMenu, SWT.CENTER);
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Times New Roman", 11, SWT.BOLD));
		FormData fd_lblNewLabel_2 = new FormData();
		fd_lblNewLabel_2.top = new FormAttachment(0, 10);
		fd_lblNewLabel_2.left = new FormAttachment(0, 210);
		fd_lblNewLabel_2.right = new FormAttachment(100, -271);
		lblNewLabel_2.setLayoutData(fd_lblNewLabel_2);
		lblNewLabel_2.setText("Identity Management System");
		
		scrolledComposite = new ScrolledComposite(shellMainMenu, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		FormData fd_scrolledComposite = new FormData();
		fd_scrolledComposite.left = new FormAttachment(textUid, 34);
		fd_scrolledComposite.bottom = new FormAttachment(lblNewLabel_2, 304, SWT.BOTTOM);
		fd_scrolledComposite.top = new FormAttachment(lblNewLabel_2, 28);
		fd_scrolledComposite.right = new FormAttachment(100, -48);
		scrolledComposite.setLayoutData(fd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		tableViewer = new TableViewer(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
	}
	
	/**
	 * Function to set the text box values as "" after Create and update operation
	 */
	public void clear() {
		textDisplayName.setText("");
		textUid.setText("");
		textEmail.setText("");
	}
	
	/**
	 * Display function to diaplay the database output to a table.
	 * It takes Identity object as an argument. 
	 * The function passes the Identity object to search function and later displays the 
	 * returned list received from the search function.
	 * @param checkid Identity object passed to the function. It also can be null.
	 */
	public void display(Identity checkid) {
		tableViewer = new TableViewer(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
        table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		IdentityJDBCDAO disp_jdbc = new IdentityJDBCDAO();
		List<Identity> resultIdentities = new ArrayList<>();
		resultIdentities = disp_jdbc.search(checkid);
		/*
		 * Sets the column ID's for the table
		 */
        String [] columnNames =  {"UID", "DISPLAY_NAME", "EMAIL"};
	        for(int i=0; i< columnNames.length ; i++) {
	        	TableViewerColumn tab = new TableViewerColumn(tableViewer, SWT.NONE);
	        	TableColumn newcol = tab.getColumn();
	        	newcol.setWidth(120);
	        	if (i==2) 
		        	newcol.setWidth(200);
	        	newcol.setText(columnNames[i]);	
	        }
	    
	        for (int i = 0; i < resultIdentities.size(); ++i) {
	        	TableItem item =new TableItem(table, SWT.NONE);
	        	item.setText(0,resultIdentities.get(i).getUid());
	        	item.setText(1,resultIdentities.get(i).getDisplayName());
	        	item.setText(2,resultIdentities.get(i).getEmail());
	         }
        }
}
