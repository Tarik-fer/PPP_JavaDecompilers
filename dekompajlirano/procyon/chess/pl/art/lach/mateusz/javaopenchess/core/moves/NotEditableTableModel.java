// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core.moves;

import javax.swing.table.DefaultTableModel;

class NotEditableTableModel extends DefaultTableModel
{
    @Override
    public boolean isCellEditable(final int a, final int b) {
        return false;
    }
}
