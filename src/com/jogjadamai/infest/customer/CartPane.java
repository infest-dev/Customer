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
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Danang Galuh Tegar P
 */
final class CartPane extends javax.swing.JPanel {
    
    private final Customer controller;
    private final CorePane parent;
    private final Integer paneWidth;
    private final Integer paneHeight;
    private final Integer tableWidth;
    private final Integer areaWidth;
    private final Tables table;
    private final Carts cart;
    private final Features currencyFeature;
    private final List<Orders> orders;
    
    protected CartPane(CorePane parent, Integer paneWidth, Integer paneHeight, Tables table, Carts cart, Features currencyFeature, List<Orders> orders) {
        this.controller = Customer.getInstance();
        this.parent = parent;
        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;
        this.tableWidth = (int) (0.65 * (this.paneWidth - 90));
        this.areaWidth = (int) (0.35 * (this.paneWidth - 90));
        this.table = table;
        this.cart = cart;
        this.currencyFeature = currencyFeature;
        this.orders = orders;
        initialiseComponents();
    }
    
    private void initialiseComponents() {

        titleLabel = new javax.swing.JLabel();
        separator = new javax.swing.JSeparator();
        tableLabel = new javax.swing.JLabel();
        dateTimeLabel = new javax.swing.JLabel();
        cartTableScrollPane = new javax.swing.JScrollPane();
        cartTable = new org.jdesktop.swingx.JXTable();
        cartNoteScrollPane = new javax.swing.JScrollPane();
        cartNoteArea = new javax.swing.JTextArea();
        writeNoteLabel = new javax.swing.JLabel();
        totalQtyLabel = new javax.swing.JLabel();
        totalPriceLabel = new javax.swing.JLabel();
        removeOrderButton = new javax.swing.JButton();
        checkoutOrderButton = new javax.swing.JButton();
        
        setBorder(null);
        setBackground(new java.awt.Color(255, 219, 146, 154));
        setMaximumSize(new java.awt.Dimension(paneWidth, paneHeight));
        setMinimumSize(new java.awt.Dimension(paneWidth, paneHeight));
        setPreferredSize(new java.awt.Dimension(paneWidth, paneHeight));

        titleLabel.setFont(new java.awt.Font("Exo", 1, 28)); // NOI18N
        titleLabel.setText("My Cart");
        titleLabel.setForeground(new java.awt.Color(0x451413));
        titleLabel.setMaximumSize(new java.awt.Dimension(paneWidth-60, 40));
        titleLabel.setMinimumSize(new java.awt.Dimension(paneWidth-60, 40));
        titleLabel.setPreferredSize(new java.awt.Dimension(paneWidth-60, 40));

        separator.setBackground(new java.awt.Color(0x451413));
        separator.setForeground(new java.awt.Color(255, 219, 146, 255));
        separator.setMaximumSize(new java.awt.Dimension(paneWidth-60, 5));
        separator.setMinimumSize(new java.awt.Dimension(paneWidth-60, 5));
        separator.setPreferredSize(new java.awt.Dimension(paneWidth-60, 5));

        tableLabel.setFont(new java.awt.Font("Open Sans Condensed", 1, 16)); // NOI18N
        tableLabel.setText(table.getName());
        tableLabel.setMaximumSize(new java.awt.Dimension(paneWidth-60, 25));
        tableLabel.setMinimumSize(new java.awt.Dimension(paneWidth-60, 25));
        tableLabel.setPreferredSize(new java.awt.Dimension(paneWidth-60, 25));

        LocalDateTime cartDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(cart.getDate().getTime()), ZoneId.systemDefault());
        LocalDateTime cartTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(cart.getTime().getTime()), ZoneId.systemDefault());
        dateTimeLabel.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        dateTimeLabel.setText("You are coming here on " + DateTimeFormatter.ofPattern("dd MMMM yyyy").format(cartDate) + " " + DateTimeFormatter.ofPattern("HH:mm:ss").format(cartTime) + ".");
        dateTimeLabel.setMaximumSize(new java.awt.Dimension(paneWidth-60, 20));
        dateTimeLabel.setMinimumSize(new java.awt.Dimension(paneWidth-60, 20));
        dateTimeLabel.setPreferredSize(new java.awt.Dimension(paneWidth-60, 20));

        cartTableScrollPane.setMaximumSize(new java.awt.Dimension(tableWidth, 200));
        cartTableScrollPane.setMinimumSize(new java.awt.Dimension(tableWidth, 200));
        cartTableScrollPane.setPreferredSize(new java.awt.Dimension(tableWidth, 200));
        
        cartTable.setSortable(false);
        cartTable.setModel(new CartsTableModel(orders, currencyFeature));
        cartTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartTableMouseClicked(evt);
            }
        });
        cartTable.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cartTableKeyReleased(evt);
            }
        });
        cartTable.packAll();
        cartTableScrollPane.setViewportView(cartTable);

        cartNoteScrollPane.setMaximumSize(new java.awt.Dimension(areaWidth, 175));
        cartNoteScrollPane.setMinimumSize(new java.awt.Dimension(areaWidth, 175));
        cartNoteScrollPane.setPreferredSize(new java.awt.Dimension(areaWidth, 175));

        cartNoteArea.setText(cart.getNote());
        cartNoteArea.setColumns(20);
        cartNoteArea.setRows(5);
        cartNoteArea.setLineWrap(true);
        cartNoteScrollPane.setViewportView(cartNoteArea);

        writeNoteLabel.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        writeNoteLabel.setText("Write a note:");
        writeNoteLabel.setMaximumSize(new java.awt.Dimension(areaWidth, 25));
        writeNoteLabel.setMinimumSize(new java.awt.Dimension(areaWidth, 25));
        writeNoteLabel.setPreferredSize(new java.awt.Dimension(areaWidth, 25));

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
        totalQtyLabel.setMaximumSize(new java.awt.Dimension(paneWidth-60, 20));
        totalQtyLabel.setMinimumSize(new java.awt.Dimension(paneWidth-60, 20));
        totalQtyLabel.setPreferredSize(new java.awt.Dimension(paneWidth-60, 20));

        totalPriceLabel.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        totalPriceLabel.setText("TOTAL PRICE        : " + controller.getMenuPrice(menu));
        totalPriceLabel.setMaximumSize(new java.awt.Dimension(paneWidth-60, 20));
        totalPriceLabel.setMinimumSize(new java.awt.Dimension(paneWidth-60, 20));
        totalPriceLabel.setPreferredSize(new java.awt.Dimension(paneWidth-60, 20));

        removeOrderButton.setEnabled(false);
        removeOrderButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        removeOrderButton.setText("");
        removeOrderButton.setBorder(null);
        removeOrderButton.setBackground(new java.awt.Color(255,255,255,0));
        removeOrderButton.setMaximumSize(new java.awt.Dimension(200, 40));
        removeOrderButton.setMinimumSize(new java.awt.Dimension(200, 40));
        removeOrderButton.setPreferredSize(new java.awt.Dimension(200, 40));
        removeOrderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-RemoveOrder.png"))); // NOI18N
        removeOrderButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                removeOrderButtonMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                removeOrderButtonMouseExited(evt);
            }
        });
        removeOrderButton.addActionListener(this::removeOrderButtonActionPerformed);

        checkoutOrderButton.setEnabled(!orders.isEmpty());
        checkoutOrderButton.setFont(new java.awt.Font("Open Sans", 1, 12)); // NOI18N
        checkoutOrderButton.setText("");
        checkoutOrderButton.setBackground(new java.awt.Color(255,255,255,0));
        checkoutOrderButton.setMaximumSize(new java.awt.Dimension(200, 40));
        checkoutOrderButton.setMinimumSize(new java.awt.Dimension(200, 40));
        checkoutOrderButton.setPreferredSize(new java.awt.Dimension(200, 40));
        checkoutOrderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-CheckOut.png"))); // NOI18N
        checkoutOrderButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                checkoutOrderButtonMouseEntered(evt);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                checkoutOrderButtonMouseExited(evt);
            }
        });
        checkoutOrderButton.addActionListener(this::checkoutOrderButtonActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(removeOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkoutOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dateTimeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tableLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(separator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalQtyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cartTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cartNoteScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(writeNoteLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(totalPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(dateTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cartTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(writeNoteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cartNoteScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(totalQtyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(totalPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkoutOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }    
    
    private void removeOrderButtonMouseEntered(java.awt.event.MouseEvent evt) {
        removeOrderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-HoverRemoveOrder.png"))); // NOI18N
    }
    
    private void removeOrderButtonMouseExited(java.awt.event.MouseEvent evt) {
        removeOrderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-RemoveOrder.png"))); // NOI18N
    }
    
    private void removeOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if(cartTable.getSelectedRow() >= 0 && cartTable.getSelectedRow() < cartTable.getRowCount()) controller.removeOrderButtonActionPerformed(cartTable.getSelectedRow(), this.cartNoteArea.getText());
    }
    
    private void checkoutOrderButtonMouseEntered(java.awt.event.MouseEvent evt) {
        checkoutOrderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-HoverCheckOut.png"))); // NOI18N
    }
    
    private void checkoutOrderButtonMouseExited(java.awt.event.MouseEvent evt) {
        checkoutOrderButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jogjadamai/infest/assets/Button-CheckOut.png"))); // NOI18N
    }
    
    private void checkoutOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {
        controller.checkoutOrderButtonActionPerformed(cartNoteArea.getText());
    }
    
    private void cartTableKeyReleased(java.awt.event.KeyEvent evt) {                                      
        if(!orders.isEmpty() && cartTable.getSelectedRow() >= 0) {
            removeOrderButton.setEnabled(true);
        } else {
            removeOrderButton.setEnabled(false);
        }
    }                                     

    private void cartTableMouseClicked(java.awt.event.MouseEvent evt) {                                       
        if(!orders.isEmpty() && cartTable.getSelectedRow() >= 0) {
            removeOrderButton.setEnabled(true);
        } else {
            removeOrderButton.setEnabled(false);
        }
    }   
    
    // Variables declaration - do not modify                     
    private javax.swing.JTextArea cartNoteArea;
    private javax.swing.JScrollPane cartNoteScrollPane;
    protected org.jdesktop.swingx.JXTable cartTable;
    private javax.swing.JScrollPane cartTableScrollPane;
    private javax.swing.JButton checkoutOrderButton;
    private javax.swing.JLabel dateTimeLabel;
    private javax.swing.JButton removeOrderButton;
    private javax.swing.JLabel writeNoteLabel;
    private javax.swing.JSeparator separator;
    private javax.swing.JLabel tableLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel totalPriceLabel;
    private javax.swing.JLabel totalQtyLabel;
    // End of variables declaration      
    
}
