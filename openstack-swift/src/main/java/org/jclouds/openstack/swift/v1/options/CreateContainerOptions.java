/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.openstack.swift.v1.options;

import java.util.Map;

import org.jclouds.http.options.BaseHttpRequestOptions;
import org.jclouds.openstack.swift.v1.binders.BindMetadataToHeaders;

/**
 * Options available to <a href=
 * "http://docs.openstack.org/api/openstack-object-storage/1.0/content/create-container.html"
 * >create a container</a>.
 * 
 * @see ContainerApi#createIfAbsent
 */
public class CreateContainerOptions extends BaseHttpRequestOptions {
   public static final CreateContainerOptions NONE = new CreateContainerOptions();

   /** corresponds to {@link Container#metadata()} */
   public CreateContainerOptions metadata(Map<String, String> metadata) {
      if (!metadata.isEmpty()) {
         this.headers.putAll(bindMetadataToHeaders.toHeaders(metadata));
      }
      return this;
   }

   /** Sets the ACL the container so that anybody can read it. */
   public CreateContainerOptions anybodyRead() {
      this.headers.put("x-container-read", ".r:*,.rlistings");
      return this;
   }

   public static class Builder {

      /** @see CreateContainerOptions#anybodyRead */
      public static CreateContainerOptions anybodyRead() {
         CreateContainerOptions options = new CreateContainerOptions();
         return options.anybodyRead();
      }

      /** @see CreateContainerOptions#metadata */
      public static CreateContainerOptions metadata(Map<String, String> metadata) {
         CreateContainerOptions options = new CreateContainerOptions();
         return options.metadata(metadata);
      }
   }

   private static final BindMetadataToHeaders bindMetadataToHeaders = new BindMetadataToHeaders("x-container-meta-");
}
