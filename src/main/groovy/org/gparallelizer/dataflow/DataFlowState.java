//  GParallelizer
//
//  Copyright © 2008-9 Vaclav Pech
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License. 

package org.gparallelizer.dataflow;

/**
 * Represents a state of a DataFlowVariable.
 *
 * @author Vaclav Pech
 * Date: Jun 4, 2009
 */
@SuppressWarnings({"RefusedBequest"})
public enum DataFlowState {
    UNASSIGNED {
        @Override public boolean canBeAssigned() {
            return true;
        }},
    ASSIGNED,
    STOPPED;

    public boolean canBeAssigned() {
        return false;
    }
}
