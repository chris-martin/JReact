package org.jreact.examples.gwt.stockwatcher.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;
import fj.Effect;
import org.jreact.adapt.ui.key.IsEnterKeyCode;
import org.jreact.expanded.list.ListVariable;
import org.jreact.expanded.stream.StreamMerge;

import java.util.Date;

public class StockPresenter {

    private static final int REFRESH_INTERVAL = 5000; // ms

    private StockPriceServiceAsync stockPriceSvc;

    private ListVariable<StockSymbol> stocks;

    private final StockView view;

    public StockPresenter(
            final StockView view) {

        this.view = view;

        stocks = new ListVariable<StockSymbol>();

        // set up event listeners for adding a new stock
        StreamMerge.<Object>mergeStreams(
            view.addButtonClick(),
            view.newSymbolTextEntry().keyDown().filter(new IsEnterKeyCode())
        ).loop(
            new Effect<Object>() {
                @Override
                public void e(Object o) {
                    addStock();
                }
            }
        );

        // set up timer to refresh list automatically
        final Timer refreshTimer = new Timer() {
            public void run() {
                refreshWatchList();
            }
        };
        refreshTimer.scheduleRepeating(REFRESH_INTERVAL);

        // move cursor focus to the text box
        view.newSymbolTextEntry().focus().put(true);

    }

    private void addStock() {

        final String symbol = view.newSymbolTextEntry().value().get().toUpperCase().trim();
        view.newSymbolTextEntry().focus().put(true);

        // symbol must be between 1 and 10 chars that are numbers, letters, or dots
        if (!symbol.matches("^[0-9a-zA-Z\\.]{1,10}$")) {
            Window.alert("'" + symbol + "' is not a valid symbol.");
            view.newSymbolTextEntry().selectAll();
            return;
        }

        view.newSymbolTextEntry().value().put("");

        final StockSymbol stockSymbol = new StockSymbol(symbol);

        // don't add the stock if it's already in the watch list
        if (stocks.get().exists(stockSymbol.equalsFunction())) {
            return;
        }

        // add the stock to the list
        stocks.snoc(stockSymbol);

        int row = stocksFlexTable.getRowCount();
        stocksFlexTable.setText(row, 0, symbol);

        Button removeStock = new Button("x");
        removeStock.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                int removedIndex = stocks.elementIndex(StockSymbol.EQUAL, stockSymbol).some();
                stocks.delete(stockSymbol, StockSymbol.EQUAL);
                stocksFlexTable.removeRow(removedIndex+1);
            }
        });
        stocksFlexTable.setWidget(row, 3, removeStock);

        // get stock price
        refreshWatchList();

    }

    private void refreshWatchList() {

        // lazy initialization of service proxy
        if (stockPriceSvc == null) {
            stockPriceSvc = GWT.create(StockPriceService.class);
        }

        AsyncCallback<StockPrice[]> callback = new AsyncCallback<StockPrice[]>() {

            public void onFailure(Throwable caught) {
                // display the error message above the watch list
                String details = caught.getMessage();
                if (caught instanceof DelistedException) {
                    details = "Company '" + ((DelistedException)caught).getSymbol() + "' was delisted";
                }

                errorMsgLabel.setText("Error: " + details);
                errorMsgLabel.setVisible(true);
            }

            public void onSuccess(
                    StockPrice[] result) {
                updateTable(result);
            }
        };

        // make the call to the stock price service
        stockPriceSvc.getPrices(stocks.toArray(new String[0]), callback);

    }

private void updateTable(StockPrice[] prices) {
  for (int i=0; i<prices.length; i++) {
    updateTable(prices[i]);
  }

  // change the last update timestamp
  lastUpdatedLabel.setText("Last update : " +
      DateTimeFormat.getMediumDateTimeFormat().format(new Date()));

      // clear any errors
  errorMsgLabel.setVisible(false);

}

private void updateTable(StockPrice price) {
  // make sure the stock is still in our watch list
  if (!stocks.contains(price.getSymbol())) {
    return;
  }

  int row = stocks.indexOf(price.getSymbol()) + 1;

  // apply nice formatting to price and change
  String priceText = NumberFormat.getFormat("#,##0.00").format(price.getPrice());
  NumberFormat changeFormat = NumberFormat.getFormat("+#,##0.00;-#,##0.00");
  String changeText = changeFormat.format(price.getChange());
  String changePercentText = changeFormat.format(price.getChangePercent());

  // update the watch list with the new values
  stocksFlexTable.setText(row, 1, priceText);
  stocksFlexTable.setText(row, 2, changeText + " (" + changePercentText + "%)");
}


}
