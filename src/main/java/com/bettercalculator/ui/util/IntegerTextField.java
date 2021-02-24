package com.bettercalculator.ui.util;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.function.Consumer;
import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import lombok.Getter;
import net.runelite.client.ui.ColorScheme;

public class IntegerTextField extends JFormattedTextField
{
	@Getter
	private Color backgroundColor = ColorScheme.DARKER_GRAY_COLOR;

	@Getter
	private Color hoverBackgroundColor = ColorScheme.DARKER_GRAY_HOVER_COLOR;

	@Getter
	private final InputType inputType;

	public IntegerTextField(InputType inputType)
	{
		this.inputType = inputType;
		setSelectedTextColor(Color.WHITE);
		setSelectionColor(ColorScheme.BRAND_ORANGE_TRANSPARENT);

		int maxDigits = inputType.getMaxDigits();
		NumberFormat intFormat = NumberFormat.getIntegerInstance();
		intFormat.setMaximumIntegerDigits(maxDigits);

		NumberFormatter displayFormatter = new NumberFormatter(new DecimalFormat("#,###"));
		NumberFormatter numberFormatter = new NumberFormatter(intFormat);
		numberFormatter.setValueClass(Integer.class);
		numberFormatter.setMinimum(inputType.getMinValue());
		numberFormatter.setMaximum(inputType.getMaxValue());
		DefaultFormatterFactory factory = new DefaultFormatterFactory(numberFormatter, displayFormatter);
		setFormatterFactory(factory);

		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseEntered(MouseEvent e)
			{
				if (hoverBackgroundColor != null)
				{
					setBackground(hoverBackgroundColor, false);
				}
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				setBackground(backgroundColor);
			}
		});

		addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				setText(String.valueOf(getCurrentValue()));
				SwingUtilities.invokeLater(() -> setCaretPosition(getDocument().getLength()));
			}

			@Override
			public void focusLost(FocusEvent e)
			{
				String text = getText();
				if (isNumber(text))
				{
					parseText(text);
				}
			}
		});
	}

	private boolean isNumber(String text)
	{
		if (text == null || text.isEmpty())
		{
			return false;
		}
		try
		{
			Integer.parseInt(text);
			return true;
		}
		catch (NumberFormatException e)
		{
			return false;
		}
	}

	private void parseText(String text)
	{
		try
		{
			int value = Integer.parseInt(text);
			setValue(enforceBounds(value));
		}
		catch (NumberFormatException ignored)
		{

		}
	}

	public void addFocusListener(Consumer<FocusEvent> consumer)
	{
		addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				consumer.accept(e);
			}
		});
	}

	public int getCurrentValue()
	{
		try
		{
			Object value = getValue();
			String valueString = String.valueOf(value);
			int num = Integer.parseInt(valueString);
			return Math.min(inputType.getMaxValue(), Math.max(inputType.getMinValue(), num));
		}
		catch (NumberFormatException e)
		{
			return inputType.getMinValue();
		}
	}

	@Override
	public void setBackground(Color color)
	{
		setBackground(color, true);
	}

	public void setBackground(Color color, boolean saveColor)
	{
		if (color == null)
		{
			return;
		}

		super.setBackground(color);
		if (saveColor)
		{
			this.backgroundColor = color;
		}
	}

	public void setHoverBackgroundColor(Color color)
	{
		if (color == null)
		{
			return;
		}
		this.hoverBackgroundColor = color;
	}

	private int enforceBounds(int value)
	{
		return Math.min(inputType.getMaxValue(), Math.max(inputType.getMinValue(), value));
	}
}
