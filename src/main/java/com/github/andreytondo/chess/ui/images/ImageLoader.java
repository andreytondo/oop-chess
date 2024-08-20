package com.github.andreytondo.chess.ui.images;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageLoader {

    private static final Logger LOGGER = Logger.getLogger(ImageLoader.class.getName());

    public static Image load(String filePath) {
        try {
            return fromSVG(filePath);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException("Error loading image from file", e);
        }
    }

    public static Image fromSVG(String path) throws IOException {
        FileInputStream svgFileStream = new FileInputStream(path);
        TranscoderInput inputSvgImage = new TranscoderInput(svgFileStream);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        TranscoderOutput outputPngImage = new TranscoderOutput(pngOutputStream);

        PNGTranscoder transcoder = new PNGTranscoder();

        try {
            transcoder.transcode(inputSvgImage, outputPngImage);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error converting SVG to PNG: " + e.getMessage(), e);
            throw new IOException("Error converting SVG to PNG", e);
        } finally {
            svgFileStream.close();
            pngOutputStream.close();
        }

        ByteArrayInputStream pngInputStream = new ByteArrayInputStream(pngOutputStream.toByteArray());
        return ImageIO.read(pngInputStream);
    }

}
