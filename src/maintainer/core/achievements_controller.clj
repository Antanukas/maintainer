(ns maintainer.core.achievements-controller
  (:require [ring.util.response :refer [response]]
             [maintainer.core.achievements-service :as service]))


(defn get-teams [request]
  (response (service/get-teams)))

(defn get-achievements [team]
  (response
   [{ :achievementName "Most commits"
      :achiever "Antanas Bastys"
      :severity "success" }
    { :achievementName "Most Sonar violations"
      :achiever "Antanas Bastys"
      :severity "warning" }
    { :achievementName "Most lines of code"
      :achiever "Antanas Bastys"
      :severity "success" }
    { :achievementName "Most Achievements"
      :achiever "Antanas Bastys"
      :severity "success" }
    { :achievementName "Master of build breaking"
      :achiever (service/get-best-build-breaker team)
      :severity "failure" }
]))

(defn get-build-breaker-top [team]
  (response
   (for [[name build-count] (service/get-culprits-top team)
         :let [item {:name name :buildCount build-count}]]
     item)))
