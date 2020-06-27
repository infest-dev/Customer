/**
 * Copyright © 2017 Infest Developer Team.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * * Neither the name of the copyright holder nor the names of its contributors
 *   may be used to endorse or promote products derived from this software 
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.jogjadamai.infest.customer;

import com.jogjadamai.infest.communication.CustomerClient;
import com.jogjadamai.infest.communication.IProtocolClient;
import com.jogjadamai.infest.communication.IProtocolServer;
import com.jogjadamai.infest.customer.BussinessPane.ChildContentPaneList;
import com.jogjadamai.infest.customer.CorePane.ContentPaneList;
import com.jogjadamai.infest.customer.MainGUI.MainPaneList;
import com.jogjadamai.infest.customer.MainGUI.HeaderButtonOption;
import com.jogjadamai.infest.entity.Carts;
import com.jogjadamai.infest.entity.Features;
import com.jogjadamai.infest.entity.Menus;
import com.jogjadamai.infest.entity.Orders;
import com.jogjadamai.infest.entity.Tables;
import com.jogjadamai.infest.security.Credentials;
import com.jogjadamai.infest.security.CredentialsManager;
import com.jogjadamai.infest.service.ProgramPropertiesManager;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Danang Galuh Tegar P
 */
public class Customer {
    
    private Registry registry;
    private IProtocolClient protocolClient;
    private IProtocolServer protocolServer;
    protected Tables activeTable;
    private Carts activeCart;
    private List<Orders> activeListOfOrders;
    private List<Menus> activeListOfMenus;
    private ProgramPropertiesManager programPropertiesManager;
    private HeaderButtonOption activeHeaderButton;
    
    private final MainGUI mainFrame;
    
    protected static enum CustomerFeatures {
        MAINTENANCE_MODE, SHOW_CURRENCY, PRINT_BILL, SHOW_MENU_DURATION, SHOW_MENU_IMAGE
    }
    
    private static Customer INSTANCE;
    
    private Customer(MainGUI mainFrame) {
        this.mainFrame = mainFrame;
    }
    
    protected static Customer createInstance(MainGUI mainFrame) {
        if(INSTANCE == null) INSTANCE = new Customer(mainFrame);
        return INSTANCE;
    }
    
    protected static Customer getInstance() {
        return INSTANCE;
    }
    
    private String getNowTime() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now());
    }
    
    protected void onFirstRun() {
        File configFile = new File("infest.conf");
        if (!configFile.exists()) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Hi, welcome to Infest Program!\n"
                            + "\n"
                            + "We believe that it is your first time running this application.\n"
                            + "Before proceeding, please fill something for us :)", 
                    "INFEST: Program Configuration Manager", 
                    JOptionPane.INFORMATION_MESSAGE);
           new FirstTimeConfiguration().setVisible(true);
        } else {
           initialiseConnection();
           askTable();
        }
    }
    
    private void initialiseConnection() {
        programPropertiesManager = ProgramPropertiesManager.getInstance();
        String serverAddress = null;
        try {
            serverAddress = programPropertiesManager.getProperty("serveraddress");
        } catch (NullPointerException ex) {
            System.err.println("[INFEST] " +  getNowTime() + ": " + ex);
            JOptionPane.showMessageDialog(mainFrame, 
                    "Infest Configuration File is miss-configured!\n"
                            + "\n"
                            + "Please verify that the Infest Configuration File (infest.conf) is exist in the current\n"
                            + "working directory and is properly configured. Any wrong setting or modification of\n"
                            + "Infest Configuration File would cause this error.", 
                    "INFEST: Program Configuration Manager",
                    JOptionPane.ERROR_MESSAGE);
            fatalExit(-1);
        }
        try {
            this.registry = LocateRegistry.getRegistry(serverAddress, 42700);
            this.protocolClient = new CustomerClient();
            this.protocolServer = (IProtocolServer) this.registry.lookup("InfestAPIServer");
            this.protocolServer.authenticate(this.protocolClient);
        } catch (NotBoundException | RemoteException ex) {
            write(-1, ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(mainFrame, 
                    "Failed to initialise remote connection to Infest API Server (" + serverAddress  +")!\n"
                            + "\n"
                            + "Please verify that Infest API Server is currently turned on and the configuration file\n"
                            + "of this program is properly configured to the Infest API Server address.",
                    "INFEST: Remote Connection Error", 
                    JOptionPane.ERROR_MESSAGE);
            fatalExit(-1);
        }
    }
    
    protected void askTable() {
        if(isMaintenanceModeActive()) goOffline(-1);
        else {
            if(activeTable == null) {
                try {
                    List<Tables> allTables = this.protocolServer.readAllTable(protocolClient);
                    if(allTables == null) {
                        goOffline(-1);
                    } else {
                        List<Tables> activeTables = new ArrayList<>();
                        allTables.stream().filter((table) -> (table.getStatus() == 1)).forEachOrdered((table) -> {
                            activeTables.add(table);
                        });
                        AskTableDialog askTableDialog = AskTableDialog.getDialogInstance(activeTables);
                        askTableDialog.setVisible(true);
                    }
                } catch (RemoteException ex) {
                    write(-1, ex.getLocalizedMessage());
                    JOptionPane.showMessageDialog(mainFrame, 
                            "Failed to communicate with Infest API Server!\n"
                                    + "\n"
                                    + "Please verify that Infest API Server is currently turned on and your network connection is working.\n"
                                    + "If Infest API Server is OFF, try to restart this program if problem after turning ON Infest API Server.\n"
                                    + "Infest Program will now switched to MAINTENANCE MODE.",
                            "INFEST: Remote Connection Error", 
                            JOptionPane.ERROR_MESSAGE);
                    fatalExit(-1);
                }    
            } else {
                write(0, "Customer using Active Table ID {" + activeTable.getId() +"}");
                ProgramControlWindow programControlWindow = ProgramControlWindow.getDialogInstance();
                programControlWindow.setVisible(true);
                mainFrame.setVisible(true);
                mainFrame.requestFocus();
                mainFrame.requestFocusInWindow();
            }
        }
    }
    
    private void goOffline(int code) {
        write(-1, "Infest Customer is now offline because of MAINTENANCE MODE is active.");
        JOptionPane.showMessageDialog(mainFrame, 
                "INFEST is being maintenanced right now.\n"
                        + "\n"
                        + "We apologize for this inconvinienve. Please try again later.", 
                "INFEST: Maintenance Mode", 
                JOptionPane.ERROR_MESSAGE);
        if(code == 0) switchMainPane(MainPaneList.WELCOME);
        else fatalExit(code);
    }
    
    protected void fatalExit(int code) {
        if(code!=0) JOptionPane.showMessageDialog(mainFrame,
                "Fatal error occured! Please contact an Infest Adminisrator.\n"
                        + "\n"
                        + "CODE [" + code + "]\n"
                        + "Infest Program is now exiting.", 
                "INFEST: System Error", 
                JOptionPane.ERROR_MESSAGE);
        shutdown(code);
    }
    
    protected void shutdown() {
        shutdown(0);
    }
    
    private void shutdown(int code) {
        write(code, "System exited with code " + code + ".");
        mainFrame.dispose();
        System.exit(code);
    }
    
    private void write(int code, String status) {
        if(code==0) System.out.println("[INFEST] " +  getNowTime() + ": " + status);
        else System.err.println("[INFEST] " +  getNowTime() + ": " + status);
    }
    
    private Features getFeature(CustomerFeatures customerFeature) {
        Integer featureId;
        switch(customerFeature) {
            case MAINTENANCE_MODE:
                featureId = 1;
                break;
            case SHOW_CURRENCY:
                featureId = 2;
                break;
            case PRINT_BILL:
                featureId = 4;
                break;
            case SHOW_MENU_DURATION:
                featureId = 5;
                break;
            case SHOW_MENU_IMAGE:
                featureId = 6;
                break;
            default:
                featureId = 0;
                break;
        }
        Features feature = new Features();
        try {
            feature = protocolServer.readFeature(protocolClient, featureId);
            write(0, "Succesfully getting feature {" + feature +"}.");
        } catch (RemoteException ex) {
            write(-1, ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(mainFrame,
                    "Failed to communicate with Infest API Server!\n\n"
                            + "Please verify that Infest API Server is currently turned on and your network connection is working.\n"
                            + "If Infest API Server is OFF, try to restart this program if problem after turning ON Infest API Server.\n"
                            + "Infest Program will now switched to MAINTENANCE MODE.",
                    "INFEST: Remote Connection Error",
                    JOptionPane.ERROR_MESSAGE);
            goOffline(0);
        } catch (NullPointerException ex) {
            write(-1, ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(mainFrame,
                    "Infest API Server give blank response!\n"
                            + "\n"
                            + "Please verify that Infest API Server is currently ON & LISTENING (not in IDLE mode).\n"
                            + "If Infest API Server is OFF, try to restart this program if problem after turning ON Infest API Server.\n"
                            + "Infest Program will now switched to MAINTENANCE MODE.",
                    "INFEST: Blank Response from Server",
                    JOptionPane.ERROR_MESSAGE);
            goOffline(0);
        }
        if(customerFeature == CustomerFeatures.SHOW_CURRENCY && feature.getStatus() == 0) feature.setDescription("");
        return feature;
    }
    
    private Boolean isMaintenanceModeActive() {
        return (getFeature(CustomerFeatures.MAINTENANCE_MODE).getStatus() == 1);
    }
    
    protected String getMenuType(Menus menu) {
        if (menu == null) return "";
        else {
            switch(menu.getType()) {
                case 0:
                    return "OTHER";
                case 1:
                    return "APPETIZER";
                case 2:
                    return "MAIN COURSE";
                case 3:
                    return "DESSERT";
                case 4:
                    return "BEVERAGES";
                default:
                    return "UNDEFINED MENU TYPE";
            }
        }
    }
    
    protected Carts getActiveCart() {
        if(activeCart == null) return new Carts();
        else return activeCart;
    }
    
    protected List<Orders> getActiveListOfOrders() {
        if(activeListOfOrders == null) return new ArrayList<>();
        else return activeListOfOrders;
    }
    
    protected String getMenuPrice(Menus menu) {
        if (menu == null) return "";
        else {
            java.text.NumberFormat format = java.text.NumberFormat.getNumberInstance(java.util.Locale.getDefault());
            return format.format(menu.getPrice()) + ((!getFeature(CustomerFeatures.SHOW_CURRENCY).getDescription().isEmpty()) ? " " + getFeature(CustomerFeatures.SHOW_CURRENCY).getDescription() : "");
        }
    }
    
    protected String getMenuDuration(Menus menu) {
        if (menu == null) return "";
        else {
            if (getFeature(CustomerFeatures.SHOW_MENU_DURATION).getStatus() == 0) return "  ";
            else {
                LocalTime menuDurationLocalTime = LocalDateTime.ofInstant(menu.getDuration().toInstant(), ZoneId.systemDefault()).toLocalTime();
                String menuDurationString;
                if (menuDurationLocalTime.getHour() > 0) {
                    if (menuDurationLocalTime.getMinute() > 0) {
                        menuDurationString = menuDurationLocalTime.getHour() + " hour(s) and " + menuDurationLocalTime.getMinute() + " minute(s)";
                    } else {
                        menuDurationString = menuDurationLocalTime.getHour() + " hour(s)";
                    }
                } else {
                    if (menuDurationLocalTime.getMinute() > 0) {
                        menuDurationString = menuDurationLocalTime.getMinute() + " minute(s)";
                    } else {
                        menuDurationString = "can not be determined";
                    }
                }
                return menuDurationString;
            }
        }
    }
    
    protected BufferedImage getMenuImage(Menus menu) {
        if (menu == null) return null;
        else {
            if (getFeature(CustomerFeatures.SHOW_MENU_IMAGE).getStatus() == 0) {
                BufferedImage bufferedImage = null;
                try {
                    bufferedImage = ImageIO.read(getClass().getResource("/com/jogjadamai/infest/assets/DefaultMenuImage.png"));
                } catch (IOException ex) {
                    write(-1, ex.getLocalizedMessage());
                    bufferedImage = null;
                }
                return bufferedImage;
            }
            else {
                BufferedImage bufferedImage = null;
                try {
                    bufferedImage = ImageIO.read(new ByteArrayInputStream(menu.getImage()));
                } catch (IOException ex) {
                    write(-1, ex.getLocalizedMessage());
                    bufferedImage = null;
                } catch (NullPointerException ex) {
                    write(-1, ex.getLocalizedMessage());
                    try {
                        bufferedImage = ImageIO.read(getClass().getResource("/com/jogjadamai/infest/assets/DefaultMenuImage.png"));
                    } catch (IOException ioEx) {
                        write(-1, ioEx.getLocalizedMessage());
                        bufferedImage = null;
                    }
                } 
                return bufferedImage;
            }
        }
    }
    
    protected Boolean getMenuAvailability(Menus menu) {
        if (menu == null) return false;
        else {
            if (menu.getStock() > 0) return true;
            else return false;
        }
    }
    
    private void createActiveCart() throws RemoteException {
        Carts cart = new Carts();
        Instant dateInstant = LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date nowDate = Date.from(dateInstant);
        cart.setDate(nowDate);
        cart.setIdtable(activeTable);
        cart.setNote("");
        Instant timeInstant = LocalTime.now().atDate(LocalDate.of(2017, 1, 1)).atZone(ZoneId.systemDefault()).toInstant();
        Date nowTime = Date.from(timeInstant);
        cart.setTime(nowTime);
        activeCart = cart;
        activeListOfOrders = new ArrayList<>();
        activeListOfMenus = new ArrayList<>();
        try {
            activeCart = protocolServer.createCart(protocolClient, activeCart);
            activeListOfMenus = protocolServer.readAllMenu(protocolClient);
        } catch (RemoteException ex) {
            write(-1, ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(mainFrame,
                    "Failed to communicate with Infest API Server!\n"
                            + "\n"
                            + "Please verify that Infest API Server is currently turned on and your network connection is working.\n"
                            + "If Infest API Server is OFF, try to restart this program if problem after turning ON Infest API Server.\n"
                            + "Infest Program will now switched to MAINTENANCE MODE.",
                    "INFEST: Remote Connection Error",
                    JOptionPane.ERROR_MESSAGE);
            goOffline(0);
            throw ex;
        }
    }
    
    private String getSalt() throws NullPointerException {
        String salt;
        try {
            salt = programPropertiesManager.getProperty("salt");
            if(salt.isEmpty()) throw new NullPointerException();
            write(0, "Succesfully getting salt {" + salt +"}");
        } catch(NullPointerException ex) {
            write(-1, ex.getLocalizedMessage());
            salt = "";
            throw ex;
        }
        return salt;
    }
    
    private Boolean isCredentialsCurrent(Component parent, Credentials credentials) {
        Credentials savedCred = null;
        try {    
            savedCred = protocolServer.getCredentials(protocolClient);
            if(savedCred == null) {
                savedCred = CredentialsManager.createCredentials("", "");
                write(-1, "java.lang.NullPointerException on method isCredentialsCurrent().savedCred.");
                JOptionPane.showMessageDialog(parent, 
                        "Infest Configuration File is miss-configured!\n"
                                + "\n"
                                + "Please verify that the Infest Configuration File (infest.conf) is exist in the current\n"
                                + "working directory and is properly configured. Any wrong setting or modification of\n"
                                + "Infest Configuration File would cause this error.", 
                        "INFEST: Program Configuration Manager", 
                        JOptionPane.ERROR_MESSAGE);
                fatalExit(-1);
            }
            try {
                String salt = getSalt();
                try {
                    CredentialsManager.encryptCredentials(credentials, salt);
                    return savedCred.equals(credentials);
                } catch (Exception ex) {
                    write(-1, ex.getLocalizedMessage());
                    JOptionPane.showMessageDialog(parent,
                            "Failed to encrypt credentials!\n"
                                    + "\n"
                                    + "Please contact an Infest Administrator for further help.", 
                            "INFEST: Encryption Service", 
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (NullPointerException ex) {
                write(-1, ex.getLocalizedMessage());
                JOptionPane.showMessageDialog(parent, 
                        "Infest Configuration File is miss-configured!\n"
                                + "\n"
                                + "Please verify that the Infest Configuration File (infest.conf) is exist in the current\n"
                                + "working directory and is properly configured. Any wrong setting or modification of\n"
                                + "Infest Configuration File would cause this error.", 
                        "INFEST: Program Configuration Manager", 
                        JOptionPane.ERROR_MESSAGE);
                fatalExit(-1);
                return false;
            }
        } catch (RemoteException ex) {
            write(-1, ex.getLocalizedMessage());
            savedCred = CredentialsManager.createCredentials("", "");
            JOptionPane.showMessageDialog(parent, 
                    "Failed to communicate with Infest API Server!\n"
                            + "\n"
                            + "Please verify that Infest API Server is currently turned on and your network connection is working.\n"
                            + "If Infest API Server is OFF, try to restart this program if problem after turning ON Infest API Server.\n"
                            + "Infest Program will now switched to MAINTENANCE MODE.",
                    "INFEST: Remote Connection Error", 
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    protected void controlDialogProgramConfig(ProgramControlWindow dialog) {
        Credentials credentials;
        try {
            credentials = CredentialsManager.createEncryptedCredentials("infestcustomer", dialog.passwordField.getPassword(), this.getSalt());            
            if(this.isCredentialsCurrent(mainFrame, credentials)) {
                write(0, "Showing Program Configuration...");
                com.jogjadamai.infest.configurationmanager.Program.main(null);
            } else {
                write(-1, "Program Control Dialog: Invalid Password.");
                JOptionPane.showMessageDialog(mainFrame,
                        "Invalid Password!", 
                        "INFEST: Customer", 
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            write(-1, ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(mainFrame,
                    "Failed to encrypt credentials!\n"
                            + "\n"
                            + "Please contact an Infest Administrator for further help.",
                    "INFEST: Encryption Service",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    protected void controlDialogProgramExit(ProgramControlWindow dialog) {
        Credentials credentials;
        try {
            credentials = CredentialsManager.createEncryptedCredentials("infestcustomer", dialog.passwordField.getPassword(), this.getSalt());            
            if(this.isCredentialsCurrent(mainFrame, credentials)) {
                int response = JOptionPane.showConfirmDialog(mainFrame,
                        "Do you really want to exit?",
                        "INFEST: Customer", 
                        JOptionPane.YES_NO_OPTION);
                if(response == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(mainFrame, 
                            "Good-bye! Thank your for using INFEST!",
                            "INFEST: Customer",
                            JOptionPane.INFORMATION_MESSAGE);
                    shutdown(0);
                }
            } else {
                write(-1, "Program Control Dialog: Invalid Password.");
                JOptionPane.showMessageDialog(mainFrame,
                        "Invalid Password!", 
                        "INFEST: Customer", 
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            write(-1, ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(mainFrame,
                    "Failed to encrypt credentials!\n"
                            + "\n"
                            + "Please contact an Infest Administrator for further help.",
                    "INFEST: Encryption Service",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void switchMainPane(MainPaneList mainPane) {
        if(mainPane == null) shutdown();
        else {
            CardLayout cardLayout = (CardLayout) mainFrame.mainPanel.getLayout();
            cardLayout.show(mainFrame.mainPanel, mainPane.name());
            mainFrame.repaint();
            mainFrame.revalidate();
            write(0, "View switched to " + mainPane.name() + ".");
        }
    }
    
    private void switchContentPane(ContentPaneList contentPane) {
        if(contentPane == null) shutdown();
        else {
            switchMainPane(MainPaneList.CORE);
            CardLayout cardLayout = (CardLayout) mainFrame.corePane.contentPane.getLayout();
            cardLayout.show(mainFrame.corePane.contentPane, contentPane.name());
            write(0, "View switched to " + contentPane.name() + ".");
        }
    }
    
    private void switchChildContentPane(ChildContentPaneList childContentPane) {
        if(childContentPane == null) shutdown();
        else {
            switchContentPane(ContentPaneList.BUSSINESS);
            CardLayout cardLayout = (CardLayout) mainFrame.corePane.bussinessPane.childContentPane.getLayout();
            cardLayout.show(mainFrame.corePane.bussinessPane.childContentPane, childContentPane.name());
            write(0, "View switched to " + childContentPane.name() + ".");
        }
    }
    
    protected void getStartedButtonActionPerformed() {
        if (isMaintenanceModeActive()) goOffline(0); 
        else {
            try {
                createActiveCart();
                switchContentPane(ContentPaneList.SLIDE);
            } catch (RemoteException ex) {
                // Do nothing, already caught.
            }
        }
    }
    
    protected void headerButtonActionPerformed(HeaderButtonOption headerButtonOption) {
        if (isMaintenanceModeActive()) goOffline(0); 
        else {
            activeHeaderButton = headerButtonOption;
            List<Menus> listMenu;
            ListMenuPane listMenuPane;
            CartPane cartPane;
            Integer childContentPaneWidth = mainFrame.corePane.bussinessPane.childContentPane.getPreferredSize().width;
            Integer childContentPaneHeight = mainFrame.corePane.bussinessPane.childContentPane.getPreferredSize().height;
            switch(headerButtonOption) {
                case APPETIZER:
                    listMenu = new ArrayList<>();
                    for(Menus menu : activeListOfMenus) {
                        if(menu.getType() == 1 && menu.getStatus() != 0) listMenu.add(menu);
                    }
                    listMenuPane = new ListMenuPane(mainFrame.corePane, childContentPaneWidth, childContentPaneHeight, listMenu);
                    write(0, headerButtonOption.name() + " menus loaded.");
                    mainFrame.corePane.bussinessPane.childContentPane.add(listMenuPane, ChildContentPaneList.LIST_MENU.name());
                    switchChildContentPane(ChildContentPaneList.LIST_MENU);
                    break;
                case MAIN_COURSE:
                    listMenu = new ArrayList<>();
                    for(Menus menu : activeListOfMenus) {
                        if(menu.getType() == 2 && menu.getStatus() != 0) listMenu.add(menu);
                    }
                    listMenuPane = new ListMenuPane(mainFrame.corePane, childContentPaneWidth, childContentPaneHeight, listMenu);
                    write(0, headerButtonOption.name() + " menus loaded.");
                    mainFrame.corePane.bussinessPane.childContentPane.add(listMenuPane, ChildContentPaneList.LIST_MENU.name());
                    switchChildContentPane(ChildContentPaneList.LIST_MENU);
                    break;
                case DESSERT:
                    listMenu = new ArrayList<>();
                    for(Menus menu : activeListOfMenus) {
                        if(menu.getType() == 3 && menu.getStatus() != 0) listMenu.add(menu);
                    }
                    listMenuPane = new ListMenuPane(mainFrame.corePane, childContentPaneWidth, childContentPaneHeight, listMenu);
                    write(0, headerButtonOption.name() + " menus loaded.");
                    mainFrame.corePane.bussinessPane.childContentPane.add(listMenuPane, ChildContentPaneList.LIST_MENU.name());
                    switchChildContentPane(ChildContentPaneList.LIST_MENU);
                    break;
                case BEVERAGES:
                    listMenu = new ArrayList<>();
                    for(Menus menu : activeListOfMenus) {
                        if(menu.getType() == 4 && menu.getStatus() != 0) listMenu.add(menu);
                    }
                    listMenuPane = new ListMenuPane(mainFrame.corePane, childContentPaneWidth, childContentPaneHeight, listMenu);
                    write(0, headerButtonOption.name() + " menus loaded.");
                    mainFrame.corePane.bussinessPane.childContentPane.add(listMenuPane, ChildContentPaneList.LIST_MENU.name());
                    switchChildContentPane(ChildContentPaneList.LIST_MENU);
                    break;
                case CART:
                    cartPane = new CartPane(mainFrame.corePane, childContentPaneWidth, childContentPaneHeight, this.activeTable, this.getActiveCart(), this.getFeature(CustomerFeatures.SHOW_CURRENCY), this.getActiveListOfOrders());
                    mainFrame.corePane.bussinessPane.childContentPane.add(cartPane, ChildContentPaneList.CART.name());
                    switchChildContentPane(ChildContentPaneList.CART);
                    break;
                default:
                    switchContentPane(ContentPaneList.SLIDE);
                    break;
            }
        }
    }
    
    protected void menuPaneClicked(Menus menu) {
        if (isMaintenanceModeActive()) goOffline(0); 
        else {
            DetailMenuPane detailMenuPane;
            Integer childContentPaneWidth = mainFrame.corePane.bussinessPane.childContentPane.getPreferredSize().width;
            Integer childContentPaneHeight = mainFrame.corePane.bussinessPane.childContentPane.getPreferredSize().height;
            detailMenuPane = new DetailMenuPane(mainFrame, childContentPaneWidth, childContentPaneHeight, menu);
            write(0, "Showing menu detail of {" + menu + "}.");
            mainFrame.corePane.bussinessPane.childContentPane.add(detailMenuPane, ChildContentPaneList.DETAIL_MENU.name());
            switchChildContentPane(ChildContentPaneList.DETAIL_MENU);
        }
    }
    
    protected void menuBackButtonActionPerformed() {
        if (isMaintenanceModeActive()) goOffline(0); 
        else {
            this.headerButtonActionPerformed(activeHeaderButton);
        }
    }
    
    protected void addToCartButtonActionPerformed(Menus menu, Integer count) {
        if(menu != null) {
            if(count <= 0) {
                JOptionPane.showMessageDialog(mainFrame,
                        "You have not set your order quantity!",
                        "INFEST: Customer",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int response = JOptionPane.showConfirmDialog(mainFrame, 
                        "Add " + count + " of " + menu.getName() + " to your Cart?", 
                        "INFEST: Customer", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION) {
                    Orders order = new Orders();
                    order.setIdcart(activeCart);
                    order.setIdmenu(menu);
                    order.setTotal(count);
                    activeListOfMenus.remove(menu);
                    menu.setStock(menu.getStock()-count);
                    activeListOfMenus.add(menu);
                    activeListOfOrders.add(order);
                    write(0, "Menu added as an order {" + menu + "}.");
                    write(0, "Order has been added to current active cart {" + order + "}.");
                    menuPaneClicked(menu);
                }
            }
        }
    }
    
    protected void removeOrderButtonActionPerformed(int index, String note) {
        int response = JOptionPane.showConfirmDialog(mainFrame, 
                "Do you really want to remove selected order?", 
                "INFEST: Customer", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.YES_OPTION) {
            activeCart.setNote(note);
            Menus menu = activeListOfOrders.get(index).getIdmenu();
            int count = activeListOfOrders.get(index).getTotal();
            activeListOfMenus.remove(menu);
            menu.setStock(menu.getStock()+count);
            activeListOfMenus.add(menu);
            activeListOfOrders.remove(index);
            headerButtonActionPerformed(HeaderButtonOption.CART);
            write(0, "Order index {" + index + "} remove from active cart.");
        }
    }
    
    protected void checkoutOrderButtonActionPerformed(String note) {
        int response = JOptionPane.showConfirmDialog(mainFrame, 
                "Finish order and continue checkout?", 
                "INFEST: Customer", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.YES_OPTION) {
            Boolean isSuccess = false;
            activeCart.setNote(note);
            try {
                isSuccess = protocolServer.updateCart(protocolClient, activeCart);
            } catch (RemoteException ex) {
                write(-1, ex.getLocalizedMessage());
                JOptionPane.showMessageDialog(mainFrame,
                        "Failed to communicate with Infest API Server!\n"
                                + "\n"
                                + "Please verify that Infest API Server is currently turned on and your network connection is working.\n"
                                + "If Infest API Server is OFF, try to restart this program if problem after turning ON Infest API Server.\n"
                                + "Infest Program will now switched to MAINTENANCE MODE.",
                        "INFEST: Remote Connection Error",
                        JOptionPane.ERROR_MESSAGE);
                isSuccess = false;
            }
            try {
                for(Orders order : activeListOfOrders) {
                    order = protocolServer.createOrder(protocolClient, order);
                }
            } catch (RemoteException ex) {
                write(-1, ex.getLocalizedMessage());
                JOptionPane.showMessageDialog(mainFrame,
                        "Failed to communicate with Infest API Server!\n"
                                + "\n"
                                + "Please verify that Infest API Server is currently turned on and your network connection is working.\n"
                                + "If Infest API Server is OFF, try to restart this program if problem after turning ON Infest API Server.\n"
                                + "Infest Program will now switched to MAINTENANCE MODE.",
                        "INFEST: Remote Connection Error",
                        JOptionPane.ERROR_MESSAGE);
                isSuccess = false;
            }    
            if(isSuccess) {
                JOptionPane.showMessageDialog(mainFrame, 
                        "Your order(s) has been made!\n"
                                + "\n"
                                + "Thank you for coming to Jogja Damai Hotel.",
                        "INFEST: Customer", 
                        JOptionPane.INFORMATION_MESSAGE);
                write(0, "Cart has been finalised {" + activeCart + "}.");
                FinalPane finalPane = new FinalPane(this.activeTable, this.getActiveCart(), this.getFeature(CustomerFeatures.SHOW_CURRENCY), this.getActiveListOfOrders());
                mainFrame.mainPanel.add(finalPane, MainPaneList.FINAL.name());
                switchMainPane(MainPaneList.FINAL);
            }
            else JOptionPane.showMessageDialog(mainFrame,
                    "Failed to submit your order(s).",
                    "INFEST: Customer",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    protected Boolean isBillPrintable() {
        return (getFeature(CustomerFeatures.PRINT_BILL).getStatus()==1);
    }
    
    protected void printBillButtonActionPerformed(JXTable cartTable, String cost) {
        if(getFeature(CustomerFeatures.PRINT_BILL).getStatus()==1) {
            try {
                cartTable.print(JTable.PrintMode.FIT_WIDTH, new MessageFormat("My Bill (Total Cost: " + cost + ")"), new MessageFormat("Generated automatically by Infest."));
            } catch (PrinterException ex) {
                System.err.println("[INFEST] " + ex.getLocalizedMessage());
                JOptionPane.showMessageDialog(mainFrame, "Failed to print bill!", "INFEST: Customer", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Bill printing feature is OFF!\n"
                    + "Please contact and Infest Administrator.", "INFEST: Customer", JOptionPane.ERROR_MESSAGE);
        }
        
    }
        
    protected void exitButtonActionPerformed() {
        switchMainPane(MainPaneList.WELCOME);
    }
    
}
