package com.bettercalculator.ui.component;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaxLengthFilter extends DocumentFilter
{
	private final int maxLength;

	public MaxLengthFilter(int maxLength)
	{
		this.maxLength = maxLength;
	}

	@Override
	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException
	{
		if (fb.getDocument().getLength() + string.length() <= maxLength && isNumber(string))
		{
			fb.insertString(offset, string, attr);
		}
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException
	{
		if ( (fb.getDocument().getLength() + text.length()) - length <= maxLength && isNumber(text))
		{
			fb.replace(offset, length, text, attrs);
		}
	}

	private boolean isNumber(String text)
	{
		try
		{
			Integer.parseInt(text);
			return true;
		} catch (NumberFormatException e)
		{
			return false;
		}
	}
}
