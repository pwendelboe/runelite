package net.runelite.client.plugins.enchantingassist;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;

public class EnchantingAssistOverlay extends Overlay
{
	private final Client client;
	private final EnchantingAssistConfig config;
	private final PanelComponent panelComponent = new PanelComponent();

	@Inject
	private EnchantingAssistOverlay(Client client, EnchantingAssistConfig config)
	{
		setPosition(OverlayPosition.TOP_CENTER);
		this.client = client;
		this.config = config;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		panelComponent.getChildren().clear();

		if (config.booleanConfig())
		{
			String testString = "test";

			panelComponent.getChildren().add(LineComponent.builder().left("Type:").right(testString).build());
		}

		return panelComponent.render(graphics);
	}
}
