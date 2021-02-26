package com.bettercalculator.farming.ui;

import com.bettercalculator.farming.crop.Crop;
import com.bettercalculator.farming.crop.CropType;
import com.bettercalculator.farming.crops.CropProvider;
import com.bettercalculator.ui.component.CheckBox;
import com.bettercalculator.ui.component.EnumComboBox;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lombok.Getter;
import net.runelite.client.util.Text;

@Getter
public class CropSectionUI<T extends Enum<T> & CropProvider> extends JPanel
{
	private final CropType cropType;
	private final EnumComboBox<T> dropDownMenu;
	private final CheckBox harvestCheckbox;

	public CropSectionUI(CropType type, Class<T> cropProviderClass)
	{
		super();
		setLayout(new BorderLayout(5, 3));
		JPanel dropDownPanel = new JPanel();
		this.dropDownMenu = new EnumComboBox<>(cropProviderClass);
		dropDownPanel.add(dropDownMenu );
		this.cropType = type;

		harvestCheckbox = new CheckBox("Harvest");
		JLabel label = new JLabel(Text.titleCase(cropType));
		add(label, BorderLayout.NORTH);
		add(dropDownPanel, BorderLayout.WEST);
		add(harvestCheckbox, BorderLayout.EAST);
	}

	public T getSelectedItem()
	{
		return dropDownMenu.getSelectedItem();
	}

	public Crop getSelectedCrop()
	{
		T crop = getSelectedItem();
		return crop != null ? crop.getCrop() : CropType.getEmptyCrop();
	}

	public double getExperience()
	{
		if (harvestCheckbox.isSelected())
		{
			return getSelectedCrop().getCropExperience().getExperience();
		}
		return getSelectedCrop().getCropExperience().getExperience(0);
	}
}
