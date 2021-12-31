# shadow-cljs/bootstrap

ShadowCLJS搭建全栈Clojure(Script)应用开发环境

# 启动

```sh
yarn start
```

# 服务端REPL

用leiningen或cider连接localhost:9999，例如：

```sh
lein repl :connect :port 9999
```

# 客户端REPL

连接上服务端REPL后，执行：

```clojure
(shadow/repl :app)
```

可以尝试执行：

```clojure
(js/alert "Hello CLJS")
```

如果连接正常，会在页面上弹出一个对话框。

退出客户端REPL：

```clojure
:repl/quit
```
