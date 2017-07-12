(ns reagent-webcam-with-server.prod
  (:require [reagent-webcam-with-server.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
