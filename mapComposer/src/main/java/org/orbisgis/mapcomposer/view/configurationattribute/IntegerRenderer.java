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

package org.orbisgis.mapcomposer.view.configurationattribute;

import org.orbisgis.mapcomposer.model.configurationattribute.interfaces.ConfigurationAttribute;
import org.orbisgis.mapcomposer.model.configurationattribute.attribute.IntegerCA;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.beans.EventHandler;
import javax.swing.*;
import javax.swing.event.ChangeListener;

/**
 * Renderer associated to the IntegerCA ConfigurationAttribute.
 * The JComponent returned by the createJComponentFromCA method look like :
 *   ____________________________
 *  |integer value           | ^ |
 *  |________________________|_v_|
 *
 * @see org.orbisgis.mapcomposer.model.configurationattribute.attribute.IntegerCA
 *
 * @author Sylvain PALOMINOS
 */
public class IntegerRenderer implements CARenderer{

    @Override
    public JComponent createJComponentFromCA(ConfigurationAttribute ca) {
        IntegerCA integerCA = (IntegerCA)ca;

        SpinnerModel model;
        //Display the IntegerCA into a JSpinner
        if(integerCA.getLimits())
            model =new SpinnerNumberModel((int)integerCA.getValue(), integerCA.getMin(), integerCA.getMax(), 1);
        else
            model =new SpinnerNumberModel((int)integerCA.getValue(), Integer.MIN_VALUE, Integer.MAX_VALUE, 1);
        JSpinner spinner = new JSpinner(model);
        spinner.addChangeListener(EventHandler.create(ChangeListener.class, integerCA, "setValue", "source.value"));
        spinner.addMouseWheelListener(EventHandler.create(MouseWheelListener.class, this, "mouseWheel", ""));

        return spinner;
    }

    /**
     * Action done when the mouse wheel is used in the JComboBox of the rendered IntegerCA.
     * @param mwe MouseWheelEvent
     */
    public void mouseWheel(MouseWheelEvent mwe){
        JSpinner source = (JSpinner) mwe.getSource();
        SpinnerNumberModel spinMod = (SpinnerNumberModel)source.getModel();
        int value = (int)source.getValue()-mwe.getWheelRotation();
        if(value<=(int)spinMod.getMaximum() && value>=(int)spinMod.getMinimum())
            source.setValue(value);
    }
}
