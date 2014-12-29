(defproject maintainer "0.1.0-SNAPSHOT"
  :description "Maintainer appilcation that itegrates with jenkins, gitlab, sonar to show some achievements as well as current project maintainer"
  :url "Not yet present"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [ring/ring-defaults "0.1.2"]
                 [ring/ring-json "0.3.1"]
                 [clj-http "1.0.1"]
                 [org.clojure/core.memoize "0.5.6"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler maintainer.core.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
