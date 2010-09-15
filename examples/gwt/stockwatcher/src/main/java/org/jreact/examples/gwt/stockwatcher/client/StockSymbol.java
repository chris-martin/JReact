package org.jreact.examples.gwt.stockwatcher.client;

import fj.Equal;
import fj.F;

import static fj.Equal.equal;

public class StockSymbol {

    private final String str;

    public StockSymbol(
            final String str) {

        this.str = str;

    }

    public static final Equal<StockSymbol> EQUAL = equal(
        new F<StockSymbol, F<StockSymbol, Boolean>>() {
            @Override
            public F<StockSymbol, Boolean> f(final StockSymbol stockSymbol) {
                return stockSymbol.equalsFunction();
            }
        }
    );

    public F<StockSymbol, Boolean> equalsFunction() {

        return new F<StockSymbol, Boolean>() {
            @Override
            public Boolean f(final StockSymbol stockSymbol) {
                return str.equals(stockSymbol.str);
            }
        };

    }

}
