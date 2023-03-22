package docrob.cag.framework.screens;

import docrob.cag.framework.menu.FlowAction;

import java.util.HashMap;

public class ScreenBuilder {
    // we can save screens in a cache to reuse them later
    private static final HashMap<String, Screen> screenCache = new HashMap<>();

    public static FlowAction makeFlowActionForScreen(Screen screen) {
        Screen nextScreen = getCachedScreen(screen);
        return () -> {
            ScreenManager.setNextScreen(nextScreen);
        };
    }

    public static Screen getCachedScreen(Screen screen) {
        String screenClassName = screen.getClass().getSimpleName();
        if(!screenCache.containsKey(screenClassName) || screen instanceof NotCacheable) {
            if(!(screen instanceof NotCacheable)) {
                screenCache.put(screenClassName, screen);
            }
            screen.setupMenu();
//        } else {
//            System.out.println("Found screen in cache: " + screenClassName);
//            System.out.println(screenCache.get(screenClassName).getMenu().getChoices().size());
////            System.out.println(screen.get);
        }
        return screenCache.get(screenClassName);
    }
}
