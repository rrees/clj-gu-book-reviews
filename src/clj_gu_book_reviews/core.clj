(ns clj-gu-book-reviews.core
	(:require [clj-gu-book-reviews.handler :as handler])
	(:use [ring.adapter.jetty :only [run-jetty]]))

(defn -main [port]
  (run-jetty handler/app {:port (Integer. port)}))