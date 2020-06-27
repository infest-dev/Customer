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

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author Danang Galuh Tegar P
 */
public class MainGUI extends JFrame implements Runnable {
    
    protected static enum MainPaneList {
        WELCOME, CORE, FINAL
    }
    
    protected static enum HeaderButtonOption {
        APPETIZER, MAIN_COURSE, DESSERT, BEVERAGES, CART
    }
    
    public MainGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.err.println("[INFEST] " + ex.getLocalizedMessage());
        }
        initialiseComponents();
    }

    private void initialiseComponents() {
        
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainPanel = new JPanel() {
            
            private String getNowTime() {
                return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now());
            }
            
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage;
                BufferedImage backgroundBufferedImage = null;
                try {
                    backgroundBufferedImage = ImageIO.read(getClass().getResource("/com/jogjadamai/infest/assets/Background-Main.png"));
                    try {
                        Double widthScalingFactor = new Integer(Toolkit.getDefaultToolkit().getScreenSize().width).doubleValue() / new Integer(backgroundBufferedImage.getWidth()).doubleValue();
                        Double heightScalingFactor = new Integer(Toolkit.getDefaultToolkit().getScreenSize().height).doubleValue() / new Integer(backgroundBufferedImage.getHeight()).doubleValue();
                        Double scalingFactor = (widthScalingFactor >= heightScalingFactor) ? widthScalingFactor : heightScalingFactor;
                        Integer newWidth = (int) (backgroundBufferedImage.getWidth() * scalingFactor);
                        Integer newHeight = (int) (backgroundBufferedImage.getHeight() * scalingFactor);
                        backgroundImage = new ImageIcon(backgroundBufferedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH)).getImage();
                    } catch (NullPointerException ex) {
                        System.err.println("[INFEST] " + getNowTime() + ": " + ex.getLocalizedMessage());
                        backgroundImage = null;
                    }
                } catch (IOException ex) {
                    System.err.println("[INFEST] " + getNowTime() + ": " + ex.getLocalizedMessage());
                    backgroundImage = null;
                }
                if(backgroundImage != null) g.drawImage(backgroundImage, 0, 0, null);
            }
            
        };
        welcomePane = new WelcomePane();
        corePane = new CorePane();
        
        setTitle("INFEST: Customer");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setUndecorated(true);
        setIconImage(new ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/InfestIcon.png")).getImage());
        
        mainPanel.setMaximumSize(screenSize);
        mainPanel.setMinimumSize(screenSize);
        mainPanel.setName("mainPanel"); // NOI18N
        mainPanel.setOpaque(false);
        mainPanel.setPreferredSize(screenSize);
        mainPanel.setLayout(new CardLayout());
        mainPanel.setSize(screenSize);
        mainPanel.add(welcomePane, MainPaneList.WELCOME.name());
        mainPanel.add(corePane, MainPaneList.CORE.name());
        
        setContentPane(mainPanel);
        
        pack();
        
    }
    
    private Customer customer;
    private Dimension screenSize;
    protected JPanel mainPanel;
    protected WelcomePane welcomePane;
    protected CorePane corePane;
    
    @Override
    public void run() {
        customer = Customer.getInstance();
        getContentPane().setBackground(new java.awt.Color(0xffffff));
    }
    
}
