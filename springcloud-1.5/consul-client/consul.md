# Consul集群环境搭建
本次搭建采用了三台虚拟机，Linux系统，从Consul官网下载安装包，解压即可使用。在每台机器下建一个目录/data/consul/data，这个目录主要存放器群启动后生成的一些数据。需要开启的端口，8300， 8301， 8500， 8600。

分别在以下三个机器上执行一下启动命令。

机器1：10.0.0.45

./consul agent -server -bootstrap-expect 2 -data-dir=/data/consul/data -node=consul-server-1 -bind=10.0.0.45 -client=0.0.0.0 &

机器2：10.0.0.100

./consul agent -server -bootstrap-expect 2 -data-dir=/data/consul/data -node=consul-server-2 -bind=10.0.0.100 -client=0.0.0.0 &

机器3：10.0.0.191

./consul agent -server -bootstrap-expect 2 -data-dir=/data/consul/data -node=consul-server-3 -bind=10.0.0.191 -client=0.0.0.0 -ui &

参数说明：

 server： 以server身份启动。默认是client
 bootstrap-expect：集群要求的最少server数量，当低于这个数量，集群即失效。
 data-dir：data存放的目录，更多信息请参阅consul数据同步机制
 node：节点id，在同一集群不能重复。
 bind：监听的ip地址。默认绑定0.0.0.0，可以不指定
 client: 客户端的ip地址，0.0.0.0是指谁都可以访问
 ui: 可以访问UI界面
三台机器上的服务启动完成后，将两台机器添加到其中一台机器上，组建成集群。

分别在机器2和机器3上执行：./consul join 10.0.0.45，构成集群，同样也可以再增加别的节点。

启动成功后访问任意一台机器  http://10.0.0.45:8500，会看到如下界面，有3个健康的节点：