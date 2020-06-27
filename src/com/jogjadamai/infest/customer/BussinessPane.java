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


/**
 *
 * @author Danang Galuh Tegar P
 */
public class BussinessPane extends javax.swing.JPanel {

    private final CorePane parent;
    private final Integer paneWidth;
    private final Integer paneHeight;
    private final Integer sideGap;
    private final Integer upperGap;
    private final Integer childContentPaneWidth;
    private final Integer childContentPaneHeight;
    
    protected static enum ChildContentPaneList {
        LIST_MENU, DETAIL_MENU, CART
    }
    
    /**
     * Creates new form ChildContentPanel
     * @param paneWidth
     * @param paneHeight
     * @param sideGap
     */
    BussinessPane(CorePane parent, Integer paneWidth, Integer paneHeight, Integer sideGap) {
        this.parent = parent;
        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;
        this.sideGap = sideGap;
        this.upperGap = 120;
        this.childContentPaneWidth = (this.paneWidth - (2 * this.sideGap));
        this.childContentPaneHeight = (this.paneHeight - upperGap);
        initComponents();
    }
                        
    private void initComponents() {

        childContentPane = new javax.swing.JPanel();

        setMaximumSize(new java.awt.Dimension(paneWidth, paneHeight));
        setMinimumSize(new java.awt.Dimension(paneWidth, paneHeight));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(paneWidth, paneHeight));

        childContentPane.setOpaque(false);
        childContentPane.setBorder(null);
        childContentPane.setMaximumSize(new java.awt.Dimension(childContentPaneWidth, childContentPaneHeight));
        childContentPane.setMinimumSize(new java.awt.Dimension(childContentPaneWidth, childContentPaneHeight));
        childContentPane.setPreferredSize(new java.awt.Dimension(childContentPaneWidth, childContentPaneHeight));
        childContentPane.setLayout(new java.awt.CardLayout());
        childContentPane.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                childContentPaneMouseEntered(evt);
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(sideGap, sideGap, sideGap)
                .addComponent(childContentPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(sideGap, sideGap, sideGap))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(upperGap, upperGap, upperGap)
                .addComponent(childContentPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }               

    private void childContentPaneMouseEntered(java.awt.event.MouseEvent evt) {
        if(parent.isSubMenuVisible()) parent.setSubMenuVisible(false);
    }

    // Variables declaration - do not modify                     
    protected javax.swing.JPanel childContentPane;
    // End of variables declaration                   
}
