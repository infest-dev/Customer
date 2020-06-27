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

import com.jogjadamai.infest.entity.Menus;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author Danang Galuh Tegar P
 */
public class MenuPane extends javax.swing.JPanel {

    private final CorePane parent;
    private final Menus menu;
    private final Customer controller;

    /**
     * Creates new form MenuPanel
     * @param parent
     * @param menu
     */
    public MenuPane(CorePane parent, Menus menu) {
        this.parent = parent;
        this.menu = menu;
        this.controller = Customer.getInstance();
        initialiseComponents();
    }
                        
    private void initialiseComponents() {

        menuPane = new javax.swing.JPanel();
        menuImagePane = new javax.swing.JPanel();
        menuImageLabel = new javax.swing.JLabel();
        menuNameLabel = new javax.swing.JLabel();
        menuPriceLabel = new javax.swing.JLabel();
        menuDurationLabel = new javax.swing.JLabel();
        menuAvailabilityLabel = new javax.swing.JLabel();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 219, 146, 255), 1, true));
        setMaximumSize(new java.awt.Dimension(450, 150));
        setMinimumSize(new java.awt.Dimension(450, 150));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(450, 150));
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuPaneMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuPaneMouseExited(evt);
            }
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPaneMouseClicked(evt);
            }
        });

        menuPane.setBackground(Color.WHITE);
        menuPane.setMaximumSize(new java.awt.Dimension(450, 150));
        menuPane.setMinimumSize(new java.awt.Dimension(450, 150));
        menuPane.setPreferredSize(new java.awt.Dimension(450, 150));
        menuPane.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuPaneMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuPaneMouseExited(evt);
            }
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuPaneMouseClicked(evt);
            }
        });

        menuImagePane.setBackground(new java.awt.Color(255, 255, 255));
        menuImagePane.setMaximumSize(new java.awt.Dimension(150, 150));
        menuImagePane.setMinimumSize(new java.awt.Dimension(150, 150));
        menuImagePane.setPreferredSize(new java.awt.Dimension(150, 150));

        menuImageLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        menuImageLabel.setMaximumSize(new java.awt.Dimension(150, 150));
        menuImageLabel.setMinimumSize(new java.awt.Dimension(150, 150));
        menuImageLabel.setPreferredSize(new java.awt.Dimension(150, 150));
        menuImageLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        loadMenuImage();
        
        javax.swing.GroupLayout menuImagePaneLayout = new javax.swing.GroupLayout(menuImagePane);
        menuImagePane.setLayout(menuImagePaneLayout);
        menuImagePaneLayout.setHorizontalGroup(
            menuImagePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuImagePaneLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(menuImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuImagePaneLayout.setVerticalGroup(
            menuImagePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuImagePaneLayout.createSequentialGroup()
                .addComponent(menuImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        menuNameLabel.setFont(new java.awt.Font("Exo", 1, 24)); // NOI18N
        menuNameLabel.setText("<html>" + menu.getName() + "</html>");
        menuNameLabel.setToolTipText("Click to order: " + menu.getName());
        menuNameLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        menuNameLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        menuPriceLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        menuPriceLabel.setText(controller.getMenuPrice(menu));
        menuPriceLabel.setToolTipText("Menu price: " + controller.getMenuPrice(menu) + ".");
        menuPriceLabel.setMaximumSize(new java.awt.Dimension(280, 20));
        menuPriceLabel.setMinimumSize(new java.awt.Dimension(280, 20));
        menuPriceLabel.setPreferredSize(new java.awt.Dimension(280, 20));

        menuDurationLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        menuDurationLabel.setText(controller.getMenuDuration(menu).substring(0, 1).toUpperCase() + controller.getMenuDuration(menu).substring(1));
        menuDurationLabel.setToolTipText("Menu creation duration: " + controller.getMenuDuration(menu) + ".");
        menuDurationLabel.setMaximumSize(new java.awt.Dimension(280, 20));
        menuDurationLabel.setMinimumSize(new java.awt.Dimension(280, 20));
        menuDurationLabel.setPreferredSize(new java.awt.Dimension(280, 20));

        menuAvailabilityLabel.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        menuAvailabilityLabel.setForeground((controller.getMenuAvailability(menu)) ? Color.GREEN : Color.RED);
        menuAvailabilityLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        menuAvailabilityLabel.setText((controller.getMenuAvailability(menu)) ? "AVAILABLE" : "NOT AVAILABLE");
        menuAvailabilityLabel.setToolTipText("This menu is " + ((controller.getMenuAvailability(menu)) ? "" : "NOT") + " available to order.");
        menuAvailabilityLabel.setMaximumSize(new java.awt.Dimension(280, 20));
        menuAvailabilityLabel.setMinimumSize(new java.awt.Dimension(280, 20));
        menuAvailabilityLabel.setPreferredSize(new java.awt.Dimension(280, 20));

        javax.swing.GroupLayout menuPaneLayout = new javax.swing.GroupLayout(menuPane);
        menuPane.setLayout(menuPaneLayout);
        menuPaneLayout.setHorizontalGroup(
            menuPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPaneLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(menuImagePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(menuPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuDurationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuAvailabilityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        menuPaneLayout.setVerticalGroup(
            menuPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPaneLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(menuPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuImagePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(menuPaneLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(menuNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(menuPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(menuDurationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menuAvailabilityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(2, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(menuPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(menuPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }    
    
        
    private void menuPaneMouseEntered(java.awt.event.MouseEvent evt) {
        menuPane.setBackground(Color.ORANGE);
        if(parent.isSubMenuVisible()) parent.setSubMenuVisible(false);
        repaint();
        revalidate();
    }
    
    private void menuPaneMouseExited(java.awt.event.MouseEvent evt) {
        menuPane.setBackground(Color.WHITE);
        repaint();
        revalidate();
        
    }
    
    private void menuPaneMouseClicked(java.awt.event.MouseEvent evt) {
        controller.menuPaneClicked(menu);
    }
   
    private void loadMenuImage() {
        Image image;
        BufferedImage bufferedImage = controller.getMenuImage(menu);
        if(bufferedImage != null) {
            try {
                Double widthScalingFactor = menuImageLabel.getPreferredSize().getWidth() / new Integer(bufferedImage.getWidth()).doubleValue();
                Double heightScalingFactor = menuImageLabel.getPreferredSize().getHeight() / new Integer(bufferedImage.getHeight()).doubleValue();
                Double scalingFactor = (widthScalingFactor >= heightScalingFactor) ? widthScalingFactor : heightScalingFactor;
                Integer newWidth = (int) (bufferedImage.getWidth() * scalingFactor);
                Integer newHeight = (int) (bufferedImage.getHeight() * scalingFactor);
                image = new ImageIcon(bufferedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH)).getImage();
            } catch (NullPointerException ex) {
                System.err.println("[INFEST] " + ex.getLocalizedMessage());
                image = null;
            }
        } else image = null;
        if(image != null) menuImageLabel.setIcon(new ImageIcon(image));
    }

    // Variables declaration - do not modify 
    private javax.swing.JLabel menuAvailabilityLabel;
    private javax.swing.JLabel menuDurationLabel;
    private javax.swing.JLabel menuImageLabel;
    private javax.swing.JPanel menuImagePane;
    private javax.swing.JLabel menuNameLabel;
    private javax.swing.JPanel menuPane;
    private javax.swing.JLabel menuPriceLabel;
    // End of variables declaration                   
}