(ns me.zzp.web.core
  "服务端"
  (:require [clojure.string :as cs]
            [org.httpkit.server :as http]
            [ring.util
             [mime-type :refer [ext-mime-type]]
             [request :refer [path-info]]
             [response :refer [content-type
                               not-found
                               redirect
                               resource-response
                               response]]]
            [ring.middleware
             [content-type :refer [wrap-content-type]]
             [head :refer [wrap-head]]
             [json :refer [wrap-json-body]]
             [keyword-params :refer [wrap-keyword-params]]
             [multipart-params :refer [wrap-multipart-params]]
             [nested-params :refer [wrap-nested-params]]
             [not-modified :refer [wrap-not-modified]]
             [params :refer [wrap-params]]
             [resource :refer [wrap-resource]]])
  (:gen-class))

(defn handler
  [request]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body "{\"name\":\"Joe\"}"})

(defonce static-resource-path "statics")

(defn wrap-home-page
  [handler file-path]
  (fn [request]
    (if (and (= :get (:request-method request))
             (= (path-info request) "/"))
      (-> file-path
        (resource-response {:root static-resource-path})
        (assoc-in [:headers "Content-Type"] "text/html; charset=utf-8"))
      (handler request))))

(def routes
  (-> handler
    (wrap-json-body {:keywords? true})
    wrap-multipart-params
    wrap-nested-params
    wrap-params
    (wrap-home-page "/index.html")
    wrap-content-type
    wrap-head
    (wrap-resource static-resource-path)
    wrap-not-modified))

(defn -main
  [& args]
  (http/run-server routes {:port 3000 :host "0.0.0.0"})
  (println "Web Server Started @ 3000"))
