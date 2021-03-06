# 1. docker 概述
实现轻量级的操作系统虚拟化解决方案。Docker 的基础是 Linux 容器（LXC）等技术
## 1.1 centos8安装docker步骤

1. 下载docker-ce的repo

> curl https://download.docker.com/linux/centos/docker-ce.repo -o /etc/yum.repos.d/docker-ce.repo

2. 安装依赖

> yum install https://download.docker.com/linux/fedora/30/x86_64/stable/Packages/containerd.io-1.2.6-3.3.fc30.x86_64.rpm

3. 安装docker-ce

> yum install docker-ce

4. 启动docker

> systemctl start docker

# 2. image镜像
只读层
镜像不可变
镜像无状态

## 2. 1 基础命令

```shell
docker pull # 获取镜像
docker pull ubuntu:12.04 # 获取unbutu12.04 操作系统 

docker images # 显示本地已有的所有镜像

```

创建镜像方式

> 1. 启动镜像 ->  修改镜像 -> 提交

```shell
docker run -t -i training/sinatra /bin/bash # 启动惊醒
gem install json # 修改镜像
sudo docker commit -m "Added json gem" -a "Docker Newbee" 0b2616b0e5a8 ouruser/sinatra:v2 # 提交镜像
```

> 2. 利用Dockerfile来创建镜像

```shell
# This is a comment
FROM ubuntu:14.04
MAINTAINER Docker Newbee <newbee@docker.com>
RUN apt-get -qq update
RUN apt-get -qqy install ruby ruby-dev
RUN gem install sinatra
```
执行dockerfile创建一个新的镜像

```shell
sudo docker build -t="ouruser/sinatra:v2" .
```
```shell
sudo docker push ouruser/sinatra # 上传镜像
docker save 存出镜像
docker load 载入镜像
docker rmi 移除镜像
```

# 3.容器

```shell
docker run ubuntu:14.04 /bin/echo 'Hello world' # 新建并启动docker
docker run -t -i ubuntu:14.04 /bin/bash #启动docker -t 表示启动一个终端,-i表示打开容器的标准输入
```

docker run 执行流程

```shell
检查本地是否存在指定的镜像，不存在就从公有仓库下载
利用镜像创建并启动一个容器
分配一个文件系统，并在只读的镜像层外面挂载一层可读写层
从宿主主机配置的网桥接口中桥接一个虚拟接口到容器中去
从地址池配置一个 ip 地址给容器
执行用户指定的应用程序
执行完毕后容器被终止
```

```shell
docker stop # 终止一个运行中的容器 docker ps -a 可以查看已经终止的容器
docker run -idt ubuntu #进入容器,所有窗口都会同步显示
docker export # 导出容器
docker import # 导入容器
docker rm # 删除容器
```
# 4. 数据卷volumes
## 4.1 数据卷

一个可供一个或多个容器使用的特殊目录,可以提供很多特性

1. 容器之间共享和重用
2. 对数据卷的修改立马生效
3. 对数据卷的更新,不会影响镜像
4. 卷会一直存在,知道没有容器使用
5. 数据卷的使用,类似于Linux下,对目录的或文件进行amount

```shell
docker run -d -P --name web -v /webapp training/webapp python app.py # 挂载目录
docker run -d -P --name web -v /src/webapp:/opt/webapp training/webapp python app.py # 指定挂载一个本地主机的目录到容器中去
docker run --rm -it -v ~/.bash_history:/.bash_history ubuntu /bin/bash #挂载单个文件到容器中
```

## 4.2 数据卷容器

用于容器间数据共享

备份、恢复、迁移数据卷

# 5. 网络相关

```shell
docker run -d -P training/webapp python app.py #-P 随机映射一个 49000~49900 的端口到内部容器开放的网络端口
docker run -d -p ip:hostPort:containerPort | ip::containerPort | hostPort:containerPort # 执行映射地址和接口
docker port nostalgic_morse 5000 # 查看指定映射端口
```

# 6. Dockerfile

1. 由一行行命令行语句组成,以#开头的注释行
2. 分为四部分
   1. 基础镜像信息
   2. 维护者信息
   3. 镜像操作指令
   4. 容器启动时的时执行指令

## 6.1 指令

1. FROM

   1. FROM<image> 或FROM <image>:<tag>
   2. 第一条指令必须是FROM,如果在同一个Dockerfile中创建多个镜像时，可以使用多个 `FROM` 指令（每个镜像一次）
2. MAINTAINER
   1. MAINTAINER <name> 指定维护者信息
3. RUN
   1. 格式为 `RUN <command>` 或 `RUN ["executable", "param1", "param2"]`
   2. 每条 `RUN` 指令将在当前镜像基础上执行指定命令，并提交为新的镜像。当命令较长时可以使用 `\` 来换行。
4. CMD
   1. 支持三种格式 
      1. CMD ["executable","param1","param2"]` 使用 `exec` 执行，推荐方式；
      2. `CMD command param1 param2` 在 `/bin/sh` 中执行，提供给需要交互的应用
      3. `CMD ["param1","param2"]` 提供给 `ENTRYPOINT` 的默认参数
   2. 指定启动容器时执行的命令
   3. 每个 Dockerfile 只能有一条 `CMD` 命令。如果指定了多条命令，只有最后一条会被执行。
   4. 如果用户启动容器时候指定了运行的命令，则会覆盖掉 `CMD` 指定的命令。
5. EXPOSE
   1. 格式为 `EXPOSE <port> [<port>...]`
   2. 告诉 Docker 服务端容器暴露的端口号，供互联系统使用
6. ENV
   1. ENV <key> <value>
   2. 指定一个环境变量，会被后续 `RUN` 指令使用，并在容器运行时保持
7. ADD
   1. ADD <src> <dest>
   2. 该命令将复制指定的 `<src>` 到容器中的 `<dest>`
   3. 其中 `<src>` 可以是Dockerfile所在目录的一个相对路径
8. COPY
   1. COPY <src> <dest>
   2. 复制本地主机的 `<src>`（为 Dockerfile 所在目录的相对路径）到容器中的 `<dest>`
   3. 当使用本地目录为源目录时，推荐使用 `COPY`
9. ENTRYPOINT
   1. 两种格式
      1. ENTRYPOINT ["executable", "param1", "param2"]
      2. ENTRYPOINT command param1 param2
   2. 配置容器启动后执行的命令,并且不可被dokcer run提供的参数覆盖
   3. 只能有一个,多个时,最后一个生效
10. VOLUMN
    1. VOLUME ["/data"]
    2. 创建一个可以从本地主机或其他容器挂载的挂载点，一般用来存放数据库和需要保持的数据等
11. USER 
    1. USER daemon
    2. 指定运行容器时的用户名或 UID，后续的 `RUN` 也会使用指定用户
12. WORKDIR
    1. WORKDIR /path/to/workdir
    2. 为后续的 `RUN`、`CMD`、`ENTRYPOINT` 指令配置工作目录
13. ONBUILD
    1. ONBUILD [INSTRUCTION]
    2. 配置当所创建的镜像作为其它新创建镜像的基础镜像时，所执行的操作指令

创建镜像:

```shell
# 编写完成 Dockerfile 之后，可以通过 docker build 命令来创建镜像。
docker build [选项] 路径
```

# 7. docker-compose

docker官方编排项目之一,负责快速在集群中部署分布式应用.Dockerfile 可以让用户管理一个单独的应用容器；而 Compose 则允许用户在一个模板（YAML 格式）中定义一组相关联的应用容器（被称为一个 `project`，即项目）

## 7.1 安装

1. 安装pip(如果python没有安装,先安装python)

```shell
curl https://bootstrap.pypa.io/get-pip.py -o get-pip.py   # 下载安装脚本
sudo python get-pip.py    # 运行安装脚本
```

2. 安装docker-ce

```shell
pip install -U docker-compose
```

## 7.2 术语

一个项目可以由多个服务（容器）关联而成，Compose 面向项目进行管理

1. 服务:一个应用容器,可以运行多个相同镜像的实例
2. 项目:由一组关联的应用容器组成的一个完整业务单元

## 7.3 配置demo

查看 compose-haproxy-web项目

```sh
compose-haproxy-web
├── docker-compose.yml
├── haproxy
│   └── haproxy.cfg
└── web
    ├── Dockerfile
    ├── index.html
    └── index.py
```

运行

```shell
docker-compose up
```

## 7.4 docker-compose命令说明

```shell
docker-compose [options] [COMMAND] [ARGS...]
```

- `--verbose` 输出更多调试信息。
- `--version` 打印版本并退出。
- `-f, --file FILE` 使用特定的 compose 模板文件，默认为 `docker-compose.yml`。
- `-p, --project-name NAME` 指定项目名称，默认使用目录名称。

> build #构建或者重新构建服务
>
> help #获取命令帮助
>
> kill #强制停止服务容器
>
> logs #查看服务的输出
>
> port #打印绑定的公共端口
>
> ps #列出所有容器
>
> pull #拉取服务镜像
>
> rm #删除停止的服务容器
>
> run #在一个服务上执行一个命令
>
> scale #设置一个服务运行容器的个数
>
> start #启动一个已经存在的容器
>
> stop #停止一个已经运行的容器,但是不删除
>
> up #构建或者重新构建一个服务相关的容器

## 7.5 环境变量

> COMPOSE_PROJECT_NAME 设置通过 Compose 启动的每一个容器前添加的项目名称，默认是当前工作目录的名字
>
> COMPOSE_FILE 设置要使用的 `docker-compose.yml` 的路径。默认路径是当前工作目录
>
> DOCKER_HOST 设置 Docker daemon 的地址。默认使用 `unix:///var/run/docker.sock`，与 Docker 客户端采用的默认值一致。
>
> DOCKER_TLS_VERIFY 如果设置不为空,则与Docker Daemon交互通过TLS进行
>
> DOCKER_CERT_PATH 配置 TLS 通信所需要的验证（`ca.pem`、`cert.pem` 和 `key.pem`）文件的路径，默认是 `~/.docker` 。

## 7.6 YAML 模版文件说明

1. 默认模版文件是docker-compose.yml
2. 每个服务都必须通过 `image` 指令指定镜像或 `build` 指令（需要 Dockerfile）来自动构建

> image 指定为镜像名称或镜像 ID。如果镜像在本地不存在，`Compose` 将会尝试拉去这个镜像。
>
> build 指定 `Dockerfile` 所在文件夹的路径。 `Compose` 将会利用它自动构建这个镜像，然后使用这个镜像
>
> command 覆盖容器启动后默认执行的命令
>
> links 链接到其它服务中的容器。使用服务名称（同时作为别名）或服务名称：服务别名 `（SERVICE:ALIAS）` 格式都可以
>
> external 链接到 docker-compose.yml 外部的容器，甚至 并非 `Compose` 管理的容器。参数格式跟 `links` 类似
>
> ports 暴露端口信息
>
> expose 暴露端口，但不映射到宿主机，只被连接的服务访问
>
> volumes 卷挂载路径设置。可以设置宿主机路径 （`HOST:CONTAINER`） 或加上访问模式 （`HOST:CONTAINER:ro`）
>
> volumes_from 从另一个服务或容器挂载它的所有卷。
>
> environment 设置环境变量。你可以使用数组或字典两种格式。
>
> env_file 从文件中获取环境变量，可以为单独的文件路径或列表
>
> extends 基于已有的服务进行扩展
>
> net 设置网络模式
>
> pid 跟主机系统共享进程命名空间
>
> dns 配置dns服务器
>
> cap_add,cap_drop 添加或放弃容器的 Linux 能力（Capabiliity）
>
> dns_search 配置 DNS 搜索域。可以是一个值，也可以是一个列表。
>
> working_dir, entrypoint, user, hostname, domainname, mem_limit, privileged, restart, stdin_open, tty, cpu_shares 和 `docker run` 支持的选项类似





