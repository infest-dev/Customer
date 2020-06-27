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

import java.awt.Toolkit;

/**
 *
 * @author Danang Galuh Tegar P
 */
public final class CorePane extends javax.swing.JPanel {

    private final Integer screenWidth;
    private final Integer screenHeight;
    private final Integer headerPaneWidth;
    private final Integer headerPaneHeight;
    private final Integer contentPaneWidth;
    private final Integer contentPaneHeight;
    private final Integer footerPaneWidth;
    private final Integer footerPaneHeight;
    private final Integer gapPaneWidth;
    private final Integer gapPaneHeight;
    private final Integer sideGap;
    private final Integer headerButtonWidth;
    private final Integer headerButtonHeight;
    private final Integer headerMenuButtonWidth;
    private final Integer headerMenuButtonHeight;
    
    private Boolean isSubMenuVisible; 
    
    protected static enum ContentPaneList {
        SLIDE, BUSSINESS
    }
    
    /**
     * Creates new form SlidePanel
     */
    public CorePane() {
        this.screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.headerButtonWidth = 100;        // Value of i
        this.headerButtonHeight = 100;       // Value of i
        this.headerMenuButtonWidth = 126;    // Value of y
        this.headerMenuButtonHeight = 40;    // Value of x
        this.headerPaneWidth = (int) (0.8 * this.screenWidth);
        this.headerPaneHeight = (4 * this.headerMenuButtonHeight);
        this.footerPaneWidth = this.screenWidth;
        this.footerPaneHeight = 25;
        this.contentPaneWidth = this.screenWidth;
        this.contentPaneHeight = (this.screenHeight - this.footerPaneHeight);
        this.gapPaneWidth = (this.headerPaneWidth - ((2 * this.headerButtonWidth) + (2 * this.headerMenuButtonWidth)));
        this.gapPaneHeight = this.headerMenuButtonHeight;
        this.sideGap = ((this.screenWidth - this.headerPaneWidth) / 2);
        initialiseComponents();
    }
                      
    private void initialiseComponents() {

        layeredPane = new javax.swing.JLayeredPane();
        headerPane = new javax.swing.JPanel();
        headerLogoLabel = new javax.swing.JLabel();
        mealsButton = new javax.swing.JButton();
        beveragesButton = new javax.swing.JButton();
        appetizerButton = new javax.swing.JButton();
        cartButton = new javax.swing.JButton();
        mainCourseButton = new javax.swing.JButton();
        dessertButton = new javax.swing.JButton();
        gapPane = new javax.swing.JPanel();
        contentPane = new javax.swing.JPanel();
        slidePane = new SlidePane(this, contentPaneWidth, contentPaneHeight);
        bussinessPane = new BussinessPane(this, contentPaneWidth, contentPaneHeight, sideGap);
        footerPane = new javax.swing.JPanel();
        footerLabel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(screenWidth, screenHeight));
        setMinimumSize(new java.awt.Dimension(screenWidth, screenHeight));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        layeredPane.setDoubleBuffered(true);
        layeredPane.setMaximumSize(new java.awt.Dimension(contentPaneWidth, contentPaneHeight));
        layeredPane.setMinimumSize(new java.awt.Dimension(contentPaneWidth, contentPaneHeight));
        layeredPane.setPreferredSize(new java.awt.Dimension(contentPaneWidth, contentPaneHeight));

        headerPane.setMaximumSize(new java.awt.Dimension(headerPaneWidth, headerPaneHeight));
        headerPane.setMinimumSize(new java.awt.Dimension(headerPaneWidth, headerPaneHeight));
        headerPane.setOpaque(false);
        headerPane.setPreferredSize(new java.awt.Dimension(headerPaneWidth, headerPaneHeight));

        headerLogoLabel.setBackground(new java.awt.Color(0x451413));
        headerLogoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Logo-Header.png"))); // NOI18N
        headerLogoLabel.setMaximumSize(new java.awt.Dimension(headerButtonWidth, headerButtonHeight));
        headerLogoLabel.setMinimumSize(new java.awt.Dimension(headerButtonWidth, headerButtonHeight));
        headerLogoLabel.setPreferredSize(new java.awt.Dimension(headerButtonWidth, headerButtonHeight));
        headerLogoLabel.setDoubleBuffered(true);

        mealsButton.setBackground(new java.awt.Color(0x451413));
        mealsButton.setBorder(null);
        mealsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-Meals.png"))); // NOI18N
        mealsButton.setToolTipText("");
        mealsButton.setDoubleBuffered(true);
        mealsButton.setSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        mealsButton.setMaximumSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        mealsButton.setMinimumSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        mealsButton.setPreferredSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        mealsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mealsButtonMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mealsButtonMouseExited(evt);
            }
        });
        mealsButton.addActionListener(this::mealsButtonActionPerformed);

        beveragesButton.setBackground(new java.awt.Color(0x451413));
        beveragesButton.setBorder(null);
        beveragesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-Beverages.png"))); // NOI18N
        beveragesButton.setDoubleBuffered(true);
        beveragesButton.setSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        beveragesButton.setMaximumSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        beveragesButton.setMinimumSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        beveragesButton.setPreferredSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        beveragesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                beveragesButtonMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                beveragesButtonMouseExited(evt);
            }
        });
        beveragesButton.addActionListener(this::beveragesButtonActionPerformed);

        appetizerButton.setBackground(new java.awt.Color(0x451413));
        appetizerButton.setBorder(null);
        appetizerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-Appetizer.png"))); // NOI18N
        appetizerButton.setDoubleBuffered(true);
        appetizerButton.setSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        appetizerButton.setMaximumSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        appetizerButton.setMinimumSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        appetizerButton.setPreferredSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        appetizerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                appetizerButtonMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                appetizerButtonMouseExited(evt);
            }
        });
        appetizerButton.addActionListener(this::appetizerButtonActionPerformed);
        appetizerButton.setVisible(false);

        cartButton.setBackground(new java.awt.Color(0x451413));
        cartButton.setBorder(null);
        cartButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-Cart.png"))); // NOI18N
        cartButton.setDoubleBuffered(true);
        cartButton.setSize(new java.awt.Dimension(headerButtonWidth, headerButtonHeight));
        cartButton.setMaximumSize(new java.awt.Dimension(headerButtonWidth, headerButtonHeight));
        cartButton.setMinimumSize(new java.awt.Dimension(headerButtonWidth, headerButtonHeight));
        cartButton.setPreferredSize(new java.awt.Dimension(headerButtonWidth, headerButtonHeight));
        cartButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cartButtonMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cartButtonMouseExited(evt);
            }
        });
        cartButton.addActionListener(this::cartButtonActionPerformed);

        mainCourseButton.setBackground(new java.awt.Color(0x451413));
        mainCourseButton.setBorder(null);
        mainCourseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-MainCourse.png"))); // NOI18N
        mainCourseButton.setDoubleBuffered(true);
        mainCourseButton.setSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        mainCourseButton.setMaximumSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        mainCourseButton.setMinimumSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        mainCourseButton.setPreferredSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        mainCourseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mainCourseButtonMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mainCourseButtonMouseExited(evt);
            }
        });
        mainCourseButton.addActionListener(this::mainCourseButtonActionPerformed);
        mainCourseButton.setVisible(false);

        dessertButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-Dessert.png"))); // NOI18N
        dessertButton.setSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        dessertButton.setMaximumSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        dessertButton.setMinimumSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        dessertButton.setPreferredSize(new java.awt.Dimension(headerMenuButtonWidth, headerMenuButtonHeight));
        dessertButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dessertButtonMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dessertButtonMouseExited(evt);
            }
        });
        dessertButton.addActionListener(this::dessertButtonActionPerformed);
        dessertButton.setVisible(false);

        gapPane.setBackground(new java.awt.Color(0x451413));
        gapPane.setSize(new java.awt.Dimension(gapPaneWidth, gapPaneHeight));
        gapPane.setMaximumSize(new java.awt.Dimension(gapPaneWidth, gapPaneHeight));
        gapPane.setMinimumSize(new java.awt.Dimension(gapPaneWidth, gapPaneHeight));
        gapPane.setPreferredSize(new java.awt.Dimension(gapPaneWidth, gapPaneHeight));

        javax.swing.GroupLayout gapPaneLayout = new javax.swing.GroupLayout(gapPane);
        gapPane.setLayout(gapPaneLayout);
        gapPaneLayout.setHorizontalGroup(
            gapPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, gapPaneWidth, gapPaneWidth)
        );
        gapPaneLayout.setVerticalGroup(
            gapPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, 0)
        );

        javax.swing.GroupLayout headerPaneLayout = new javax.swing.GroupLayout(headerPane);
        headerPaneLayout.setAutoCreateGaps(false);
        headerPaneLayout.setAutoCreateContainerGaps(false);
        headerPane.setLayout(headerPaneLayout);
        headerPaneLayout.setHorizontalGroup(
            headerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPaneLayout.createSequentialGroup()
                .addComponent(headerLogoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(headerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dessertButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(headerPaneLayout.createSequentialGroup()
                        .addGroup(headerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(headerPaneLayout.createSequentialGroup()
                                .addComponent(mealsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0)
                                .addComponent(beveragesButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0)
                                .addComponent(gapPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(appetizerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mainCourseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0)
                        .addComponent(cartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0))
        );
        headerPaneLayout.setVerticalGroup(
            headerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPaneLayout.createSequentialGroup()
                .addGroup(headerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headerLogoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(headerPaneLayout.createSequentialGroup()
                        .addGroup(headerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(headerPaneLayout.createSequentialGroup()
                                .addGroup(headerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(mealsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(beveragesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(gapPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0)
                                .addComponent(appetizerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0)
                                .addComponent(mainCourseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cartButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0)
                        .addComponent(dessertButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0))
        );

        contentPane.setMaximumSize(new java.awt.Dimension(contentPaneWidth, contentPaneHeight));
        contentPane.setMinimumSize(new java.awt.Dimension(contentPaneWidth, contentPaneHeight));
        contentPane.setOpaque(false);
        contentPane.setLayout(new java.awt.CardLayout());
        contentPane.add(slidePane, ContentPaneList.SLIDE.name());
        contentPane.add(bussinessPane, ContentPaneList.BUSSINESS.name());
        

        layeredPane.setLayer(headerPane, 10);
        layeredPane.setLayer(contentPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layeredPaneLayout = new javax.swing.GroupLayout(layeredPane);
        layeredPane.setLayout(layeredPaneLayout);
        layeredPaneLayout.setHorizontalGroup(
            layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layeredPaneLayout.createSequentialGroup()
                .addGap(sideGap, sideGap, sideGap)
                .addComponent(headerPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(contentPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layeredPaneLayout.setVerticalGroup(
            layeredPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(contentPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        add(layeredPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        footerPane.setBackground(new java.awt.Color(0x451413));
        footerPane.setMaximumSize(new java.awt.Dimension(footerPaneWidth, footerPaneHeight));
        footerPane.setMinimumSize(new java.awt.Dimension(footerPaneWidth, footerPaneHeight));
        footerPane.setPreferredSize(new java.awt.Dimension(footerPaneWidth, footerPaneHeight));
        footerPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        footerLabel.setMaximumSize(new java.awt.Dimension(footerPaneWidth, footerPaneHeight));
        footerLabel.setMinimumSize(new java.awt.Dimension(footerPaneWidth, footerPaneHeight));
        footerLabel.setPreferredSize(new java.awt.Dimension(footerPaneWidth, footerPaneHeight));
        footerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        footerLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Label-Footer.png"))); // NOI18N
        footerLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        footerPane.add(footerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, footerPaneWidth, footerPaneHeight));

        add(footerPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, contentPaneHeight, -1, -1));
    
    }               

    private void mealsButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        
    }                                           

    private void beveragesButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
        Customer.getInstance().headerButtonActionPerformed(MainGUI.HeaderButtonOption.BEVERAGES);
    }                                               

    private void appetizerButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
        if(isSubMenuVisible) Customer.getInstance().headerButtonActionPerformed(MainGUI.HeaderButtonOption.APPETIZER);
    }                                               

    private void mainCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        if(isSubMenuVisible) Customer.getInstance().headerButtonActionPerformed(MainGUI.HeaderButtonOption.MAIN_COURSE);
    }                                                

    private void dessertButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        if(isSubMenuVisible) Customer.getInstance().headerButtonActionPerformed(MainGUI.HeaderButtonOption.DESSERT);
    }                                             

    private void cartButtonActionPerformed(java.awt.event.ActionEvent evt) {  
        Customer.getInstance().headerButtonActionPerformed(MainGUI.HeaderButtonOption.CART);
    }                                          

    private void mealsButtonMouseEntered(java.awt.event.MouseEvent evt) { 
        mealsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-HoverMeals.png"))); // NOI18N
        setSubMenuVisible(true);
    }                                        

    private void beveragesButtonMouseEntered(java.awt.event.MouseEvent evt) {   
        beveragesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-HoverBeverages.png"))); // NOI18N
    }                                            

    private void appetizerButtonMouseEntered(java.awt.event.MouseEvent evt) {                                             
        appetizerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-HoverAppetizer.png"))); // NOI18N
    }                                            

    private void mainCourseButtonMouseEntered(java.awt.event.MouseEvent evt) {                                              
        mainCourseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-HoverMainCourse.png"))); // NOI18N
    }                                             

    private void dessertButtonMouseEntered(java.awt.event.MouseEvent evt) {                                           
        dessertButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-HoverDessert.png"))); // NOI18N
    }                                          

    private void cartButtonMouseEntered(java.awt.event.MouseEvent evt) { 
        cartButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-HoverCart.png"))); // NOI18N
    }                                       

    private void mealsButtonMouseExited(java.awt.event.MouseEvent evt) {                                        
        mealsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-Meals.png"))); // NOI18N
    }                                       

    private void beveragesButtonMouseExited(java.awt.event.MouseEvent evt) {                                            
        beveragesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-Beverages.png"))); // NOI18N
    }                                           

    private void appetizerButtonMouseExited(java.awt.event.MouseEvent evt) {                                            
        appetizerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-Appetizer.png"))); // NOI18N
    }                                           

    private void mainCourseButtonMouseExited(java.awt.event.MouseEvent evt) {                                             
        mainCourseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-MainCourse.png"))); // NOI18N
    }                                            

    private void dessertButtonMouseExited(java.awt.event.MouseEvent evt) {                                          
        dessertButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-Dessert.png"))); // NOI18N
    }                                         

    private void cartButtonMouseExited(java.awt.event.MouseEvent evt) {                                       
        cartButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-Cart.png"))); // NOI18N
    }                
    
    private void formMouseEntered(java.awt.event.MouseEvent evt) {                                  
        setSubMenuVisible(false);
    }   
    
    protected Boolean isSubMenuVisible() {
        return isSubMenuVisible;
    }
    
    protected void setSubMenuVisible(Boolean isVisible) {
        appetizerButton.setVisible(isVisible);
        mainCourseButton.setVisible(isVisible);
        dessertButton.setVisible(isVisible);
        isSubMenuVisible = isVisible;
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JButton appetizerButton;
    private javax.swing.JButton beveragesButton;
    private javax.swing.JButton cartButton;
    private javax.swing.JButton dessertButton;
    private javax.swing.JLabel footerLabel;
    private javax.swing.JPanel footerPane;
    private javax.swing.JPanel gapPane;
    private javax.swing.JLabel headerLogoLabel;
    private javax.swing.JPanel headerPane;
    private javax.swing.JLayeredPane layeredPane;
    private javax.swing.JButton mainCourseButton;
    private javax.swing.JButton mealsButton;
    protected javax.swing.JPanel contentPane;
    protected SlidePane slidePane;
    protected BussinessPane bussinessPane;
    // End of variables declaration                   
}
