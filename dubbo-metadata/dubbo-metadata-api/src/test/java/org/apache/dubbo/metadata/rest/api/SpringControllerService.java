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
package org.apache.dubbo.metadata.rest.api;

import org.apache.dubbo.metadata.rest.User;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringControllerService {
    @RequestMapping(
            value = "/param",
            method = RequestMethod.GET,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String param(@RequestParam String param) {
        return param;
    }

    @RequestMapping(
            value = "/header",
            method = RequestMethod.GET,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String header(@RequestHeader String header) {
        return header;
    }

    @RequestMapping(
            value = "/body",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User body(@RequestBody User user) {
        return user;
    }

    @RequestMapping(
            value = "/multiValue",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public MultiValueMap multiValue(@RequestBody MultiValueMap map) {
        return map;
    }

    @RequestMapping(
            value = "/pathVariable/{a}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String pathVariable(@PathVariable String a) {
        return a;
    }

    @RequestMapping(
            value = "/noAnnoParam",
            method = RequestMethod.POST,
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public String noAnnoParam(String a) {
        return a;
    }

    @RequestMapping(
            value = "/noAnnoNumber",
            method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.ALL_VALUE)
    public int noAnnoNumber(Integer b) {
        return b;
    }

    @RequestMapping(
            value = "/noAnnoPrimitive",
            method = RequestMethod.POST,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.ALL_VALUE)
    public int noAnnoPrimitive(int c) {
        return c;
    }
}
