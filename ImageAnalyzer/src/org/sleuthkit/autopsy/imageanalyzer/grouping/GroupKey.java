/*
 * Autopsy Forensic Browser
 *
 * Copyright 2013 Basis Technology Corp.
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
package org.sleuthkit.autopsy.imageanalyzer.grouping;

import java.util.Map;
import java.util.Objects;
import org.sleuthkit.autopsy.imageanalyzer.datamodel.DrawableAttribute;
import org.sleuthkit.datamodel.TagName;

/**
 * key identifying information of a {@link Grouping}. Used to look up groups in
 * {@link Map}s and from the db.
 */
public class GroupKey<T> implements Comparable<GroupKey<Comparable<T>>> {

    private final Comparable<T> val;

    public Comparable<T> getValue() {
        return val;
    }

    public DrawableAttribute<T> getAttribute() {
        return attr;
    }

    private final DrawableAttribute<T> attr;

    public GroupKey(DrawableAttribute<T> attr, Comparable<T> val) {
        this.attr = attr;
        this.val = val;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.val);
        hash = 29 * hash + Objects.hashCode(this.attr);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GroupKey<T> other = (GroupKey<T>) obj;
        if (!Objects.equals(this.val, other.val)) {
            return false;
        }
        if (this.attr != other.attr) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(GroupKey<Comparable<T>> o) {
        if (val instanceof Comparable) {
            return ((Comparable) val).compareTo(o.val);
        } else {
            return Integer.compare(val.hashCode(), o.val.hashCode());
        }

    }

    public String getValueDisplayName() {
        if (attr == DrawableAttribute.TAGS) {
            return ((TagName) getValue()).getDisplayName();
        } else {

            return getValue().toString();
        }
    }

    @Override
    public String toString() {
        return "GroupKey: " + getAttribute() + " = " + getValue();
    }
}
