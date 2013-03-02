(ns clj-gu-book-reviews.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [clostache.parser :as moustache]))

(defroutes app-routes
  (GET "/" [] (moustache/render-resource "templates/index.html" {:reviews [{:title "Murder Mile"} {:title "Slugs and lettuces"}]}))
  (route/resources "/static/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
