(ns maintainer.core.achievements-service
  (:require [maintainer.core.jenkins-client :refer [get-view-builds]]
            [maintainer.core.gitlab-client :refer [get-contributors]]
            [maintainer.config.config :refer [config get-team-config]]))


(defn- is-failure [build]
  (= (:result build) "FAILURE"))

(defn- get-jenkins-view [team]
  (:jenkins-view (get-team-config team)))
(defn- is-not-exculded-job [team job]
  (let [excluded-jobs (:excluded-jobs (get-team-config team))]
    (not (contains? excluded-jobs (:name job)))))

(defn get-teams []
  (map #(:team %1) (:teams config)))

(defn get-culprits-top [team]
  (->> (:jobs (get-view-builds (get-jenkins-view team)))
       (filter (partial is-not-exculded-job team))
       (mapcat #(:builds %1))
       (filter is-failure)
       (mapcat #(:culprits %1))
       (map #(:fullName %1))
       (frequencies)
       (sort-by val >)
       (take 5)))

(defn get-best-build-breaker [team]
  (let [[username, build-count] (first (get-culprits-top team))]
    username))

(defn get-commit-top [team])
