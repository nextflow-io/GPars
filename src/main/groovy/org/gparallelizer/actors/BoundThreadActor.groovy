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

package org.gparallelizer.actors;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Provides an Actor implementation with bounded fair ArrayBlockingQueue storing the messages.
 * The send() method will wait for space to become available in the queue, if it is full.
 *
 * @author Vaclav Pech
 * Date: Jan 7, 2009
 */
public class BoundThreadActor extends AbstractThreadActor {

    public static final int DEFAULT_CAPACITY=1000;



    def BoundThreadActor() {
        this(DEFAULT_CAPACITY);
    }

    def BoundThreadActor(int capacity) {
        super(new ArrayBlockingQueue<ActorMessage>(capacity, false));
    }
}
