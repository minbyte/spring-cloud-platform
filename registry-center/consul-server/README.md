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