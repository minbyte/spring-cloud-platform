# 1、安装Consul
  以Windows版演示， [下载地址](https://www.consul.io/downloads.html) ： https://www.consul.io/downloads.html ，解压得到：consul.exe
 
# 2、配置环境变量
  在path最后下加上consul.exe的路径，cmd能够执行该目录的文件，如 D:\consul
  
# 启动
  win+R键 打开运行窗口，输入cmd 打开"命令提示符"窗口，输入“consul agent -dev”运行，输出如下：
```angular2html
C:\Users\Klyg028>consul agent -dev
==> Starting Consul agent...
==> Consul agent running!
           Version: 'v1.3.0'
           Node ID: '2d53c20f-01e4-a881-ca88-d9b0315139e2'
         Node name: 'Klyg0255'
        Datacenter: 'dc1' (Segment: '<all>')
            Server: true (Bootstrap: false)
       Client Addr: [127.0.0.1] (HTTP: 8500, HTTPS: -1, gRPC: 8502, DNS: 8600)
      Cluster Addr: 127.0.0.1 (LAN: 8301, WAN: 8302)
           Encrypt: Gossip: false, TLS-Outgoing: false, TLS-Incoming: false

==> Log data will now stream in as it occurs:
    2018/11/02 17:14:46 [DEBUG] agent: Using random ID "c1f2528d-a1e5-d95a-e3ec-5780b7fbae00" as node ID
    2018/11/02 17:14:46 [INFO] raft: Initial configuration (index=1): [{Suffrage:Voter ID:c1f2528d-a1e5-d95a-e3ec-5780b7fbae00 Address:127.0.0.1:8300}]
    2018/11/02 17:14:46 [INFO] raft: Node at 127.0.0.1:8300 [Follower] entering Follower state (Leader: "")
    2018/11/02 17:14:46 [INFO] serf: EventMemberJoin: Klyg0255.dc1 127.0.0.1
    2018/11/02 17:14:46 [INFO] serf: EventMemberJoin: Klyg0255 127.0.0.1
    2018/11/02 17:14:46 [INFO] consul: Adding LAN server Klyg0255 (Addr: tcp/127.0.0.1:8300) (DC: dc1)
    2018/11/02 17:14:46 [INFO] consul: Handled member-join event for server "Klyg0255.dc1" in area "wan"
    2018/11/02 17:14:46 [DEBUG] agent/proxy: managed Connect proxy manager started
    2018/11/02 17:14:46 [INFO] agent: Started DNS server 127.0.0.1:8600 (udp)
    2018/11/02 17:14:46 [INFO] agent: Started DNS server 127.0.0.1:8600 (tcp)
    2018/11/02 17:14:46 [INFO] agent: Started HTTP server on 127.0.0.1:8500 (tcp)
    2018/11/02 17:14:46 [INFO] agent: started state syncer
    2018/11/02 17:14:46 [INFO] agent: Started gRPC server on 127.0.0.1:8502 (tcp)
    2018/11/02 17:14:46 [WARN] raft: Heartbeat timeout from "" reached, starting election
    2018/11/02 17:14:46 [INFO] raft: Node at 127.0.0.1:8300 [Candidate] entering Candidate state in term 2
    2018/11/02 17:14:46 [DEBUG] raft: Votes needed: 1
    2018/11/02 17:14:46 [DEBUG] raft: Vote granted from c1f2528d-a1e5-d95a-e3ec-5780b7fbae00 in term 2. Tally: 1
    2018/11/02 17:14:46 [INFO] raft: Election won. Tally: 1
    2018/11/02 17:14:46 [INFO] raft: Node at 127.0.0.1:8300 [Leader] entering Leader state
    2018/11/02 17:14:46 [INFO] consul: cluster leadership acquired
    2018/11/02 17:14:46 [INFO] consul: New leader elected: Klyg0255
    2018/11/02 17:14:46 [INFO] connect: initialized CA with provider "consul"
    2018/11/02 17:14:46 [DEBUG] consul: Skipping self join check for "Klyg0255" since the cluster is too small
    2018/11/02 17:14:46 [INFO] consul: member 'Klyg0255' joined, marking health alive
    2018/11/02 17:14:46 [DEBUG] agent: Skipping remote check "serfHealth" since it is managed automatically
    2018/11/02 17:14:46 [INFO] agent: Synced node info
    2018/11/02 17:14:48 [DEBUG] agent: Skipping remote check "serfHealth" since it is managed automatically
    2018/11/02 17:14:48 [DEBUG] agent: Node info in sync
```
代表启动成功，打开控制台看一下：http://localhost:8500，

# 启动参数说明

启动参数	|	说明
----------- |  -----------
-advertise	|	通知展现地址用来改变我们给集群中的其他节点展现的地址，一般情况下-bind地址就是展现地址
-bootstrap	|	用来控制一个server是否在bootstrap模式，在一个datacenter中只能有一个server处于bootstrap模式，当一个server处于bootstrap模式时，可以自己选举为raft leader
-bootstrap-expect	|	在一个datacenter中期望提供的server节点数目，当该值提供的时候，consul一直等到达到指定sever数目的时候才会引导整个集群，该标记不能和bootstrap公用
-bind	|	该地址用来在集群内部的通讯，集群内的所有节点到地址都必须是可达的，默认是0.0.0.0
-client	|	consul绑定在哪个client地址上，这个地址提供HTTP、DNS、RPC等服务，默认是127.0.0.1
-config-file	|	明确的指定要加载哪个配置文件
-config-dir	|	配置文件目录，里面所有以.json结尾的文件都会被加载
-data-dir	|	提供一个目录用来存放agent的状态，所有的agent允许都需要该目录，该目录必须是稳定的，系统重启后都继续存在
-dc	|	该标记控制agent允许的datacenter的名称，默认是dc1
-encrypt	|	指定secret key，使consul在通讯时进行加密，key可以通过consul keygen生成，同一个集群中的节点必须使用相同的key
-join	|	加入一个已经启动的agent的ip地址，可以多次指定多个agent的地址。如果consul不能加入任何指定的地址中，则agent会启动失败，默认agent启动时不会加入任何节点。
-retry-join	|	和join类似，但是允许你在第一次失败后进行尝试
-retry-interval	|	两次join之间的时间间隔，默认是30s
-retry-max	|	尝试重复join的次数，默认是0，也就是无限次尝试
-log-level	|	consul agent启动后显示的日志信息级别。默认是info，可选：trace、debug、info、warn、err
-node	|	节点在集群中的名称，在一个集群中必须是唯一的，默认是该节点的主机名
-protocol	|	consul使用的协议版本
-rejoin	|	使consul忽略先前的离开，在再次启动后仍旧尝试加入集群中
-server	|	定义agent运行在server模式，每个集群至少有一个server，建议每个集群的server不要超过5个
-syslog	|	开启系统日志功能，只在linux/osx上生效
-ui	|	使用自带的ui
-ui-dir	|	提供存放web ui资源的路径，该目录必须是可读的
-pid-file	|	提供一个路径来存放pid文件，可以使用该文件进行SIGINT/SIGHUP(关闭/更新)agent


