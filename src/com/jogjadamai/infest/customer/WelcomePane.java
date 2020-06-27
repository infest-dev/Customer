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

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Danang Galuh Tegar P
 */
public final class WelcomePane extends javax.swing.JPanel {

    private final Integer screenWidth;
    private final Integer screenHeight;

    private enum ScreenDimension {
        WIDTH, HEIGHT
    }
    
    /**
     * Creates new form WelcomePanel view.
     * 
     */
    public WelcomePane() {
        this.screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        initialiseComponents();
    }
                         
    private void initialiseComponents() {

        layeredPane = new javax.swing.JLayeredPane();
        welcomeBackgroundLabel = new javax.swing.JLabel();
        corePanel = new javax.swing.JPanel();
        welcomeLogoLabel = new javax.swing.JLabel();
        welcomeButton = new javax.swing.JButton();
        
        setOpaque(false);
        setMaximumSize(new java.awt.Dimension(screenWidth, screenHeight));
        setMinimumSize(new java.awt.Dimension(screenWidth, screenHeight));
        setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));

        layeredPane.setMaximumSize(new java.awt.Dimension(screenWidth, screenHeight));
        layeredPane.setMinimumSize(new java.awt.Dimension(screenWidth, screenHeight));
        layeredPane.setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));
        layeredPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        layeredPane.add(welcomeBackgroundLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, screenWidth, screenHeight));

        corePanel.setOpaque(false);
        corePanel.setMaximumSize(new java.awt.Dimension(screenWidth, screenHeight));
        corePanel.setMinimumSize(new java.awt.Dimension(screenWidth, screenHeight));
        corePanel.setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));

        welcomeLogoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeLogoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Logo-Welcome.png"))); // NOI18N
        welcomeLogoLabel.setText("");
        welcomeLogoLabel.setDoubleBuffered(true);
        welcomeLogoLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        loadBackgroundImage();

        welcomeButton.setBackground(new java.awt.Color(0x651112));
        welcomeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-Welcome.png"))); // NOI18N
        welcomeButton.setBorder(null);
        welcomeButton.setDoubleBuffered(true);
        welcomeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                welcomeButtonMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                welcomeButtonMouseExited(evt);
            }
        });
        welcomeButton.addActionListener(this::welcomeButtonActionPerformed);

        javax.swing.GroupLayout corePanelLayout = new javax.swing.GroupLayout(corePanel);
        corePanel.setLayout(corePanelLayout);
        corePanelLayout.setHorizontalGroup(
            corePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(corePanelLayout.createSequentialGroup()
                .addGroup(corePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(corePanelLayout.createSequentialGroup()
                        .addGap(calculateAbsoluteGap(ScreenDimension.WIDTH, 0.5), calculateAbsoluteGap(ScreenDimension.WIDTH, 0.5), calculateAbsoluteGap(ScreenDimension.WIDTH, 0.5))
                        .addComponent(welcomeLogoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(corePanelLayout.createSequentialGroup()
                        .addGap(calculateAbsoluteGap(ScreenDimension.WIDTH, 0.5), calculateAbsoluteGap(ScreenDimension.WIDTH, 0.5), calculateAbsoluteGap(ScreenDimension.WIDTH, 0.5))
                        .addComponent(welcomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(610, Short.MAX_VALUE))
        );
        corePanelLayout.setVerticalGroup(
            corePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(corePanelLayout.createSequentialGroup()
                .addGap(calculateAbsoluteGap(ScreenDimension.HEIGHT, 0.3), calculateAbsoluteGap(ScreenDimension.HEIGHT, 0.3), calculateAbsoluteGap(ScreenDimension.HEIGHT, 0.3))
                .addComponent(welcomeLogoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(calculateAbsoluteGap(ScreenDimension.HEIGHT, 0.2), calculateAbsoluteGap(ScreenDimension.HEIGHT, 0.2), calculateAbsoluteGap(ScreenDimension.HEIGHT, 0.2))
                .addComponent(welcomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(255, Short.MAX_VALUE))
        );

        layeredPane.setLayer(corePanel, 10);
        layeredPane.add(corePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(layeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(layeredPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }                     

    
    private void welcomeButtonMouseEntered(java.awt.event.MouseEvent evt) {
        welcomeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-HoverWelcome.png"))); // NOI18N
    }
    
    private void welcomeButtonMouseExited(java.awt.event.MouseEvent evt) {
        welcomeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-Welcome.png"))); // NOI18N
    }
    
    private void welcomeButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        Customer controller = Customer.getInstance();
        controller.getStartedButtonActionPerformed();
    }                                             


    // Variables declaration - do not modify                     
    private javax.swing.JPanel corePanel;
    private javax.swing.JLayeredPane layeredPane;
    private javax.swing.JLabel welcomeBackgroundLabel;
    private javax.swing.JButton welcomeButton;
    private javax.swing.JLabel welcomeLogoLabel;
    // End of variables declaration                   

    private Integer calculateAbsoluteGap(ScreenDimension sd, double percentage) {
        Double gap;
        switch(sd) {
            case WIDTH:
                gap = this.screenWidth.doubleValue() - 200.0;
                break;
            case HEIGHT:
                gap = this.screenHeight.doubleValue() - 258.0;
                break;
            default:
                gap = 0.0;
                break;
        }
        gap = gap * percentage;
        return gap.intValue();
    }
    
    private void loadBackgroundImage() {
        BufferedImage backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(getClass().getResource("/com/jogjadamai/infest/assets/Background-Welcome.png"));
            try {
                Double widthScalingFactor = screenWidth.doubleValue() / new Integer(backgroundImage.getWidth()).doubleValue();
                Double heightScalingFactor = screenHeight.doubleValue() / new Integer(backgroundImage.getHeight()).doubleValue();
                Double scalingFactor = (widthScalingFactor >= heightScalingFactor) ? widthScalingFactor : heightScalingFactor;
                Integer newWidth = (int) (backgroundImage.getWidth() * scalingFactor);
                Integer newHeight = (int) (backgroundImage.getHeight() * scalingFactor);
                welcomeBackgroundLabel.setText("");
                welcomeBackgroundLabel.setIcon(new ImageIcon(backgroundImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH)));
            } catch (NullPointerException ex) {
                System.err.println("[INFEST] " + ex);
            }
        } catch (IOException ex) {
            System.err.println("[INFEST] " +  ex);
        }
    }
    
}
