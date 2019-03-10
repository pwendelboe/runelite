package net.runelite.client.plugins.enchantingassist;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("enchantingAssist")
public interface EnchantingAssistConfig extends Config
{
	@ConfigItem(
		position = 1,
		keyName = "boolOn",
		name = "Plugin active",
		description = "Highlights jewlery that you can enchant in your invewntory."
	)
	default boolean booleanConfig(){return true;}
}
