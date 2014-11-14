fx-platform-utils
=================

This is  a collection of classes used to improve system integration for JavaFX applications. At the moment, it only provides special support for OS X and in particular to alter the application or "apple" menu.

The current API is pretty easy to use:

```java
OSXIntegration.init();
```

initializes the library. You do not need to check if the program is actually running on OSX, this will be done automatically.

to populate the application menu and provide handlers for the "About" and "Preferences" menu just invoke

```java
OSXIntegration.populateAppleMenu(Runnable aboutCallback, Runnable preferencesCallback);
```

The runnable will be called when the corresponding menus are clicked. This method returns a boolean, telling you if this succeeded. If this succeeded you can then remove (or avoid inserting) the about and preferences menu in other locations. This method succeeds if you are on OSX and OSXIntegration.init() has been invoked before.

Additionally, if you have an application handling documents, you can invoke

```java
OSXIntegration.setOpenFilesHandler(OSXOpenFilesHandler openFilesHandler);
```

This will allow you handling files dragged on the Dock (you need to package your custom Info.plist file with the app too, of course). The OSXOpenFileHandler interface defines a single method, which you need to implement:

```java
public void handleOpenFilesEvent(File[] files);
```

So basically you get an array of file to process.

These classes are only tested with JavaFX 8, older (or newer) versions might not work or might need adjustments.
