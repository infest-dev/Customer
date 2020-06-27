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

import com.jogjadamai.infest.entity.Carts;
import com.jogjadamai.infest.entity.Features;
import com.jogjadamai.infest.entity.Menus;
import com.jogjadamai.infest.entity.Orders;
import com.jogjadamai.infest.entity.Tables;
import com.jogjadamai.infest.tablemodel.CartsTableModel;
import java.awt.Toolkit;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 
 * @author Danang Galuh Tegar P
 */
class FinalPane extends javax.swing.JPanel {
    
    private final Customer controller;
    private final Integer screenWidth;
    private final Integer screenHeight;
    private final Integer finalPaneWidth;
    private final Integer finalPaneHeight;
    private final Integer footerPaneWidth;
    private final Integer footerPaneHeight;
    private final Tables table;
    private final Carts cart;
    private final Features currencyFeature;
    private final List<Orders> orders;
    
    /**
     * Creates new form FinalPane
     */
    protected FinalPane(Tables table, Carts cart, Features currencyFeature, List<Orders> orders) {
        this.controller = Customer.getInstance();
        this.screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.footerPaneWidth = this.screenWidth;
        this.footerPaneHeight = 25;
        this.finalPaneWidth = this.screenWidth;
        this.finalPaneHeight = (this.screenHeight - this.footerPaneHeight);
        this.table = table;
        this.cart = cart;
        this.currencyFeature = currencyFeature;
        this.orders = orders;
        initialiseComponents();
    }
    
    private void initialiseComponents() {
        
        finalPane = new javax.swing.JPanel();
        cartTableScrollPane = new javax.swing.JScrollPane();
        cartTable = new org.jdesktop.swingx.JXTable();
        cartNoteScrollPane = new javax.swing.JScrollPane();
        cartNoteArea = new javax.swing.JTextArea();
        totalQtyLabel = new javax.swing.JLabel();
        totalPriceLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        printBillButton = new javax.swing.JButton();
        separator = new javax.swing.JSeparator();
        exitButton = new javax.swing.JButton();
        tableLabel = new javax.swing.JLabel();
        dateTimeLabel = new javax.swing.JLabel();
        noteLabel = new javax.swing.JLabel();
        footerPane = new javax.swing.JPanel();
        footerLabel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(screenWidth, screenHeight));
        setMinimumSize(new java.awt.Dimension(screenWidth, screenHeight));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(screenWidth, screenHeight));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        finalPane.setMaximumSize(new java.awt.Dimension(finalPaneWidth, finalPaneHeight));
        finalPane.setMinimumSize(new java.awt.Dimension(finalPaneWidth, finalPaneHeight));
        finalPane.setBackground(new java.awt.Color(255, 219, 146, 154));
        finalPane.setPreferredSize(new java.awt.Dimension(finalPaneWidth, finalPaneHeight));
        finalPane.setLayout(null);

        cartTableScrollPane.setMaximumSize(new java.awt.Dimension(finalPaneWidth-120, 250));
        cartTableScrollPane.setMinimumSize(new java.awt.Dimension(finalPaneWidth-120, 250));
        cartTableScrollPane.setPreferredSize(new java.awt.Dimension(finalPaneWidth-120, 250));

        cartTable.setModel(new CartsTableModel(orders, currencyFeature));
        cartTable.setRowSelectionAllowed(false);
        cartTableScrollPane.setViewportView(cartTable);

        finalPane.add(cartTableScrollPane);
        cartTableScrollPane.setBounds(60, 150, finalPaneWidth-120, 250);

        cartNoteScrollPane.setMaximumSize(new java.awt.Dimension((int) (0.3 * (finalPaneWidth-140)), 175));
        cartNoteScrollPane.setMinimumSize(new java.awt.Dimension((int) (0.3 * (finalPaneWidth-140)), 175));
        cartNoteScrollPane.setPreferredSize(new java.awt.Dimension((int) (0.3 * (finalPaneWidth-140)), 175));

        cartNoteArea.setEditable(false);
        cartNoteArea.setText(cart.getNote());
        cartNoteArea.setColumns(20);
        cartNoteArea.setRows(5);
        cartNoteArea.setLineWrap(true);
        cartNoteArea.setMaximumSize(new java.awt.Dimension((int) (0.3 * (finalPaneWidth-140)), 175));
        cartNoteArea.setMinimumSize(new java.awt.Dimension((int) (0.3 * (finalPaneWidth-140)), 175));
        cartNoteScrollPane.setViewportView(cartNoteArea);

        finalPane.add(cartNoteScrollPane);
        cartNoteScrollPane.setBounds(60, 436, (int) (0.3 * (finalPaneWidth-80)), 175);

        int quantity = 0;
        int price = 0;
        for (Orders order : orders) {
            quantity = quantity + order.getTotal();
            int subtotal = order.getTotal() * order.getIdmenu().getPrice();
            price = price + subtotal;
        }
        Menus menu = new Menus();
        menu.setPrice(price);
        
        totalQtyLabel.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        totalQtyLabel.setText("TOTAL QUANTITY : " + quantity);
        totalQtyLabel.setMaximumSize(new java.awt.Dimension((int) (0.7 * (finalPaneWidth-140)), 20));
        totalQtyLabel.setMinimumSize(new java.awt.Dimension((int) (0.7 * (finalPaneWidth-140)), 20));
        totalQtyLabel.setPreferredSize(new java.awt.Dimension((int) (0.7 * (finalPaneWidth-140)), 20));
        finalPane.add(totalQtyLabel);
        totalQtyLabel.setBounds(80 + cartNoteScrollPane.getPreferredSize().width, 436, (int) (0.7 * (finalPaneWidth-140)), 20);

        totalPriceLabel.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        totalPriceLabel.setText("TOTAL PRICE        : " + controller.getMenuPrice(menu));
        totalPriceLabel.setMaximumSize(new java.awt.Dimension((int) (0.7 * (finalPaneWidth-140)), 20));
        totalPriceLabel.setMinimumSize(new java.awt.Dimension((int) (0.7 * (finalPaneWidth-140)), 20));
        totalPriceLabel.setPreferredSize(new java.awt.Dimension((int) (0.7 * (finalPaneWidth-140)), 20));
        finalPane.add(totalPriceLabel);
        totalPriceLabel.setBounds(80 + cartNoteScrollPane.getPreferredSize().width, 466, 858, 20);

        titleLabel.setFont(new java.awt.Font("Exo", 1, 28)); // NOI18N
        titleLabel.setText("My Cart");
        titleLabel.setMaximumSize(new java.awt.Dimension(finalPaneWidth-60, 40));
        titleLabel.setMinimumSize(new java.awt.Dimension(finalPaneWidth-60, 40));
        titleLabel.setPreferredSize(new java.awt.Dimension(finalPaneWidth-60, 40));
        finalPane.add(titleLabel);
        titleLabel.setBounds(30, 30, 1306, 40);

        printBillButton.setEnabled(controller.isBillPrintable());
        printBillButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        printBillButton.setBorder(null);
        printBillButton.setLabel("");
        printBillButton.setBackground(new java.awt.Color(255,255,255,0));
        printBillButton.setMaximumSize(new java.awt.Dimension(200, 40));
        printBillButton.setMinimumSize(new java.awt.Dimension(200, 40));
        printBillButton.setPreferredSize(new java.awt.Dimension(200, 40));
        printBillButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-PrintBill.png"))); // NOI18N
        printBillButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printBillButtonMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                printBillButtonMouseExited(evt);
            }
        });
        printBillButton.addActionListener(this::printBillButtonActionPerformed);
        finalPane.add(printBillButton);
        printBillButton.setBounds(screenWidth-480, 571, 200, 40);

        separator.setBackground(new java.awt.Color(0x451413));
        separator.setForeground(new java.awt.Color(255, 219, 146, 255));
        separator.setMaximumSize(new java.awt.Dimension(finalPaneWidth-60, 5));
        separator.setMinimumSize(new java.awt.Dimension(finalPaneWidth-60, 5));
        separator.setPreferredSize(new java.awt.Dimension(finalPaneWidth-60, 5));
        finalPane.add(separator);
        separator.setBounds(30, 70, finalPaneWidth-60, 5);

        exitButton.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        exitButton.setLabel("");
        exitButton.setBorder(null);
        exitButton.setBackground(new java.awt.Color(255,255,255,0));
        exitButton.setMaximumSize(new java.awt.Dimension(200, 40));
        exitButton.setMinimumSize(new java.awt.Dimension(200, 40));
        exitButton.setPreferredSize(new java.awt.Dimension(200, 40));
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-Exit.png"))); // NOI18N
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitButtonMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButtonMouseExited(evt);
            }
        });
        exitButton.addActionListener(this::exitButtonActionPerformed);
        finalPane.add(exitButton);
        exitButton.setBounds(screenWidth-260, 571, 200, 40);
        
        tableLabel.setFont(new java.awt.Font("Open Sans Condensed", 1, 16)); // NOI18N
        tableLabel.setText(table.getName());
        tableLabel.setMaximumSize(new java.awt.Dimension(finalPaneWidth-60, 25));
        tableLabel.setMinimumSize(new java.awt.Dimension(finalPaneWidth-60, 25));
        tableLabel.setPreferredSize(new java.awt.Dimension(finalPaneWidth-60, 25));
        finalPane.add(tableLabel);
        tableLabel.setBounds(30, 75, finalPaneWidth-60, 25);
        
        LocalDateTime cartDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(cart.getDate().getTime()), ZoneId.systemDefault());
        LocalDateTime cartTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(cart.getTime().getTime()), ZoneId.systemDefault());
        dateTimeLabel.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        dateTimeLabel.setText("You are coming here on " + DateTimeFormatter.ofPattern("dd MMMM yyyy").format(cartDate) + " " + DateTimeFormatter.ofPattern("HH:mm:ss").format(cartTime) + ".");
        dateTimeLabel.setMaximumSize(new java.awt.Dimension(finalPaneWidth-60, 20));
        dateTimeLabel.setMinimumSize(new java.awt.Dimension(finalPaneWidth-60, 20));
        dateTimeLabel.setPreferredSize(new java.awt.Dimension(finalPaneWidth-60, 20));
        finalPane.add(dateTimeLabel);
        dateTimeLabel.setBounds(30, 100, finalPaneWidth-60, 20);

        noteLabel.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        noteLabel.setText("NOTE:");
        noteLabel.setMaximumSize(new java.awt.Dimension(finalPaneWidth-120, 20));
        noteLabel.setMinimumSize(new java.awt.Dimension(finalPaneWidth-120, 20));
        noteLabel.setPreferredSize(new java.awt.Dimension(finalPaneWidth-120, 20));
        finalPane.add(noteLabel);
        noteLabel.setBounds(60, 411, finalPaneWidth-120, 20);

        add(finalPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        footerPane.setBackground(new java.awt.Color(0x451413));
        footerPane.setMaximumSize(new java.awt.Dimension(footerPaneWidth, footerPaneHeight));
        footerPane.setMinimumSize(new java.awt.Dimension(footerPaneWidth, footerPaneHeight));
        footerPane.setPreferredSize(new java.awt.Dimension(footerPaneWidth, footerPaneHeight));
        footerPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        footerLabel.setBackground(new java.awt.Color(0x651112));
        footerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        footerLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Label-Footer.png"))); // NOI18N
        footerLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        footerPane.add(footerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, footerPaneWidth, footerPaneHeight));

        add(footerPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, finalPaneHeight, -1, -1));
        
    }
    
    private void printBillButtonMouseEntered(java.awt.event.MouseEvent evt) {
        printBillButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-HoverPrintBill.png"))); // NOI18N
    }
    
    private void printBillButtonMouseExited(java.awt.event.MouseEvent evt) {
        printBillButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-PrintBill.png"))); // NOI18N
    }
    
    private void printBillButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int price = 0;
        for (Orders order : orders) {
            int subtotal = order.getTotal() * order.getIdmenu().getPrice();
            price = price + subtotal;
        }
        Menus menu = new Menus();
        menu.setPrice(price);
        controller.printBillButtonActionPerformed(cartTable, controller.getMenuPrice(menu));
    }
    
    private void exitButtonMouseEntered(java.awt.event.MouseEvent evt) {
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-HoverExit.png"))); // NOI18N
    }
    
    private void exitButtonMouseExited(java.awt.event.MouseEvent evt) {
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-Exit.png"))); // NOI18N
    }
    
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        controller.exitButtonActionPerformed();
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JTextArea cartNoteArea;
    private javax.swing.JScrollPane cartNoteScrollPane;
    private org.jdesktop.swingx.JXTable cartTable;
    private javax.swing.JScrollPane cartTableScrollPane;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel dateTimeLabel;
    private javax.swing.JPanel finalPane;
    private javax.swing.JLabel footerLabel;
    private javax.swing.JPanel footerPane;
    private javax.swing.JLabel noteLabel;
    private javax.swing.JButton printBillButton;
    private javax.swing.JSeparator separator;
    private javax.swing.JLabel tableLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel totalPriceLabel;
    private javax.swing.JLabel totalQtyLabel;
    // End of variables declaration       
    
}
