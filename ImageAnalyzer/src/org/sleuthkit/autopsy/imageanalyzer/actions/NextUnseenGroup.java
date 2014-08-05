/*
 * Autopsy Forensic Browser
 *
 * Copyright 2013-14 Basis Technology Corp.
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
package org.sleuthkit.autopsy.imageanalyzer.actions;

import org.sleuthkit.autopsy.imageanalyzer.EurekaController;
import org.sleuthkit.autopsy.imageanalyzer.grouping.GroupViewState;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import org.controlsfx.control.action.AbstractAction;

/**
 *
 */
public class NextUnseenGroup extends AbstractAction {

    private final EurekaController controller;

    public NextUnseenGroup(EurekaController controller) {
        super("Next Unseen group");
        this.controller = controller;
        setGraphic(new ImageView("/org/sleuthkit/autopsy/imageanalyzer/images/control-double.png"));
        disabledProperty().set(controller.getGroupManager().getUnSeenGroups().size() <= 1);
        controller.getGroupManager().getUnSeenGroups().addListener((Observable observable) -> {
            disabledProperty().set(controller.getGroupManager().getUnSeenGroups().size() <= 1);
        });
    }

    @Override
    public void handle(ActionEvent event) {
        if (controller.viewState() != null && controller.viewState().get() != null && controller.viewState().get().getGroup() != null) {
            controller.getGroupManager().markGroupSeen(controller.viewState().get().getGroup());
        }
        if (controller.getGroupManager().getUnSeenGroups().size() > 0) {
            controller.pushGroup(GroupViewState.tile(controller.getGroupManager().getUnSeenGroups().get(0)), false);
        }
    }
}
