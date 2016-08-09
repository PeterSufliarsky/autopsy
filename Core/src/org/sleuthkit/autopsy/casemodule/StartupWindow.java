/*
 * Autopsy Forensic Browser
 *
 * Copyright 2011 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.casemodule;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import org.openide.LifecycleManager;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.WindowManager;
import org.sleuthkit.autopsy.core.UserPreferences;
import org.sleuthkit.autopsy.coreutils.NetworkUtils;
import org.sleuthkit.autopsy.autoingest.AutoIngestDashboard;

/**
 * The default implementation of the Autopsy startup window
 */
@ServiceProvider(service = StartupWindowInterface.class)
public final class StartupWindow extends JDialog implements StartupWindowInterface {

    private static StartupWindow instance;
    private static final String TITLE = NbBundle.getMessage(StartupWindow.class, "StartupWindow.title.text");
    private static Dimension DIMENSIONS = new Dimension(750, 400);
    private static CueBannerPanel welcomeWindow;
//ELTODO     private ReviewModeCasePanel caseManagementPanel = null;
//ELTODO     private CaseImportPanel caseImportPanel = null;
    private JTabbedPane copyPane = new JTabbedPane();
    private static final String localHostName = NetworkUtils.getLocalHostName();

    public StartupWindow() {
        //ELTODO super(WindowManager.getDefault().getMainWindow(), TITLE, true); // ELTODO do we need this??
        super(new JFrame(TITLE), TITLE, true); // ELTODO last parameter - modal - used to be set to "false"
        init();
    }

    /**
     * Shows the startup window.
     */
    private void init() {

        setModalityType(ModalityType.APPLICATION_MODAL); // ELTODO this was moved over, do we need this?

        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();

        // set the popUp window / JFrame
        setSize(DIMENSIONS);
        int w = getSize().width;
        int h = getSize().height;

        // set the location of the popUp Window on the center of the screen
        setLocation((screenDimension.width - w) / 2, (screenDimension.height - h) / 2);
        //ELTODO setLocationRelativeTo(WindowManager.getDefault().getMainWindow());
        
        addPanelForMode();
        pack();
        setResizable(false);
    }

    @Override
    public void open() {
        
        //ELTODO if (caseManagementPanel != null) {
        //ELTODO     caseManagementPanel.updateView();
        //ELTODO     caseManagementPanel.setCursor(Cursor.getDefaultCursor());
        //ELTODO }
        
        if (welcomeWindow != null) {
            welcomeWindow.refresh();
        }
        this.setLocationRelativeTo(WindowManager.getDefault().getMainWindow());
        setVisible(true);
    }

    /**
     * Closes the startup window.
     */
    @Override
    public void close() {
        this.setVisible(false);
    }

    /**
     * Adds a panel to the dialog based on operational mode selected by the
     * user.
     */
    private void addPanelForMode() {
        UserPreferences.SelectedMode mode = UserPreferences.getMode();

        switch (mode) {
            case AUTOMATED:
                this.setTitle(NbBundle.getMessage(StartupWindow.class, "StartupWindow.AutoIngestMode") + " (" + localHostName + ")");
                this.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        AutoIngestDashboard.getInstance().shutdown();
                    }
                });
                setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                add(AutoIngestDashboard.getInstance());
                break;
            case REVIEW:
                this.setTitle(NbBundle.getMessage(StartupWindow.class, "StartupWindow.ReviewMode") + " (" + localHostName + ")");
//ELTODO                 caseManagementPanel = new ReviewModeCasePanel(this);
//ELTODO                 add(caseManagementPanel);
                break;
            case COPYFILES:
                this.setTitle(NbBundle.getMessage(StartupWindow.class, "StartupWindow.CopyAndImportMode") + " (" + localHostName + ")");
//ELTODO                 caseImportPanel = new CaseImportPanel();
//ELTODO                 copyPane.add(NbBundle.getMessage(StartupWindow.class, "StartupWindow.CaseImportMode"), caseImportPanel);
                this.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        LifecycleManager.getDefault().exit();
                    }
                });
                setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                //ELTODO add(copyPane);
                break;
            default:                
                welcomeWindow = new CueBannerPanel();
                // add the command to close the window to the button on the Volume Detail Panel
                welcomeWindow.setCloseButtonActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        close();
                    }
                });
                add(welcomeWindow);
                break;
        }
    }
}
