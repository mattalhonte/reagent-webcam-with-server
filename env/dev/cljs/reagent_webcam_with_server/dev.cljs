(ns ^:figwheel-no-load reagent-webcam-with-server.dev
  (:require
    [reagent-webcam-with-server.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)
