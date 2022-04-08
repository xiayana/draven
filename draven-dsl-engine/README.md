<!--
{% comment %}
Licensed to the Apache Software Foundation (ASF) under one or more
contributor license agreements.  See the NOTICE file distributed with
this work for additional information regarding copyright ownership.
The ASF licenses this file to you under the Apache License, Version 2.0
(the "License"); you may not use this file except in compliance with
the License.  You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
{% endcomment %}
-->
## 打包方式
在deaven根目录下 打开cmd窗口  执行
mvn clean install -Dmaven.test.skip=true -Ppro
打包完成后在draven-dsl-engine下面的target下
将原项目kill进程kill掉
将draven-dsl-engine-0.0.1-SNAPSHOT.jar取出放到指定服务器文件夹下替换原来jar
执行
nohup java -jar draven-dsl-engine-0.0.1-SNAPSHOT.jar &
重启
