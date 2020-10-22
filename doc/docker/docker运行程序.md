# 增加阿里镜像源
解决下载你镜像慢的问题
```shelll
>> sudo mkdir -p /etc/docker
>> sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://xxxx.mirror.aliyuncs.com"]
}
EOF
>> sudo systemctl daemon-reload
>> sudo systemctl restart docker
```

# redis
拉取最新的redis镜像
```shell
    docker pull redis:latest
```

运行nginx
```shell
    docker run -itd --name redis-test -p 6379:6379 redis
    docker run -itd --name redis-test -p 6379:6379 redis  --requirepass "123456"
```

# mongodb
拉取最新的镜像
```shell
    docker pull mongo:latest
```

运行容器
```shell
    docker run -itd --name mongo -p 27017:27017 mongo --auth
```

设置密码
```shell
    docker exec -it mongo mongo admin
    db.createUser({ user:'admin',pwd:'123456',roles:[ { role:'userAdminAnyDatabase', db: 'admin'},"readWriteAnyDatabase"]});
    db.auth('admin', '123456')
```
