(ns maintainer.config.config)

(def config-file
  (or (System/getProperty "config.location") "src/maintainer/config/config.edn"))

(require 'clojure.edn)
(def config (clojure.edn/read-string (slurp config-file)))

(defn get-team-config [team]
  (let [teams (:teams config)
        team-predicate #(= team (:team %1))]
    (some #(when (team-predicate %1) %1) teams)))



