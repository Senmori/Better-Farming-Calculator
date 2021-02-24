package com.bettercalculator.ui.component;

import java.util.function.Consumer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@FunctionalInterface
public interface SimpleDocumentListener extends Consumer<DocumentEvent>, DocumentListener
{
	void accept(DocumentEvent e);

	@Override
	default void insertUpdate(DocumentEvent e) {
		accept(e);
	}

	@Override
	default void removeUpdate(DocumentEvent e) {
		accept(e);
	}

	@Override
	default void changedUpdate(DocumentEvent e) {
		accept(e);
	}
}