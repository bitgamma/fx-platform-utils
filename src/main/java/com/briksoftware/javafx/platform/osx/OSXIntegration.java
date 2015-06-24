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


public final class OSXIntegration {
	private static OSXEventHandler osxHandler;
	private static OSXMenuHandler osxMenuHandler;
			
	public static void init() {
		String currentPlatform = System.getProperty("os.name").toLowerCase();

		if (currentPlatform.startsWith("mac") && osxHandler == null) {
			com.sun.glass.ui.Application lowLevelApp = com.sun.glass.ui.Application.GetApplication();
			osxHandler = new OSXEventHandler(lowLevelApp);
			osxMenuHandler = new OSXMenuHandler(lowLevelApp);
		}
	}
	
	public static void installHandlers() {
		com.sun.glass.ui.Application.GetApplication().setEventHandler(osxHandler);
	}
	
	public static boolean setOpenFilesHandler(OSXOpenFilesHandler openFilesHandler) {
		if (osxHandler != null) {
			osxHandler.setOSXOpenFilesHandler(openFilesHandler);
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean populateAppleMenu(Runnable aboutCallback, Runnable preferencesCallback) {
		if (osxMenuHandler != null) {
			osxMenuHandler.populateAppleMenu(aboutCallback, preferencesCallback);
			return true;
		} else {
			return false;
		}
	}
}
