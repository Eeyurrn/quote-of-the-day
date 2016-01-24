(ns quote-of-the-day.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [hiccup.core :as hic]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))
(def hits (atom 0))                                         ;;keep track of the visits that we recieve the only persistent state we have

(defroutes app-routes
  (GET "/" [] (swap! count inc)
       (str "Hello World " @count))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
