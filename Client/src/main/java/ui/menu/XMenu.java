
package ui.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.border.Border;

import ui.XContorlUtil;

public class XMenu extends JMenu {
	private Color backgroundColor;
	private Color foregroundColor;
	private int borderThickness;
	private Border border;
	private int preferredHeight;

	public XMenu() {
		backgroundColor = XContorlUtil.MENUITEM_BACKGROUND;
		foregroundColor = XContorlUtil.DEFAULT_OUTLOOK_TEXT_COLOR;
		borderThickness = 1;
		border = BorderFactory.createLineBorder(backgroundColor, borderThickness);
		preferredHeight = 25;
		init();
	}

	public XMenu(String text) {
		super(text);
		backgroundColor = XContorlUtil.MENUITEM_BACKGROUND;
		foregroundColor = XContorlUtil.DEFAULT_OUTLOOK_TEXT_COLOR;
		borderThickness = 1;
		border = BorderFactory.createLineBorder(backgroundColor, borderThickness);
		preferredHeight = 25;
		init();
	}

	private void init() {
		setForeground(foregroundColor);
		setFont(XContorlUtil.FONT_14_BOLD);
		setOpaque(true);
		setBackground(backgroundColor);
		setBorder(border);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (isSelected()) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(XContorlUtil.MENUITEM_BACKGROUND);
			g2d.fillRect(0, 0, getWidth(), getHeight());
			super.paintComponent(g);
		} else {
			super.paintComponent(g);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(super.getPreferredSize().width, preferredHeight);
	}
}
