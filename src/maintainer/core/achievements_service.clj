(ns maintainer.core.achievements-service
  (:require [maintainer.core.jenkins-client :refer [get-view-builds]]
            [maintainer.core.gitlab-client :refer [get-contributors]]))


(defn- is-success [build]
  (= (:result build) "SUCCESS"))

(defn get-culprits-top [view-name]
  (->> (:jobs (get-view-builds view-name))
       (mapcat #(:builds %1))
       (filter (complement is-success))
       (mapcat #(:culprits %1))
       (map #(:fullName %1))
       (frequencies)
       (sort-by val >)))

(defn get-best-build-breaker [view-name]
  (let [[username, build-count] (first (get-culprits-top "CDS-DEV-MONITOR"))]
    username))

(defn get-commit-top [project-name])
