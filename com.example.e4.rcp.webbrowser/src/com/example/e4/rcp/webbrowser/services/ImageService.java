package com.example.e4.rcp.webbrowser.services;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.Bundle;

public class ImageService {

	/**
	 * creates a new image found on the projects path
	 * and resizes it to (14, 14) scale
	 * 
	 * @param parent the Composite
	 * @param path the image path inside the project
	 * @return new Image
	 */
	public static Image createImage(Composite parent, String path) {
		Bundle bundle = Platform.getBundle("com.example.e4.rcp.webbrowser");
		URL url = FileLocator.find(bundle, new Path(path), null);
		ImageDescriptor imageDesc = ImageDescriptor.createFromURL(url);
		Image image = new Image(parent.getDisplay(), 
				imageDesc.createImage().getImageData().scaledTo(14, 14));
		return image;
	}
	
	/**
	 * creates a new image descriptor from the projects path
	 * @param path the image path inside the project
	 * @return new Image descriptor
	 */
	public static ImageDescriptor createImageDescriptor(String path) {
        Bundle bundle = Platform.getBundle("com.example.e4.rcp.webbrowser");
        URL url = FileLocator.find(bundle, new Path(path), null);
        return ImageDescriptor.createFromURL(url);
    }
}
