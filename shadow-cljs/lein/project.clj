(defproject shadow-cljs-lein "0.0.1"
  :description "用Leiningen打包成独立可执行的uberjar"
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [cider/cider-nrepl "0.27.4"]
                 [nrepl "0.9.0"]

                 [http-kit "2.5.3"]
                 [ring/ring-core "1.9.4"]
                 [ring/ring-json "0.5.1"]
                 [javax.servlet/javax.servlet-api "4.0.1"]]
  :profiles {:cljs
             {:source-paths ["src"]
              :dependencies [[thheller/shadow-cljs "2.16.10"]
                             [reagent "1.1.0"
                              :exclusions [cljsjs/react
                                           cljsjs/react-dom]]]}
             :uberjar
             {:aot [me.zzp.web.core]}}
  :target-path "target/%s"
  :main me.zzp.web.core
  :omit-source true)
