/*
* MapComposer is an OrbisGIS plugin dedicated to the creation of cartographic
* documents.
*
* This plugin was firstly developed  at French IRSTV institute as part of the MApUCE project,
* funded by the French Agence Nationale de la Recherche (ANR) under contract ANR-13-VBDU-0004.
* 
* Since 2015, MapComposer is developed and maintened by the GIS group of the DECIDE team of the 
* Lab-STICC CNRS laboratory, see <http://www.lab-sticc.fr/>.
*
* The GIS group of the DECIDE team is located at :
*
* Laboratoire Lab-STICC – CNRS UMR 6285
* Equipe DECIDE
* UNIVERSITÉ DE BRETAGNE-SUD
* Institut Universitaire de Technologie de Vannes
* 8, Rue Montaigne - BP 561 56017 Vannes Cedex
*
* Copyright (C) 2007-2014 CNRS (IRSTV FR CNRS 2488)
* Copyright (C) 2015-2017 CNRS (Lab-STICC UMR CNRS 6285)
*
* The MapComposer plugin is distributed under GPL 3 license. 
* This file is part of the MapComposer plugin.
*
* The MapComposer plugin is free software: you can redistribute it and/or modify it under the
* terms of the GNU General Public License as published by the Free Software
* Foundation, either version 3 of the License, or (at your option) any later
* version.
*
* The MapComposer plugin is distributed in the hope that it will be useful, but WITHOUT ANY
* WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
* A PARTICULAR PURPOSE. See the GNU General Public License for more details <http://www.gnu.org/licenses/>.
*/

package org.orbisgis.mapcomposer.view.graphicalelement;

import org.orbisgis.commons.progress.ProgressMonitor;
import org.orbisgis.mapcomposer.model.graphicalelement.interfaces.GraphicalElement;
import org.orbisgis.mapcomposer.model.graphicalelement.element.cartographic.Orientation;
import org.orbisgis.mapcomposer.view.utils.MapComposerIcon;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Renderer associated to the Orientation GraphicalElement.
 *
 * @author Sylvain PALOMINOS
 */
public class OrientationRenderer implements RendererRaster {

    @Override
    public BufferedImage createGEImage(GraphicalElement ge, ProgressMonitor pm) {

        double rad = Math.toRadians(ge.getRotation());
        double newHeight = Math.abs(sin(rad) * ge.getWidth()) + Math.abs(cos(rad) * ge.getHeight());
        double newWidth = Math.abs(sin(rad) * ge.getHeight()) + Math.abs(cos(rad) * ge.getWidth());

        //Gets the maximum size (bounding box) of the rotated GE
        int maxWidth = Math.max((int) newWidth, ge.getWidth());
        int maxHeight = Math.max((int) newHeight, ge.getHeight());

        //Get the center of the graphical representation according to the GE dimension and rotation
        int x = maxWidth / 2;
        int y = maxHeight / 2;

        BufferedImage bi = new BufferedImage(maxWidth, maxHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = bi.createGraphics();

        //Get the image to draw
        File file = new File(((Orientation) ge).getIconPath());
        ImageIcon icon;
        if (file.exists() && file.isFile())
            icon = new ImageIcon(((Orientation) ge).getIconPath());
        else
            icon = MapComposerIcon.getIcon("compass");

        graphics2D.rotate(rad, x, y);

        //Scale the icon to the GraphicalElement size
        graphics2D.drawImage(icon.getImage(), x - ge.getWidth() / 2, y - ge.getHeight() / 2,
                x + ge.getWidth() / 2, y + ge.getHeight() / 2,
                0, 0,
                icon.getIconWidth(), icon.getIconHeight(), null);

        graphics2D.dispose();

        return bi;
    }
}
