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



