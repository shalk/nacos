/*
 *
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.alibaba.nacos.client.naming.core;

import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.common.http.HttpRestResult;
import com.alibaba.nacos.common.http.client.NacosRestTemplate;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.any;

public class ServerListManagerTest extends TestCase {
    
    @Test
    public void testConstructWithEndpoint() throws Exception {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.ENDPOINT, "127.0.0.1");
        final ServerListManager serverListManager = new ServerListManager(properties);
        NacosRestTemplate mock = Mockito.mock(NacosRestTemplate.class);
        
        HttpRestResult<Object> a = new HttpRestResult<Object>();
        a.setData("127.0.0.1:8848");
        a.setCode(200);
        Mockito.when(mock.get(any(), any(), any(), any())).thenReturn(a);
        
        final Field nacosRestTemplate = ServerListManager.class.getDeclaredField("nacosRestTemplate");
        nacosRestTemplate.setAccessible(true);
        nacosRestTemplate.set(serverListManager, mock);
        TimeUnit.SECONDS.sleep(31);
        final List<String> serverList = serverListManager.getServerList();
        Assert.assertEquals(1, serverList.size());
        Assert.assertEquals("127.0.0.1:8848", serverList.get(0));
    }
    
}