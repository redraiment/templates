(ns me.zzp.web.core
  "服务端")

(defn handler
  [request]
  {:status 200
   :headers {"Content-Type" "text/html; charset=utf-8"}
   :body "<h1>Hello from Server</h1>"})
