(ns maintainer.config.config)

(def config-file
  (or (System/getProperty "config.location") "src/maintainer/config/config.edn"))

(require 'clojure.edn)
(def config (clojure.edn/read-string (slurp config-file)))
