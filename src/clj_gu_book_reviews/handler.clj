(ns clj-gu-book-reviews.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [clostache.parser :as moustache]
            [cheshire.core :as json]))

(def book-reviews-url "http://content.guardianapis.com/search?tag=type%2Farticle%2Ctone%2Freviews&section=books&page-size=20&format=json&show-fields=standfirst")

(defn clean-title [item]
	(assoc item :strippedTitle (.replaceFirst (:webTitle item) " . review$", "")))

(defn retrieve-data []
	(let [url (new java.net.URL book-reviews-url)
		content (slurp (.getContent url))
		json-data (json/parse-string content true)
		results (get-in json-data [:response :results])]
		{:reviews (map clean-title results)}))

(defroutes app-routes
  (GET "/" [] (moustache/render-resource "templates/index.html" (retrieve-data)))
  (route/resources "/static/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
