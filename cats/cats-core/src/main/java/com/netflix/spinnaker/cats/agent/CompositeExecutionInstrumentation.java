/*
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.cats.agent;

import java.util.Collection;

public class CompositeExecutionInstrumentation implements ExecutionInstrumentation {
  private final Collection<ExecutionInstrumentation> instrumentations;

  public CompositeExecutionInstrumentation(Collection<ExecutionInstrumentation> instrumentations) {
    this.instrumentations = instrumentations;
  }

  @Override
  public void executionStarted(Agent agent) {
    for (ExecutionInstrumentation exec : instrumentations) {
      exec.executionStarted(agent);
    }
  }

  @Override
  public void executionCompleted(Agent agent, long elapsedMs) {
    for (ExecutionInstrumentation exec : instrumentations) {
      exec.executionCompleted(agent, elapsedMs);
    }
  }

  @Override
  public void executionFailed(Agent agent, Throwable cause) {
    for (ExecutionInstrumentation exec : instrumentations) {
      exec.executionFailed(agent, cause);
    }
  }
}
