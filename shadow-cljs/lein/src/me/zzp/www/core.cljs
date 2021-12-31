(ns me.zzp.www.core
  "客户端"
  (:require [reagent.core :as ag]
            [reagent.dom :as react-dom]
            ["cross-fetch" :as fetch]))

(def user-name (ag/atom nil))

(defn Viewport
  []
  [:h1 "Hello from Client"
   [:p (if @user-name
         (str "Hello " @user-name)
         (str "Loading"))]])

(defn ^:dev/after-load render
  []
  (react-dom/render
   [Viewport]
   (. js/document getElementById "app")))

(defn ^:export init
  []
  (-> (fetch "/api/session")
      (.then #(.json %))
      (.then #(reset! user-name (get (js->clj %) "name"))))
  (render))
