package com.github.andreytondo.chess.ui.images;

import java.awt.*;
import java.awt.image.ImageObserver;

public class ImageObserverImpl implements ImageObserver {

    private static final ImageObserverImpl instance = new ImageObserverImpl();

    public static ImageObserver getInstance() {
        return instance;
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return true;
    }
}
