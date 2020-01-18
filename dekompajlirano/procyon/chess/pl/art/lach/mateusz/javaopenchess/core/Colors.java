// 
// Decompiled by Procyon v0.5.36
// 

package pl.art.lach.mateusz.javaopenchess.core;

public enum Colors
{
    WHITE("white", 'w'), 
    BLACK("black", 'b');
    
    protected String colorName;
    protected char symbol;
    
    private Colors(final String colorName, final char symbol) {
        this.colorName = colorName;
        this.symbol = symbol;
    }
    
    public String getColorName() {
        return this.colorName;
    }
    
    public char getSymbol() {
        return this.symbol;
    }
    
    public String getSymbolAsString() {
        return String.valueOf(this.symbol);
    }
}
