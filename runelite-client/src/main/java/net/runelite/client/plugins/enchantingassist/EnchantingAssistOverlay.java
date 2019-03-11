package net.runelite.client.plugins.enchantingassist;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.Client;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;

public class EnchantingAssistOverlay extends Overlay
{
	private static final int ITEM_EMPTY = 6512;
	private static final int ITEM_FILLED = 20594;
	private static final Color CYAN = new Color(0, 184, 212);
	private final Client client;
	private final EnchantingAssistConfig config;
	private final PanelComponent panelComponent = new PanelComponent();
	private static final Color GREEN = new Color(0, 200, 83);
	private static final Color ORANGE = new Color(255, 109, 0);
	private static final Color YELLOW = new Color(255, 214, 0);

	@Setter
	@Getter
	private Widget widget;

	@Setter
	private int itemIndex = -1;

	@Inject
	private EnchantingAssistOverlay(Client client, EnchantingAssistConfig config)
	{
		setPosition(OverlayPosition.DYNAMIC);
		this.client = client;
		this.config = config;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		panelComponent.getChildren().clear();

		if (config.booleanConfig())
		{
			renderInvBox(graphics);
		}
		renderWidgets(graphics);

		return null;
	}

	private void renderInvBox(Graphics2D graphics)
	{

		Widget inventoryWidget = client.getWidget(WidgetInfo.INVENTORY);
		if (inventoryWidget == null || inventoryWidget.isHidden())
		{
			return;
		}

		int i = 0;
		for (WidgetItem item : inventoryWidget.getWidgetItems())
		{
			Rectangle slotBounds = item.getCanvasBounds();

			String idText = "" + item.getId();
			FontMetrics fm = graphics.getFontMetrics();
			Rectangle2D textBounds = fm.getStringBounds(idText, graphics);

			int textX = (int) (slotBounds.getX() + (slotBounds.getWidth() / 2) - (textBounds.getWidth() / 2));
			int textY = (int) (slotBounds.getY() + (slotBounds.getHeight() / 2) + (textBounds.getHeight() / 2));

			graphics.setColor(new Color(255, 255, 255, 65));
			graphics.fill(slotBounds);

			graphics.setColor(Color.BLACK);
			graphics.drawString(idText, textX + 1, textY + 1);
			graphics.setColor(GREEN);
			graphics.drawString(idText, textX, textY);
		}
	}

	private void renderWidgets(Graphics2D graphics)
	{
		if (widget == null || widget.isHidden())
		{
			return;
		}

		Rectangle childBounds = widget.getBounds();
		graphics.setColor(CYAN);
		graphics.draw(childBounds);

		if (itemIndex == -1)
		{
			return;
		}

		if (widget.getItemId() != ITEM_EMPTY
			&& widget.getItemId() != ITEM_FILLED)
		{
			Rectangle componentBounds = widget.getBounds();

			graphics.setColor(ORANGE);
			graphics.draw(componentBounds);

			renderWidgetText(graphics, componentBounds, widget.getItemId(), YELLOW);
		}

		WidgetItem widgetItem = widget.getWidgetItem(itemIndex);
		if (widgetItem == null
			|| widgetItem.getId() == ITEM_EMPTY
			|| widgetItem.getId() == ITEM_FILLED)
		{
			return;
		}

		Rectangle itemBounds = widgetItem.getCanvasBounds();

		graphics.setColor(ORANGE);
		graphics.draw(itemBounds);

		renderWidgetText(graphics, itemBounds, widgetItem.getId(), YELLOW);
	}

	private void renderWidgetText(Graphics2D graphics, Rectangle bounds, int itemId, Color color)
	{
		if (itemId == -1)
		{
			return;
		}

		String text = itemId + "";
		FontMetrics fm = graphics.getFontMetrics();
		Rectangle2D textBounds = fm.getStringBounds(text, graphics);

		int textX = (int) (bounds.getX() + (bounds.getWidth() / 2) - (textBounds.getWidth() / 2));
		int textY = (int) (bounds.getY() + (bounds.getHeight() / 2) + (textBounds.getHeight() / 2));

		graphics.setColor(Color.BLACK);
		graphics.drawString(text, textX + 1, textY + 1);
		graphics.setColor(color);
		graphics.drawString(text, textX, textY);
	}

}
