package org.jreact.examples.gwt.stockwatcher.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.user.client.ui.*;
import org.jreact.adapt.gwt.GwtEventAdapter;
import org.jreact.adapt.gwt.focus.FocusVariableAdapter;
import org.jreact.adapt.gwt.key.KeyCodePredicate;
import org.jreact.adapt.gwt.key.KeyDownHandlerAdder;
import org.jreact.adapt.gwt.mouse.ClickHandlerAdder;
import org.jreact.adapt.gwt.value.ValueVariableAdapter;
import org.jreact.core.Stream;
import org.jreact.core.Variable;
import org.jreact.core.impl.Reactives;

public class StockPanel
        extends Composite
        implements StockView {

    private final VerticalPanel mainPanel;
    private final FlexTable stocksFlexTable;
    private final HorizontalPanel addPanel;
    private final TextBox newSymbolTextBox;
    private final Button addButton;
    private final Label lastUpdatedLabel;
    private final Label errorMsgLabel;

    public StockPanel() {

        this(
            new VerticalPanel(),
            new FlexTable(),
            new HorizontalPanel(),
            new TextBox(),
            new Button("Add"),
            new Label(),
            new Label()
        );

    }

    public StockPanel(
            final VerticalPanel mainPanel,
            final FlexTable stocksFlexTable,
            final HorizontalPanel addPanel,
            final TextBox newSymbolTextBox,
            final Button addButton,
            final Label lastUpdatedLabel,
            final Label errorMsgLabel) {

        this.mainPanel = mainPanel;
        this.stocksFlexTable = stocksFlexTable;
        this.addPanel = addPanel;
        this.newSymbolTextBox = newSymbolTextBox;
        this.addButton = addButton;
        this.lastUpdatedLabel = lastUpdatedLabel;
        this.errorMsgLabel = errorMsgLabel;

        loaded = Reactives.variable(false);

        // set up stock list watch list
        stocksFlexTable.setText(0, 0, "Symbol");
        stocksFlexTable.setText(0, 1, "Price");
        stocksFlexTable.setText(0, 2, "Change");
        stocksFlexTable.setText(0, 3, "Remove");

        // assemble Add Stock panel
        addPanel.add(newSymbolTextBox);
        addPanel.add(addButton);
        addPanel.addStyleName("addPanel");

        // assemble main panel
        errorMsgLabel.setStyleName("errorMessage");
        errorMsgLabel.setVisible(false);
        mainPanel.add(errorMsgLabel);
        mainPanel.add(stocksFlexTable);
        mainPanel.add(addPanel);
        mainPanel.add(lastUpdatedLabel);

        addButtonClick = new GwtEventAdapter<ClickEvent>(
            loaded,
            new ClickHandlerAdder(addButton)
        );

        newSymbolEnterKey = new GwtEventAdapter<KeyDownEvent>(
            loaded,
            new KeyDownHandlerAdder(newSymbolTextBox)
        ).filter(
            new KeyCodePredicate(KeyCodes.KEY_ENTER)
        );

        newSymbolText = new ValueVariableAdapter<String>(
            loaded,
            newSymbolTextBox
        );

        newSymbolFocus = new FocusVariableAdapter(
            loaded,
            newSymbolTextBox
        );

        new StockPresenter(this);

    }

    private final Stream<?> addButtonClick;
    @Override public Stream<?> addButtonClick()
    { return addButtonClick; }

    private final Stream<?> newSymbolEnterKey;
    @Override public Stream<?> newSymbolEnterKey()
    { return newSymbolEnterKey; }

    private final Variable<String> newSymbolText;
    @Override public Variable<String> newSymbolText()
    { return newSymbolText; }

    private final Variable<Boolean> newSymbolFocus;
    @Override public Variable<Boolean> newSymbolFocus()
    { return newSymbolFocus; }

    private final Variable<Boolean> loaded;
    @Override protected void onLoad()
    { super.onLoad(); loaded.put(true); }
    @Override protected void onUnload()
    { super.onUnload(); loaded.put(false); }

}
