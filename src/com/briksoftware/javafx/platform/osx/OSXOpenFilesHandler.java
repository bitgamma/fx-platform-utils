package com.briksoftware.javafx.platform.osx;

import java.io.File;

@FunctionalInterface
public interface OSXOpenFilesHandler {
	public void handleOpenFilesEvent(File[] files);
}
