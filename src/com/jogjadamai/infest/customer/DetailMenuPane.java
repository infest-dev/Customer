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
public class DetailMenuPane extends javax.swing.JPanel {

    private final MainGUI parent;
    private final Integer paneWidth;
    private final Integer paneHeight;
    private final Integer containerGap;
    private final Integer menuImagePaneWidth;
    private final Integer menuImagePaneHeight;
    private final Integer menuDescriptionPaneWidth;
    private final Integer menuDescriptionPaneHeight;
    private final String stockWarning;
    private final Menus menu;
    private final Customer controller;

    /**
     * Creates new form CartPanel
     * @param parent
     * @param paneWidth
     * @param paneHeight
     * @param menu
     */
    protected DetailMenuPane(MainGUI parent, Integer paneWidth, Integer paneHeight, Menus menu) {
        this.parent = parent;
        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;
        this.containerGap = 30;
        this.menuImagePaneWidth = (int) (0.4 * (this.paneWidth - (3 * this.containerGap)));
        this.menuImagePaneHeight = this.menuImagePaneWidth;
        this.menuDescriptionPaneWidth = (int) (0.6 * (this.paneWidth - (3 * this.containerGap)));
        this.menuDescriptionPaneHeight = 410;
        this.stockWarning = "<html><head>\n <style type=\"text/css\"> DIV.par {text-align: right}</style>\n<body><div class=\"par\">\n<p>We apologize. Your order total reached menu's stock total. So, you can't order more of this menu.</p>\n</div></body></html>";
        this.menu = menu;
        this.controller = Customer.getInstance();
        initialiseComponents();
    }
                    
    private void initialiseComponents() {

        mainScrollPane = new javax.swing.JScrollPane();
        detailMenuPane = new javax.swing.JPanel();
        menuImagePane = new javax.swing.JPanel();
        menuImageLabel = new javax.swing.JLabel();
        menuDescriptionPane = new javax.swing.JPanel();
        menuNameLabel = new javax.swing.JLabel();
        separator = new javax.swing.JSeparator();
        menuTypeLabel = new javax.swing.JLabel();
        menuDescriptionScrollPane = new javax.swing.JScrollPane();
        menuDescriptionArea = new javax.swing.JTextPane();
        menuPriceLabel = new javax.swing.JLabel();
        menuDurationLabel = new javax.swing.JLabel();
        menuAvailabilityLabel = new javax.swing.JLabel();
        addToCartButton = new javax.swing.JButton();
        orderCountSpinner = new javax.swing.JSpinner();
        menuBackButton = new javax.swing.JButton();
        stockWarningLabel = new javax.swing.JLabel();

        setBorder(null);
        setOpaque(false);
        setMaximumSize(new java.awt.Dimension(paneWidth, paneHeight));
        setMinimumSize(new java.awt.Dimension(paneWidth, paneHeight));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(paneWidth, paneHeight));

        mainScrollPane.setBorder(null);
        mainScrollPane.setMaximumSize(new java.awt.Dimension(paneWidth, paneHeight));
        mainScrollPane.setMinimumSize(new java.awt.Dimension(paneWidth, paneHeight));
        mainScrollPane.setOpaque(false);
        mainScrollPane.setPreferredSize(new java.awt.Dimension(paneWidth, paneHeight));

        detailMenuPane.setBorder(null);
        detailMenuPane.setBackground(new java.awt.Color(255, 219, 146, 154));
        detailMenuPane.setMaximumSize(new java.awt.Dimension(paneWidth, paneHeight));
        detailMenuPane.setMinimumSize(new java.awt.Dimension(paneWidth, paneHeight));

        menuImagePane.setMaximumSize(new java.awt.Dimension(menuImagePaneWidth, menuImagePaneHeight));
        menuImagePane.setMinimumSize(new java.awt.Dimension(menuImagePaneWidth, menuImagePaneHeight));
        menuImagePane.setOpaque(false);
        menuImagePane.setPreferredSize(new java.awt.Dimension(menuImagePaneWidth, menuImagePaneHeight));

        menuImageLabel.setToolTipText("Image of " + menu.getName() + ".");
        menuImageLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        menuImageLabel.setMaximumSize(new java.awt.Dimension(menuImagePaneWidth, menuImagePaneHeight));
        menuImageLabel.setMinimumSize(new java.awt.Dimension(menuImagePaneWidth, menuImagePaneHeight));
        menuImageLabel.setPreferredSize(new java.awt.Dimension(menuImagePaneWidth, menuImagePaneHeight));
        menuImageLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        loadMenuImage();
        
        javax.swing.GroupLayout menuImagePaneLayout = new javax.swing.GroupLayout(menuImagePane);
        menuImagePane.setLayout(menuImagePaneLayout);
        menuImagePaneLayout.setHorizontalGroup(
            menuImagePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuImagePaneLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(menuImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, menuImagePaneWidth, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        menuImagePaneLayout.setVerticalGroup(
            menuImagePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuImagePaneLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(menuImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, menuImagePaneWidth, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        menuDescriptionPane.setFont(new java.awt.Font("Open Sans", 0, 11)); // NOI18N
        menuDescriptionPane.setMaximumSize(new java.awt.Dimension(menuDescriptionPaneWidth, menuDescriptionPaneHeight));
        menuDescriptionPane.setMinimumSize(new java.awt.Dimension(menuDescriptionPaneWidth, menuDescriptionPaneHeight));
        menuDescriptionPane.setOpaque(false);
        menuDescriptionPane.setPreferredSize(new java.awt.Dimension(menuDescriptionPaneWidth, menuDescriptionPaneHeight));

        menuNameLabel.setFont(new java.awt.Font("Exo", 1, 28)); // NOI18N
        menuNameLabel.setText(menu.getName());
        menuNameLabel.setForeground(new java.awt.Color(0x451413));
        menuNameLabel.setMaximumSize(new java.awt.Dimension(menuDescriptionPaneWidth-40, 30));
        menuNameLabel.setMinimumSize(new java.awt.Dimension(menuDescriptionPaneWidth-40, 30));
        menuNameLabel.setPreferredSize(new java.awt.Dimension(menuDescriptionPaneWidth-40, 30));

        separator.setBackground(new java.awt.Color(0x451413));
        separator.setForeground(new java.awt.Color(255, 219, 146, 255));

        menuTypeLabel.setFont(new java.awt.Font("Open Sans Condensed", 0, 16)); // NOI18N
        menuTypeLabel.setText(controller.getMenuType(menu));
        menuTypeLabel.setMaximumSize(new java.awt.Dimension(menuDescriptionPaneWidth, 20));
        menuTypeLabel.setMinimumSize(new java.awt.Dimension(menuDescriptionPaneWidth, 20));
        menuTypeLabel.setPreferredSize(new java.awt.Dimension(menuDescriptionPaneWidth, 20));

        menuDescriptionScrollPane.setBorder(null);
        menuDescriptionScrollPane.setMaximumSize(new java.awt.Dimension(menuDescriptionPaneWidth, 150));
        menuDescriptionScrollPane.setMinimumSize(new java.awt.Dimension(menuDescriptionPaneWidth, 150));
        menuDescriptionScrollPane.setOpaque(false);
        menuDescriptionScrollPane.setPreferredSize(new java.awt.Dimension(menuDescriptionPaneWidth, 150));

        menuDescriptionArea.setEditable(false);
        menuDescriptionArea.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        org.jdesktop.swingx.border.DropShadowBorder menuDescriptionAreaBorder = new org.jdesktop.swingx.border.DropShadowBorder();
        menuDescriptionAreaBorder.setShowLeftShadow(true);
        menuDescriptionAreaBorder.setShowTopShadow(true);
        menuDescriptionAreaBorder.setShadowOpacity(1.0F);
        menuDescriptionArea.setBorder(menuDescriptionAreaBorder);
        menuDescriptionArea.setSelectionColor(new java.awt.Color(0xffffff));
        menuDescriptionArea.setSelectedTextColor(new java.awt.Color(0x000000));
        menuDescriptionArea.setMaximumSize(new java.awt.Dimension(menuDescriptionPaneWidth, 150));
        menuDescriptionArea.setMinimumSize(new java.awt.Dimension(menuDescriptionPaneWidth, 150));
        menuDescriptionArea.setForeground(new java.awt.Color(0x000000));
        menuDescriptionArea.setBackground(new java.awt.Color(255, 219, 146, 255));
        menuDescriptionArea.setPreferredSize(new java.awt.Dimension(menuDescriptionPaneWidth, 150));
        menuDescriptionArea.setContentType("text/html");
        menuDescriptionArea.setText("<html><head></head><body style=\"font-family:ISOCPEUR;font-size:16pt;line-height: 150%\">" + menu.getDescription() + "</body></html>");
        menuDescriptionArea.setToolTipText("Description of " + menu.getName() + ".");
        menuDescriptionScrollPane.setViewportView(menuDescriptionArea);

        menuPriceLabel.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        menuPriceLabel.setText("Price: " + controller.getMenuPrice(menu) + ".");
        menuPriceLabel.setToolTipText("Price of this menu: " + controller.getMenuDuration(menu) + ".");
        menuPriceLabel.setMaximumSize(new java.awt.Dimension(menuDescriptionPaneWidth, 20));
        menuPriceLabel.setMinimumSize(new java.awt.Dimension(menuDescriptionPaneWidth, 20));
        menuPriceLabel.setPreferredSize(new java.awt.Dimension(menuDescriptionPaneWidth, 20));
        
        menuDurationLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        menuDurationLabel.setText("Menu creation duration: " + controller.getMenuDuration(menu) + ".");
        menuDurationLabel.setToolTipText("Menu creation duration: " + controller.getMenuDuration(menu) + ".");
        menuDurationLabel.setMaximumSize(new java.awt.Dimension(menuDescriptionPaneWidth, 20));
        menuDurationLabel.setMinimumSize(new java.awt.Dimension(menuDescriptionPaneWidth, 20));
        menuDurationLabel.setPreferredSize(new java.awt.Dimension(menuDescriptionPaneWidth, 20));

        menuAvailabilityLabel.setFont(new java.awt.Font("Open Sans", 1, 14)); // NOI18N
        menuAvailabilityLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        menuAvailabilityLabel.setForeground(Color.RED);
        menuAvailabilityLabel.setText((controller.getMenuAvailability(menu)) ? "" : "NOT AVAILABLE");
        menuAvailabilityLabel.setToolTipText((controller.getMenuAvailability(menu)) ? "This menu is available for order." : "This menu is NOT available at the moment.");
        menuAvailabilityLabel.setMaximumSize(new java.awt.Dimension(menuDescriptionPaneWidth, 20));
        menuAvailabilityLabel.setMinimumSize(new java.awt.Dimension(menuDescriptionPaneWidth, 20));
        menuAvailabilityLabel.setPreferredSize(new java.awt.Dimension(menuDescriptionPaneWidth, 20));

        addToCartButton.setBackground(new java.awt.Color(255, 255, 255, 0));
        addToCartButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        addToCartButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-AddCart.png"))); // NOI18N
        addToCartButton.setBorder(null);
        addToCartButton.setDoubleBuffered(true);
        addToCartButton.setMaximumSize(new java.awt.Dimension(200, 40));
        addToCartButton.setMinimumSize(new java.awt.Dimension(200, 40));
        addToCartButton.setEnabled(controller.getMenuAvailability(menu));
        addToCartButton.setOpaque(false);
        addToCartButton.setPreferredSize(new java.awt.Dimension(200, 40));
        addToCartButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addToCartButtonMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addToCartButtonMouseExited(evt);
            }
        });
        addToCartButton.addActionListener(this::addToCartButtonActionPerformed);

        orderCountSpinner.setEnabled(controller.getMenuAvailability(menu));
        orderCountSpinner.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        orderCountSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, menu.getStock(), 1));
        orderCountSpinner.setBackground(new java.awt.Color(255, 219, 146, 0));
        orderCountSpinner.setOpaque(false);
        orderCountSpinner.setToolTipText("");
        orderCountSpinner.setMaximumSize(new java.awt.Dimension(60, 40));
        orderCountSpinner.setMinimumSize(new java.awt.Dimension(60, 40));
        orderCountSpinner.setPreferredSize(new java.awt.Dimension(60, 40));
        orderCountSpinner.addChangeListener(this::orderCountSpinnerStateChanged);
        
        menuBackButton.setBackground(new java.awt.Color(255, 219, 146, 0));
        menuBackButton.setForeground(new java.awt.Color(255, 219, 146, 0));
        menuBackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-Back.png"))); // NOI18N
        menuBackButton.setBorder(null);
        menuBackButton.setDoubleBuffered(true);
        menuBackButton.setOpaque(false);
        menuBackButton.setMaximumSize(new java.awt.Dimension(30, 30));
        menuBackButton.setMinimumSize(new java.awt.Dimension(30, 30));
        menuBackButton.setPreferredSize(new java.awt.Dimension(30, 30));
        menuBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuBackButtonMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuBackButtonMouseExited(evt);
            }
        });
        menuBackButton.addActionListener(this::menuBackButtonActionPerformed);
        
        stockWarningLabel.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        stockWarningLabel.setForeground(java.awt.Color.RED);
        stockWarningLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        stockWarningLabel.setText(stockWarning);
        stockWarningLabel.setMaximumSize(new java.awt.Dimension(menuDescriptionPaneWidth-270, 40));
        stockWarningLabel.setMinimumSize(new java.awt.Dimension(menuDescriptionPaneWidth-270, 40));
        stockWarningLabel.setPreferredSize(new java.awt.Dimension(menuDescriptionPaneWidth-270, 40));
        stockWarningLabel.setVisible(false);

        javax.swing.GroupLayout menuDescriptionPaneLayout = new javax.swing.GroupLayout(menuDescriptionPane);
        menuDescriptionPane.setLayout(menuDescriptionPaneLayout);
        menuDescriptionPaneLayout.setHorizontalGroup(
            menuDescriptionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(separator)
            .addGroup(menuDescriptionPaneLayout.createSequentialGroup()
                .addGroup(menuDescriptionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuDescriptionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(menuTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(menuDescriptionPaneLayout.createSequentialGroup()
                            .addComponent(stockWarningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(orderCountSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addToCartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(menuDescriptionPaneLayout.createSequentialGroup()
                        .addGroup(menuDescriptionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(menuPriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(menuDescriptionPaneLayout.createSequentialGroup()
                                .addComponent(menuBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(menuNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(menuAvailabilityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(menuDescriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuDescriptionPaneLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(menuDurationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        menuDescriptionPaneLayout.setVerticalGroup(
            menuDescriptionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuDescriptionPaneLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(menuDescriptionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(menuBackButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(menuTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(menuDescriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(menuPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(menuDurationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(menuAvailabilityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(menuDescriptionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addToCartButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderCountSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockWarningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(containerGap, containerGap, containerGap))
        );

        javax.swing.GroupLayout detailMenuPaneLayout = new javax.swing.GroupLayout(detailMenuPane);
        detailMenuPane.setLayout(detailMenuPaneLayout);
        detailMenuPaneLayout.setHorizontalGroup(
            detailMenuPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailMenuPaneLayout.createSequentialGroup()
                .addGap(containerGap, containerGap, containerGap)
                .addComponent(menuImagePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(containerGap, containerGap, containerGap)
                .addComponent(menuDescriptionPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(containerGap, containerGap, containerGap))
        );
        detailMenuPaneLayout.setVerticalGroup(
            detailMenuPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailMenuPaneLayout.createSequentialGroup()
                .addGap(containerGap, containerGap, containerGap)
                .addGroup(detailMenuPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuImagePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuDescriptionPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(containerGap, containerGap, containerGap))
        );

        mainScrollPane.setViewportView(detailMenuPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(mainScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(mainScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }                       

    private void menuBackButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        controller.menuBackButtonActionPerformed();
    }                                              

    private void menuBackButtonMouseEntered(java.awt.event.MouseEvent evt) {
        menuBackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-HoverBack.png"))); // NOI18N
        parent.repaint();
        parent.revalidate();
    }                                           

    private void menuBackButtonMouseExited(java.awt.event.MouseEvent evt) {
        menuBackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-Back.png"))); // NOI18N
        parent.repaint();
        parent.revalidate();
    }                                          

    private void addToCartButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
        controller.addToCartButtonActionPerformed(menu, (int) orderCountSpinner.getValue());
    }   
    
    private void addToCartButtonMouseEntered(java.awt.event.MouseEvent evt) {
        addToCartButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-HoverAddCart.png"))); // NOI18N
        parent.repaint();
        parent.revalidate();
    }                                              

    private void addToCartButtonMouseExited(java.awt.event.MouseEvent evt) {
        addToCartButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-AddCart.png"))); // NOI18N
        parent.repaint();
        parent.revalidate();
    }     
    
    private void orderCountSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {
        if((int)orderCountSpinner.getValue() >= menu.getStock()) {
            orderCountSpinner.setValue(menu.getStock());
            stockWarningLabel.setVisible(true);
            parent.repaint();
            parent.revalidate();
        } else {
            stockWarningLabel.setVisible(false);
            parent.repaint();
            parent.revalidate();
        }
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
    private javax.swing.JButton addToCartButton;
    private javax.swing.JPanel detailMenuPane;
    private javax.swing.JSpinner orderCountSpinner;
    private javax.swing.JLabel menuAvailabilityLabel;
    private javax.swing.JButton menuBackButton;
    private javax.swing.JTextPane menuDescriptionArea;
    private javax.swing.JPanel menuDescriptionPane;
    private javax.swing.JLabel menuPriceLabel;
    private javax.swing.JLabel menuDurationLabel;
    private javax.swing.JLabel menuImageLabel;
    private javax.swing.JPanel menuImagePane;
    private javax.swing.JLabel menuNameLabel;
    private javax.swing.JLabel menuTypeLabel;
    private javax.swing.JScrollPane mainScrollPane;
    private javax.swing.JScrollPane menuDescriptionScrollPane;
    private javax.swing.JSeparator separator;
    private javax.swing.JLabel stockWarningLabel;
    // End of variables declaration                   
}
