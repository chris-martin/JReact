package org.jreact.adapt.gwt.focus;

import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import org.jreact.expanded.toggle.Toggles;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class FocusToggleTest {

    private static interface FocusObject
        extends HasFocusHandlers, HasBlurHandlers { }

    private FocusObject focusObject;

    private HandlerRegistration focusRegistration;
    private HandlerRegistration blurRegistration;

    private FocusHandler focusHandler;
    private BlurHandler blurHandler;

    @BeforeMethod
    public void createMocks() {

        focusHandler = null;
        blurHandler = null;

        focusRegistration = mock(HandlerRegistration.class);
        blurRegistration = mock(HandlerRegistration.class);

        focusObject = mock(FocusObject.class);
        when(focusObject.addFocusHandler(any(FocusHandler.class))).thenAnswer(
            new Answer<HandlerRegistration>() {
                @Override
                public HandlerRegistration answer(final InvocationOnMock invocationOnMock) {
                    focusHandler = (FocusHandler) invocationOnMock.getArguments()[0];
                    return focusRegistration;
                }
            }
        );
        when(focusObject.addBlurHandler(any(BlurHandler.class))).thenAnswer(
            new Answer<HandlerRegistration>() {
                @Override
                public HandlerRegistration answer(final InvocationOnMock invocationOnMock) {
                    blurHandler = (BlurHandler) invocationOnMock.getArguments()[0];
                    return blurRegistration;
                }
            }
        );

    }

    @Test
    public void trueSignalAndFireNothing() {

        final FocusToggleAdapter adapter = new FocusToggleAdapter(
            Toggles.trueSignal(),
            focusObject
        );

        verify(focusObject).addFocusHandler(focusHandler);
        verify(focusObject).addBlurHandler(blurHandler);

        verify(focusRegistration, never()).removeHandler();
        verify(blurRegistration, never()).removeHandler();

        assertEquals(adapter.get(), (Boolean) false);

    }

    @Test
    public void falseSignalAndFireNothing() {

        final FocusToggleAdapter adapter = new FocusToggleAdapter(
            Toggles.falseSignal(),
            focusObject
        );

        verify(focusObject, never()).addFocusHandler(focusHandler);
        verify(focusObject, never()).addBlurHandler(blurHandler);

        verify(focusRegistration, never()).removeHandler();
        verify(blurRegistration, never()).removeHandler();

        assertEquals(adapter.get(), (Boolean) false);

    }

    // TODO add some less trivial tests

}
