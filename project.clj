(defproject clj-gu-book-reviews "0.0.1"
  :description "Guardian book reviews"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.5"]]
  :plugins [[lein-ring "0.8.2"]]
  :ring {:handler clj-gu-book-reviews.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]}})
