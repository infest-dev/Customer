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
import java.util.List;

/**
 *
 * @author Danang Galuh Tegar P
 */
public class ListMenuPane extends javax.swing.JPanel {
    
    private final CorePane parent;
    private final Integer paneWidth;
    private final Integer paneHeight;
    private final Integer menuPaneWidth;
    private final Integer menuPaneHeight;
    private final List<Menus> listMenu;
    private final Integer columnCount;
    private final Integer rowCount;
    private final Integer horizontalMenuGapCount;
    private final Integer verticalMenuGapCount;
    private final Integer menuGapWidth;
    private final Integer menuGapHeight;
    private final Integer listMenuPaneWidth;
    private final Integer listMenuPaneHeight;
    

    /**
     * Creates new form ListMenuPanel
     * @param parent
     * @param paneWidth
     * @param paneHeight
     * @param listMenu
     */
    public ListMenuPane(CorePane parent, Integer paneWidth, Integer paneHeight, List<Menus> listMenu) {
        this.parent = parent;
        this.paneWidth = paneWidth;
        this.paneHeight = paneHeight;
        this.menuPaneWidth = 450;
        this.menuPaneHeight = 150;
        this.listMenu = listMenu;
        this.columnCount = Math.floorDiv(this.paneWidth, this.menuPaneWidth);
        this.rowCount = (int) Math.ceil((double) this.listMenu.size() / (double) this.columnCount);
        this.horizontalMenuGapCount = (this.columnCount + 1);
        this.verticalMenuGapCount = (this.rowCount + 1);
        this.menuGapWidth = (this.paneWidth - (this.columnCount * this.menuPaneWidth)) / this.horizontalMenuGapCount;
        this.menuGapHeight = 20;
        this.listMenuPaneHeight = (this.rowCount * this.menuPaneHeight) + (this.verticalMenuGapCount * this.menuGapHeight);
        this.listMenuPaneWidth = (this.listMenuPaneHeight >= this.paneHeight) ? (this.paneWidth - 20) : this.paneWidth;
        initialiseComponents();
    }
                         
    private void initialiseComponents() {

        scrollPane = new javax.swing.JScrollPane();
        listMenuPane = new javax.swing.JPanel();
        
        setBorder(null);
        setOpaque(false);
        setMaximumSize(new java.awt.Dimension(paneWidth, paneHeight));
        setMinimumSize(new java.awt.Dimension(paneWidth, paneHeight));
        setPreferredSize(new java.awt.Dimension(paneWidth, paneHeight));

        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.setMaximumSize(new java.awt.Dimension(paneWidth, paneHeight));
        scrollPane.setMinimumSize(new java.awt.Dimension(paneWidth, paneHeight));
        scrollPane.setPreferredSize(new java.awt.Dimension(paneWidth, paneHeight));

        listMenuPane.setBorder(null);
        listMenuPane.setBackground(new java.awt.Color(255, 219, 146, 154));
        listMenuPane.setMaximumSize(new java.awt.Dimension(listMenuPaneWidth, listMenuPaneHeight));
        listMenuPane.setMinimumSize(new java.awt.Dimension(listMenuPaneWidth, listMenuPaneHeight));
        listMenuPane.setPreferredSize(new java.awt.Dimension(listMenuPaneWidth, listMenuPaneHeight));
        listMenuPane.setLayout(null);
        scrollPane.setViewportView(listMenuPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, paneWidth, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, paneHeight, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        
        int menuIndex = 0;
        int y = listMenuPane.getInsets().top + menuGapHeight;
        for(int row = 0; row < rowCount; row++) {
            int x = listMenuPane.getInsets().left + menuGapWidth;
            for(int column = 0; column < columnCount; column++) {
                if(menuIndex >= 0 && menuIndex < listMenu.size()) {
                    MenuPane menuPane = new MenuPane(parent, listMenu.get(menuIndex));
                    listMenuPane.add(menuPane);
                    menuPane.setBounds(x, y, menuPaneWidth, menuPaneHeight);
                }
                menuIndex = menuIndex + 1;
                x = x + menuPaneWidth + menuGapWidth;
            }
            y = y + menuPaneHeight + menuGapHeight;
        }
        
    }               

    

    // Variables declaration - do not modify                     
    protected javax.swing.JPanel listMenuPane;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration                   
}
