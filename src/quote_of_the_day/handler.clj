(ns quote-of-the-day.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [hiccup.core :as hic]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))
(def hits (atom 0)) ;;keep track of the visits that we recieve the only persistent state we have
;;keep track of the last quote's content
(def last-quote (atom nil))

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
   {:author  "Stephen King"
    :topic   "Philosophy"
    :content "Monsters are real, and ghosts are real too. They live inside us, and sometimes, they win."}
   {:author  "Winston Churchill"
    :topic   "Politics"
    :content "History will be kind to me for I intend to write it."}
   {:author  "Al Gore"
    :topic   "Politics"
    :content "I invented the internet"}
   {:author  "Stephen King"
    :topic   "Psychology"
    :content "The scariest moment is always just before you start."}
   ])
;; Our winner html
(defn get-winner []
  (hic/html [:p (str "Congratulations! You have just won $1,000,000. Please "
                     (hic/html [:a {:href " http://www.performancecentre.com"} "click here"])
                     " to redeem your prize.")]))

;;Takes the value of the last quote and removes it from a set
;;Then converts that set back into list so we can grab it with random integer. Ensures a new quote is reloaded "new" meaning not the same one
(defn random-quote []
  (let [reduced-set (vec (disj (set my-quotes) @last-quote))]
    (get reduced-set (rand-int (count reduced-set)))))

;;This helps generate our view
(defn get-quote-html [q]
  (let [{:keys [author topic content]} q
        html (hic/html [:h2 (str "Author: " author)]
                       [:h3 (str "Topic: " topic)]
                       [:p (str "\"" content "\"")])]
    (reset! last-quote q)
    html))

;;The controller layer
(defn get-quote [hit]
  (if (= (mod hit 7) 0)                                     ;;checks if we have a winner
    (get-winner)
    (get-quote-html (random-quote))))

;;Routing layer which processes our request
(defroutes app-routes
           (GET "/" [] (swap! hits inc)
                       (get-quote @hits))
           (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
