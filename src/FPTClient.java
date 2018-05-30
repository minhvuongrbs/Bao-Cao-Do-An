

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;


import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.GroupLayout;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Dimension;
import javax.swing.SwingConstants;

/**
 *
 * @author MINHVUONG
 */
public class FPTClient extends javax.swing.JFrame implements ActionListener{

    private static String FTP_SERVER_ADDRESS=null;
    private static int FTP_PORT_NUMBER =0;
    private static final int FTP_TIMEOUT=60000;
    private static final int BUFFER_SIZE=1024*1024*1;
    private static String FTP_USERNAME=null;
    private static String FTP_PASSWORD=null;
    private static final String SLASH="/";
    private FTPClient ftpClient;
    private DefaultTableModel modelRemote;
    private String status="";
    private  static FPTClient fptClient;
    //tree
    private DefaultMutableTreeNode root, root_1;
    private DefaultTreeModel treeModel,treeModel_1;
    private static File fileRoot, fileRoot_1;
    private JTree tree, tree_1;
    private static String defaultLocalPath="F:\\";
    private static String downloadFolder = "E:\\IT\\Ki - 8\\FTPClient";
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FPTClient().setVisible(true);
            }
        });
        
        
        
        
    }
    
    public FPTClient() {
        initComponents();
        modelRemote=new DefaultTableModel();
        jTableRemote.setModel(modelRemote);
        String column[]={"FileName","FileSize","FileType","LastModified"};
        modelRemote.setColumnIdentifiers(column);
        jTableRemote.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                System.out.println(jTableRemote.getValueAt(jTableRemote.getSelectedRow(), 0).toString());
                status+="\n "+jTableRemote.getValueAt(jTableRemote.getSelectedRow(), 0).toString();
                txtpaneStatus.setText(status);
            }
        });
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
		
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldHost = new javax.swing.JTextField();
        jTextFieldUsername = new javax.swing.JTextField();
        jTextFieldStatus = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldPort = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButtonConnect = new javax.swing.JButton();
        jPasswordField = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPaneRemote = new javax.swing.JScrollPane();
        jTableRemote = new javax.swing.JTable();
        jScrollPaneLocal = new javax.swing.JScrollPane();
        scrollPaneStatus = new JScrollPane();
        txtpaneStatus = new JTextPane();
        txtpaneStatus.setPreferredSize(new Dimension(950, 20));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ftp Client");
        setPreferredSize(new Dimension(1000, 550));

        jLabel1.setText("Host:");

        jLabel2.setText("Username:");

        jLabel3.setText("Password:");

        jLabel4.setText("Port:");

        jButtonConnect.setText("Connect");
        jButtonConnect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonConnectMouseClicked(evt);
            }
        });

        jPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldHost, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(jPasswordField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jButtonConnect)
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButtonConnect)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("Remote site");

        jTableRemote.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
//        jScrollPaneRemote.setViewportView(jTableRemote);
        
        btnDownload = new JButton("Download");
        
        btnUpload = new JButton("Upload");
        
        btnDelete = new JButton("Delete");
        
        btnChoose = new JButton("Choose");
        btnChoose.addActionListener(this);
        btnChoose.setActionCommand("btnBrowse");
        
        
        scrollPaneStatus.setRowHeaderView(txtpaneStatus);
        jLabel5 = new javax.swing.JLabel();
        
                jLabel5.setText("Local site");
        
        textFieldPath = new JTextField(defaultLocalPath);
        textFieldPath.setEditable(false);
        textFieldPath.setColumns(10);
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap(676, Short.MAX_VALUE)
        			.addComponent(jLabel6)
        			.addGap(251))
        		.addGroup(layout.createSequentialGroup()
        			.addGap(49)
        			.addComponent(jLabel5)
        			.addContainerGap(891, Short.MAX_VALUE))
        		.addGroup(layout.createSequentialGroup()
        			.addGap(34)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(491))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(scrollPaneStatus, GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(jScrollPaneLocal, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
        							.addComponent(jScrollPaneRemote, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE))
        						.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        							.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        								.addGap(55)
        								.addComponent(btnDownload)
        								.addGap(44)
        								.addComponent(btnUpload, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
        								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        								.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
        							.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        								.addComponent(textFieldPath, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
        								.addGap(18)
        								.addComponent(btnChoose, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))))
        					.addGap(55))))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(scrollPaneStatus, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnDownload)
        				.addComponent(btnUpload)
        				.addComponent(btnDelete))
        			.addGap(18)
        			.addComponent(jLabel6)
        			.addGap(9)
        			.addComponent(jLabel5)
        			.addGap(10)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(textFieldPath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnChoose))
        			.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jScrollPaneRemote, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jScrollPaneLocal, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        
        
        
//addTree(fileRoot);
        
        //tree 1
        fileRoot_1 = new File(defaultLocalPath);
        root_1 = new DefaultMutableTreeNode(new FileNode(fileRoot_1));
        treeModel_1 = new DefaultTreeModel(root_1);
        
        

        tree_1 = new JTree(treeModel_1);
        tree_1.setBounds(10, 252, 343, 436);
        tree_1.setShowsRootHandles(true);
//        btnShow.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				System.out.println("click show");
//				defaultLocalPath=textFieldPath.getText().trim();
//				System.out.println("default "+defaultLocalPath);
//				root_1 = new DefaultMutableTreeNode(new FileNode(fileRoot_1));
//		        treeModel_1 = new DefaultTreeModel(root_1);
//		        tree_1.setModel(treeModel_1);
//		        jScrollPaneLocal.setViewportView(tree_1);
//		        CreateChildNodes ccn_1 = 
//		                new CreateChildNodes(fileRoot_1, root_1);
//		        new Thread(ccn_1).start();
//				
//			}
//		});
        tree_1.addTreeExpansionListener(new TreeExpansionListener() {
			
			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				// TODO Auto-generated method stub
				TreePath path = event.getPath();
		        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
		        String data = node.getUserObject().toString();
		        System.out.println("Expanded: " + data);
			}
			
			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				// TODO Auto-generated method stub
				TreePath path = event.getPath();
		        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
		        String data = node.getUserObject().toString();
		        System.out.println("Collapsed: " + data);
			}
		});
        tree_1.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
              DefaultMutableTreeNode node = (DefaultMutableTreeNode) e
                  .getPath().getLastPathComponent();
              System.out.println("You selected " + node);
              status+="\n Status: You selected " + node;
              txtpaneStatus.setText(status);
              
            }
          });
        jScrollPaneLocal.setViewportView(tree_1);
        getContentPane().setLayout(layout);
        pack();
        
        CreateChildNodes ccn_1 = 
                new CreateChildNodes(fileRoot_1, root_1);
        new Thread(ccn_1).start();
        btnDownload.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("click download");
				status+="\n Status: Click download";
				txtpaneStatus.setText(status);
//				FPTClient fptClient=new FPTClient();
				String downloadPath= SLASH+jTableRemote.getValueAt(jTableRemote.getSelectedRow(), 0).toString();
				System.out.println("downloadpath: "+downloadPath);
				System.out.println("dowload: dowloadfolder: "+downloadFolder);
				fptClient.downloadFTPFile(downloadPath, downloadFolder+SLASH+jTableRemote.getValueAt(jTableRemote.getSelectedRow(), 0).toString());
			}
		});
        btnChoose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(fileRoot_1);
				//jc.showOpenDialog(jc);
				if(fileRoot_1==null)
					chooser.setCurrentDirectory(new java.io.File("E:\\"));
				else chooser.setCurrentDirectory(new java.io.File(fileRoot_1.getPath()));
			    chooser.setDialogTitle("Chọn thư mục");
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    //
			    // disable the "All files" option.
			    //
			    chooser.setAcceptAllFileFilterUsed(false);
			    //    
			   
			    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
			      System.out.println("getCurrentDirectory(): " 
			         +  chooser.getCurrentDirectory());
			      System.out.println("getSelectedFile() : " 
			         +  chooser.getSelectedFile());
			      fileRoot_1 = chooser.getSelectedFile();
				    System.out.println(fileRoot_1.getPath());
				    textFieldPath.setText(fileRoot_1.getPath());
				    status+="\n Status: You choose direction:"+fileRoot_1.getPath();
				    txtpaneStatus.setText(status);
				    addTree(fileRoot_1);
				   // contentPane.updateUI();
				    
				    getContentPane().repaint();
			      }
			    else {
			      System.out.println("No Selection ");
			      status+="\n Status: No selection direction";
			      }
				
			}
		});
    }// </editor-fold>
    
//    public void actionPerformed(ActionEvent e) {
//		switch(e.getActionCommand()) {
//		case "btnChoose": 
//			
//		    break;
//		}	    
//	}
    
	public void addTree(File fileRoot){
		fileRoot = new File(fileRoot.getPath());
		System.out.println("addtree: pass create file");
        root = new DefaultMutableTreeNode(new FileNode(fileRoot));
        treeModel = new DefaultTreeModel(root);

        tree_1 = new JTree(treeModel);
        tree_1.setBounds(10, 252, 343, 436);
        tree_1.setShowsRootHandles(true);
        
        tree_1.addTreeExpansionListener(new TreeExpansionListener() {
			
			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				// TODO Auto-generated method stub
				TreePath path = event.getPath();
		        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
		        String data = node.getUserObject().toString();
		        System.out.println("Expanded: " + data);
			}
			
			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				// TODO Auto-generated method stub
				TreePath path = event.getPath();
		        DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
		        String data = node.getUserObject().toString();
		        System.out.println("Collapsed: " + data);
			}
		});
        tree_1.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
              DefaultMutableTreeNode node = (DefaultMutableTreeNode) e
                  .getPath().getLastPathComponent();
              System.out.println("You selected " + node);
              status+="\n Status: You selected "+node;
              txtpaneStatus.setText(status);
            }
          });
       
        
        jScrollPaneLocal.setViewportView(tree_1);
        CreateChildNodes ccn = 
                new CreateChildNodes(fileRoot, root);
        new Thread(ccn).start();
        
        getContentPane().revalidate();
        //contentPane.add(tree);
	}
    
    public class CreateChildNodes implements Runnable {

        private DefaultMutableTreeNode root;

        private File fileRoot;

        public CreateChildNodes(File fileRoot, 
                DefaultMutableTreeNode root) {
            this.fileRoot = fileRoot;
            this.root = root;
        }

        @Override
        public void run() {
            createChildren(fileRoot, root);
        }

        private void createChildren(File fileRoot, 
                DefaultMutableTreeNode node) {
            File[] files = fileRoot.listFiles();
            if (files == null) return;
            for (File file : files) {
                DefaultMutableTreeNode childNode = 
                        new DefaultMutableTreeNode(new FileNode(file));
                node.add(childNode);
                if (file.isDirectory()) {
                    createChildren(file, childNode);
                }
            }
        }

    }

    public class FileNode {

        private File file;

        public FileNode(File file) {
            this.file = file;
        }

        @Override
        public String toString() {
            String name = file.getName();
            if (name.equals("")) {
                return file.getAbsolutePath();
            } else {
                return name;
            }
        }
    }


    private void jPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void jButtonConnectMouseClicked(java.awt.event.MouseEvent evt) {                                            
        connectFTPServer();
    }                                           

    /**
     * @param args the command line arguments
     */

    private void connectFTPServer() {
        FTP_SERVER_ADDRESS=jTextFieldHost.getText();
        FTP_USERNAME=jTextFieldUsername.getText();
        FTP_PASSWORD=jPasswordField.getText();
        FTP_PORT_NUMBER=Integer.parseInt(jTextFieldPort.getText());
        ftpClient=new FTPClient();
        try {
            System.out.println("connecting to ftp server.....");
            //connect to ftp server
            ftpClient.setDefaultTimeout(FTP_TIMEOUT);
            ftpClient.connect(FTP_SERVER_ADDRESS, FTP_PORT_NUMBER);
            //run the passive mode command
            ftpClient.enterLocalPassiveMode();
            //check reply code
            if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
                disConnectFTPServer();
                throw new Exception("FTP server not respond!");
            }else{
                ftpClient.setSoTimeout(FTP_TIMEOUT);
                //login ftp server
                try {
                    if(ftpClient.login(FTP_USERNAME, FTP_PASSWORD)){
                        System.out.println(FTP_USERNAME+" Login thanh cong");
                        setTitle(FTP_USERNAME+"@"+FTP_SERVER_ADDRESS);
                        status+="\n Status: Đăng nhập thành công";
                        txtpaneStatus.setText(status);
                        getListFile();
                        
                }else{
                        System.out.println("User or password is incorrect!");
                        JOptionPane.showMessageDialog(this, "Thông tin đăng nhập không xác thực");
                        status+="\n Status: Thông tin đăng nhập không xác thực";
                        txtpaneStatus.setText(status);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ftpClient.setDataTimeout(FTP_TIMEOUT);
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void disConnectFTPServer() {
        if(ftpClient!=null&&ftpClient.isConnected()){
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    // Variables declaration - do not modify                     
    private javax.swing.JButton jButtonConnect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JScrollPane jScrollPaneLocal;
    private javax.swing.JScrollPane jScrollPaneRemote;
    private javax.swing.JTable jTableRemote;
    private javax.swing.JTextField jTextFieldHost;
    private javax.swing.JTextField jTextFieldPort;
    private javax.swing.JTextField jTextFieldUsername;
    private javax.swing.JTextField jTextFieldStatus;
    private JButton btnDownload;
    private JButton btnUpload;
    private JButton btnDelete;
    private JButton btnChoose;
    private JScrollPane scrollPaneStatus;
    private JTextPane txtpaneStatus;
    private JTextField textFieldPath;
    
    // End of variables declaration                   

    private void getListFile() throws IOException {
        String column[]={"FileName","FileSize","FileType","LastModified"};
        modelRemote.setColumnIdentifiers(column);
        jScrollPaneRemote.setViewportView(jTableRemote);
        FTPFile[] ftpFiles=ftpClient.listFiles();
        for (FTPFile ftpFile:ftpFiles) {
        	String rows[] = new String[4];
            if(ftpFile.getType()==FTPFile.FILE_TYPE){
                String fileName=ftpFile.getName().toString();
                rows[0]=fileName;
                rows[1]=Long.toString(ftpFile.getSize()/1024)+" KB";
                if(ftpFile.getType()==0) {
                	int kt=fileName.length()-1;
                	while(fileName.charAt(kt)!='.' && kt!=0) {
                		kt--;
                		if(fileName.charAt(kt)=='.') {
                        	String fileType=fileName.substring(kt+1, fileName.length());
                        	rows[2]=fileType;
                		}else {
                        	String fileType="file";
                        	rows[2]=fileType;
                		}
                	}
                }
            }else if(ftpFile.getType()==FTPFile.DIRECTORY_TYPE) {
            	System.out.println("file type: direction");
                String fileName=ftpFile.getName().toString();
            	rows[0]=fileName;
                rows[1]=Long.toString(ftpFile.getSize()/1024)+" KB";
            	rows[2]="Folder";
            }
            rows[3]=ftpFile.getRawListing().toString();
            modelRemote.addRow(rows); 
        }
    }
    private void downloadFTPFile(String ftpFilePath, String downloadFilePath) {
        System.out.println("File " + ftpFilePath + " is downloading...");
        System.out.println("download: downloadfilepath: "+downloadFilePath);
        status+="\n"+"File " + ftpFilePath + " is downloading...";
        System.out.println("Download: test ftpClient charset: "+ftpClient.getCharsetName());
        System.out.println("mv");
        txtpaneStatus.setText(status);
        System.out.println("mv2");
        OutputStream outputStream=null;
        boolean success=false;
        try {
            File downloadFile=new File(downloadFilePath);
            outputStream=new BufferedOutputStream(new FileOutputStream(downloadFile));
            //download file from FTPServer
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.setBufferSize(BUFFER_SIZE);
            success=ftpClient.retrieveFile(ftpFilePath, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (success) {
            System.out.println("File " + ftpFilePath 
                    + " has been downloaded successfully.");
            status+="\n"+" Status:File " + ftpFilePath 
                    + " has been downloaded successfully.";
            txtpaneStatus.setText(status);
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
