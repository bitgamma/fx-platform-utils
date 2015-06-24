package com.briksoftware.javafx.platform.osx;

import java.io.File;

public class OSXEventHandler extends com.sun.glass.ui.Application.EventHandler {
	private final com.sun.glass.ui.Application.EventHandler handler;
	private OSXOpenFilesHandler openFilesHandler;
	
	public OSXEventHandler(com.sun.glass.ui.Application lowLevelApp) {
		handler = lowLevelApp.getEventHandler();
	}
	
	public void setOSXOpenFilesHandler(OSXOpenFilesHandler openFilesHandler) {
		this.openFilesHandler = openFilesHandler;
	}

	@Override
	public void handleWillFinishLaunchingAction(com.sun.glass.ui.Application app, long time) {
		if (handler != null) {
			handler.handleWillFinishLaunchingAction(app, time);
		} else {
			super.handleWillFinishLaunchingAction(app, time);
		}
	}

	@Override
	public void handleDidFinishLaunchingAction(com.sun.glass.ui.Application app, long time) {
		if (handler != null) {
			handler.handleDidFinishLaunchingAction(app, time);
		} else {
			super.handleDidFinishLaunchingAction(app, time);
		}
	}

	@Override
	public void handleWillBecomeActiveAction(com.sun.glass.ui.Application app, long time) {
		if (handler != null) {
			handler.handleWillBecomeActiveAction(app, time);
		} else {
			super.handleWillBecomeActiveAction(app, time);
		}
	}

	@Override
	public void handleDidBecomeActiveAction(com.sun.glass.ui.Application app, long time) {
		if (handler != null) {
			handler.handleDidBecomeActiveAction(app, time);
		} else {
			super.handleDidBecomeActiveAction(app, time);
		}
	}

	@Override
	public void handleWillResignActiveAction(com.sun.glass.ui.Application app, long time) {
		if (handler != null) {
			handler.handleWillResignActiveAction(app, time);
		} else {
			super.handleWillResignActiveAction(app, time);
		}
	}

	@Override
	public void handleDidResignActiveAction(com.sun.glass.ui.Application app, long time) {
		if (handler != null) {
			handler.handleDidResignActiveAction(app, time);
		} else {
			super.handleDidResignActiveAction(app, time);
		}
	}

	@Override
	public void handleDidReceiveMemoryWarning(com.sun.glass.ui.Application app, long time) {
		if (handler != null) {
			handler.handleDidReceiveMemoryWarning(app, time);
		} else {
			super.handleDidReceiveMemoryWarning(app, time);
		}
	}

	@Override
	public void handleWillHideAction(com.sun.glass.ui.Application app, long time) {
		if (handler != null) {
			handler.handleWillHideAction(app, time);
		} else {
			super.handleWillHideAction(app, time);
		}
	}

	@Override
	public void handleDidHideAction(com.sun.glass.ui.Application app, long time) {
		if (handler != null) {
			handler.handleDidHideAction(app, time);
		} else {
			super.handleDidHideAction(app, time);
		}
	}

	@Override
	public void handleWillUnhideAction(com.sun.glass.ui.Application app, long time) {
		if (handler != null) {
			handler.handleWillUnhideAction(app, time);
		} else {
			super.handleWillUnhideAction(app, time);
		}
	}

	@Override
	public void handleDidUnhideAction(com.sun.glass.ui.Application app, long time) {
		if (handler != null) {
			handler.handleDidUnhideAction(app, time);
		} else {
			super.handleDidUnhideAction(app, time);
		}
	}

	@Override
	public void handleOpenFilesAction(com.sun.glass.ui.Application app, long time, String[] files) {	
		if (openFilesHandler != null) {
			File[] fileObjects = new File[files.length];
			
			for (int i = 0; i < files.length; i++) {
				fileObjects[i] = new File(files[i]);
			}
			
			openFilesHandler.handleOpenFilesEvent(fileObjects);
		} else if (handler != null) {
			handler.handleOpenFilesAction(app, time, files);
		} else {
			super.handleOpenFilesAction(app, time, files);
		}
	}

	@Override
	public void handleQuitAction(com.sun.glass.ui.Application app, long time) {
		if (handler != null) {
			handler.handleQuitAction(app, time);
		} else {
			super.handleQuitAction(app, time);
		}
	}

	@Override
	public boolean handleThemeChanged(String themeName) {
		if (handler != null) {
			return handler.handleThemeChanged(themeName);
		} else {
			return super.handleThemeChanged(themeName);
		}
	}
}
