/*
 * Copyright 2017 Mirantis, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.netflix.spinnaker.clouddriver.kubernetes.op.handler;

import static com.netflix.spinnaker.clouddriver.kubernetes.op.handler.KubernetesHandler.DeployPriority.PDB_PRIORITY;

import com.netflix.spinnaker.clouddriver.kubernetes.caching.agent.KubernetesCoreCachingAgent;
import com.netflix.spinnaker.clouddriver.kubernetes.caching.agent.KubernetesV2CachingAgentFactory;
import com.netflix.spinnaker.clouddriver.kubernetes.description.SpinnakerKind;
import com.netflix.spinnaker.clouddriver.kubernetes.description.manifest.KubernetesKind;
import com.netflix.spinnaker.clouddriver.kubernetes.description.manifest.KubernetesManifest;
import com.netflix.spinnaker.clouddriver.kubernetes.model.Manifest.Status;
import javax.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class KubernetesPodDisruptionBudgetHandler extends KubernetesHandler {
  @Override
  public int deployPriority() {
    return PDB_PRIORITY.getValue();
  }

  @Nonnull
  @Override
  public KubernetesKind kind() {
    return KubernetesKind.POD_DISRUPTION_BUDGET;
  }

  @Override
  public boolean versioned() {
    return false;
  }

  @Nonnull
  @Override
  public SpinnakerKind spinnakerKind() {
    return SpinnakerKind.UNCLASSIFIED;
  }

  @Override
  public Status status(KubernetesManifest manifest) {
    return Status.defaultStatus();
  }

  @Override
  protected KubernetesV2CachingAgentFactory cachingAgentFactory() {
    return KubernetesCoreCachingAgent::new;
  }
}
