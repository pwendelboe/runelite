package net.runelite.client.plugins.enchantingassist;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
	name = "Enchanting assist",
	description = "Hihlights enchantable jewlery",
	tags = {"jewlery","enchanting","enchant","magic","crafting","craft"}
)

public class EnchantingAssistPlugin extends Plugin
{
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private EnchantingAssistOverlay overlay;

	@Inject
	private EnchantingAssistConfig config;

	@Provides
	EnchantingAssistConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(EnchantingAssistConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}

}
