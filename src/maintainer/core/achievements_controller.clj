(ns maintainer.core.achievements-controller
  (:require [ring.util.response :refer [response]]
             [maintainer.core.achievements-service :as service]))


(defn get-achievements [request]
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
      :achiever (service/get-best-build-breaker "CDS-DEV-MONITOR")
      :severity "failure" }
]))

(defn get-build-breaker-top [request]
  (response
   (for [[name build-count] (service/get-culprits-top "CDS-DEV-MONITOR")
         :let [item {:name name :buildCount build-count}]]
     item)))
