(ns quote-of-the-day.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [hiccup.core :as hic]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))
(def hits (atom 0))                                         ;;keep track of the visits that we recieve the only persistent state we have
;;The model or "The database"
(def my-quotes
  [{:author  "Friedrich Nietzsche"
    :content "He who fights with monsters might take care lest he thereby become a monster.
            And if you gaze for long into an abyss, the abyss gazes also into you."
    :topic   "Philosophy"}
   {:author  "Elon Musk"
    :topic   "Philosophy"
    :content "You should take the approach that you’re wrong. Your goal is to be less wrong"}
   {:author  "Albert Einstein"
    :topic   "Love"
    :content "Gravitation cannot be held responsible for people falling in love."}
   {:author "Stephen King"
    :topic "Philosophy"
    :content "Monsters are real, and ghosts are real too. They live inside us, and sometimes, they win."
    }
   ])

(defroutes app-routes
  (GET "/" [] (swap! count inc)
       (str "Hello World " @count))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
