package com.mapcomposer;

import com.mapcomposer.view.ui.MainWindow;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Registers services provided by this plugin bundle.
 */
public class Activator implements BundleActivator {
        /**
         * Starting bundle, register services.
         * @param bc
         * @throws Exception
         */
        @Override
        public void start(BundleContext bc) throws Exception {
                /*
                bc.registerService(ServiceClass.class,
                        new ServiceImplementation(),
                        null);
                */
            MainWindow win = MainWindow.getInstance();
            win.setVisible(true);
        }

        /**
         * Called before the bundle is unloaded.
         * @param bc
         * @throws Exception
         */
        @Override
        public void stop(BundleContext bc) throws Exception {

        }
}
