(ns quote-of-the-day.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [quote-of-the-day.handler :refer :all]))

(deftest test-app
  #_(testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
