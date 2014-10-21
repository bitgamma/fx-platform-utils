/*
 * Copyright (C) 2014 Michele Balistreri
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package com.briksoftware.javafx.platform.osx;

import java.io.File;

import com.sun.glass.ui.Application;

public final class OSXIntegration {
	private static OSXEventHandler osxHandler;
	
	public static class OSXEventHandler extends com.sun.glass.ui.Application.EventHandler {
		private com.sun.glass.ui.Application.EventHandler handler;
		private OSXOpenFilesHandler openFilesHandler;
		
		public OSXEventHandler(com.sun.glass.ui.Application lowLevelApp) {
			handler = lowLevelApp.getEventHandler();
		}
		
		public void setOSXOpenFilesHandler(OSXOpenFilesHandler openFilesHandler) {
			this.openFilesHandler = openFilesHandler;
		}

		@Override
		public void handleWillFinishLaunchingAction(Application app, long time) {
			if (handler != null) {
				handler.handleWillFinishLaunchingAction(app, time);
			} else {
				super.handleWillFinishLaunchingAction(app, time);
			}
		}

		@Override
		public void handleDidFinishLaunchingAction(Application app, long time) {
			if (handler != null) {
				handler.handleDidFinishLaunchingAction(app, time);
			} else {
				super.handleDidFinishLaunchingAction(app, time);
			}
		}

		@Override
		public void handleWillBecomeActiveAction(Application app, long time) {
			if (handler != null) {
				handler.handleWillBecomeActiveAction(app, time);
			} else {
				super.handleWillBecomeActiveAction(app, time);
			}
		}

		@Override
		public void handleDidBecomeActiveAction(Application app, long time) {
			if (handler != null) {
				handler.handleDidBecomeActiveAction(app, time);
			} else {
				super.handleDidBecomeActiveAction(app, time);
			}
		}

		@Override
		public void handleWillResignActiveAction(Application app, long time) {
			if (handler != null) {
				handler.handleWillResignActiveAction(app, time);
			} else {
				super.handleWillResignActiveAction(app, time);
			}
		}

		@Override
		public void handleDidResignActiveAction(Application app, long time) {
			if (handler != null) {
				handler.handleDidResignActiveAction(app, time);
			} else {
				super.handleDidResignActiveAction(app, time);
			}
		}

		@Override
		public void handleDidReceiveMemoryWarning(Application app, long time) {
			if (handler != null) {
				handler.handleDidReceiveMemoryWarning(app, time);
			} else {
				super.handleDidReceiveMemoryWarning(app, time);
			}
		}

		@Override
		public void handleWillHideAction(Application app, long time) {
			if (handler != null) {
				handler.handleWillHideAction(app, time);
			} else {
				super.handleWillHideAction(app, time);
			}
		}

		@Override
		public void handleDidHideAction(Application app, long time) {
			if (handler != null) {
				handler.handleDidHideAction(app, time);
			} else {
				super.handleDidHideAction(app, time);
			}
		}

		@Override
		public void handleWillUnhideAction(Application app, long time) {
			if (handler != null) {
				handler.handleWillUnhideAction(app, time);
			} else {
				super.handleWillUnhideAction(app, time);
			}
		}

		@Override
		public void handleDidUnhideAction(Application app, long time) {
			if (handler != null) {
				handler.handleDidUnhideAction(app, time);
			} else {
				super.handleDidUnhideAction(app, time);
			}
		}

		@Override
		public void handleOpenFilesAction(Application app, long time, String[] files) {	
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
		public void handleQuitAction(Application app, long time) {
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
	
	public final static void init() {
		String currentPlatform = System.getProperty("os.name").toLowerCase();

		if (currentPlatform.startsWith("mac") && osxHandler == null) {
			com.sun.glass.ui.Application lowLevelApp = com.sun.glass.ui.Application.GetApplication();
			osxHandler = new OSXEventHandler(lowLevelApp);
			lowLevelApp.setEventHandler(osxHandler);
		}
	}
	
	public final static void setOpenFilesHandler(OSXOpenFilesHandler openFilesHandler) {
		osxHandler.setOSXOpenFilesHandler(openFilesHandler);
	}
}
