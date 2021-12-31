(ns me.zzp.www.core
  "客户端"
  (:require [reagent.core :as ag]
            [reagent.dom :as react-dom]
            [re-frame.core :as fr]
            [day8.re-frame.http-fx]
            [ajax.core :as ajax]))

;;; Event Handlers

(fr/reg-event-fx
 :app/init
 (fn [cofx event]
   (println "app/init")
   {:fx [[:http-xhrio {:method :get
                       :uri "/users"
                       :timeout 3000
                       :response-format (ajax/json-response-format {:keywords? true})
                       :on-success [:users/set]}]]}))

(fr/reg-event-db
 :users/set
 (fn [db [_ users]]
   (println "users/set" users)
   (assoc db :users users)))

;;; Effect Handlers

;;; Extractors

(fr/reg-sub
 :users
 (fn [db query]
   (println "extractors users")
   (:users db)))

;;; Transformers

(fr/reg-sub
 :names

 :<- [:users]

 (fn [users query]
   (println "transformers names")
   (mapv :name users)))

;;; Views

(defn Viewport
  []
  [:<>
   [:h1 "Hello re-frame"]
   [:ul
    (for [name @(fr/subscribe [:names])]
      [:li {:key name} name])]])

(defn ^:dev/after-load render
  []
  (println "render")
  (react-dom/render
   [Viewport]
   (. js/document getElementById "app")))

(defn ^:export init
  []
  (println "init")
  (fr/dispatch [:app/init])
  (render))

(defn ^:dev/before-load reset
  []
  (println "reset")
  (fr/clear-subscription-cache!))
