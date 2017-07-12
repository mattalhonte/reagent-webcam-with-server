(ns reagent-webcam-with-server.core
    (:require [reagent.core :as reagent :refer [atom]]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]
              [webpack.bundle]))

;; -------------------------
;; Views

(defn home-page []
  (let [webcam-component (aget js/window "deps" "react-webcam")]  ;;Based on this: https://github.com/mozmorris/react-webcam
    [:div
     [:h2 "Reagent Webcam"]
     [:> webcam-component {:audio false   ;;Some attributes to play with.  Mess with them!
                           :height 400
                           :width 400}]]))

(defn about-page []
  [:div [:h2 "About reagent-webcam-with-server"]
   [:div [:a {:href "/"} "go to the home page"]]])

;; -------------------------
;; Routes

(def page (atom #'home-page))

(defn current-page []
  [:div [@page]])

(secretary/defroute "/" []
  (reset! page #'home-page))

(secretary/defroute "/about" []
  (reset! page #'about-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
