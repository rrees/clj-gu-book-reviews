(defproject clj-gu-book-reviews "0.0.1"
  :description "Guardian book reviews"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.5"]
                 [ring/ring-jetty-adapter "1.1.0"]
                 [de.ubercode.clostache/clostache "1.3.1"]
                 [cheshire "5.0.2"]]
  :plugins [[lein-ring "0.8.2"]]
  :min-lein-version "2.0.0"
  :ring {:handler clj-gu-book-reviews.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]}})
