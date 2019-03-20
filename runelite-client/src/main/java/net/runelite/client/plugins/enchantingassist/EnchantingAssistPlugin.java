package net.runelite.client.plugins.enchantingassist;

//import com.google.common.eventbus.Subscribe;
import static net.runelite.api.widgets.WidgetID.SPELLBOOK_GROUP_ID;
import static net.runelite.api.widgets.WidgetInfo.TO_GROUP;
import net.runelite.client.eventbus.Subscribe;
import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
//import net.runelite.api.widgets.WidgetInfo;
//import net.runelite.api.events.ConfigChanged;
//import net.runelite.api.events.GameStateChanged;
//import net.runelite.api.events.VarbitChanged;
import net.runelite.api.events.WidgetHiddenChanged;
//import net.runelite.api.events.WidgetLoaded;
//import net.runelite.api.widgets.Widget;
//import static net.runelite.api.widgets.WidgetID.RESIZABLE_VIEWPORT_OLD_SCHOOL_BOX_GROUP_ID;

@PluginDescriptor(
	name = "Enchanting assist",
	description = "Hihlights enchantable jewlery",
	tags = {"jewlery", "enchanting", "enchant", "magic", "crafting", "craft"}
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

	@Subscribe
	public void onWidgetHiddenChanged(WidgetHiddenChanged event)
	{
		//System.out.println(event);
		//for (int i = 0; i < event.getWidget().getChildren().length;i++)
		//{
		//	System.out.println(event.getWidget().getChild(i).getText());
		//}
		//System.out.println(event.getWidget().getId());
		if (TO_GROUP(event.getWidget().getId()) == SPELLBOOK_GROUP_ID)
		{
			System.out.println("Got 'em!");
			System.out.println(event.getWidget());
			System.out.println(event.getWidget().getChildren());

		}
	}


	@Subscribe
	public void onWidgetLoaded(WidgetLoaded event)
	{
		//if (event.getGroupId())
		//System.out.println(event);
		//System.out.println(event.getGroupId());
		//System.out.println(event.getClass().getName());
		if (event.getGroupId() == SPELLBOOK_GROUP_ID)
		{
			System.out.println("Found it!");
		}
		System.out.println(event);
	}



}
