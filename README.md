# micro-service
spring-cloud 微服务组件demo

<table>
<tbody><tr>
<td>**工程名**</td>  <td>**描述**</td>  <td>**端口**</td>
</tr>
<tr>
<td>eureka-server</td>  <td>服务发现与注册中心</td>  <td>7070</td>
</tr>
<tr>
<td>ribbon</td>  <td>负载均衡器</td>  <td>7071</td>
</tr>
<tr>
<td>config-server</td>  <td>配置管理中心</td>  <td>7072</td>
</tr>
<tr>
<td>zuul</td>  <td>动态路由器</td>  <td>7073</td>
</tr>
<tr>
<td>service-A</td>  <td>A服务，用来测试服务间调用与路由</td>  <td>7074</td>
</tr>
<tr>
<td>service-B</td>  <td>B服务，整合Mybatis、PageHelper、Redis，整合接口限速方案，可选google Guava RateLimiter与自实现</td>  <td>7075</td>
</tr>
<tr>
<td>service-B2</td>  <td>B2服务，与B服务serviceId相同，用来测试负载均衡和容错</td>  <td>7076</td>
</tr>
<tr>
<td>hystrix-ribbon</td>  <td>负载均衡器的容错测试</td>  <td>7077</td>
</tr>
<tr>
<td>feign</td>  <td>声明式、模板化的HTTP客户端，可用来做负载均衡，较轻量</td>  <td>7078</td>
</tr>
<tr>
<td>hystrix-feign</td>  <td>feign的容错测试</td>  <td>7079</td>
</tr>
<tr>
<td>hystrix-dashboard</td>  <td>hystrix可视化监控台</td>  <td>7080</td>
</tr>
<tr>
<td>turbine</td>  <td>集群下hystrix可视化监控台</td>  <td>7081</td>
</tr>
<tr>
<td>sleuth</td>  <td>服务链路追踪</td>  <td>7082</td>
</tr>
<tr>
<td>service-admin</td>  <td>spring boot admin监控台</td>  <td>7088</td>
</tr>
</tbody></table>
