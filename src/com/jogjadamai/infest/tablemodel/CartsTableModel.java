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
package com.jogjadamai.infest.tablemodel;

/**
 * <h1>class <code>CartsTableModel</code></h1>
 * <p><code>CartsTableModel</code> is <code>javax.swing.table.AbstractTableModel</code>
 * class defining the table model of <code>Report</code> entity.</p>
 * <br>
 * <p><b><i>Coded, built, and packaged with passion by Danang Galuh Tegar P for Infest.</i></b></p>
 * 
 * @author Danang Galuh Tegar P
 * @version 2017.03.10.0001
 */
public final class CartsTableModel extends javax.swing.table.AbstractTableModel {

    private final java.util.List<com.jogjadamai.infest.entity.Orders> ORDER_LIST;
    private final com.jogjadamai.infest.entity.Features currencyFeature;

    public CartsTableModel(java.util.List<com.jogjadamai.infest.entity.Orders> tableList, com.jogjadamai.infest.entity.Features currencyFeature) {
        this.ORDER_LIST = tableList;
        this.currencyFeature = currencyFeature;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return this.ORDER_LIST.size();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Menu Name";
            case 1:
                return "Quantity [unit(s)]";
            case 2:
                return (currencyFeature.getStatus()==1) ?  "Unit Price [" + currencyFeature.getDescription() + "]" : "Unit Price";
            case 3:
                return "Sub-Total";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return this.ORDER_LIST.get(row).getIdmenu().getName();
            case 1:
                return this.ORDER_LIST.get(row).getTotal();
            case 2:
                return getPrice(this.ORDER_LIST.get(row).getIdmenu());
            case 3:
                return (this.ORDER_LIST.get(row).getTotal() * this.ORDER_LIST.get(row).getIdmenu().getPrice());
            default:
                return null;
        }
    }
    
    private String getPrice(com.jogjadamai.infest.entity.Menus menu) {
        java.text.NumberFormat format = java.text.NumberFormat.getNumberInstance(java.util.Locale.getDefault());
        return format.format(menu.getPrice()) + " " + currencyFeature.getDescription();
    }
    
}
