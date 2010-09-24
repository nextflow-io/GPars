// GPars - Groovy Parallel Systems
//
// Copyright © 2008-10  The original author or authors
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package groovyx.gpars.dataflow.operator

import groovyx.gpars.dataflow.PrioritySelect
import groovyx.gpars.group.PGroup

/**
 * Dataflow selectors and operators (processors) form the basic units in dataflow networks. They are typically combined into oriented graphs that transform data.
 * They accept a set of input and output dataflow channels so that once values are available to be consumed in any
 * of the input channels the selector's body is triggered on the values, potentially generating values to be written into the output channels.
 * The output channels at the same time are suitable to be used as input channels by some other dataflow processors.
 * The channels allow processors to communicate.
 *
 * Dataflow selectors and operators enable creation of highly concurrent applications yet the abstraction hides the low-level concurrency primitives
 * and exposes much friendlier API.
 * Since selectors and operators internally leverage the actor implementation, they reuse a pool of threads and so the actual number of threads
 * used by the calculation can be kept much lower than the actual number of processors used in the network.
 *
 * @author Vaclav Pech
 * Date: Sep 23, 2009
 */
public final class DataFlowPrioritySelector extends DataFlowSelector {

    private PrioritySelect select

    /**
     * Creates a priority selector
     * After creation the selector needs to be started using the start() method.
     * @param channels A map specifying "inputs" and "outputs" - dataflow channels (instances of the DataFlowStream or DataFlowVariable classes) to use for inputs and outputs
     * @param select A PrioritySelect instance prioritizing incoming values by the input channel, wrapping them into a map, which holds the original index and the message value
     * @param code The selector's body to run each time all inputs have a value to read
     */
    protected def DataFlowPrioritySelector(final PGroup group, final Map channels, final PrioritySelect select, final Closure code) {
        super(group, channels, code)
        this.select = select
    }

    /**
     * Extracts the index of the channel to pass to the client closure.
     * With PrioritySelect shielding the DataFlowPrioritySelector instance
     * the information about the original input channel index and the message value itself
     * must be stored in a message wrapper so that the original index could be consumed by the prioritySelector's body
     * DataFlowPrioritySelector unwraps both the index and the value in the extractIndex() and extractValue methods.
     */
    protected final def extractIndex(message) {
        message.result.index
    }

    /**
     * Extracts the value to pass to the client closure
     * With PrioritySelect shielding the DataFlowPrioritySelector instance
     * the information about the original input channel index and the message value itself
     * must be stored in a message wrapper so that the original index could be consumed by the prioritySelector's body
     * DataFlowPrioritySelector unwraps both the index and the value in the extractIndex() and extractValue methods.
     */
    protected final def extractValue(message) {
        message.result.value
    }

    /**
     * Stops the internal actor as well as the PrioritySelect instance
     */
    @Override
    def void stop() {
        super.stop()
        select.close()
    }
}


