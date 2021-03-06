(ns clj-gu-book-reviews.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [clostache.parser :as moustache]
            [cheshire.core :as json]))

(def capi-key (System/getenv "CAPI_KEY"))

(def book-reviews-url (str "http://content.guardianapis.com/search"
	"?tag=type%2Farticle%2Ctone%2Freviews&section=books&page-size=40&format=json&show-fields=standfirst"
	"&api-key=" capi-key))

(defn clean-title [item]
	(apply assoc item 
		(if-let [[title author] (.split (:webTitle item) " by " 2)]
			[:strippedTitle title :author (->> author seq (drop-last 9) (apply str))]
			[:strippedTitle (-> (:webTitle item)
				(.replaceAll "\\p{Pd}" "-")
				(.replaceFirst " - review$" ""))])))

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
