package com.bettercalculator.ui.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.Comparator;
import java.util.stream.Stream;
import javax.swing.JComboBox;

public class EnumComboBox<T extends Enum<T>> extends JComboBox<Enum<T>>
{

	public EnumComboBox(Class<T> enumClass)
	{
		this(enumClass.getEnumConstants());
	}

	public EnumComboBox(T[] options)
	{
		super(options);
		setRenderer(new DropdownRenderer());
		setForeground(Color.WHITE);
		setFocusable(false);
		setEditable(false);
	}

	@Override
	public T getSelectedItem()
	{
		Object obj =  super.getSelectedItem();
		if (obj == null)
		{
			obj = getItemAt(0);
			return (T) obj;
		}
		return (T) obj;
	}
}
