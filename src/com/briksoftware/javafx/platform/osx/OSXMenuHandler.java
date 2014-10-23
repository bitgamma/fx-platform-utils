package com.briksoftware.javafx.platform.osx;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.glass.ui.Menu;
import com.sun.glass.ui.MenuItem;

public class OSXMenuHandler {
	private com.sun.glass.ui.Application app;
	private ResourceBundle i18n;

	public OSXMenuHandler(com.sun.glass.ui.Application app) {
		this.app = app;
		this.i18n = ResourceBundle.getBundle("com.briksoftware.javafx.platform.osx.i18n.MainMenu");
	}

	public void populateAppleMenu(Runnable aboutCallback, Runnable preferencesCallback) {
		com.sun.glass.ui.Menu appleMenu = getAppleMenu();

		ArrayList<com.sun.glass.ui.MenuItem> savedMenus = new ArrayList<>();
		List<Object> oldMenus = appleMenu.getItems();
		
		for (int i = oldMenus.size(); i > 0; i--) {
			savedMenus.add((com.sun.glass.ui.MenuItem) oldMenus.get(0));
			appleMenu.remove(0);
		}
				
		appleMenu.add(createAboutMenuItem(aboutCallback));
		appleMenu.add(com.sun.glass.ui.MenuItem.Separator);
		
		appleMenu.add(createPreferencesMenuItem(preferencesCallback));
		appleMenu.add(com.sun.glass.ui.MenuItem.Separator);

		for(com.sun.glass.ui.MenuItem item : savedMenus) {
			if (item != com.sun.glass.ui.MenuItem.Separator) {
				adjustTitle(item);
			}
			
			appleMenu.add(item);
		}
	}
	
	private Menu getAppleMenu() {
		try {
			Method getAppleMenu = app.getClass().getMethod("getAppleMenu");
			getAppleMenu.setAccessible(true);
			return (Menu) getAppleMenu.invoke(app);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private com.sun.glass.ui.MenuItem createAboutMenuItem(Runnable aboutCallback) {
		return app.createMenuItem(String.format(i18n.getString("menu.apple.about"), app.getName()), new com.sun.glass.ui.MenuItem.Callback() {
			@Override
			public void action() {
				aboutCallback.run();
			}

			@Override
			public void validate() {
			}
		});
	}
	
	private com.sun.glass.ui.MenuItem createPreferencesMenuItem(Runnable preferencesCallback) {
		return app.createMenuItem(i18n.getString("menu.apple.preferences"), new com.sun.glass.ui.MenuItem.Callback() {
			@Override
			public void action() {
				preferencesCallback.run();
			}

			@Override
			public void validate() {
			}
		}, ',', com.sun.glass.events.KeyEvent.MODIFIER_COMMAND);
	}
	
	private void adjustTitle(MenuItem item) {
		if (item.getTitle().equals("Hide Others")) {
			item.setTitle(i18n.getString("menu.apple.hideothers"));
		} else if (item.getTitle().startsWith("Hide ")) {
			item.setTitle(String.format(i18n.getString("menu.apple.hide"), app.getName()));			
		} else if (item.getTitle().equals("Show All")) {
			item.setTitle(i18n.getString("menu.apple.showall"));			
		}  else if (item.getTitle().startsWith("Quit ")) {
			item.setTitle(String.format(i18n.getString("menu.apple.quit"), app.getName()));			
		}
	}
}
