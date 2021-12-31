(ns me.zzp.www.core
  "客户端"
  (:require [reagent.core :as ag]
            [reagent.dom :as react-dom]))

(defn Viewport
  []
  [:h1 "Hello from Client"])

(defn ^:dev/after-load render
  []
  (react-dom/render
   [Viewport]
   (. js/document getElementById "app")))

(defn ^:export init
  []
  (render))
